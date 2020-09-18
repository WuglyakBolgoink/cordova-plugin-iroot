var exec = require('cordova/exec');

module.exports = {
    isRooted: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'isRooted', []);
    },
    isRootedWithBusyBox: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'isRootedWithBusyBox', []);
    },
    detectRootManagementApps: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'detectRootManagementApps', []);
    },
    detectPotentiallyDangerousApps: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'detectPotentiallyDangerousApps', []);
    },
    detectTestKeys: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'detectTestKeys', []);
    },
    checkForBusyBoxBinary: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'checkForBusyBoxBinary', []);
    },
    checkForSuBinary: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'checkForSuBinary', []);
    },
    checkSuExists: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'checkSuExists', []);
    },
    checkForRWPaths: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'checkForRWPaths', []);
    },
    checkForDangerousProps: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'checkForDangerousProps', []);
    },
    checkForRootNative: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'checkForRootNative', []);
    },
    detectRootCloakingApps: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'detectRootCloakingApps', []);
    },
    isSelinuxFlagInEnabled: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'isSelinuxFlagInEnabled', []);
    },
    isExistBuildTags: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'isExistBuildTags', []);
    },
    doesSuperuserApkExist: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'doesSuperuserApkExist', []);
    },
    isExistSUPath: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'isExistSUPath', []);
    },
    checkDirPermissions: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'checkDirPermissions', []);
    },
    checkExecutingCommands: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'checkExecutingCommands', []);
    },
    checkInstalledPackages: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'checkInstalledPackages', []);
    },
    checkforOverTheAirCertificates: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'checkforOverTheAirCertificates', []);
    },
    isRunningOnEmulator: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'isRunningOnEmulator', []);
    },
    simpleCheckEmulator: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'simpleCheckEmulator', []);
    },
    simpleCheckSDKBF86: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'simpleCheckSDKBF86', []);
    },
    simpleCheckQRREFPH: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'simpleCheckQRREFPH', []);
    },
    simpleCheckBuild: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'simpleCheckBuild', []);
    },
    checkGenymotion: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'checkGenymotion', []);
    },
    checkGeneric: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'checkGeneric', []);
    },
    checkGoogleSDK: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'checkGoogleSDK', []);
    },
    togetDeviceInfo: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'togetDeviceInfo', []);
    },
    isRootedWithEmulator: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'isRootedWithEmulator', []);
    },
    isRootedWithBusyBoxWithEmulator: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'isRootedWithBusyBoxWithEmulator', []);
    }
};
