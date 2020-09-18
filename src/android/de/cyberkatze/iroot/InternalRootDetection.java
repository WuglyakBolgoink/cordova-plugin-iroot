package de.cyberkatze.iroot;

import android.content.Context;
import android.os.Build;

import org.apache.cordova.LOG;
import org.json.JSONException;
import org.json.JSONObject;

public class InternalRootDetection {

    private final String TAG = "IRoot";

    // ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---

    public JSONObject isRooted(final Context context) {
        boolean c1 = isExistBuildTags();
        boolean c2 = doesSuperuserApkExist();
        boolean c3 = isExistSUPath();
        boolean c4 = checkDirPermissions();
        boolean c5 = checkExecutingCommands();
        boolean c6 = checkInstalledPackages(context);
        boolean c7 = checkforOverTheAirCertificates();
        // boolean c8 = isRunningOnEmulator();

        //        LOG.d(TAG, "check c1 = isExistBuildTags: " + c1);
        //        LOG.d(TAG, "check c2 = doesSuperuserApkExist: " + c2);
        //        LOG.d(TAG, "check c3 = isExistSUPath: " + c3);
        //        LOG.d(TAG, "check c4 = checkDirPermissions: " + c4);
        //        LOG.d(TAG, "check c5 = checkExecutingCommands: " + c5);
        //        LOG.d(TAG, "check c6 = checkInstalledPackages: " + c6);
        //        LOG.d(TAG, "check c7 = checkforOverTheAirCertificates: " + c7);
        // LOG.d(TAG, "check c8 = isRunningOnEmulator: " + c8);

        boolean resultChecks = c1 || c2 || c3 || c4 || c5 || c6 || c7;

        //        LOG.d(TAG, String.format("[checkDirPermissions] resultChecks: %s", resultChecks));

        JSONObject result = new JSONObject();
        JSONObject checks = new JSONObject();

        try {
            obj.put("status", true);
            obj.put("isRooted", resultChecks);

            checks.put("isExistBuildTags", c1);
            checks.put("doesSuperuserApkExist", c2);
            checks.put("isExistSUPath", c3);
            checks.put("checkDirPermissions", c4);
            checks.put("checkExecutingCommands", c5);
            checks.put("checkInstalledPackages", c6);
            checks.put("checkforOverTheAirCertificates", c7);

            obj.put("checks", checks);
        } catch (JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
        }

        return result;
    }

    public boolean isRootedWithEmulator(final Context context) {
        boolean c1 = isExistBuildTags();
        boolean c2 = doesSuperuserApkExist();
        boolean c3 = isExistSUPath();
        boolean c4 = checkDirPermissions();
        boolean c5 = checkExecutingCommands();
        boolean c6 = checkInstalledPackages(context);
        boolean c7 = checkforOverTheAirCertificates();
        boolean c8 = isRunningOnEmulator();

        LOG.d(TAG, "check c1 = isExistBuildTags: " + c1);
        LOG.d(TAG, "check c2 = doesSuperuserApkExist: " + c2);
        LOG.d(TAG, "check c3 = isExistSUPath: " + c3);
        LOG.d(TAG, "check c4 = checkDirPermissions: " + c4);
        LOG.d(TAG, "check c5 = checkExecutingCommands: " + c5);
        LOG.d(TAG, "check c6 = checkInstalledPackages: " + c6);
        LOG.d(TAG, "check c7 = checkforOverTheAirCertificates: " + c7);
        LOG.d(TAG, "check c8 = isRunningOnEmulator: " + c8);

        boolean result = c1 || c2 || c3 || c4 || c5 || c6 || c7 || c8;

        LOG.d(TAG, String.format("[checkDirPermissions] result: %s", result));

        return result;
    }

    public boolean whatIsRooted(final String action, final Context context) {
        boolean restest = false;
        switch (action) {
            case "isExistBuildTags":
                restest = isExistBuildTags();
                break;
            case "doesSuperuserApkExist":
                restest = doesSuperuserApkExist();
                break;
            case "isExistSUPath":
                restest = isExistSUPath();
                break;
            case "checkDirPermissions":
                restest = checkDirPermissions();
                break;
            case "checkExecutingCommands":
                restest = checkExecutingCommands();
                break;
            case "checkInstalledPackages":
                restest = checkInstalledPackages(context);
                break;
            case "checkforOverTheAirCertificates":
                restest = checkforOverTheAirCertificates();
                break;
            case "isRunningOnEmulator":
                restest = isRunningOnEmulator();
                break;
            case "simpleCheckEmulator":
                restest = WhatisRunningOnEmulator(action);
                break;
            case "simpleCheckSDKBF86":
                restest = WhatisRunningOnEmulator(action);
                break;
            case "simpleCheckQRREFPH":
                restest = WhatisRunningOnEmulator(action);
                break;
            case "simpleCheckBuild":
                restest = WhatisRunningOnEmulator(action);
                break;
            case "checkGenymotion":
                restest = WhatisRunningOnEmulator(action);
                break;
            case "checkGeneric":
                restest = WhatisRunningOnEmulator(action);
                break;
            case "checkGoogleSDK":
                restest = WhatisRunningOnEmulator(action);
                break;
            default:
                LOG.e(TAG, String.format("[whatIsRooted] action: %s", action));
        }
        boolean result = restest;
        return result;
    }





}
