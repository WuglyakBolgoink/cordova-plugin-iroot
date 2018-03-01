export interface IRoot {
    isRooted(success: (boolean) => void, error: (any) => void): void;

    isRootedRedBeer(success: (boolean) => void, error: (any) => void): void;
}
