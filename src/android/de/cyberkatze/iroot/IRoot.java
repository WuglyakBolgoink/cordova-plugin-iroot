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
import org.json.JSONObject;

/**
 * Detect weather device is rooted or not.
 */
public class IRoot extends CordovaPlugin {

    private final String TAG = "IRoot";

    private final String UNKNOWN_ACTION = "Unknown action";

    private InternalRootDetection internalRootDetection = new InternalRootDetection();

    @Override
    public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        // throws JSONException
        CordovaActions.Action cordovaAction = CordovaActions.Action.get(action);

        if (cordovaAction == null) {
            cordova.getActivity().runOnUiThread(new Runnable() {

                public void run() {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, UNKNOWN_ACTION));
                }
            });

            return false;
        }

        switch (cordovaAction) {
            case ACTION_IS_ROOTED: {
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
            }

            case ACTION_IS_ROOTED_WITH_BUSY_BOX: {
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
            }

            case ACTION_IS_ROOTED_WITH_EMULATOR: {
                cordova.getThreadPool().execute(new Runnable() {

                    @Override
                    public void run() {
                        PluginResult result;

                        try {
                            result = checkIsRootedWithEmulator(args, callbackContext);
                        } catch (Exception error) {
                            result = new PluginResult(PluginResult.Status.ERROR, error.toString());
                        }

                        callbackContext.sendPluginResult(result);
                    }
                });

                return true;
            }

            case ACTION_IS_ROOTED_WITH_BUSY_BOX_WITH_EMULATOR: {
                cordova.getThreadPool().execute(new Runnable() {

                    @Override
                    public void run() {
                        PluginResult result;

                        try {
                            result = checkIsRootedWithBusyBoxWithEmulator(args, callbackContext);
                        } catch (Exception error) {
                            result = new PluginResult(PluginResult.Status.ERROR, error.toString());
                        }

                        callbackContext.sendPluginResult(result);
                    }
                });

                return true;
            }

            case ACTION_DETECTROOTMANAGEMENTAPPS:
            case ACTION_DETECTPOTENTIALLYDANGEROUSAPPS:
            case ACTION_DETECTTESTKEYS:
            case ACTION_CHECKFORBUSYBOXBINARY:
            case ACTION_CHECKFORSUBINARY:
            case ACTION_CHECKSUEXISTS:
            case ACTION_CHECKFORRWPATHS:
            case ACTION_CHECKFORDANGEROUSPROPS:
            case ACTION_CHECKFORROOTNATIVE:
            case ACTION_DETECTROOTCLOAKINGAPPS:
            case ACTION_ISSELINUXFLAGINENABLED:
            case ACTION_ISEXISTBUILDTAGS:
            case ACTION_DOESSUPERUSERAPKEXIST:
            case ACTION_ISEXISTSUPATH:
            case ACTION_CHECKDIRPERMISSIONS:
            case ACTION_CHECKEXECUTINGCOMMANDS:
            case ACTION_CHECKINSTALLEDPACKAGES:
            case ACTION_CHECKFOROVERTHEAIRCERTIFICATES:
            case ACTION_ISRUNNINGONEMULATOR:
            case ACTION_SIMPLECHECKEMULATOR:
            case ACTION_SIMPLECHECKSDKBF86:
            case ACTION_SIMPLECHECKQRREFPH:
            case ACTION_SIMPLECHECKBUILD:
            case ACTION_CHECKGENYMOTION:
            case ACTION_CHECKGENERIC:
            case ACTION_CHECKGOOGLESDK: {
                cordova.getThreadPool().execute(new Runnable() {

                    @Override
                    public void run() {
                        PluginResult result;

                        try {
                            result = whatIsRooted(args, callbackContext, action);
                        } catch (Exception error) {
                            result = new PluginResult(PluginResult.Status.ERROR, error.toString());
                        }

                        callbackContext.sendPluginResult(result);
                    }
                });

                return true;
            }

            case ACTION_GET_DEVICE_INFORMATION: {
                cordova.getThreadPool().execute(new Runnable() {

                    @Override
                    public void run() {
                        PluginResult result;

                        try {
                            result = getDeviceInformation(args, callbackContext);
                        } catch (Exception error) {
                            result = new PluginResult(PluginResult.Status.ERROR, error.toString());
                        }

                        callbackContext.sendPluginResult(result);
                    }
                });

                return true;
            }

            default: {
                cordova.getActivity().runOnUiThread(new Runnable() {

                    public void run() {
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, UNKNOWN_ACTION));
                    }
                });

                return false;
            }
        }
    }

    private PluginResult whatIsRooted(final JSONArray args, final CallbackContext callbackContext, final String action) {
        try {
            Context context = this.cordova.getActivity().getApplicationContext();

            RootBeer rootBeer = new RootBeer(context);

            boolean whatIsRooted;

            switch (action) {
                case "detectRootManagementApps":
                    whatIsRooted = rootBeer.detectRootManagementApps();
                    break;

                case "detectPotentiallyDangerousApps":
                    whatIsRooted = rootBeer.detectPotentiallyDangerousApps();
                    break;

                case "detectTestKeys":
                    whatIsRooted = rootBeer.detectTestKeys();
                    break;

                case "checkForBusyBoxBinary":
                    whatIsRooted = rootBeer.checkForBusyBoxBinary();
                    break;

                case "checkForSuBinary":
                    whatIsRooted = rootBeer.checkForSuBinary();
                    break;

                case "checkSuExists":
                    whatIsRooted = rootBeer.checkSuExists();
                    break;

                case "checkForRWPaths":
                    whatIsRooted = rootBeer.checkForRWPaths();
                    break;

                case "checkForDangerousProps":
                    whatIsRooted = rootBeer.checkForDangerousProps();
                    break;

                case "checkForRootNative":
                    whatIsRooted = rootBeer.checkForRootNative();
                    break;

                case "detectRootCloakingApps":
                    whatIsRooted = rootBeer.detectRootCloakingApps();
                    break;

                case "isSelinuxFlagInEnabled":
                    whatIsRooted = Utils.isSelinuxFlagInEnabled();
                    break;

                default:
                    whatIsRooted = this.internalRootDetection.whatIsRooted(action, context);
            }

            LOG.e(TAG, "[WhatIsRooted] " + action + ": " + whatIsRooted);

            return new PluginResult(Status.OK, whatIsRooted);
        } catch (Exception error) {
            return Utils.getPluginResultError("WhatIsRooted", error);
        }
    }

    private PluginResult getDeviceInformation(final JSONArray args, final CallbackContext callbackContext) {
        try {
            Context context = this.cordova.getActivity().getApplicationContext();

            JSONObject MyDeviceInfo = this.internalRootDetection.;

            LOG.e(TAG, "[getDeviceInformation] MyDeviceInfo: " + MyDeviceInfo.toString());

            return new PluginResult(Status.OK, MyDeviceInfo);
        } catch (Exception error) {
            return Utils.getPluginResultError("MyDeviceInfo", error);
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

            LOG.d(TAG, "[checkIsRooted] checkRootBeer: " + checkRootBeer);
            LOG.d(TAG, "[checkIsRooted] checkInternal: " + checkInternal);

            boolean isRooted = checkRootBeer || checkInternal;
            // boolean isRooted = checkRootBeer;

            return new PluginResult(Status.OK, isRooted);
        } catch (Exception error) {
            return Utils.getPluginResultError("checkIsRooted", error);
        }
    }

    /**
     * Check with rootBeer with BusyBox and with internal checks.
     */
    private PluginResult checkIsRootedWithBusyBox(final JSONArray args, final CallbackContext callbackContext) {
        try {
            Context context = this.cordova.getActivity().getApplicationContext();
            RootBeer rootBeer = new RootBeer(context);

            boolean checkRootBeer = rootBeer.isRootedWithBusyBoxCheck();
            boolean checkInternal = this.internalRootDetection.isRooted(context);

            LOG.d(TAG, "[checkIsRootedWithBusyBox] checkRootBeer: " + checkRootBeer);
            LOG.d(TAG, "[checkIsRootedWithBusyBox] checkInternal: " + checkInternal);

            boolean isRooted = checkRootBeer || checkInternal;

            return new PluginResult(Status.OK, isRooted);
        } catch (Exception error) {
            return Utils.getPluginResultError("checkIsRootedWithBusyBox", error);
        }
    }

    /**
     * Check with rootBeer and with internal checks and with isRunningOnEmulator
     */
    private PluginResult checkIsRootedWithEmulator(final JSONArray args, final CallbackContext callbackContext) {
        try {
            Context context = this.cordova.getActivity().getApplicationContext();
            RootBeer rootBeer = new RootBeer(context);

            boolean checkRootBeer = rootBeer.isRooted();
            boolean checkInternal = this.internalRootDetection.isRootedWithEmulator(context);

            LOG.d(TAG, "[checkIsRootedWithEmulator] checkRootBeer: " + checkRootBeer);
            LOG.d(TAG, "[checkIsRootedWithEmulator] checkInternal: " + checkInternal);

            boolean isRooted = checkRootBeer || checkInternal;
            // boolean isRooted = checkRootBeer;

            return new PluginResult(Status.OK, isRooted);
        } catch (Exception error) {
            return Utils.getPluginResultError("checkIsRootedWithEmulator", error);
        }
    }

    /**
     * Check with rootBeer with BusyBox and with internal checks and with isRunningOnEmulator
     */
    private PluginResult checkIsRootedWithBusyBoxWithEmulator(final JSONArray args, final CallbackContext callbackContext) {
        try {
            Context context = this.cordova.getActivity().getApplicationContext();
            RootBeer rootBeer = new RootBeer(context);

            boolean checkRootBeer = rootBeer.isRootedWithBusyBoxCheck();
            boolean checkInternal = this.internalRootDetection.isRootedWithEmulator(context);

            LOG.d(TAG, "[checkIsRootedWithBusyBoxWithEmulator] checkRootBeer: " + checkRootBeer);
            LOG.d(TAG, "[checkIsRootedWithBusyBoxWithEmulator] checkInternal: " + checkInternal);

            boolean isRooted = checkRootBeer || checkInternal;

            return new PluginResult(Status.OK, isRooted);
        } catch (Exception error) {
            return Utils.getPluginResultError("checkIsRootedWithBusyBoxWithEmulator", error);
        }
    }

}
