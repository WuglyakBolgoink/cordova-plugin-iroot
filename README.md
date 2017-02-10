## Cordova Jailbreak/Root Detection Plugin for Apache Cordova

[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/WuglyakBolgoink/cordova-plugin-iroot/master/LICENSE)
[![Android](https://img.shields.io/badge/android-failed-red.svg)](https://shields.io)
[![iOS](https://img.shields.io/badge/android-success-green.svg)](https://shields.io)


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

isRooted

IRoot.isRooted(successCallback, failureCallback);
=> successCallback is called with true if the device is Jailbroken/rooted, otherwise false
=> failureCallback is called if there was an error determining if the device is Jailbroken/rooted


## License

MIT License
