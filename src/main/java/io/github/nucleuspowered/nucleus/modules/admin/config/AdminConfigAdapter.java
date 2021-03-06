/*
 * This file is part of Nucleus, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package io.github.nucleuspowered.nucleus.modules.admin.config;

import com.google.common.reflect.TypeToken;
import io.github.nucleuspowered.nucleus.internal.qsml.NucleusConfigAdapter;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.SimpleCommentedConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

public class AdminConfigAdapter extends NucleusConfigAdapter<AdminConfig> {

    @Override
    protected AdminConfig getDefaultObject() {
        return new AdminConfig();
    }

    @Override
    protected AdminConfig convertFromConfigurateNode(ConfigurationNode node) throws ObjectMappingException {
        return node.getValue(TypeToken.of(AdminConfig.class));
    }

    @Override
    protected ConfigurationNode insertIntoConfigurateNode(AdminConfig data) throws ObjectMappingException {
        return SimpleCommentedConfigurationNode.root().setValue(TypeToken.of(AdminConfig.class), data);
    }
}
