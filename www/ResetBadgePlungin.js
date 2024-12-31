/*
  This JavaScript file is the bridge between your OutSystems (or Cordova) app
  and the native iOS/Android code. The 'exec' function calls into the native
  layer using the plugin name ("ResetBadgePlungin") and the appropriate
  action method name ("clearBadge"/"setBadge").
*/

var exec = require('cordova/exec');

/**
 * Clears the app's badge number on both iOS and supported Android launchers.
 * @param {Function} success - Callback when the operation succeeds
 * @param {Function} error - Callback when the operation fails
 */
exports.clearBadge = function (success, error) {
    // The third parameter "ResetBadgePlungin" must match the native class name
    exec(success, error, "ResetBadgePlungin", "clearBadge", []);
};

/**
 * Sets the badge to a custom number.
 * @param {number} number - Badge count to set
 * @param {Function} success - Callback when the operation succeeds
 * @param {Function} error - Callback when the operation fails
 */
exports.setBadge = function (number, success, error) {
    exec(success, error, "ResetBadgePlungin", "setBadge", [number]);
};
