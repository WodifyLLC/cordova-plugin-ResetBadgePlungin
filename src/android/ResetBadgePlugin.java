//
//  ResetBadgePlugin.java
//
//  This is the Android implementation of the ResetBadgePlugin Cordova plugin.
//  It uses the ShortcutBadger library to apply or remove a badge count.
//
//  Not all Android devices or launchers support badges. ShortcutBadger covers many,
//  but behavior can still vary.
//

package com.mycompany.ResetBadgePlugin;

import org.apache.cordova.Cordovaplugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import me.leolin.shortcutbadger.ShortcutBadger;

public class ResetBadgePlugin extends Cordovaplugin {

    /**
     * The Cordova runtime calls this method when it receives an 'exec' request
     * from the JS layer. Based on the "action" parameter, we'll clear or set the badge.
     */
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        // "clearBadge" action from JS
        if (action.equals("clearBadge")) {
            clearBadge(callbackContext);
            return true;
        }
        // "setBadge" action from JS
        else if (action.equals("setBadge")) {
            // The first argument is the badge number
            int number = args.getInt(0);
            setBadge(number, callbackContext);
            return true;
        }

        // If no recognized action, return false so Cordova knows it’s not handled
        return false;
    }

    /**
     * Clears the badge count (sets it to 0) using ShortcutBadger.
     */
    private void clearBadge(CallbackContext callbackContext) {
        // ShortcutBadger.removeCount(...) removes the badge from the app icon
        ShortcutBadger.removeCount(cordova.getContext());

        // Notify JS of success
        callbackContext.success("Badge cleared");
    }

    /**
     * Sets the badge to a given number using ShortcutBadger.
     *
     * @param number the badge count
     */
    private void setBadge(int number, CallbackContext callbackContext) {
        // ShortcutBadger.applyCount(...) applies the badge count
        ShortcutBadger.applyCount(cordova.getContext(), number);

        // Notify JS of success
        callbackContext.success("Badge set to " + number);
    }
}
