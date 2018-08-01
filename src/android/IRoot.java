package de.cyberkatze.iroot;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.Exception;
import java.io.File;

import com.scottyab.rootbeer.RootBeer;

import org.apache.cordova.LOG;


/**
 * Detect weather device is rooted or not
 *
 * @author trykov
 * @author Ali Elderov
 */
public class IRoot extends CordovaPlugin {
    private static final String LOG_TAG = "[IRoot]";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        LOG.d(LOG_TAG, "Plugin executed");

        if (action.equals("isRootedRedBeer")) {
            // Simple check with rootBeer
            try {
                RootBeer rootBeer = new RootBeer(this.cordova.getActivity().getApplicationContext());

                callbackContext.success(rootBeer.isRooted() ? 1 : 0);

                return true;
            } catch (Exception e) {
                LOG.e(LOG_TAG, "Error", e);

                callbackContext.error("N/A");

                return false;
            }
        } else if (action.equals("isRootedRedBeerWithoutBusyBox")) {
            // Simple check with rootBeer without BusyBox
            try {
                RootBeer rootBeer = new RootBeer(this.cordova.getActivity().getApplicationContext());

                callbackContext.success(rootBeer.isRootedWithoutBusyBoxCheck() ? 1 : 0);

                return true;
            } catch (Exception e) {
                LOG.e(LOG_TAG, "Error", e);

                callbackContext.error("N/A");

                return false;
            }
        } else if (action.equals("isRooted")) {
            // Combine simple check and rootBeer. User can activate check with BusyBox -> put argument with TRUE/FALSE.
            try {
                RootBeer rootBeer = new RootBeer(this.cordova.getActivity().getApplicationContext());

                boolean without = true;

                try {
                    without = args.getBoolean(0);
                    LOG.d(LOG_TAG, "args[0] value is: " + without);
                } catch (Exception e) {
                    LOG.e(LOG_TAG, "Error", e);

                    without = true;
                }

                LOG.d(LOG_TAG, "without: " + without);

                boolean check = isDeviceRooted() || ((without) ? rootBeer.isRootedWithoutBusyBoxCheck() : rootBeer.isRooted());
                LOG.d(LOG_TAG, "check: " + check);

                callbackContext.success(check ? 1 : 0);

                return true;
            } catch (Exception e) {
                LOG.e(LOG_TAG, "Error", e);

                callbackContext.error("N/A");

                return false;
            }
        }

        return false;
    }

    private boolean isDeviceRooted() {
        return checkBuildTags() || checkSuperUserApk() || checkFilePath();
    }

    private boolean checkBuildTags() {
        String buildTags = android.os.Build.TAGS;
        return buildTags != null && buildTags.contains("test-keys");
    }

    private boolean checkSuperUserApk() {
        return new File("/system/app/Superuser.apk").exists();
    }

    private boolean checkFilePath() {
        String[] paths = {
                "/sbin/su",
                "/system/bin/su",
                "/system/xbin/su",
                "/data/local/xbin/su",
                "/data/local/bin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su"
        };

        for (String path : paths) {
            if (new File(path).exists()) {
                return true;
            }
        }

        return false;
    }
}
