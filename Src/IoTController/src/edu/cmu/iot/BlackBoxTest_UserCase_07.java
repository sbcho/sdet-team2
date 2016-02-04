package edu.cmu.iot;

import static org.junit.Assert.*;

import java.util.Hashtable;
import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BlackBoxTest_UserCase_07 {

	private static final String settingsDir = null;
	private static final String houseAddressText = "192.168.1.119";
	private IoTControlManager controller;
	private Hashtable<String, Object> state;

	@BeforeClass
	public static void setupClass() {
		System.out.println("Start BlackBox Test for UserCase_07");
	}

	@AfterClass
	public static void tearDownClass() {
		System.out.println("End BlackBox Test for UserCase_07");
	}

	@Before
	public void setup() {
		controller = new IoTControlManager(null, settingsDir);
		state = new Hashtable<String, Object>();
	}

	@After
	public void tearDown() {
		controller = null;
		state = null;
	}

	public void requestStateUpdate(Boolean doorControlState, Boolean lightControlState, Boolean alarmControlState) {

		// Initial control values.
		Integer tempSettingControl = 70;
		Boolean humidifierControlState = false;
		Boolean runHeater = true;
		Boolean runChiller = false;
		// Boolean doorControlState = false;
		// Boolean lightControlState = false;
		Boolean proximityControlState = false;
		// Boolean alarmControlState = false;

		if (tempSettingControl != null) {
			state.put(IoTValues.TARGET_TEMP, tempSettingControl);
		}
		if (humidifierControlState != null) {
			state.put(IoTValues.HUMIDIFIER_STATE, humidifierControlState);
		}
		if (doorControlState != null) {
			state.put(IoTValues.DOOR_STATE, doorControlState);
		}
		if (lightControlState != null) {
			state.put(IoTValues.LIGHT_STATE, lightControlState);
		}
		if (proximityControlState != null) {
			state.put(IoTValues.PROXIMITY_STATE, proximityControlState);
		}
		if (alarmControlState != null) {
			state.put(IoTValues.ALARM_STATE, alarmControlState);
		}

		if (runHeater != null) {
			if (runHeater) {
				state.put(IoTValues.HVAC_MODE, "Heater");
			}
		}

		if (runChiller != null) {
			if (runChiller) {
				state.put(IoTValues.HVAC_MODE, "Chiller");
			}
		}

		// have the controller evaluate the new state
		controller.processStateUpdate(state);

		// reset the state
		state.clear();
	}

	@Test
	public void testTestCase_01() {
		Exception expected = null;

		Integer alarmDelay = new Integer("-2147483649");

		Hashtable<String, Object> setting = new Hashtable<String, Object>();
		setting.put(IoTValues.ALARM_DELAY, alarmDelay);

		try {
			// update the settings
			controller.updateSettings(setting);
		} catch (Exception e) {
			expected = e;
		}

		assertNull(expected);
	}

//	@Test
//	//TODO: 
//	public void testTestCase_02() {
//		Exception expected = null;
//
//		Integer alarmDelay = new Integer("-2147483648");
//
//		Hashtable<String, Object> setting = new Hashtable<String, Object>();
//		setting.put(IoTValues.ALARM_DELAY, alarmDelay);
//
//		try {
//			// update the settings
//			controller.updateSettings(setting);
//		} catch (Exception e) {
//			expected = e;
//		}
//
//		assertNull(expected);
//
//		// conntect to server
//		if (controller.connectToHouse(houseAddressText)) {
//			System.out.println("Success connect to " + houseAddressText);
//			// door open, turn on light, disable alram
//			requestStateUpdate(true, true, false);
//		} else {
//			fail("Could not connect to " + houseAddressText);
//		}		
//	}
	
	@Test
	//TODO: 
	public void testTestCase_06() {
		Exception expected = null;

		Integer alarmDelay = new Integer("2147483647");

		Hashtable<String, Object> setting = new Hashtable<String, Object>();
		setting.put(IoTValues.ALARM_DELAY, alarmDelay);

		try {
			// update the settings
			controller.updateSettings(setting);
		} catch (Exception e) {
			expected = e;
		}

		assertNull(expected);
	}
	
	@Test
	public void testTestCase_07() {
		Exception expected = null;

		Integer alarmDelay = new Integer("2147483648");

		Hashtable<String, Object> setting = new Hashtable<String, Object>();
		setting.put(IoTValues.ALARM_DELAY, alarmDelay);

		try {
			// update the settings
			controller.updateSettings(setting);
		} catch (Exception e) {
			expected = e;
		}

		assertNull(expected);
	}
	
	@Test
	public void testTestCase_08() {
		Exception expected = null;

		Integer alarmDelay = new Integer("a");

		Hashtable<String, Object> setting = new Hashtable<String, Object>();
		setting.put(IoTValues.ALARM_DELAY, alarmDelay);

		try {
			// update the settings
			controller.updateSettings(setting);
		} catch (Exception e) {
			expected = e;
		}

		assertNull(expected);
	}
	
	@Test
	public void testTestCase_09() {
		Exception expected = null;

		Integer alarmDelay = new Integer("abcd");

		Hashtable<String, Object> setting = new Hashtable<String, Object>();
		setting.put(IoTValues.ALARM_DELAY, alarmDelay);

		try {
			// update the settings
			controller.updateSettings(setting);
		} catch (Exception e) {
			expected = e;
		}

		assertNull(expected);
	}
	
	@Test
	public void testTestCase_10() {
		Exception expected = null;

		Integer alarmDelay = new Integer("!");

		Hashtable<String, Object> setting = new Hashtable<String, Object>();
		setting.put(IoTValues.ALARM_DELAY, alarmDelay);

		try {
			// update the settings
			controller.updateSettings(setting);
		} catch (Exception e) {
			expected = e;
		}

		assertNull(expected);
	}
	
	@Test
	public void testTestCase_11() {
		Exception expected = null;

		Integer alarmDelay = new Integer("!@#$");

		Hashtable<String, Object> setting = new Hashtable<String, Object>();
		setting.put(IoTValues.ALARM_DELAY, alarmDelay);

		try {
			// update the settings
			controller.updateSettings(setting);
		} catch (Exception e) {
			expected = e;
		}

		assertNull(expected);
	}
	
	@Test
	public void testTestCase_12() {
		Exception expected = null;

		Integer alarmDelay = new Integer(" ");

		Hashtable<String, Object> setting = new Hashtable<String, Object>();
		setting.put(IoTValues.ALARM_DELAY, alarmDelay);

		try {
			// update the settings
			controller.updateSettings(setting);
		} catch (Exception e) {
			expected = e;
		}

		assertNull(expected);
	}
	
	@Test
	public void testTestCase_13() {
		Exception expected = null;

		Integer alarmDelay = new Integer("");

		Hashtable<String, Object> setting = new Hashtable<String, Object>();
		setting.put(IoTValues.ALARM_DELAY, alarmDelay);

		try {
			// update the settings
			controller.updateSettings(setting);
		} catch (Exception e) {
			expected = e;
		}

		assertNull(expected);
	}
}
