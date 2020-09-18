/**
 * This plugin defines a global "IRoot" object, which allow you to check if your device was rooted/jailbrocken.
 * Although the object is in the global scope, it is not available until after the deviceready event.
 */
interface IRootPlugin {
    isRooted(onSuccess: (boolean) => void, onError: (any) => void): void;

    isRootedWithBusyBox(onSuccess: (boolean) => void, onError: (any) => void): void;

    detectRootManagementApps(onSuccess: (boolean) => void, onError: (any) => void): void;

    detectPotentiallyDangerousApps(onSuccess: (boolean) => void, onError: (any) => void): void;

    detectTestKeys(onSuccess: (boolean) => void, onError: (any) => void): void;

    checkForBusyBoxBinary(onSuccess: (boolean) => void, onError: (any) => void): void;

    checkForSuBinary(onSuccess: (boolean) => void, onError: (any) => void): void;

    checkSuExists(onSuccess: (boolean) => void, onError: (any) => void): void;

    checkForRWPaths(onSuccess: (boolean) => void, onError: (any) => void): void;

    checkForDangerousProps(onSuccess: (boolean) => void, onError: (any) => void): void;

    checkForRootNative(onSuccess: (boolean) => void, onError: (any) => void): void;

    detectRootCloakingApps(onSuccess: (boolean) => void, onError: (any) => void): void;

    isSelinuxFlagInEnabled(onSuccess: (boolean) => void, onError: (any) => void): void;

    isExistBuildTags(onSuccess: (boolean) => void, onError: (any) => void): void;

    doesSuperuserApkExist(onSuccess: (boolean) => void, onError: (any) => void): void;

    isExistSUPath(onSuccess: (boolean) => void, onError: (any) => void): void;

    checkDirPermissions(onSuccess: (boolean) => void, onError: (any) => void): void;

    checkExecutingCommands(onSuccess: (boolean) => void, onError: (any) => void): void;

    checkInstalledPackages(onSuccess: (boolean) => void, onError: (any) => void): void;

    checkforOverTheAirCertificates(onSuccess: (boolean) => void, onError: (any) => void): void;

    isRunningOnEmulator(onSuccess: (boolean) => void, onError: (any) => void): void;

    simpleCheckEmulator(onSuccess: (boolean) => void, onError: (any) => void): void;

    simpleCheckSDKBF86(onSuccess: (boolean) => void, onError: (any) => void): void;

    simpleCheckQRREFPH(onSuccess: (boolean) => void, onError: (any) => void): void;

    simpleCheckBuild(onSuccess: (boolean) => void, onError: (any) => void): void;

    checkGenymotion(onSuccess: (boolean) => void, onError: (any) => void): void;

    checkGeneric(onSuccess: (boolean) => void, onError: (any) => void): void;

    checkGoogleSDK(onSuccess: (boolean) => void, onError: (any) => void): void;

    togetDeviceInfo(onSuccess: (boolean) => void, onError: (any) => void): void;

    isRootedWithEmulator(onSuccess: (boolean) => void, onError: (any) => void): void;

    isRootedWithBusyBoxWithEmulator(onSuccess: (boolean) => void, onError: (any) => void): void;
}

declare var IRoot: IRootPlugin;
