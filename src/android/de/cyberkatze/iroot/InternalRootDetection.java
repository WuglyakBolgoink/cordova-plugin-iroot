package de.cyberkatze.iroot;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import org.apache.cordova.LOG;

import java.io.File;
import java.util.List;

public class InternalRootDetection {

    // ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---

    public boolean isRooted(final Context context) {
        boolean c1 = isExistBuildTags();
        boolean c2 = isExistSuperUserApk();
        boolean c3 = isExistSUPath();
        boolean c4 = checkDirPermissions();
        boolean c5 = checkExecutingCommands();
        boolean c6 = checkInstalledPackages(context);
        boolean c7 = checkOTACertificates();
        boolean c8 = isRunningOnEmulator();

        LOG.d(Constants.LOG_TAG, "check c1 = isExistBuildTags: " + c1);
        LOG.d(Constants.LOG_TAG, "check c2 = isExistSuperUserApk: " + c2);
        LOG.d(Constants.LOG_TAG, "check c3 = isExistSUPath: " + c3);
        LOG.d(Constants.LOG_TAG, "check c4 = checkDirPermissions: " + c4);
        LOG.d(Constants.LOG_TAG, "check c5 = checkExecutingCommands: " + c5);
        LOG.d(Constants.LOG_TAG, "check c6 = checkInstalledPackages: " + c6);
        LOG.d(Constants.LOG_TAG, "check c7 = checkOTACertificates: " + c7);
        LOG.d(Constants.LOG_TAG, "check c8 = isRunningOnEmulator: " + c8);

        return c1 || c2 || c3 || c4 || c5 || c6 || c7 || c8;
    }

    /**
     * Checks whether any of the system directories are writable or the /data directory is readable.
     * This test will usually result in a false negative on rooted devices.
     */
    private boolean checkDirPermissions() {
        boolean isWritableDir;
        boolean isReadableDataDir;
        boolean result = false;

        for (String dirName : Constants.PATHS_THAT_SHOULD_NOT_BE_WRITABLE) {
            final File currentDir = new File(dirName);

            isWritableDir = currentDir.exists() && currentDir.canWrite();
            isReadableDataDir = (dirName.equals("/data") && currentDir.canRead());

            if (isWritableDir || isReadableDataDir) {
                LOG.d(Constants.LOG_TAG, String.format("[checkDirPermissions] check [%s] => [isWritable:%s][isReadableData:%s]", dirName, isWritableDir, isReadableDataDir));

                result = true;
            }
        }

        LOG.d(Constants.LOG_TAG, "[checkDirPermissions] result: " + result);

        return result;
    }

    /**
     * Checking rooted or not by 'android.os.Build.TAGS' contains test-keys.
     */
    private boolean isExistBuildTags() {
            boolean result = false;

            try {
                String buildTags = Constants.ANDROID_OS_BUILD_TAGS;

                LOG.d(Constants.LOG_TAG, "buildTags: " + buildTags);

                result = (buildTags != null) && buildTags.contains("test-keys");
            } catch (Exception e) {
                LOG.e(Constants.LOG_TAG, "[isExistBuildTags] Error: " + e.getMessage());
            }

            LOG.d(Constants.LOG_TAG, "[isExistBuildTags] result: " + result);

            return result;
        }

    /**
     * Checks whether the Superuser.apk is present in the system applications.
     */
    private boolean isExistSuperUserApk() {
        boolean result = false;

                for (String path : Constants.SUPER_USER_APK_FILES) {
                    final File suAPK = new File(path);

                    if (suAPK.exists()) {
                        LOG.d(Constants.LOG_TAG, "[isExistSuperUserApk] found SU apk: " + path);

                        result = true;
                    }
                }

                LOG.d(Constants.LOG_TAG, "[isExistSuperUserApk] result: " + result);

                return result;
    }

    /**
     * Checking if SU path exist (case sensitive).
     */
    private boolean isExistSUPath() {
        boolean result = false;

                for (String path : Constants.SU_PATHES) {
                    final File suPath = new File(path);

                    if (suPath.exists()) {
                        LOG.d(Constants.LOG_TAG, "[isExistSUPath] found SU path: " + path);

                        result = true;
                    }
                }

                LOG.d(Constants.LOG_TAG, "[isExistSUPath] result: " + result);

                return result;
    }

    /**
     * Checks for installed packages which are known to be present on rooted devices.
     *
     * @param context Used for accessing the package manager.
     */
    private boolean checkInstalledPackages(final Context context) {
        final PackageManager pm = context.getPackageManager();
        final List<PackageInfo> installedPackages = pm.getInstalledPackages(0);

        int rootOnlyAppCount = 0;

        for (PackageInfo packageInfo : installedPackages) {
            final String packageName = packageInfo.packageName;

            LOG.d(Constants.LOG_TAG, "[checkInstalledPackages] Check package [" + packageName + "]");

            if (Constants.BLACKLISTED_PACKAGES.contains(packageName)) {
                LOG.d(Constants.LOG_TAG, "[checkInstalledPackages] Package [" + packageName + "] found in BLACKLISTED_PACKAGES");

                return true;
            }

            if (Constants.ROOT_ONLY_APPLICATIONS.contains(packageName)) {
                LOG.d(Constants.LOG_TAG, "[checkInstalledPackages] Package [" + packageName + "] found in ROOT_ONLY_APPLICATIONS");

                rootOnlyAppCount += 1;
            }

            // Check to see if the Cydia Substrate exists.
            if (Constants.CYDIA_SUBSTRATE_PACKAGE.equals(packageName)) {
                LOG.d(Constants.LOG_TAG, "[checkInstalledPackages] Package [" + packageName + "] found in CYDIA_SUBSTRATE_PACKAGE");

                rootOnlyAppCount += 1;
            }
        }

        return rootOnlyAppCount > 2; // todo: why?
    }

    /**
     * Check to see if the file /etc/security/otacerts.zip exists.
     */
    private boolean checkOTACertificates() {
        String otacerts = Constants.OTA_CERTIFICATES_PATH;

        return new File(otacerts).exists();
    }

    /**
     * Checking if possible to call SU command.
     *
     * @see <a href="https://github.com/xdhfir/xdd/blob/0df93556e4b8605057196ddb9a1c10fbc0f6e200/yeshttp/baselib/src/main/java/com/my/baselib/lib/utils/root/RootUtils.java">TODO: check xdhfir RootUtils.java</a>
     * @see <a href="https://github.com/xdhfir/xdd/blob/0df93556e4b8605057196ddb9a1c10fbc0f6e200/yeshttp/baselib/src/main/java/com/my/baselib/lib/utils/root/ExecShell.java">TODO: check xdhfir ExecShell.java</a>
     * @see <a href="https://github.com/huohong01/truck/blob/master/app/src/main/java/com/hsdi/NetMe/util/RootUtils.java">adopted huohong01 RootUtils.java</a>
     * @see <a href="https://github.com/tansiufang54/fncgss/blob/master/app/src/main/java/co/id/franknco/controller/RootUtil.java">adopted tansiufang54 RootUtils.java</a>
     */
    private boolean checkExecutingCommands() {
        return Utils.canExecuteCommand("/system/xbin/which su")
                || Utils.canExecuteCommand("/system/bin/which su")
                || Utils.canExecuteCommand("which su");
    }

    /**
     * Simple implementation.
     * <p>TODO: move in another class.</p>
     * <p>TODO: check this repos:</p>
     * <ul>
     *     <li><a href="https://github.com/strazzere/anti-emulator">anti-emulator</a></li>
     *     <li><a href="https://github.com/framgia/android-emulator-detector">android-emulator-detector</a></li>
     * </ul>
     *
     * @return true if running on emulator.
     *
     * @see <a href="https://github.com/testmandy/NativeAdLibrary-master/blob/68e1a972fc746a0b51395f813f5bcf32fd619376/library/src/main/java/me/dt/nativeadlibary/util/RootUtil.java#L59">testmandy RootUtil.java</a>
     */
    public boolean isRunningOnEmulator() {
        Utils.getDeviceInfo();

        return Build.FINGERPRINT.startsWith("generic")
                // ||Build.FINGERPRINT.startsWith("unknown") // Meizu Mx Pro will return unknown, so comment it!
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.BOARD.equals("QC_Reference_Phone") //bluestacks
                || Build.HOST.startsWith("Build") //MSI App Player
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
                || "google_sdk".equals(Build.PRODUCT);
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

}
