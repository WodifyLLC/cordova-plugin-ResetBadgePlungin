//
//  ResetBadgeplugin.swift
//  
//  This is the iOS implementation of the ResetBadgeplugin Cordova plugin.
//  It uses UIApplication's badge APIs to clear or set the icon badge number.
//
//  To call these methods from your OutSystems/Cordova layer, use the JavaScript
//  bridging in ResetBadgeplugin.js.
//

import UIKit
import UserNotifications
import Foundation

// The @objc(...) name here must match what you use in plugin.xml and the JS exec calls.
// e.g., "ResetBadgeplugin"
@objc(ResetBadgeplugin)
class ResetBadgeplugin: CDVplugin {
    
    /**
     Clears the badge number on the app icon.
     Called from the JS side with "clearBadge".
     */
    @objc(clearBadge:)
    func clearBadge(command: CDVInvokedUrlCommand) {
        // Reset the badge count to zero
        UIApplication.shared.applicationIconBadgeNumber = 0
        
        // Create a success result to send back to JS
        let pluginResult = CDVpluginResult(status: CDVCommandStatus_OK, messageAs: "Badge cleared")
        
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
            let pluginResult = CDVpluginResult(status: CDVCommandStatus_ERROR, messageAs: "Missing badge number")
            self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
            return
        }
        
        // Update the badge number
        UIApplication.shared.applicationIconBadgeNumber = badgeNumber
        
        // Build a success result
        let pluginResult = CDVpluginResult(status: CDVCommandStatus_OK, messageAs: "Badge set to \(badgeNumber)")
        
        // Send success back to JS
        self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
    }
}