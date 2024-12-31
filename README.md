# ResetBadgePlugin

This is a custom Cordova plugin for OutSystems that lets you **set** or **clear** the badge number on both iOS and Android devices.

## Installation

1. Zip up this folder (including `plugin.xml`, `src/`, and `www/`).
2. In OutSystems Service Studio, create a new Native Module (or "Extensibility Config") and upload the ZIP.
3. Expose the JavaScript methods (`setBadge` and `clearBadge`) as Public Client Actions.

## Usage

```js
// Clear the badge
ResetBadgePlugin.clearBadge(
  () => console.log("Badge cleared"),
  (err) => console.error("Failed to clear badge:", err)
);

// Set the badge to 5
ResetBadgePlugin.setBadge(5,
  () => console.log("Badge set to 5"),
  (err) => console.error("Failed to set badge:", err)
);
