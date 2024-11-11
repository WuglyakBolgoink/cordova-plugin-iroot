# Jailbreak/Root Detection Plugin for Apache Cordova

## Version History

### 3.1.0

- bump `rootbeer` from `0.0.9` to `0.1.0` (see [rootbeer#170](scottyab/rootbeer#170) [rootbeer#171](scottyab/rootbeer#171))


### 3.0.0

- bump `rootbeer` from `0.0.8` to `0.0.9`

**BREAKING CHANGES**:

- support "cordova" version ">=10.0.0"
- support "cordova-android" version ">=9.0.0"
- support "cordova-ios" version ">=6.0.0"

### v2.1.0

- Add Check by Methods and Remove Check isRunningOnEmulator by default (https://github.com/WuglyakBolgoink/cordova-plugin-iroot/pull/43)

### v2.0.0

**BREAKING CHANGES**:

- remove nodeJS stuff
- remove cordova hooks
- remove `ENABLE_BUSYBOX_CHECK` plugin preference
- remove `isRootedRedBeerWithoutBusyBoxCheck` function
- add new internal checks
    - add simple emulator check
    - add simple magisk check (full implementation will be finished soon in `rootbeer` library)
- update documentation / Typings
- update gradle configuration
- bump `rootbeer` from `0.0.7` to `0.0.8`


### v0.7.0-0.8.0

- Add new optional install variable `ENABLE_BUSYBOX_CHECK`
- Fixed Android Check: `IRoot.isRooted()`
- Add additional iOS checks. Merged [PR#8](https://github.com/WuglyakBolgoink/cordova-plugin-iroot/pull/8)
- Updated Types (index.d.ts)

#### BREAKING CHANGES

- Add/rename new Android Check: `IRoot.isRootedRedBeer()`
- Add/rename new Android Check: `IRoot.isRootedRedBeerWithoutBusyBox()`

#### Updates/Upgrades

- bump `rootbeer` from `0.0.6` to `0.0.7`


### v0.6.0

#### Bug Fixes

* add `iroot.d.ts'
* small fix in `www/iroot.js`

### v0.5.0

#### Updates/Upgrades

- bump `rootbeer` from `0.0.4` to `0.0.6`
