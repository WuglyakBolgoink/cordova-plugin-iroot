module.exports = function() {
    return (this.opts.platforms || this.opts.cordova.platforms || [])
        .filter(function(platform) {
            return this.opts.plugin.pluginInfo.getPlatformsArray().indexOf(platform) > -1;
        }, this);
};
