/**
 * Copyright (c) 2010-2018 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.wink.client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestJsonWinkDevice {
    private String testData = "    {\n" + "      \"object_type\": \"light_bulb\",\n"
            + "      \"object_id\": \"2666129\",\n" + "      \"uuid\": \"be171da6-a520-41bb-97f1-9ed01c6b0800\",\n"
            + "      \"icon_id\": \"71\",\n" + "      \"icon_code\": \"light_bulb-light_bulb\",\n"
            + "      \"desired_state\": {},\n" + "      \"last_reading\": {\n" + "        \"connection\": true,\n"
            + "        \"connection_updated_at\": 1500168291.3083792,\n" + "        \"needs_repair\": false,\n"
            + "        \"needs_repair_updated_at\": 1500168291.3083792,\n" + "        \"powered\": false,\n"
            + "        \"powered_updated_at\": 1500168291.3083792,\n" + "        \"brightness\": 0.45,\n"
            + "        \"brightness_updated_at\": 1500168291.3083792,\n"
            + "        \"desired_powered_updated_at\": 1499963949.1642816,\n"
            + "        \"desired_brightness_updated_at\": 1499963926.4453058,\n"
            + "        \"desired_powered_changed_at\": 1499963812.5263052,\n"
            + "        \"desired_brightness_changed_at\": 1499963802.7901878,\n"
            + "        \"powered_changed_at\": 1500168291.3083792,\n"
            + "        \"brightness_changed_at\": 1500161604.595307\n" + "      },\n" + "      \"subscription\": {\n"
            + "        \"pubnub\": {\n"
            + "          \"subscribe_key\": \"sub-c-f7bf7f7e-0542-11e3-a5e8-02ee2ddab7fe\",\n"
            + "          \"channel\": \"abcdef01234567890987654321fedcba|light_bulb-87654321|user-123456\"\n"
            + "        }\n" + "      },\n" + "      \"light_bulb_id\": \"2666129\",\n"
            + "      \"name\": \"Rec Room Light\",\n" + "      \"locale\": \"en_ca\",\n" + "      \"units\": {},\n"
            + "      \"created_at\": 1494194322,\n" + "      \"hidden_at\": null,\n" + "      \"capabilities\": {},\n"
            + "      \"triggers\": [],\n" + "      \"manufacturer_device_model\": \"lutron_p_pkg1_w_wh_d\",\n"
            + "      \"manufacturer_device_id\": null,\n" + "      \"device_manufacturer\": \"lutron\",\n"
            + "      \"model_name\": \"Caseta Wireless Dimmer & Pico\",\n" + "      \"upc_id\": \"556\",\n"
            + "      \"upc_code\": \"lutron_p-pkg1w-wh-d\",\n"
            + "      \"primary_upc_code\": \"lutron_p-pkg1w-wh-d\",\n" + "      \"gang_id\": null,\n"
            + "      \"hub_id\": \"646537\",\n" + "      \"local_id\": \"1\",\n" + "      \"radio_type\": \"lutron\",\n"
            + "      \"linked_service_id\": null,\n" + "      \"lat_lng\": [\n" + "        12.3456677,\n"
            + "        56.789000\n" + "      ],\n" + "      \"location\": \"location\",\n" + "      \"order\": 0\n"
            + "    }";

    private IWinkDevice device;

    @Before
    public void setUp() {
        JsonParser parser = new JsonParser();
        JsonObject resultJson = parser.parse(testData).getAsJsonObject();
        device = new JsonWinkDevice(resultJson);
    }

    @Test
    public void testGetId() {
        assertEquals("be171da6-a520-41bb-97f1-9ed01c6b0800", device.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Rec Room Light", device.getName());
    }

    @Test
    public void testGetDeviceType() {
        assertEquals(WinkSupportedDevice.DIMMABLE_LIGHT, device.getDeviceType());
    }

    @Test
    public void testGetProperty() {
        assertEquals("2666129", device.getProperty("object_id"));
    }

    @Test
    public void testGetPubNubSubscribeKey() {
        assertEquals("sub-c-f7bf7f7e-0542-11e3-a5e8-02ee2ddab7fe", device.getPubNubSubscriberKey());
    }

    @Test
    public void testGetPubNubChannel() {
        assertEquals("abcdef01234567890987654321fedcba|light_bulb-87654321|user-123456", device.getPubNubChannel());
    }

    @Test
    public void testGetCurrentState() {
        assertEquals("false", device.getCurrentState().get("powered"));
        assertEquals("0.45", device.getCurrentState().get("brightness"));
    }

    @Test
    public void testGetDesiredState() {
        assertNull(device.getDesiredState().get("powered"));
    }

}
