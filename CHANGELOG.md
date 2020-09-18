# Cordova Jailbreak/Root Detection Plugin

## Version History

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
- bump `rootbeer` from 0.0.7 to 0.0.8


### v0.7.0-0.8.0

+ Add new optional install variable `ENABLE_BUSYBOX_CHECK`
+ Fixed Android Check: `IRoot.isRooted()`
+ Add additional iOS checks. Merged [PR#8](https://github.com/WuglyakBolgoink/cordova-plugin-iroot/pull/8)
+ Updated Types (index.d.ts)

#### BREAKING CHANGES

+ Add/rename new Android Check: `IRoot.isRootedRedBeer()`
+ Add/rename new Android Check: `IRoot.isRootedRedBeerWithoutBusyBox()`

#### Updates/Upgrades

* upgrade `rootbeer` v0.0.6 -> 0.0.7


### v0.6.0

#### Bug Fixes

* add `iroot.d.ts'
* small fix in `www/iroot.js`

### v0.5.0

#### Updates/Upgrades

* upgrade `rootbeer` v0.0.4 -> 0.0.6
