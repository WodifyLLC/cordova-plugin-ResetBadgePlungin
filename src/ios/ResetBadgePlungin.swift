//
//  ResetBadgePlungin.swift
//  
//  This is the iOS implementation of the ResetBadgePlungin Cordova plugin.
//  It uses UIApplication's badge APIs to clear or set the icon badge number.
//
//  To call these methods from your OutSystems/Cordova layer, use the JavaScript
//  bridging in ResetBadgePlungin.js.
//

import UIKit
import UserNotifications
import Foundation

// The @objc(...) name here must match what you use in plugin.xml and the JS exec calls.
// e.g., "ResetBadgePlungin"
@objc(ResetBadgePlungin)
class ResetBadgePlungin: CDVPlugin {
    
    /**
     Clears the badge number on the app icon.
     Called from the JS side with "clearBadge".
     */
    @objc(clearBadge:)
    func clearBadge(command: CDVInvokedUrlCommand) {
        // Reset the badge count to zero
        UIApplication.shared.applicationIconBadgeNumber = 0
        
        // Create a success result to send back to JS
        let pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: "Badge cleared")
        
        // Send the result back to Cordova
        self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
    }
    
    /**
     Sets the badge number on the app icon.
     Called from the JS side with "setBadge".
     */
    @objc(setBadge:)
    func setBadge(command: CDVInvokedUrlCommand) {
        // Attempt to read the "number" argument from JS
        guard let badgeNumber = command.argument(at: 0) as? Int else {
            // If it's missing or invalid, return an error
            let pluginResult = CDVPluginResult(status: CDVCommandStatus_ERROR, messageAs: "Missing badge number")
            self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
            return
        }
        
        // Update the badge number
        UIApplication.shared.applicationIconBadgeNumber = badgeNumber
        
        // Build a success result
        let pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: "Badge set to \(badgeNumber)")
        
        // Send success back to JS
        self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
    }
}
