/*
 * This file is part of Nucleus, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package io.github.nucleuspowered.nucleus.modules.misc;

import io.github.nucleuspowered.nucleus.internal.qsml.module.ConfigurableModule;
import io.github.nucleuspowered.nucleus.modules.misc.config.MiscConfigAdapter;
import uk.co.drnaylor.quickstart.annotations.ModuleData;

@ModuleData(id = "misc", name = "Miscellaneous")
public class MiscModule extends ConfigurableModule<MiscConfigAdapter> {
    @Override
    public MiscConfigAdapter getAdapter() {
        return new MiscConfigAdapter();
    }
}
