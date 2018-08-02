/**
 * This plugin defines a global IRoot object, which allow you to check if your device was rooted/jailbrocken.
 * Although the object is in the global scope, it is not available until after the deviceready event.
 */
interface IIRoot {
    isRooted(onSuccess: (boolean) => void, onError: (any) => void): void;

    isRootedRedBeer(onSuccess: (boolean) => void, onError: (any) => void): void;

    isRootedRedBeerWithoutBusyBoxCheck(onSuccess: (boolean) => void, onError: (any) => void): void;
}

declare var IRoot: IIRoot;
