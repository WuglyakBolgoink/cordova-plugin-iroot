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
cordova plugin add cordova-plugin-iroot
```

## Usage in Javascript

```
// available => iOS + Android
IRoot.isRooted(successCallback, failureCallback);

// available => Android
IRoot.isRootedWithBusyBox(successCallback, failureCallback);
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
- **Articles**
  - [Android Root Detection Techniques](https://blog.netspi.com/android-root-detection-techniques/)
- **Repos**
  - ...
## Demo ionic

See [comments](https://github.com/WuglyakBolgoink/cordova-plugin-iroot/issues/1).

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request

## ToDo's

1. Cyanogenmod.superuser
  > If the Cyanogenmod ROM is installed, the cyanogenmod.superuser activity may be in the com.android.settings package.
  > This can be detected by listing the activities within com.android.settings.
2. Su
  > Execute su and then id to check if the current user has a uid of 0 or if it contains (root).
  > shell@android:/ $ su
  > shell@android:/ # id
  > uid=0(root) gid=0(root) groups=1003(graphics),1004(input),1007(log),1009(mount),1011(adb),1015(sdcard_rw),1028(sdcard_r)
3. Busybox
  > If a device has been rooted, more often then not Busybox has been installed as well.
  > Busybox is a binary that provides many common linux commands. Running Busybox is a good indication that a device has been rooted.
  > root@android:/ # busybox df
  > Filesystem           1K-blocks      Used Available Use% Mounted on
  > tmpfs                   958500        32    958468   0% /dev
  > tmpfs                   958500         0    958500   0% /mnt/secure
  > tmpfs                   958500         0    958500   0% /mnt/asec
  > tmpfs                   958500         0    958500   0% /mnt/obb


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

