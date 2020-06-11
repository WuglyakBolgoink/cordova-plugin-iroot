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
        boolean c2 = doesSuperuserApkExist();
        boolean c3 = isExistSUPath();
        boolean c4 = checkDirPermissions();
        boolean c5 = checkExecutingCommands();
        boolean c6 = checkInstalledPackages(context);
        boolean c7 = checkforOverTheAirCertificates();
        boolean c8 = isRunningOnEmulator();

        LOG.d(Constants.LOG_TAG, "check c1 = isExistBuildTags: " + c1);
        LOG.d(Constants.LOG_TAG, "check c2 = doesSuperuserApkExist: " + c2);
        LOG.d(Constants.LOG_TAG, "check c3 = isExistSUPath: " + c3);
        LOG.d(Constants.LOG_TAG, "check c4 = checkDirPermissions: " + c4);
        LOG.d(Constants.LOG_TAG, "check c5 = checkExecutingCommands: " + c5);
        LOG.d(Constants.LOG_TAG, "check c6 = checkInstalledPackages: " + c6);
        LOG.d(Constants.LOG_TAG, "check c7 = checkforOverTheAirCertificates: " + c7);
        LOG.d(Constants.LOG_TAG, "check c8 = isRunningOnEmulator: " + c8);

        boolean result = c1 || c2 || c3 || c4 || c5 || c6 || c7 || c8;

        LOG.d(Constants.LOG_TAG, String.format("[checkDirPermissions] result: %s", result));

        return result;
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

        LOG.d(Constants.LOG_TAG, String.format("[checkDirPermissions] result: %s", result));

        return result;
    }

    /**
     * Checking the BUILD tag for test-keys. By default, stock Android ROMs from Google are built with release-keys tags.
     * If test-keys are present, this can mean that the Android build on the device is either a developer build
     * or an unofficial Google build.
     *
     * For example: Nexus 4 is running stock Android from Google’s (Android Open Source Project) AOSP.
     * This is why the build tags show "release-keys".
     *
     * > root@android:/ # cat /system/build.prop | grep ro.build.tags
     * > ro.build.tags=release-keys
     */
    private boolean isExistBuildTags() {
        boolean result = false;

        try {
            String buildTags = Constants.ANDROID_OS_BUILD_TAGS;

            // LOG.d(Constants.LOG_TAG, String.format("[isExistBuildTags] buildTags: %s", buildTags));

            result = (buildTags != null) && buildTags.contains("test-keys");
        } catch (Exception e) {
            LOG.e(Constants.LOG_TAG, String.format("[isExistBuildTags] Error: %s", e.getMessage()));
        }

        LOG.d(Constants.LOG_TAG, String.format("[isExistBuildTags] result: %s", result));

        return result;
    }

    /**
     * Checks whether the Superuser.apk is present in the system applications.
     *
     * Superuser.apk. This package is most often looked for on rooted devices.
     * Superuser allows the user to authorize applications to run as root on the device.
     */
    private boolean doesSuperuserApkExist() {
        boolean result = false;

        for (String path : Constants.SUPER_USER_APK_FILES) {
            final File rootFile = new File(path);

            if (rootFile.exists()) {
                LOG.d(Constants.LOG_TAG, String.format("[doesSuperuserApkExist] found SU apk: %s", path));

                result = true;
            }
        }

        LOG.d(Constants.LOG_TAG, String.format("[doesSuperuserApkExist] result: %s", result));

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
                LOG.d(Constants.LOG_TAG, String.format("[isExistSUPath] found SU path: %s", path));

                result = true;
            }
        }

        LOG.d(Constants.LOG_TAG, String.format("[isExistSUPath] result: %s", result));

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

            if (Constants.BLACKLISTED_PACKAGES.contains(packageName)) {
                LOG.d(Constants.LOG_TAG, String.format("[checkInstalledPackages] Package [%s] found in BLACKLISTED_PACKAGES", packageName));

                return true;
            }

            if (Constants.ROOT_ONLY_APPLICATIONS.contains(packageName)) {
                LOG.d(Constants.LOG_TAG, String.format("[checkInstalledPackages] Package [%s] found in ROOT_ONLY_APPLICATIONS", packageName));

                rootOnlyAppCount += 1;
            }

            // Check to see if the Cydia Substrate exists.
            if (Constants.CYDIA_SUBSTRATE_PACKAGE.equals(packageName)) {
                LOG.d(Constants.LOG_TAG, String.format("[checkInstalledPackages] Package [%s] found in CYDIA_SUBSTRATE_PACKAGE", packageName));

                rootOnlyAppCount += 1;
            }
        }

        LOG.d(Constants.LOG_TAG, String.format("[checkInstalledPackages] count of root-only apps: %s", rootOnlyAppCount));

        boolean result = rootOnlyAppCount > 2; // todo: why?

        LOG.d(Constants.LOG_TAG, String.format("[checkInstalledPackages] result: %s", result));

        return result;
    }

    /**
     * Checking for Over The Air (OTA) certificates.
     *
     * By default, Android is updated OTA using public certs from Google. If the certs are not there,
     * this usually means that there is a custom ROM installed which is updated through other means.
     *
     * For example: Nexus 4 has no custom ROM and is updated through Google. Updating this device however, will probably break root.
     * > 1|bullhead:/ $ ls -l /etc/security/otacerts.zip
     * > -rw-r--r-- 1 root root 1544 2009-01-01 09:00 /etc/security/otacerts.zip
     */
    private boolean checkforOverTheAirCertificates() {
        File otacerts = new File(Constants.OTA_CERTIFICATES_PATH);
        boolean result = !otacerts.exists();

        LOG.d(Constants.LOG_TAG, String.format("[checkforOverTheAirCertificates] result: %s", result));

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
    private boolean checkExecutingCommands() {
        boolean c1 = Utils.canExecuteCommand("/system/xbin/which su");
        boolean c2 = Utils.canExecuteCommand("/system/bin/which su");
        boolean c3 = Utils.canExecuteCommand("which su");

        boolean result = c1 || c2 || c3;

        LOG.d(Constants.LOG_TAG, String.format("[checkExecutingCommands] result [%s] => [c1:%s][c2:%s][c3:%s]", result, c1, c2, c3));

        return result;
    }

    /**
     * Simple implementation.
     * <p>
     * TODO: move in another class.
     * TODO: check this repos:
     *
     * @see <a href="https://github.com/strazzere/anti-emulator">anti-emulator</a>
     * @see <a href="https://github.com/framgia/android-emulator-detector">android-emulator-detector</a>
     * @see <a href="https://github.com/testmandy/NativeAdLibrary-master/blob/68e1a972fc746a0b51395f813f5bcf32fd619376/library/src/main/java/me/dt/nativeadlibary/util/RootUtil.java#L59">testmandy RootUtil.java</a>
     */
    public boolean isRunningOnEmulator() {
        Utils.getDeviceInfo();

        boolean simpleCheck = Build.MODEL.contains("Emulator")
            // ||Build.FINGERPRINT.startsWith("unknown") // Meizu Mx Pro will return unknown, so comment it!
            || Build.MODEL.contains("Android SDK built for x86")
            || Build.BOARD.equals("QC_Reference_Phone") //bluestacks
            || Build.HOST.startsWith("Build"); //MSI App Player

        boolean checkGenymotion = Build.MANUFACTURER.contains("Genymotion");
        boolean checkGeneric = Build.FINGERPRINT.startsWith("generic") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"));
        boolean checkGoogleSDK = Build.MODEL.contains("google_sdk") || "google_sdk".equals(Build.PRODUCT);

        boolean result = simpleCheck || checkGenymotion || checkGeneric || checkGoogleSDK;

        LOG.d(
            Constants.LOG_TAG,
            String.format(
                "[isRunningOnEmulator] result [%s] => [simpleCheck:%s][checkGenymotion:%s][checkGeneric:%s][checkGoogleSDK:%s]",
                result,
                simpleCheck,
                checkGenymotion,
                checkGeneric,
                checkGoogleSDK
            )
        );

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

}
