var exec = require('cordova/exec');

module.exports = {
    isRooted: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'isRooted', []);
    },
    isRootedWithBusyBox: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'isRootedWithBusyBox', []);
    }
};
