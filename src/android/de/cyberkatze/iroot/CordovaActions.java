package de.cyberkatze.iroot;

import java.util.HashMap;
import java.util.Map;

public class CordovaActions {

    public enum Action {

        ACTION_IS_ROOTED("isRooted"),
        ACTION_IS_ROOTED_WITH_BUSY_BOX("isRootedWithBusyBox"),
        ACTION_DETECTROOTMANAGEMENTAPPS("detectRootManagementApps"),
        ACTION_DETECTPOTENTIALLYDANGEROUSAPPS("detectPotentiallyDangerousApps"),
        ACTION_DETECTTESTKEYS("detectTestKeys"),
        ACTION_CHECKFORBUSYBOXBINARY("checkForBusyBoxBinary"),
        ACTION_CHECKFORSUBINARY("checkForSuBinary"),
        ACTION_CHECKSUEXISTS("checkSuExists"),
        ACTION_CHECKFORRWPATHS("checkForRWPaths"),
        ACTION_CHECKFORDANGEROUSPROPS("checkForDangerousProps"),
        ACTION_CHECKFORROOTNATIVE("checkForRootNative"),
        ACTION_DETECTROOTCLOAKINGAPPS("detectRootCloakingApps"),
        ACTION_ISSELINUXFLAGINENABLED("isSelinuxFlagInEnabled"),
        ACTION_ISEXISTBUILDTAGS("isExistBuildTags"),
        ACTION_DOESSUPERUSERAPKEXIST("doesSuperuserApkExist"),
        ACTION_ISEXISTSUPATH("isExistSUPath"),
        ACTION_CHECKDIRPERMISSIONS("checkDirPermissions"),
        ACTION_CHECKEXECUTINGCOMMANDS("checkExecutingCommands"),
        ACTION_CHECKINSTALLEDPACKAGES("checkInstalledPackages"),
        ACTION_CHECKFOROVERTHEAIRCERTIFICATES("checkforOverTheAirCertificates"),
        ACTION_ISRUNNINGONEMULATOR("isRunningOnEmulator"),
        ACTION_SIMPLECHECKEMULATOR("simpleCheckEmulator"),
        ACTION_SIMPLECHECKSDKBF86("simpleCheckSDKBF86"),
        ACTION_SIMPLECHECKQRREFPH("simpleCheckQRREFPH"),
        ACTION_SIMPLECHECKBUILD("simpleCheckBuild"),
        ACTION_CHECKGENYMOTION("checkGenymotion"),
        ACTION_CHECKGENERIC("checkGeneric"),
        ACTION_CHECKGOOGLESDK("checkGoogleSDK"),
        ACTION_TOGETDEVICEINFO("togetDeviceInfo"),
        ACTION_IS_ROOTED_WITH_EMULATOR("isRootedWithEmulator"),
        ACTION_IS_ROOTED_WITH_BUSY_BOX_WITH_EMULATOR("isRootedWithBusyBoxWithEmulator");

        private final String name;

        private static final Map<String, Action> lookup = new HashMap<String, Action>();

        static {
            for (Action a : Action.values()) {
                lookup.put(a.getName(), a);
            }
        }

        private Action(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static Action get(final String name) {
            return lookup.get(name);
        }
    }

}
