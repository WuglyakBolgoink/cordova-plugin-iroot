[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/WuglyakBolgoink/cordova-plugin-iroot/master/LICENSE)
[![Android](https://img.shields.io/badge/android-success-green.svg)](https://shields.io)
[![iOS](https://img.shields.io/badge/iOS-success-green.svg)](https://shields.io)

<p align="center">
  <img src="/assets/logo.png">
</p>

Cordova Jailbreak/Root Detection Plugin
=======================================

Use this plugin to add an extra layer of security for your app by detecting if the device was `root`ed (android) or `jailbreak`ed (iOS).

## Install

```
cordova plugin add cordova-plugin-iroot --save
```

### Custom `Android` Install to prevent [False positives](https://github.com/scottyab/rootbeer#false-positives) checks

You can use `ENABLE_BUSYBOX_CHECK` variable to change the logic. By default is `true`.

```
cordova plugin add cordova-plugin-iroot --variable ENABLE_BUSYBOX_CHECK=false --save
```

## Usage in Javascript

```
// available => iOS + Android
IRoot.isRooted(successCallback, failureCallback);

// available => Android
IRoot.isRootedRedBeer(successCallback, failureCallback);

// available => Android
IRoot.isRootedRedBeerWithoutBusyBoxCheck(successCallback, failureCallback);
```

- `successCallback(result:boolean)` is called with `true` if the device is Jailbroken/rooted, otherwise `false`.
- `failureCallback(error:string)` is called if there was an error determining if the device is Jailbroken/rooted.

## Info

Based on:

- **iOS**
    - [cordova-plugin-jailbreak-detection](https://github.com/leecrossley/cordova-plugin-jailbreak-detection)
    - [Shmoopi Anti-Piracy](https://github.com/Shmoopi/AntiPiracy)
- **Android**
    - [RootBeer](https://github.com/scottyab/rootbeer/blob/master/README.md)
    - [cordova-plugin-root-detection](https://github.com/trykovyura/cordova-plugin-root-detection)

## Demo ionic

See [comments](https://github.com/WuglyakBolgoink/cordova-plugin-iroot/issues/1).

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request

## License

MIT License

Copyright (c) 2017 Wuglyak Bolgoink

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

