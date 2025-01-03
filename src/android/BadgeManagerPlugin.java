package com.wodify.BadgeManager;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import android.app.NotificationManager;
import android.content.Context;

import org.json.JSONArray;

public class BadgeManagerPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if ("resetBadge".equals(action)) {
            resetBadge(callbackContext);
            return true;
        }
        callbackContext.error("Invalid action");
        return false;
    }

    private void resetBadge(CallbackContext callbackContext) {
        try {
            NotificationManager notificationManager = 
                (NotificationManager) cordova.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancelAll();
            callbackContext.success("Badge count reset");
        } catch (Exception e) {
            callbackContext.error("Failed to reset badge: " + e.getMessage());
        }
    }
}
