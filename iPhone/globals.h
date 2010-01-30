/*
 *  globals.h
 *  BunnyCacher
 *
 *  Created by Holger Wiedemann on 15.10.08.
 *  Copyright 2008 CAS Software AG. All rights reserved.
 *
 */

#import <TargetConditionals.h>


//#if TARGET_IPHONE_SIMULATOR
//#if TARGET_OS_IPHONE
//#if defined(__OPTIMIZE__)


// turn of the debug logs for the release build
#ifdef NDEBUG
#define NSDebugLog(...) ((void)0)
#else
#define NSDebugLog NSLog
#endif

