//
//  IRoot.h
//  Copyright (c) 2014 Lee Crossley - http://ilee.co.uk
//

#import "Foundation/Foundation.h"
#import "Cordova/CDV.h"

@interface IRoot : CDVPlugin

- (void) isRooted:(CDVInvokedUrlCommand*)command;
- (void) isMiddleManProxyEnabled:(CDVInvokedUrlCommand*)command;

@end
