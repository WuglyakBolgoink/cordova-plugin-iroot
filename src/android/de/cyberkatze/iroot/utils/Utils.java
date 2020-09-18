package de.cyberkatze.iroot.utils;

import android.os.Build;

import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Utils {

    private final String TAG = "IRoot";

    private Utils() throws InstantiationException {
        throw new InstantiationException("This class is not for instantiation");
    }

    /**
     * Helper function that logs the error and then calls the error callback.
     */
    public static PluginResult getPluginResultError(final String from, final Throwable e) {
        String message = String.format("[%s] Error: %s", from, e.getMessage());

        LOG.e(TAG, message, e);

        return new PluginResult(Status.ERROR, message);
    }

    /**
     * Executes a command on the system.
     *
     * @param command A Command.
     */
    public static boolean canExecuteCommand(String command) {
        Process process = null;

        try {
            process = Runtime.getRuntime().exec(command);

            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            return in.readLine() != null;
        } catch (Exception e) {
            LOG.e(TAG, String.format("[canExecuteCommand] Error: %s", e.getMessage()));

            return false;
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

    /**
     * Get device information.
     */
    public static JSONObject getDeviceInformation() throws JSONException {
        JSONObject deviceInformation = new JSONObject();

        deviceInformation.put("DEVICE", Build.DEVICE);
        deviceInformation.put("MODEL", Build.MODEL);
        deviceInformation.put("MANUFACTURER", Build.MANUFACTURER);
        deviceInformation.put("BRAND", Build.BRAND);
        deviceInformation.put("BOARD", Build.BOARD);
        deviceInformation.put("HARDWARE", Build.HARDWARE);
        deviceInformation.put("PRODUCT", Build.PRODUCT);
        deviceInformation.put("FINGERPRINT", Build.FINGERPRINT);
        deviceInformation.put("HOST", Build.HOST);
        deviceInformation.put("USER", Build.USER);
        deviceInformation.put("OSNAME", System.getProperty("os.name"));
        deviceInformation.put("OSVERSION", System.getProperty("os.version"));
        deviceInformation.put("V.INCREMENTAL", Build.VERSION.INCREMENTAL);
        deviceInformation.put("V.RELEASE", Build.VERSION.RELEASE);
        deviceInformation.put("V.SDK_INT", Build.VERSION.SDK_INT);

        return deviceInformation;
    }

    public static boolean whatIsRunningOnEmulator(final ActionChecks action) {
        boolean result = false;

        switch (action) {
            case ActionChecks.:
            case "simpleCheckEmulator": {
                result = Build.MODEL.contains("Emulator");
                break;
            }

            case "simpleCheckSDKBF86": {
                result = Build.MODEL.contains("Android SDK built for x86");
                break;
            }

            case "simpleCheckQRREFPH": {
                result = Build.BOARD.equals("QC_Reference_Phone");
                break;
            }

            case "simpleCheckBuild": {
                result = Build.HOST.startsWith("Build");
                break;
            }

            case "checkGenymotion": {
                result = Build.MANUFACTURER.contains("Genymotion");
                break;
            }

            case "checkGeneric": {
                result = Build.FINGERPRINT.startsWith("generic") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"));
                break;
            }

            case "checkGoogleSDK": {
                result = Build.MODEL.contains("google_sdk") || "google_sdk".equals(Build.PRODUCT);
                break;
            }

            default: {
                break;
            }
        }

        return result;
    }

    /**
     * Simple implementation.
     * <p>
     * todo move in another class.
     * todo check this repos:
     *
     * @see <a href="https://github.com/strazzere/anti-emulator">anti-emulator</a>
     * @see <a href="https://github.com/framgia/android-emulator-detector">android-emulator-detector</a>
     * @see <a href="https://github.com/testmandy/NativeAdLibrary-master/blob/68e1a972fc746a0b51395f813f5bcf32fd619376/library/src/main/java/me/dt/nativeadlibary/util/RootUtil.java#L59">testmandy RootUtil.java</a>
     */
    public static boolean isRunningOnEmulator() {
        boolean simpleCheck = Build.MODEL.contains("Emulator")
            // ||Build.FINGERPRINT.startsWith("unknown") // Meizu Mx Pro will return unknown, so comment it!
            || Build.MODEL.contains("Android SDK built for x86")
            || Build.BOARD.equals("QC_Reference_Phone") //bluestacks
            || Build.HOST.startsWith("Build"); //MSI App Player

        boolean checkGenymotion = Build.MANUFACTURER.contains("Genymotion");
        boolean checkGeneric = Build.FINGERPRINT.startsWith("generic") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"));
        boolean checkGoogleSDK = Build.MODEL.contains("google_sdk") || "google_sdk".equals(Build.PRODUCT);

        boolean result = simpleCheck || checkGenymotion || checkGeneric || checkGoogleSDK;

        LOG.d(TAG, String.format("[isRunningOnEmulator] result [%s] => [simpleCheck:%s][checkGenymotion:%s][checkGeneric:%s][checkGoogleSDK:%s]", result, simpleCheck, checkGenymotion, checkGeneric, checkGoogleSDK));

        return result;
    }

}
