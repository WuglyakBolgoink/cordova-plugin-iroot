package de.cyberkatze.iroot;

import android.content.Context;

import com.scottyab.rootbeer.RootBeer;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Detect weather device is rooted or not.
 */
public class IRoot extends CordovaPlugin {

    private final String ERROR_UNKNOWN_ACTION = "Unknown action";

    private InternalRootDetection internalRootDetection = new InternalRootDetection();

    @Override
    public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        // throws JSONException
        CordovaActions.Action cordovaAction = CordovaActions.Action.get(action);

        if (cordovaAction == null) {
            cordova.getActivity().runOnUiThread(new Runnable() {

                public void run() {
                    LOG.e(Constants.LOG_TAG, ERROR_UNKNOWN_ACTION);

                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, ERROR_UNKNOWN_ACTION));
                }
            });

            return false;
        }

        switch (cordovaAction) {
            case ACTION_IS_ROOTED:
                cordova.getThreadPool().execute(new Runnable() {

                    @Override
                    public void run() {
                        PluginResult result;

                        try {
                            result = checkIsRooted(args, callbackContext);
                        } catch (Exception error) {
                            result = new PluginResult(PluginResult.Status.ERROR, error.toString());
                        }

                        callbackContext.sendPluginResult(result);
                    }
                });

                return true;
            case ACTION_IS_ROOTED_WITH_BUSY_BOX:
                cordova.getThreadPool().execute(new Runnable() {

                    @Override
                    public void run() {
                        PluginResult result;

                        try {
                            result = checkIsRootedWithBusyBox(args, callbackContext);
                        } catch (Exception error) {
                            result = new PluginResult(PluginResult.Status.ERROR, error.toString());
                        }

                        callbackContext.sendPluginResult(result);
                    }
                });

                return true;
            default:
                cordova.getActivity().runOnUiThread(new Runnable() {

                    public void run() {
                        LOG.e(Constants.LOG_TAG, ERROR_UNKNOWN_ACTION);
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, ERROR_UNKNOWN_ACTION));
                    }
                });

                return false;
        }
    }

    /**
     * Check with rootBeer and with internal checks.
     */
    private PluginResult checkIsRooted(final JSONArray args, final CallbackContext callbackContext) {
        try {
            Context context = this.cordova.getActivity().getApplicationContext();
            RootBeer rootBeer = new RootBeer(context);

            boolean checkRootBeer = rootBeer.isRooted();
            boolean checkInternal = this.internalRootDetection.isRooted(context);

            LOG.d(Constants.LOG_TAG, "[checkIsRooted] checkRootBeer: " + checkRootBeer);
            LOG.d(Constants.LOG_TAG, "[checkIsRooted] checkInternal: " + checkInternal);

            boolean isRooted = checkRootBeer || checkInternal;

            return new PluginResult(Status.OK, isRooted);
        } catch (Exception error) {
            return Utils.getPluginResultError("checkIsRooted", error);
        }
    }

    /**
     * Check with rootBeer without BusyBox.
     */
    private PluginResult checkIsRootedWithBusyBox(final JSONArray args, final CallbackContext callbackContext) {
        try {
            Context context = this.cordova.getActivity().getApplicationContext();
            RootBeer rootBeer = new RootBeer(context);

            boolean checkRootBeer = rootBeer.isRootedWithBusyBoxCheck();
            boolean checkInternal = this.internalRootDetection.isRooted(context);

            LOG.d(Constants.LOG_TAG, "[checkIsRootedWithBusyBox] checkRootBeer: " + checkRootBeer);
            LOG.d(Constants.LOG_TAG, "[checkIsRootedWithBusyBox] checkInternal: " + checkInternal);

            boolean isRooted = checkRootBeer || checkInternal;

            return new PluginResult(Status.OK, isRooted);
        } catch (Exception error) {
            return Utils.getPluginResultError("checkIsRootedWithBusyBox", error);
        }
    }

}
