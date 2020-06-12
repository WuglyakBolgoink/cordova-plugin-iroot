package de.cyberkatze.iroot;

import java.util.Arrays;
import java.util.List;

public final class Constants {

    public static final String LOG_TAG = "IRoot";

    public static final String ANDROID_OS_BUILD_TAGS = android.os.Build.TAGS;

    /**
     * @see <a href="https://owasp.org/www-project-mobile-top-10/2016-risks/m8-code-tampering">(2016) OWASP-mobile-top-10 m8-code-tampering</a>
     * @see <a href="https://owasp.org/www-project-mobile-top-10/2014-risks/m10-lack-of-binary-protections">(2014) OWASP-mobile-top-10 m10-lack-of-binary-protections</a>
     */
    public static final String OTA_CERTIFICATES_PATH = "/etc/security/otacerts.zip";
    public static final String CYDIA_SUBSTRATE_PACKAGE = "com.saurik.substrate";

    public static final List<String> SUPER_USER_APK_FILES = Arrays.asList(
            "/system/app/Superuser.apk",
            "/system/app/superuser.apk",
            "/system/app/Superuser/Superuser.apk",
            "/system/app/Superuser/superuser.apk",
            "/system/app/superuser/Superuser.apk",
            "/system/app/superuser/superuser.apk"
    );

    /**
     * Other packages. The following list of packages are often looked for as well.
     * The last two facilitate in temporarily hiding the su binary and disabling installed applications.
     *
     * Any chainfire package. One MDM looks for any package that is developed by chainfire. The most notable one being SuperSU.
     */
    public static final List<String> BLACKLISTED_PACKAGES = Arrays.asList(
            "com.noshufou.android.su",
            "com.thirdparty.superuser",
            "eu.chainfire.supersu",
            "com.koushikdutta.superuser",
            "com.zachspong.temprootremovejb",
            "com.ramdroid.appquarantine"
    );

    public static final List<String> ROOT_ONLY_APPLICATIONS = Arrays.asList(
            "eu.chainfire.stickmount",
            "eu.chainfire.mobileodin.pro",
            "eu.chainfire.liveboot",
            "eu.chainfire.pryfi",
            "eu.chainfire.adbd",
            "eu.chainfire.recently",
            "eu.chainfire.flash",
            "eu.chainfire.stickmount.pro",
            "eu.chainfire.triangleaway",
            "org.adblockplus.android"
    );

    /**
     * Sometimes when a device has root, the permissions are changed on common directories.
     *
     * Can we read files in /data. The /data directory contains all the installed application files. By default, /data is not readable.
     *
     * TODO: check why this folders was commented in rootbeer library!
     */
    public static final List<String> PATHS_THAT_SHOULD_NOT_BE_WRITABLE = Arrays.asList(
            "/data",
            "/",
            "/system",
            "/system/bin",
            "/system/sbin",
            "/system/xbin",
            "/vendor/bin",
            "/sbin",
            "/etc"
            // "/sys",
            // "/proc",
            // "/dev"
    );

    /**
     * Su Binaries. The following list of Su binaries are often looked for on rooted devices.
     *
     * @see <a href="https://owasp.org/www-project-mobile-top-10/2016-risks/m8-code-tampering">(2016) OWASP-mobile-top-10 m8-code-tampering</a>
     * @see <a href="https://owasp.org/www-project-mobile-top-10/2014-risks/m10-lack-of-binary-protections">(2014) OWASP-mobile-top-10 m10-lack-of-binary-protections</a>
     */
    public static final List<String> SU_PATHES = Arrays.asList(
            "/data/local/",
            "/data/local/xbin/",
            "/data/local/bin/",
            "/sbin/",
            "/system/",
            "/system/bin/",
            "/system/bin/.ext/",
            "/system/bin/.ext/.su/",
            "/system/bin/failsafe/",
            "/system/sd/xbin/",
            "/system/xbin/",
            // added from https://github.com/jacer2020/jacer2020/blob/49ce0b3d137adc21437c72a4c77ef91097df11ad/jsdroid-commons/src/main/java/com/jsdroid/commons/RootHelper.java#L27
            "/su/bin/",
            "/su/xbin/",
            "/ipcData/local/",
            "/ipcData/local/xbin/",
            // added from https://blog.netspi.com/android-root-detection-techniques/
            "/system/usr/we-need-root/",
            "/system/usr/we-need-root/su-backup/",
            "/system/xbin/mu/",
            // added from https://github.com/dmsyudis/Root_detect_3method_security/blob/24e0141c467ca2c9d9375ee66f4bf92944ca2a7c/app/src/main/java/com/duakoma/root_detect_3method_security/MainActivity.java
            "/magisk/.core/bin/"
    );

    // ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---

    private Constants() throws InstantiationException {
        throw new InstantiationException("This class is not for instantiation");
    }

}