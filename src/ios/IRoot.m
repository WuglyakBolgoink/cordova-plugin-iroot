//
//  IRoot.m
//  Original Copyright (c) 2014 Lee Crossley - http://ilee.co.uk
//  Techniques from http://highaltitudehacks.com/2013/12/17/ios-application-security-part-24-jailbreak-detection-and-evasion/
//

#import "Cordova/CDV.h"
#import "Cordova/CDVViewController.h"
#import "IRoot.h"
#import <sys/stat.h>
#import <sys/sysctl.h>



#define NOTJAIL 4783242

// Failed jailbroken checks
enum {
    // Failed the Jailbreak Check
    KFJailbroken = 3429542,
    // Failed the OpenURL Check
    KFOpenURL = 321,
    // Failed the Cydia Check
    KFCydia = 432,
    // Failed the Inaccessible Files Check
    KFIFC = 47293,
    // Failed the plist check
    KFPlist = 9412,
    // Failed the Processes Check with Cydia
    KFProcessesCydia = 10012,
    // Failed the Processes Check with other Cydia
    KFProcessesOtherCydia = 42932,
    // Failed the Processes Check with other other Cydia
    KFProcessesOtherOCydia = 10013,
    // Failed the FSTab Check
    KFFSTab = 9620,
    // Failed the System() Check
    KFSystem = 47475,
    // Failed the Symbolic Link Check
    KFSymbolic = 34859,
    // Failed the File Exists Check
    KFFileExists = 6625,
} JailbrokenChecks;

// Define the filesystem check
#define FILECHECK [NSFileManager defaultManager] fileExistsAtPath:
// Define the exe path
#define EXEPATH [[NSBundle mainBundle] executablePath]
// Define the plist path
#define PLISTPATH [[NSBundle mainBundle] infoDictionary]

// Jailbreak Check Definitions
#define CYDIA       @"MobileCydia"
#define OTHERCYDIA  @"Cydia"
#define OOCYDIA     @"afpd"
#define CYDIAPACKAGE    @"cydia://package/com.fake.package"
#define CYDIALOC        @"/Applications/Cydia.app"
#define HIDDENFILES     [NSArray arrayWithObjects:@"/Applications/RockApp.app",@"/Applications/Icy.app",@"/usr/sbin/sshd",@"/usr/bin/sshd",@"/usr/libexec/sftp-server",@"/Applications/WinterBoard.app",@"/Applications/SBSettings.app",@"/Applications/MxTube.app",@"/Applications/IntelliScreen.app",@"/Library/MobileSubstrate/DynamicLibraries/Veency.plist",@"/Library/MobileSubstrate/DynamicLibraries/LiveClock.plist",@"/private/var/lib/apt",@"/private/var/stash",@"/System/Library/LaunchDaemons/com.ikey.bbot.plist",@"/System/Library/LaunchDaemons/com.saurik.Cydia.Startup.plist",@"/private/var/tmp/cydia.log",@"/private/var/lib/cydia", @"/etc/clutch.conf", @"/var/cache/clutch.plist", @"/etc/clutch_cracked.plist", @"/var/cache/clutch_cracked.plist", @"/var/lib/clutch/overdrive.dylib", @"/var/root/Documents/Cracked/", nil]

/* End Jailbreak Definitions */


@implementation IRoot

- (void) isRooted:(CDVInvokedUrlCommand*)command;
{
    CDVPluginResult *pluginResult;

    @try
    {
        bool jailbroken = [self jailbroken];
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:jailbroken];
    }
    @catch (NSException *exception)
    {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:exception.reason];
    }
    @finally
    {
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
}




- (bool) jailbroken {

#if !(TARGET_IPHONE_SIMULATOR)

    if ([[NSFileManager defaultManager] fileExistsAtPath:@"/Applications/Cydia.app"])
    {
        return YES;
    }
    else if ([[NSFileManager defaultManager] fileExistsAtPath:@"/Library/MobileSubstrate/MobileSubstrate.dylib"])
    {
        return YES;
    }
    else if ([[NSFileManager defaultManager] fileExistsAtPath:@"/bin/bash"])
    {
        return YES;
    }
    else if ([[NSFileManager defaultManager] fileExistsAtPath:@"/usr/sbin/sshd"])
    {
        return YES;
    }
    else if ([[NSFileManager defaultManager] fileExistsAtPath:@"/etc/apt"])
    {
        return YES;
    }

    FILE *f = NULL ;
    if ((f = fopen("/bin/bash", "r")) ||
        (f = fopen("/Applications/Cydia.app", "r")) ||
        (f = fopen("/Library/MobileSubstrate/MobileSubstrate.dylib", "r")) ||
        (f = fopen("/usr/sbin/sshd", "r")) ||
        (f = fopen("/etc/apt", "r")) ||

        (f = fopen("/private/var/stash", "r")) ||
        (f = fopen("/private/var/lib/apt", "r")) ||
        (f = fopen("/private/var/tmp/cydia.log", "r")) ||
        (f = fopen("/private/var/lib/cydia", "r")) ||
        (f = fopen("/private/var/mobile/Library/SBSettings/Themes", "r")) ||

        (f = fopen("/Library/MobileSubstrate/MobileSubstrate.dylib", "r")) ||
        (f = fopen("/Library/MobileSubstrate/DynamicLibraries/Veency.plist", "r")) ||
        (f = fopen("/Library/MobileSubstrate/DynamicLibraries/LiveClock.plist", "r")) ||
        (f = fopen("/System/Library/LaunchDaemons/com.ikey.bbot.plist", "r")) ||
        (f = fopen("/System/Library/LaunchDaemons/com.saurik.Cydia.Startup.plist", "r")) ||

        (f = fopen("/var/cache/apt", "r")) ||
        (f = fopen("/var/lib/apt", "r")) ||
        (f = fopen("/var/lib/cydia", "r")) ||
        (f = fopen("/var/log/syslog", "r")) ||
        (f = fopen("/var/tmp/cydia.log", "r")) ||

        (f = fopen("/bin/bash", "r")) ||
        (f = fopen("/bin/sh", "r")) ||
        (f = fopen("/usr/libexec/ssh-keysign", "r")) ||
        (f = fopen("/usr/bin/sshd", "r")) ||
        (f = fopen("/usr/libexec/sftp-server", "r")) ||

        (f = fopen("/etc/ssh/sshd_config", "r")) ||
        (f = fopen("/etc/apt", "r")) ||
        (f = fopen("/Applications/Cydia.app", "r")) ||
        (f = fopen("/Applications/RockApp.app", "r")) ||
        (f = fopen("/Applications/Icy.app", "r")) ||

        (f = fopen("/Applications/WinterBoard.app", "r")) ||
        (f = fopen("/Applications/SBSettings.app", "r")) ||
        (f = fopen("/Applications/MxTube.app", "r")) ||
        (f = fopen("/Applications/IntelliScreen.app", "r")) ||
        (f = fopen("/Applications/FakeCarrier.app", "r")) ||

        (f = fopen("/Applications/blackra1n.app", "r")) ||
        (f = fopen("/Applications/IntelliScreen.app", "r")) ||
        (f = fopen("/Applications/FakeCarrier.app", "r")) ||
        (f = fopen("/usr/bin/frida-server", "r")) ||
        (f = fopen("/usr/local/bin/cycript", "r")) ||

        (f = fopen("/usr/lib/libcycript.dylib", "r"))
        )  {
        fclose(f);
        return YES;
    }
    fclose(f);


    NSError *error;
    NSString *testWriteText = @"Jailbreak test";
    NSString *testWritePath = @"/private/jailbreaktest.txt";

    [testWriteText writeToFile:testWritePath atomically:YES encoding:NSUTF8StringEncoding error:&error];

    if (error == nil)
    {
        [[NSFileManager defaultManager] removeItemAtPath:testWritePath error:nil];
        return YES;
    }
    else
    {
        [[NSFileManager defaultManager] removeItemAtPath:testWritePath error:nil];
    }

    if ([[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:@"cydia://package/com.example.package"]])
    {
        return YES;
    }

    //Symbolic link verification
    struct stat s;
    if(lstat("/Applications", &s) || lstat("/var/stash/Library/Ringtones", &s) || lstat("/var/stash/Library/Wallpaper", &s)
       || lstat("/var/stash/usr/include", &s) || lstat("/var/stash/usr/libexec", &s)  || lstat("/var/stash/usr/share", &s) || lstat("/var/stash/usr/arm-apple-darwin9", &s))
    {
        if(s.st_mode & S_IFLNK){
            return YES;
        }
    }

    //Try to write file in private

    [[NSString stringWithFormat:@"test string"]
     writeToFile:@"/private/test_jb.txt"
     atomically:YES
     encoding:NSUTF8StringEncoding error:&error];

    if(nil==error){
        //Wrote?: JB device
        //cleanup what you wrote
        [[NSFileManager defaultManager] removeItemAtPath:@"/private/test_jb.txt" error:nil];
        return YES;
    }

    //New Plugin
    // Make an int to monitor how many checks are failed
    int motzart = 0;

    // URL Check
    if ([self urlCheck] != NOTJAIL) {
        // Jailbroken
        motzart += 3;
    }

    // Cydia Check
    if ([self cydiaCheck] != NOTJAIL) {
        // Jailbroken
        motzart += 3;
    }

    // Inaccessible Files Check
    if ([self inaccessibleFilesCheck] != NOTJAIL) {
        // Jailbroken
        motzart += 2;
    }

    // Plist Check
    if ([self plistCheck] != NOTJAIL) {
        // Jailbroken
        motzart += 2;
    }

    // Processes Check
    if ([self processesCheck] != NOTJAIL) {
        // Jailbroken
        motzart += 2;
    }

    // FSTab Check
    if ([self fstabCheck] != NOTJAIL) {
        // Jailbroken
        motzart += 1;
    }

    // Shell Check
    if ([self systemCheck] != NOTJAIL) {
        // Jailbroken
        motzart += 2;
    }

    // Symbolic Link Check
    if ([self symbolicLinkCheck] != NOTJAIL) {
        // Jailbroken
        motzart += 2;
    }

    // FilesExist Integrity Check
    if ([self filesExistCheck] != NOTJAIL) {
        // Jailbroken
        motzart += 2;
    }

    // Check if the Jailbreak Integer is 3 or more
    if (motzart >= 3) {
        // Jailbroken
        return YES;
    }

#endif

    return NO;
}


#pragma mark - Static Jailbreak Checks

// UIApplication CanOpenURL Check
- (int)urlCheck {
    @try {
        // Create a fake url for cydia
        NSURL *FakeURL = [NSURL URLWithString:CYDIAPACKAGE];
        // Return whether or not cydia's openurl item exists
        if ([[UIApplication sharedApplication] canOpenURL:FakeURL])
            return KFOpenURL;
        else
            return NOTJAIL;
    }
    @catch (NSException *exception) {
        // Error, return false
        return NOTJAIL;
    }
}

// Cydia Check
- (int)cydiaCheck {
    @try {
        // Create a file path string
        NSString *filePath = CYDIALOC;
        // Check if it exists
        if ([[NSFileManager defaultManager] fileExistsAtPath:filePath]) {
            // It exists
            return KFCydia;
        } else {
            // It doesn't exist
            return NOTJAIL;
        }
    }
    @catch (NSException *exception) {
        // Error, return false
        return NOTJAIL;
    }
}

// Inaccessible Files Check
- (int)inaccessibleFilesCheck {
    @try {
        // Run through the array of files
        for (NSString *key in HIDDENFILES) {
            // Check if any of the files exist (should return no)
            if ([[NSFileManager defaultManager] fileExistsAtPath:key]) {
                // Jailbroken
                return KFIFC;
            }
        }

        // Shouldn't get this far, return jailbroken
        return NOTJAIL;
    }
    @catch (NSException *exception) {
        // Error, return false
        return NOTJAIL;
    }
}

// Plist Check
- (int)plistCheck {
    @try {
        // Define the Executable name
        NSString *ExeName = EXEPATH;
        NSDictionary *ipl = PLISTPATH;
        // Check if the plist exists
        if ([FILECHECK ExeName] == FALSE || ipl == nil || ipl.count <= 0) {
            // Executable file can't be found and the plist can't be found...hmmm
            return KFPlist;
        } else {
            // Everything is good
            return NOTJAIL;
        }
    }
    @catch (NSException *exception) {
        // Error, return false
        return NOTJAIL;
    }
}

// Running Processes Check
- (int)processesCheck {
    @try {
        // Make a processes array
        NSArray *processes = [self runningProcesses];

        // Check for Cydia in the running processes
        for (NSDictionary * dict in processes) {
            // Define the process name
            NSString *process = [dict objectForKey:@"ProcessName"];
            // If the process is this executable
            if ([process isEqualToString:CYDIA]) {
                // Return Jailbroken
                return KFProcessesCydia;
            } else if ([process isEqualToString:OTHERCYDIA]) {
                // Return Jailbroken
                return KFProcessesOtherCydia;
            } else if ([process isEqualToString:OOCYDIA]) {
                // Return Jailbroken
                return KFProcessesOtherOCydia;
            }
        }

        // Not Jailbroken
        return NOTJAIL;
    }
    @catch (NSException *exception) {
        // Error
        return NOTJAIL;
    }
}

// FSTab Size
- (int)fstabCheck {
    @try {
        struct stat sb;
        stat("/etc/fstab", &sb);
        long long size = sb.st_size;
        if (size == 80) {
            // Not jailbroken
            return NOTJAIL;
        } else
            // Jailbroken
            return KFFSTab;
    }
    @catch (NSException *exception) {
        // Not jailbroken
        return NOTJAIL;
    }
}

// System() available
- (int)systemCheck {
    @try {
        // See if the system call can be used
        /*if (system(0)) {
            // Jailbroken
            return KFSystem;
        } else*/
            // Not Jailbroken
            return NOTJAIL;
    }
    @catch (NSException *exception) {
        // Not Jailbroken
        return NOTJAIL;
    }
}

// Symbolic Link available
- (int)symbolicLinkCheck {
    @try {
        // See if the Applications folder is a symbolic link
        struct stat s;
        if (lstat("/Applications", &s) != 0) {
            if (s.st_mode & S_IFLNK) {
                // Device is jailbroken
                return KFSymbolic;
            } else
                // Not jailbroken
                return NOTJAIL;
        } else {
            // Not jailbroken
            return NOTJAIL;
        }
    }
    @catch (NSException *exception) {
        // Not Jailbroken
        return NOTJAIL;
    }
}

// FileSystem working correctly?
- (int)filesExistCheck {
    @try {
        // Check if filemanager is working
        if (![FILECHECK [[NSBundle mainBundle] executablePath]]) {
            // Jailbroken and trying to hide it
            return KFFileExists;
        } else
            // Not Jailbroken
            return NOTJAIL;
    }
    @catch (NSException *exception) {
        // Not Jailbroken
        return NOTJAIL;
    }
}

// Get the running processes
- (NSArray *)runningProcesses {
    // Define the int array of the kernel's processes
    int mib[4] = {CTL_KERN, KERN_PROC, KERN_PROC_ALL, 0};
    size_t miblen = 4;

    // Make a new size and int of the sysctl calls
    size_t size;
    int st = sysctl(mib, miblen, NULL, &size, NULL, 0);

    // Make new structs for the processes
    struct kinfo_proc * process = NULL;
    struct kinfo_proc * newprocess = NULL;

    // Do get all the processes while there are no errors
    do {
        // Add to the size
        size += size / 10;
        // Get the new process
        newprocess = realloc(process, size);
        // If the process selected doesn't exist
        if (!newprocess){
            // But the process exists
            if (process){
                // Free the process
                free(process);
            }
            // Return that nothing happened
            return nil;
        }

        // Make the process equal
        process = newprocess;

        // Set the st to the next process
        st = sysctl(mib, miblen, process, &size, NULL, 0);

    } while (st == -1 && errno == ENOMEM);

    // As long as the process list is empty
    if (st == 0){

        // And the size of the processes is 0
        if (size % sizeof(struct kinfo_proc) == 0){
            // Define the new process
            int nprocess = size / sizeof(struct kinfo_proc);
            // If the process exists
            if (nprocess){
                // Create a new array
                NSMutableArray * array = [[NSMutableArray alloc] init];
                // Run through a for loop of the processes
                for (int i = nprocess - 1; i >= 0; i--){
                    // Get the process ID
                    NSString * processID = [[NSString alloc] initWithFormat:@"%d", process[i].kp_proc.p_pid];
                    // Get the process Name
                    NSString * processName = [[NSString alloc] initWithFormat:@"%s", process[i].kp_proc.p_comm];
                    // Get the process Priority
                    NSString *processPriority = [[NSString alloc] initWithFormat:@"%d", process[i].kp_proc.p_priority];
                    // Get the process running time
                    NSDate   *processStartDate = [NSDate dateWithTimeIntervalSince1970:process[i].kp_proc.p_un.__p_starttime.tv_sec];
                    // Create a new dictionary containing all the process ID's and Name's
                    NSDictionary *dict = [[NSDictionary alloc] initWithObjects:[NSArray arrayWithObjects:processID, processPriority, processName, processStartDate, nil]
                                                                       forKeys:[NSArray arrayWithObjects:@"ProcessID", @"ProcessPriority", @"ProcessName", @"ProcessStartDate", nil]];

                    // Add the dictionary to the array
                    [array addObject:dict];
                }
                // Free the process array
                free(process);

                // Return the process array
                return array;

            }
        }
    }

    // If no processes are found, return nothing
    return nil;
}

@end
