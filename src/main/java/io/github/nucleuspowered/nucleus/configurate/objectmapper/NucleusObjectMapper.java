/*
 * This file is part of Nucleus, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package io.github.nucleuspowered.nucleus.configurate.objectmapper;

import io.github.nucleuspowered.nucleus.Util;
import io.github.nucleuspowered.nucleus.configurate.annotations.RemoveSettings;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMapper;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.Setting;

import java.lang.reflect.Field;
import java.util.Map;

public class NucleusObjectMapper<T> extends ObjectMapper<T> {

    private final String[] toRemove;

    /**
     * Create a new object mapper of a given type
     *
     * @param clazz The type this object mapper will work with
     * @throws ObjectMappingException if the provided class is in someway invalid
     */
    public NucleusObjectMapper(Class<T> clazz) throws ObjectMappingException {
        super(clazz);

        RemoveSettings rs = clazz.getAnnotation(RemoveSettings.class);
        if (rs == null) {
            toRemove = null;
        } else {
            toRemove = rs.value();
        }
    }

    protected void collectFields(Map<String, FieldData> cachedFields, Class<? super T> clazz) throws ObjectMappingException {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Setting.class)) {
                Setting setting = field.getAnnotation(Setting.class);
                String path = setting.value();
                if (path.isEmpty()) {
                    path = field.getName();
                }

                String comment = setting.comment();
                if (comment.startsWith("loc:")) {
                    comment = Util.getMessageWithFormat(setting.comment().split(":", 2)[1]);
                }

                FieldData data = new FieldData(field, comment);
                field.setAccessible(true);
                if (!cachedFields.containsKey(path)) {
                    cachedFields.put(path, data);
                }
            }
        }
    }

    @Override
    public BoundInstance bind(T instance) {
        return new NucleusBoundInstance(instance);
    }

    @Override
    public BoundInstance bindToNew() throws ObjectMappingException {
        return new NucleusBoundInstance(constructObject());
    }

    private class NucleusBoundInstance extends BoundInstance {

        protected NucleusBoundInstance(T t) {
            super(t);
        }

        @Override
        public T populate(ConfigurationNode source) throws ObjectMappingException {
            removePath(source);
            return super.populate(source);
        }

        @Override
        public void serialize(ConfigurationNode target) throws ObjectMappingException {
            super.serialize(target);
            removePath(target);
        }

        private void removePath(ConfigurationNode target) {
            if (toRemove != null) {
                for (String s : toRemove) {
                    target.removeChild(s);
                }
            }
        }
    }
}
