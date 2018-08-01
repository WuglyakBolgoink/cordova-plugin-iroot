var exec = require('cordova/exec');

module.exports = {
    isRooted: function(successCallback, failureCallback) {
        exec(successCallback, failureCallback, 'IRoot', 'isRooted', []);
    },
    isRootedRedBeer: function(successCallback, failureCallback) {
        exec(successCallback, failureCallback, 'IRoot', 'isRootedRedBeer', []);
    },
    isRootedRedBeerWithoutBusyBox: function(successCallback, failureCallback) {
        exec(successCallback, failureCallback, 'IRoot', 'isRootedRedBeerWithoutBusyBoxCheck', []);
    }
};
