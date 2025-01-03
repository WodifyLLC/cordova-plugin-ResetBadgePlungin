import Foundation
import UserNotifications

@objc(BadgeManagerPlugin)
class BadgeManagerPlugin: CDVPlugin {

    @objc(resetBadge:)
    func resetBadge(command: CDVInvokedUrlCommand) {
        UIApplication.shared.applicationIconBadgeNumber = 0
        let pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: "Badge count reset")
        self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
    }
}
