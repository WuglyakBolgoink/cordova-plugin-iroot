package de.cyberkatze.iroot;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.Exception;
import java.io.File;

import com.scottyab.rootbeer.RootBeer;


/**
 * Detect weather device is rooted or not
 * @author trykov
 */
public class IRoot extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("isRooted")) {
            try {
                callbackContext.success(isDeviceRooted() ? 1 : 0);
                return true;
            } catch (Exception e) {
                callbackContext.error("Error: N/A");
                return false;
            }
        } else if (action.equals("isRootedRedBeer")) {
                           try {
                               RootBeer rootBeer = new RootBeer(this.cordova.getActivity().getApplicationContext());
                               callbackContext.success(rootBeer.isRooted() ? 1 : 0);
                               return true;
                           } catch (Exception e) {
                               callbackContext.error("Error: N/A");
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
        String[] paths = { "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                "/system/bin/failsafe/su", "/data/local/su" };
        for (String path : paths) {
            if (new File(path).exists()) return true;
        }
        return false;
    }
}
