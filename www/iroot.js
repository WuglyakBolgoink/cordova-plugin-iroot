var exec = require('cordova/exec');

module.exports = {
    isRooted: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'isRooted', []);
    },
    isRootedRedBeer: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'isRootedRedBeer', []);
    },
    isRootedRedBeerWithoutBusyBox: function(onSuccess, onError) {
        exec(onSuccess, onError, 'IRoot', 'isRootedRedBeerWithoutBusyBox', []);
    }
};
