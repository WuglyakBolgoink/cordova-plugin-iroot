var exec = require('cordova/exec');

var IRoot = function () {
    this.name = "IRoot";
};

IRoot.prototype.isRooted = function (successCallback, failureCallback) {
    exec(successCallback, failureCallback, "iRoot", "isRooted", []);
};

module.exports = new IRoot();

// cordova.exec(
//     function(winParam) {},
//     function(error) {},
//     "service",
//     "action",
//     ["arguments..."]
// );
