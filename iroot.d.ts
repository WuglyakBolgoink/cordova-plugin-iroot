export interface IRoot {
    isRooted(success: (boolean) => void, error: (any) => void): void;

    isRootedRedBeer(success: (boolean) => void, error: (any) => void): void;

    isRootedRedBeerWithoutBusyBoxCheck(success: (boolean) => void, error: (any) => void): void;
}
