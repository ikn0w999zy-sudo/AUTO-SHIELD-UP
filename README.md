# Auto-Shield Minecraft Mod

A client-side automated shield defense mod for **Minecraft 1.21.1** using **Fabric Mod Loader**.

## Overview

This mod automatically detects incoming PvP threats (melee attacks, mace attacks, explosive threats) and:
1. Equips a shield from the player's hotbar or off-hand
2. Raises the shield to block incoming attacks
3. Automatically restores the previously held item after the threat passes

## Features

- **Threat Detection Engine**: Monitors for nearby hostile players and explosive threats (End Crystals, Respawn Anchors)
- **Automatic Shield Equipping**: Scans inventory (off-hand first, then hotbar)
- **Automatic Blocking**: Sends blocking packets to raise the shield before damage registers
- **Toggle Keybinding**: Press `R` (configurable) to enable/disable Auto-Shield
- **Chat Notifications**: Displays status messages when toggled

## Building the Mod

### Prerequisites
- **Java 21** or higher
- **Gradle** (included via `./gradlew`)

### Build Instructions

```bash
# Clone the repository
git clone https://github.com/ikn0w999zy-sudo/AUTO-SHIELD-UP.git
cd AUTO-SHIELD-UP

# Build the mod
./gradlew build

# The compiled .jar will be in: build/libs/auto-shield-1.0.0.jar
```

## Installation

1. Download the latest `.jar` from `build/libs/`
2. Place it in your Minecraft `mods` folder (requires Fabric Loader)
3. Launch Minecraft with the Fabric profile

## Configuration

### Toggle Keybinding
- **Default Key**: `R`
- **Change In-Game**: Open Controls → Search for "Auto-Shield" → Rebind as needed
- **Chat Command**: Type any message to see the current status (toggled via keybinding only)

## How It Works

### Threat Detection
The mod continuously scans for:
- **Melee Attacks**: Nearby players within 8 blocks swinging weapons
- **Mace/Smash Attacks**: Players holding maces (1.21+ mechanic)
- **Explosive Threats**: End Crystals or Respawn Anchors within blast radius

### Shield Equipping
1. Checks off-hand slot for shield
2. If not found, scans hotbar slots (0-8) left to right
3. Switches to shield slot if needed

### Blocking
Once shield is equipped, the mod sends a right-click packet to raise the shield before damage registers.

## Compatibility

- **Minecraft Version**: 1.21.1 (Fabric)
- **Java Version**: 21+
- **Fabric Loader**: 0.16.0+
- **Server**: Client-side only (works on multiplayer servers without server-side installation)

## License

MIT License - See LICENSE file for details

## Support & Issues

For bugs, feature requests, or questions, please open an issue on GitHub.

## Disclaimer

Use of this mod on servers may violate their terms of service. Always check server rules before using client-side automation mods.
