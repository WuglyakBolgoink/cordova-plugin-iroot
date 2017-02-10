var exec = require('cordova/exec');

var IRoot = function () {
    this.name = "IRoot";
};

IRoot.prototype.isRooted = function (successCallback, failureCallback) {
    exec(successCallback, failureCallback, "IRoot", "isRooted", []);
};

module.exports = new IRoot();
