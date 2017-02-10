## Cordova Jailbreak/Root Detection Plugin

[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/WuglyakBolgoink/cordova-plugin-iroot/master/LICENSE)
[![Android](https://img.shields.io/badge/android-failed-red.svg)](https://shields.io)
[![iOS](https://img.shields.io/badge/iOS-success-green.svg)](https://shields.io)


Use this plugin to add an extra layer of security for your app, by detecting if the device running the app is jailbroken.

Based on:

- https://github.com/leecrossley/cordova-plugin-jailbreak-detection (iOS)
- https://github.com/trykovyura/cordova-plugin-root-detection (Android)
- https://github.com/scottyab/rootbeer/blob/master/README.md (Android)

## Platform Support

## Installation

```
cordova plugin add cordova-plugin-iroot --save
```


## Usage


```
IRoot.isRooted(successCallback, failureCallback);
```

- `successCallback(result:boolean)` is called with true if the device is Jailbroken/rooted, otherwise false.
- `failureCallback(error:string)` is called if there was an error determining if the device is Jailbroken/rooted.


## TODO

- :sos: Add android classes
- :sos: Add android rootbeer as dependency

## License

MIT License
