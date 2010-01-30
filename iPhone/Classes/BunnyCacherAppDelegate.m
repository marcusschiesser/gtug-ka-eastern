//
//  BunnyCacherAppDelegate.m
//  BunnyCacher
//
//  Created by Holger Wiedemann on 30.01.10.
//  Copyright  2010. All rights reserved.
//

#import "BunnyCacherAppDelegate.h"
#import "PhoneGapViewController.h"


@implementation BunnyCacherAppDelegate


- (id)init
{	
	if (self = [super init])
	{
	}
	return self;
}

- (void)dealloc
{
	[super dealloc];
}


- (void)applicationDidFinishLaunching:(UIApplication *)application
{	
	[super applicationDidFinishLaunching:application];
}

- (void)applicationWillTerminate:(UIApplication *)application
{
}


- (id)getCommandInstance:(NSString*)className
{
	/** You can catch your own commands here, if you wanted to extend the gap: protocol, or add your
	 *  own app specific protocol to it. -jm
	 **/
	return [super getCommandInstance:className];
}

- (BOOL)execute:(InvokedUrlCommand *)command
{
	return [super execute:command];
}


#pragma mark -
#pragma mark WebKit methods
#
/**
 Called when the webview finishes loading.  This stops the activity view and closes the imageview
 */
- (void)webViewDidFinishLoad:(UIWebView *)theWebView 
{
	return [super webViewDidFinishLoad:theWebView];
}

- (void)webViewDidStartLoad:(UIWebView *)theWebView 
{
	return [super webViewDidStartLoad:theWebView];
}

/**
 * Fail Loading With Error
 * Error - If the webpage failed to load display an error with the reson.
 */
- (void)webView:(UIWebView *)theWebView didFailLoadWithError:(NSError *)error 
{
	return [super webView:theWebView didFailLoadWithError:error];
}

/**
 * Start Loading Request
 * This is where most of the magic happens... We take the request(s) and process the response.
 * From here we can re direct links and other protocalls to different internal methods.
 */
- (BOOL)webView:(UIWebView *)theWebView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType
{
	return [super webView:theWebView shouldStartLoadWithRequest:request navigationType:navigationType];
}

@end
