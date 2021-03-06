/**
 * Copyright (c) 2010-2018 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.wink.internal;

import static org.openhab.binding.wink.WinkBindingConstants.*;

import java.util.Set;

import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.openhab.binding.wink.handler.BinarySwitchHandler;
import org.openhab.binding.wink.handler.DoorBellHandler;
import org.openhab.binding.wink.handler.LightBulbHandler;
import org.openhab.binding.wink.handler.LockHandler;
import org.openhab.binding.wink.handler.WinkHub2BridgeHandler;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;

/**
 * The {@link WinkHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Sebastien Marchand - Initial contribution
 */
@Component(service = ThingHandlerFactory.class, immediate = true)
public class WinkHandlerFactory extends BaseThingHandlerFactory {

    private Logger logger = LoggerFactory.getLogger(WinkHandlerFactory.class);

    public static final Set<ThingTypeUID> DISCOVERABLE_DEVICE_TYPES_UIDS = ImmutableSet.of(THING_TYPE_LIGHT_BULB,
            THING_TYPE_BINARY_SWITCH, THING_TYPE_LOCK, THING_TYPE_DOORBELL);

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        logger.debug("Checking if the factory supports {}", thingTypeUID.toString());
        return DISCOVERABLE_DEVICE_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (thingTypeUID.equals(THING_TYPE_LIGHT_BULB)) {
            return new LightBulbHandler(thing);
        } else if (thingTypeUID.equals(THING_TYPE_BINARY_SWITCH)) {
            return new BinarySwitchHandler(thing);
        } else if (thingTypeUID.equals(THING_TYPE_LOCK)) {
            return new LockHandler(thing);
        } else if (thingTypeUID.equals(THING_TYPE_DOORBELL)) {
            return new DoorBellHandler(thing);
        }

        return null;
    }
}
