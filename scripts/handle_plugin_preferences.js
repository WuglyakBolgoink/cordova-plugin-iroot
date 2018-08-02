#!/usr/bin/env node

var helpers = require('./helpers');

module.exports = function(context) {
    var fs = context.requireCordovaModule('fs');
    var busyboxPreference = getPreference('ENABLE_BUSYBOX_CHECK');

    if (helpers.isVerbose(context)) {
        process.stdout.write('[IRoot] Preferences: ');
        process.stdout.write('[IRoot] ENABLE_BUSYBOX_CHECK = ' + busyboxPreference + '\n');
    }

    function getPreference(preference) {
        var value = helpers.getPluginPreference(context, preference);
        return String(value) === 'true';
    }

    helpers
        .getPlatformsList(context)
        .forEach(function(platform) {
            var source = helpers.getPluginSource(context, platform);
            var content = source.content;

            if (platform === 'android') {
                if (busyboxPreference !== undefined || busyboxPreference !== null) {
                    content = content.replace(/\sWITH\s=\s.+;/ig, ' WITH = ' + busyboxPreference + ';');
                }

                try {
                    fs.writeFileSync(source.path, content, 'utf-8');
                } catch (e) {
                    helpers.exit('Unable to write java class source at path ' + source.path, e);
                }
            }
        });
};
