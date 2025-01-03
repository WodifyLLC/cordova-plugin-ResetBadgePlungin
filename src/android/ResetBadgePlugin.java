//
//  ResetBadgePlugin.java
//
//  This is the Android implementation of the ResetBadgePlugin Cordova plugin.
//  It uses the ShortcutBadger library to apply or remove a badge count.
//
//  Not all Android devices or launchers support badges. ShortcutBadger covers many,
//  but behavior can still vary.
//

package com.wodify.ResetBadgePlugin;

import android.util.Log; // For logging errors
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import me.leolin.shortcutbadger.ShortcutBadger;

public class ResetBadgePlugin extends CordovaPlugin {

    private static final String TAG = "ResetBadgePlugin"; // Tag for Log messages

    /**
     * The Cordova runtime calls this method when it receives an 'exec' request
     * from the JS layer. Based on the "action" parameter, we'll clear or set the badge.
     */
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if ("clearBadge".equals(action)) {
            clearBadge(callbackContext);
            return true;
        } else if ("setBadge".equals(action)) {
            // The first argument is the badge number
            int number = args.getInt(0);
            setBadge(number, callbackContext);
            return true;
        }

        // If no recognized action, Cordova handles it as an error
        callbackContext.error("ResetBadgePlugin error: Unknown action '" + action + "'");
        return false;
    }

    /**
     * Clears the badge count (sets it to 0) using ShortcutBadger.
     */
    private void clearBadge(CallbackContext callbackContext) {
        try {
            // ShortcutBadger.removeCount(...) removes the badge from the app icon
            Log.d(TAG, "Attempting to clear badge count");
            ShortcutBadger.removeCount(cordova.getActivity().getApplicationContext());
            Log.d(TAG, "Badge cleared successfully.");
            // Notify JS of success
            callbackContext.success("Badge cleared");
        } catch (Exception e) {
            Log.e(TAG, "Error clearing badge", e);
            callbackContext.error("Error clearing badge: " + e.getMessage());
        }
    }

    /**
     * Sets the badge to a given number using ShortcutBadger.
     *
     * @param number the badge count
     */
    private void setBadge(int number, CallbackContext callbackContext) {
        try {
            // ShortcutBadger.applyCount(...) applies the badge count
            Log.d(TAG, "Attempting to set badge count: " + number);
            ShortcutBadger.applyCount(cordova.getActivity().getApplicationContext(), number);
            Log.d(TAG, "Badge set successfully.");

            // Notify JS of success
            callbackContext.success("Badge set to " + number);
        } catch (Exception e) {
            Log.e(TAG, "Error setting badge to " + number, e);
            callbackContext.error("Error setting badge: " + e.getMessage());
        }
    }
}
