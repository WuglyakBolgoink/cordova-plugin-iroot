package de.cyberkatze.iroot;

import java.util.HashMap;
import java.util.Map;

public class CordovaActions {

    public enum Action {

        ACTION_IS_ROOTED("isRooted"),
        ACTION_IS_ROOTED_WITH_BUSY_BOX("isRootedWithBusyBox");

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