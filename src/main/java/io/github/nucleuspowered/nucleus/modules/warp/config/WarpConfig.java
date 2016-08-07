/*
 * This file is part of Nucleus, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package io.github.nucleuspowered.nucleus.modules.warp.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class WarpConfig {

    @Setting(value = "default-warp-cost", comment = "loc:config.warps.cost")
    private int defaultWarpCost = 0;

    @Setting(value = "separate-permissions", comment = "loc:config.warps.separate")
    private boolean separatePermissions = false;

    @Setting(value = "safe-warp", comment = "loc:config.warps.safe")
    private boolean enableSafeWarp = false;

    public boolean isSeparatePermissions() {
        return separatePermissions;
    }

    public int getDefaultWarpCost() {
        return Math.max(0, defaultWarpCost);
    }

    public boolean isEnableSafeWarp() {
        return enableSafeWarp;
    }
}
