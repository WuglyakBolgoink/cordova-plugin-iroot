var helpers = require('./');

module.exports = function(platform) {
    var path = this.requireCordovaModule('path');
    var fs = this.requireCordovaModule('fs');
    var cordovaUtil = this.requireCordovaModule('cordova-lib/src/cordova/util');
    var projectRoot = cordovaUtil.isCordova();
    var platformPath = path.join(projectRoot, 'platforms', platform);
    var sourceFile;
    var content;

    if (platform === 'android') {
        var filePath = 'de/cyberkatze/iroot/IRoot.java';

        try {
            // cordova >= v7.x.x
            sourceFile = path.join(platformPath, 'app/src/main/java', filePath);
            content = fs.readFileSync(sourceFile, 'utf-8');
        } catch (_e) {
            try {
                // cordova <= v6.x.x
                sourceFile = path.join(platformPath, 'src', filePath);
                content = fs.readFileSync(sourceFile, 'utf-8');
            } catch (e) {
                helpers.exit('Unable to read java class source at path ' + sourceFile, e);
            }
        }
    }

    return {
        content: content,
        path: sourceFile
    };
};
