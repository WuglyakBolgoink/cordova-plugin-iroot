# cordova-plugin-iroot

Cordova Jailbreak/Root Detection Plugin

[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/WuglyakBolgoink/cordova-plugin-iroot/master/LICENSE)
[![Android](https://img.shields.io/badge/android-success-green.svg)](https://shields.io)
[![iOS](https://img.shields.io/badge/iOS-success-green.svg)](https://shields.io)

[![NPM](https://nodei.co/npm/cordova-plugin-iroot.png?mini=true)](https://nodei.co/npm/cordova-plugin-iroot/)


Use this plugin to add an extra layer of security for your app, by detecting if the device running the app is jailbroken.

Based on:

- [cordova-plugin-jailbreak-detection](https://github.com/leecrossley/cordova-plugin-jailbreak-detection) (iOS)
- [cordova-plugin-root-detection](https://github.com/trykovyura/cordova-plugin-root-detection) (Android)
- [RootBeer](https://github.com/scottyab/rootbeer/blob/master/README.md) (Android)
- [Shmoopi Anti-Piracy](https://github.com/Shmoopi/AntiPiracy) (iOS)

## Installation

```
cordova plugin add cordova-plugin-iroot --save
```

## Usage

```
// available => iOS + Android
IRoot.isRooted(successCallback, failureCallback);

// available => Android
IRoot.isRootedRedBeer(successCallback, failureCallback);

// available => Android
IRoot.isRootedRedBeerWithoutBusyBoxCheck(successCallback, failureCallback);
```

- `successCallback(result:boolean:ios|number:android)` is called with `true/1` if the device is Jailbroken/rooted, otherwise `false/0`.
- `failureCallback(error:string)` is called if there was an error determining if the device is Jailbroken/rooted.


## Demo ionic

See [comments](https://github.com/WuglyakBolgoink/cordova-plugin-iroot/issues/1).

## License

MIT License
