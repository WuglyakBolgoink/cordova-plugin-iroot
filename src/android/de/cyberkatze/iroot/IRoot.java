package de.cyberkatze.iroot;

import android.content.Context;

import com.scottyab.rootbeer.RootBeer;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONObject;
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
            case ACTION_IS_ROOTED_WITH_EMULATOR:
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
            case ACTION_IS_ROOTED_WITH_BUSY_BOX_WITH_EMULATOR:
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
            case ACTION_CHECKGOOGLESDK:
                cordova.getThreadPool().execute(new Runnable() {

                    @Override
                    public void run() {
                        PluginResult result;

                        try {
                            result = WhatIsRooted(args, callbackContext, action);
                        } catch (Exception error) {
                            result = new PluginResult(PluginResult.Status.ERROR, error.toString());
                        }

                        callbackContext.sendPluginResult(result);
                    }
                });

                return true;
            case ACTION_TOGETDEVICEINFO:
                cordova.getThreadPool().execute(new Runnable() {

                    @Override
                    public void run() {
                        PluginResult result;

                        try {
                            result = togetDeviceInfo(args, callbackContext);
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


    private PluginResult WhatIsRooted(final JSONArray args, final CallbackContext callbackContext, final String action) {
        try {
            Context context = this.cordova.getActivity().getApplicationContext();

            RootBeer rootBeer = new RootBeer(context);
            boolean WhatisRooted;
            switch (action) {
              case "detectRootManagementApps":
                WhatisRooted = rootBeer.detectRootManagementApps();
              break;
              case "detectPotentiallyDangerousApps":
                WhatisRooted = rootBeer.detectPotentiallyDangerousApps();
              break;
              case "detectTestKeys":
                WhatisRooted = rootBeer.detectTestKeys();
              break;
              case "checkForBusyBoxBinary":
                WhatisRooted = rootBeer.checkForBusyBoxBinary();
              break;
              case "checkForSuBinary":
                WhatisRooted = rootBeer.checkForSuBinary();
              break;
              case "checkSuExists":
                WhatisRooted = rootBeer.checkSuExists();
              break;
              case "checkForRWPaths":
                WhatisRooted = rootBeer.checkForRWPaths();
              break;
              case "checkForDangerousProps":
                WhatisRooted = rootBeer.checkForDangerousProps();
              break;
              case "checkForRootNative":
                WhatisRooted = rootBeer.checkForRootNative();
              break;
              case "detectRootCloakingApps":
                WhatisRooted = rootBeer.detectRootCloakingApps();
              break;
              case "isSelinuxFlagInEnabled":
                WhatisRooted = Utils.isSelinuxFlagInEnabled();
              break;
              default: WhatisRooted = this.internalRootDetection.WhatisRooted(action, context);
            }
            boolean toWhatisRooted = WhatisRooted;
            LOG.e(Constants.LOG_TAG, "[WhatIsRooted] "+action+": " + toWhatisRooted);

            return new PluginResult(Status.OK, toWhatisRooted);
        } catch (Exception error) {
            return Utils.getPluginResultError("WhatIsRooted", error);
        }
    }

    private PluginResult togetDeviceInfo(final JSONArray args, final CallbackContext callbackContext) {
        try {
            Context context = this.cordova.getActivity().getApplicationContext();

            JSONObject MyDeviceInfo = this.internalRootDetection.togetDeviceInfo();

            LOG.e(Constants.LOG_TAG, "[togetDeviceInfo] MyDeviceInfo: " + MyDeviceInfo.toString());

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

             LOG.d(Constants.LOG_TAG, "[checkIsRooted] checkRootBeer: " + checkRootBeer);
             LOG.d(Constants.LOG_TAG, "[checkIsRooted] checkInternal: " + checkInternal);

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

            LOG.d(Constants.LOG_TAG, "[checkIsRootedWithBusyBox] checkRootBeer: " + checkRootBeer);
            LOG.d(Constants.LOG_TAG, "[checkIsRootedWithBusyBox] checkInternal: " + checkInternal);

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

             LOG.d(Constants.LOG_TAG, "[checkIsRootedWithEmulator] checkRootBeer: " + checkRootBeer);
             LOG.d(Constants.LOG_TAG, "[checkIsRootedWithEmulator] checkInternal: " + checkInternal);

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

            LOG.d(Constants.LOG_TAG, "[checkIsRootedWithBusyBoxWithEmulator] checkRootBeer: " + checkRootBeer);
            LOG.d(Constants.LOG_TAG, "[checkIsRootedWithBusyBoxWithEmulator] checkInternal: " + checkInternal);

            boolean isRooted = checkRootBeer || checkInternal;

            return new PluginResult(Status.OK, isRooted);
        } catch (Exception error) {
            return Utils.getPluginResultError("checkIsRootedWithBusyBoxWithEmulator", error);
        }
    }

}
