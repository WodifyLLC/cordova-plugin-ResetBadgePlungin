var exec = require('cordova/exec');

exports.resetBadge = function (success, error) {
    exec(success, error, "BadgeManager", "resetBadge", []);
};
