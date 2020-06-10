package de.cyberkatze.iroot;

import android.os.Build;

import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Utils {

    /**
     * Helper function that logs the error and then calls the error callback.
     */
    public static PluginResult getPluginResultError(final String from, final Throwable e) {
        String message = "[" + from + "] Error";

        LOG.e(Constants.LOG_TAG, message, e);

        return new PluginResult(Status.ERROR, message);
    }

    /**
     * Executes a command on the system.
     *
     * @param command A Command.
     *
     * @return true when executing success.
     */
    public static boolean canExecuteCommand(String command) {
        Process process = null;

        try {
            process = Runtime.getRuntime().exec(command);

            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            return in.readLine() != null;
        } catch (Exception e) {
            return false;
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

    public static void getDeviceInfo() {
        LOG.d(Constants.LOG_TAG, "[getDeviceInfo] Build.DEVICE: [" + Build.DEVICE + "]");
        LOG.d(Constants.LOG_TAG, "[getDeviceInfo] Build.MODEL: [" + Build.MODEL + "]");
        LOG.d(Constants.LOG_TAG, "[getDeviceInfo] Build.MANUFACTURER: [" + Build.MANUFACTURER + "]");
        LOG.d(Constants.LOG_TAG, "[getDeviceInfo] Build.BRAND: [" + Build.BRAND + "]");
        LOG.d(Constants.LOG_TAG, "[getDeviceInfo] Build.HARDWARE: [" + Build.HARDWARE + "]");
        LOG.d(Constants.LOG_TAG, "[getDeviceInfo] Build.PRODUCT: [" + Build.PRODUCT + "]");
        LOG.d(Constants.LOG_TAG, "[getDeviceInfo] Build.FINGERPRINT: [" + Build.FINGERPRINT + "]");
        LOG.d(Constants.LOG_TAG, "[getDeviceInfo] Build.HOST: [" + Build.HOST + "]");
    }
}
