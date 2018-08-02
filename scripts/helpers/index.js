/**
 * Exit script with custom error log.
 * @param {string} msg - Error message.
 * @param {Error} exception
 */
exports.exit = require('./error_exit');

/**
 * Get the real list of platforms affected by a running plugin hook.
 * @param {Object} context - Cordova context.
 */
exports.getPlatformsList = invokeHelper.bind(null, './get_platforms_list');

/**
 * Get the value of a plugin's preference. If a value is explicitly set in project
 * config.xml variables, it overtakes the value set when first installing the plugin.
 * @param {Object} context - Cordova context.
 * @param {string} preference - Key of preference to retrieve.
 */
exports.getPluginPreference = invokeHelper.bind(null, './get_plugin_preference');

/**
 * Get the value of a plugin's preference. If a value is explicitly set in project
 * config.xml variables, it overtakes the value set when first installing the plugin.
 * @param {Object} context - Cordova context.
 * @param {string} platform - Cordova platform name.
 */
exports.getPluginSource = invokeHelper.bind(null, './get_plugin_source');

/**
 * Detect if the context process is running with verbose option.
 * @param {Object} context - Cordova context.
 */
exports.isVerbose = invokeHelper.bind(null, './is_verbose');

function invokeHelper(path) {
    var helper = require(path);
    var context = arguments[1];
    return helper.apply(context, Array.prototype.splice.call(arguments, 2));
}
