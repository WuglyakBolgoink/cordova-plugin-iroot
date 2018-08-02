module.exports = function(preference) {
    var cordovaUtil = this.requireCordovaModule('cordova-lib/src/cordova/util');
    var metadata = this.requireCordovaModule('cordova-lib/src/plugman/util/metadata');
    var ConfigParser = this.requireCordovaModule('cordova-common/src/ConfigParser/ConfigParser');
    var projectRoot = cordovaUtil.isCordova();
    var xml = cordovaUtil.projectConfig(projectRoot);
    var config = new ConfigParser(xml);
    var plugin = this.opts.plugin;
    var configPlugin = config.getPlugin(plugin.id);
    var preferences;

    if (configPlugin && typeof configPlugin.variables === 'object') {
        preferences = configPlugin.variables;

        if (Object.keys(preferences).length && typeof preferences[preference] !== 'undefined') {
            return preferences[preference];
        }
    }

    preferences = metadata.get_fetch_metadata(plugin.dir).variables;

    if (preferences) {
        return preferences[preference];
    }

    return preferences ? preferences[preference] : void 0;
};
