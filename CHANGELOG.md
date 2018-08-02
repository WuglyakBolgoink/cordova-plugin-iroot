# Cordova Jailbreak/Root Detection Plugin

## Version History

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
