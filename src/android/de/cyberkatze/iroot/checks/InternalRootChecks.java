package de.cyberkatze.iroot.checks;

public class InternalRootChecks {

    private final String TAG = "IRoot";

    public static boolean isDebuggable() {
        ApplicationInfo appInfo = getContext().getApplicationContext().getApplicationInfo();

        return (0 != (appInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
    }

    /**
     * In Development - an idea of ours was to check the if selinux is enforcing - this could be disabled for some rooting apps
     * Checking for selinux mode
     *
     * @return true if selinux enabled
     */
    public static boolean isSelinuxFlagInEnabled() {
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            String selinux = (String) get.invoke(c, "ro.build.selinux");
            return "1".equals(selinux);
        } catch (Exception ignored) {
        }

        return false;
    }

    /**
     * Checks whether any of the system directories are writable or the /data directory is readable.
     * This test will usually result in a false negative on rooted devices.
     */
    public static boolean checkDirPermissions() {
        boolean isWritableDir;
        boolean isReadableDataDir;
        boolean result = false;

        for (String dirName : Constants.PATHS_THAT_SHOULD_NOT_BE_WRITABLE) {
            final File currentDir = new File(dirName);

            isWritableDir = currentDir.exists() && currentDir.canWrite();
            isReadableDataDir = (dirName.equals("/data") && currentDir.canRead());

            if (isWritableDir || isReadableDataDir) {
                // LOG.d(TAG, String.format("[checkDirPermissions] check [%s] => [isWritable:%s][isReadableData:%s]", dirName, isWritableDir, isReadableDataDir));

                result = true;
            }
        }

        // LOG.d(TAG, String.format("[checkDirPermissions] result: %s", result));

        return result;
    }

    /**
     * Checking the BUILD tag for test-keys. By default, stock Android ROMs from Google are built with release-keys tags.
     * If test-keys are present, this can mean that the Android build on the device is either a developer build
     * or an unofficial Google build.
     * <p>
     * For example: Nexus 4 is running stock Android from Google’s (Android Open Source Project) AOSP.
     * This is why the build tags show "release-keys".
     * <p>
     * > root@android:/ # cat /system/build.prop | grep ro.build.tags
     * > ro.build.tags=release-keys
     */
    public static boolean isExistBuildTags() {
        boolean result = false;

        try {
            String buildTags = Constants.ANDROID_OS_BUILD_TAGS;

            // LOG.d(TAG, String.format("[isExistBuildTags] buildTags: %s", buildTags));

            result = (buildTags != null) && buildTags.contains("test-keys");
        } catch (Exception e) {
            LOG.e(TAG, String.format("[isExistBuildTags] Error: %s", e.getMessage()));
        }

        // LOG.d(TAG, String.format("[isExistBuildTags] result: %s", result));

        return result;
    }

    /**
     * Checks whether the Superuser.apk is present in the system applications.
     * <p>
     * Superuser.apk. This package is most often looked for on rooted devices.
     * Superuser allows the user to authorize applications to run as root on the device.
     */
    public static boolean doesSuperuserApkExist() {
        boolean result = false;

        for (String path : Constants.SUPER_USER_APK_FILES) {
            final File rootFile = new File(path);

            if (rootFile.exists()) {
                LOG.d(TAG, String.format("[doesSuperuserApkExist] found SU apk: %s", path));

                result = true;
            }
        }

        LOG.d(TAG, String.format("[doesSuperuserApkExist] result: %s", result));

        return result;
    }

    /**
     * Checking if SU path exist (case sensitive).
     */
    public static boolean isExistSUPath() {
        final String[] pathsArray = Constants.SU_PATHES.toArray(new String[0]);

        boolean result = false;

        for (final String path : pathsArray) {
            final String completePath = path + "su";
            final File suPath = new File(completePath);
            final boolean fileExists = suPath.exists();

            if (fileExists) {
                LOG.d(TAG, String.format("[isExistSUPath] binary [%s] detected!", path));

                result = true;
            }
        }

        LOG.d(TAG, String.format("[isExistSUPath] result: %s", result));

        return result;
    }

    /**
     * Checks for installed packages which are known to be present on rooted devices.
     *
     * @param context Used for accessing the package manager.
     */
    public static boolean checkInstalledPackages(final Context context) {
        final PackageManager pm = context.getPackageManager();
        final List<PackageInfo> installedPackages = pm.getInstalledPackages(0);

        int rootOnlyAppCount = 0;

        for (PackageInfo packageInfo : installedPackages) {
            final String packageName = packageInfo.packageName;

            if (Constants.BLACKLISTED_PACKAGES.contains(packageName)) {
                LOG.d(TAG, String.format("[checkInstalledPackages] Package [%s] found in BLACKLISTED_PACKAGES", packageName));

                return true;
            }

            if (Constants.ROOT_ONLY_APPLICATIONS.contains(packageName)) {
                LOG.d(TAG, String.format("[checkInstalledPackages] Package [%s] found in ROOT_ONLY_APPLICATIONS", packageName));

                rootOnlyAppCount += 1;
            }

            // Check to see if the Cydia Substrate exists.
            if (Constants.CYDIA_SUBSTRATE_PACKAGE.equals(packageName)) {
                LOG.d(TAG, String.format("[checkInstalledPackages] Package [%s] found in CYDIA_SUBSTRATE_PACKAGE", packageName));

                rootOnlyAppCount += 1;
            }
        }

        LOG.d(TAG, String.format("[checkInstalledPackages] count of root-only apps: %s", rootOnlyAppCount));

        boolean result = rootOnlyAppCount > 2; // todo: why?

        LOG.d(TAG, String.format("[checkInstalledPackages] result: %s", result));

        return result;
    }

    /**
     * Checking for Over The Air (OTA) certificates.
     * <p>
     * By default, Android is updated OTA using public certs from Google. If the certs are not there,
     * this usually means that there is a custom ROM installed which is updated through other means.
     * <p>
     * For example: Nexus 4 has no custom ROM and is updated through Google. Updating this device however, will probably break root.
     * > 1|bullhead:/ $ ls -l /etc/security/otacerts.zip
     * > -rw-r--r-- 1 root root 1544 2009-01-01 09:00 /etc/security/otacerts.zip
     */
    public static boolean checkforOverTheAirCertificates() {
        File otacerts = new File(Constants.OTA_CERTIFICATES_PATH);
        boolean exist = otacerts.exists();
        boolean result = !exist;

        LOG.d(TAG, String.format("[checkforOverTheAirCertificates] exist: %s", exist));
        LOG.d(TAG, String.format("[checkforOverTheAirCertificates] result: %s", result));

        return result;
    }

    /**
     * Checking if possible to call SU command.
     *
     * @see <a href="https://github.com/xdhfir/xdd/blob/0df93556e4b8605057196ddb9a1c10fbc0f6e200/yeshttp/baselib/src/main/java/com/my/baselib/lib/utils/root/RootUtils.java">TODO: check xdhfir RootUtils.java</a>
     * @see <a href="https://github.com/xdhfir/xdd/blob/0df93556e4b8605057196ddb9a1c10fbc0f6e200/yeshttp/baselib/src/main/java/com/my/baselib/lib/utils/root/ExecShell.java">TODO: check xdhfir ExecShell.java</a>
     * @see <a href="https://github.com/huohong01/truck/blob/master/app/src/main/java/com/hsdi/NetMe/util/RootUtils.java">adopted huohong01 RootUtils.java</a>
     * @see <a href="https://github.com/tansiufang54/fncgss/blob/master/app/src/main/java/co/id/franknco/controller/RootUtil.java">adopted tansiufang54 RootUtils.java</a>
     */
    public static boolean checkExecutingCommands() {
        boolean c1 = Utils.canExecuteCommand("/system/xbin/which su");
        boolean c2 = Utils.canExecuteCommand("/system/bin/which su");
        boolean c3 = Utils.canExecuteCommand("which su");

        boolean result = c1 || c2 || c3;

        LOG.d(TAG, String.format("[checkExecutingCommands] result [%s] => [c1:%s][c2:%s][c3:%s]", result, c1, c2, c3));

        return result;
    }

    // TODO: https://github.com/tansiufang54/fncgss/blob/master/app/src/main/java/co/id/franknco/controller/RootUtil.java#L126
    //    private boolean checkServerSocket() {
    //        try {
    //            ServerSocket ss = new ServerSocket(81);
    //            ss.close();
    //            return true;
    //        } catch (Exception e) {
    //            // not sure
    //        }
    //        return false;
    //    }

    public static boolean c() {
        for (String file : new String[]{}) {
            if (new File(file).exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks diverse root pathes.
     */
    public static boolean checkDiverseRootPathes() {
        boolean result = false;

        for (String path : Constants.DIVERSE_ROOT_PATHES) {
            final File rootFile = new File(path);

            if (rootFile.exists()) {
                LOG.d(TAG, String.format("[checkDiverseRootPathes] found file: %s", path));

                result = true;
            }
        }

        LOG.d(TAG, String.format("[checkDiverseRootPathes] result: %s", result));

        return result;
    }

}

