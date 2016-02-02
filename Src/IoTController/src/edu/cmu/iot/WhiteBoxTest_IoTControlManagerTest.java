package edu.cmu.iot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.easymock.EasyMock.expect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Vector;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WhiteBoxTest_IoTControlManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIoTControlManager() {
		String settingsPath = "d:\\";
		IoTControlManager icm = new IoTControlManager(null, settingsPath);
		assertNotNull( 		icm.getLogMessages() );
		assertNotNull(icm.getUserSettings());
		assertEquals(settingsPath, icm.getSettingsPath());
		assertNull(icm.getConnMgr());
		assertFalse(icm.getNewAlarmActiveState());
	}

	@Test
	public void testLoadUsers() {
		String settingsPath = "d:\\";
		IoTControlManager icm = new IoTControlManager(null, settingsPath);
		Vector<UserLoginInfo> users = new Vector<UserLoginInfo>();
		try {
			  
			File file = new File(settingsPath + File.separator + IoTValues.USERS_DB);

			BufferedReader br = new BufferedReader(new FileReader(file));

			String line;
			while ((line = br.readLine()) != null) {
				String[] entry = line.split("=");
				users.add(new UserLoginInfo(entry[0], entry[1]));
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		Vector<UserLoginInfo> usersFromSource = new Vector<UserLoginInfo>();
		usersFromSource = icm.loadUsers();
		assertEquals(users.size(), usersFromSource.size());
		
		for (int i =0; i < users.size(); i++)
		{
			assertEquals(users.get(i).getUserName(), usersFromSource.get(i).getUserName());
			assertEquals(users.get(i).getPassword(), usersFromSource.get(i).getPassword()); 
		} 
	}

	@Test
	public void testLoadSettings() {
		String settingsPath = "d:\\";
		IoTControlManager icm = new IoTControlManager(null, settingsPath);
		icm.loadSettings();
		assertEquals(8,icm.getUserSettings().get(IoTValues.ALARM_DELAY));
		assertEquals("q",icm.getUserSettings().get(IoTValues.ALARM_PASSCODE));
	}

	@Test
	public void testUpdateSettingsWhenSettingisNull() {
		String settingsPath = "d:\\";
		IoTControlManager icm = new IoTControlManager(null, settingsPath);
		//icm.loadSettings();
		Hashtable<String, Object> testSetting = new Hashtable<String, Object>();
		testSetting.put(IoTValues.ALARM_DELAY, 1);
		testSetting.put(IoTValues.ALARM_PASSCODE, "dj");
		icm.updateSettings(testSetting);
		assertEquals(1, icm.getUserSettings().get(IoTValues.ALARM_DELAY));
		assertEquals("dj", icm.getUserSettings().get(IoTValues.ALARM_PASSCODE));
	}
	
	@Test
	public void testUpdateSettingsWhenSettingisNotNull() {
		String settingsPath = "d:\\";
		IoTControlManager icm = new IoTControlManager(null, settingsPath);
		icm.loadSettings();
		assertEquals(8,icm.getUserSettings().get(IoTValues.ALARM_DELAY));
		assertEquals("q",icm.getUserSettings().get(IoTValues.ALARM_PASSCODE));
		Hashtable<String, Object> testSetting = new Hashtable<String, Object>();
		testSetting.put(IoTValues.ALARM_DELAY, 1);
		testSetting.put(IoTValues.ALARM_PASSCODE, "dj");
		icm.updateSettings(testSetting);
		assertEquals(1,icm.getUserSettings().get(IoTValues.ALARM_DELAY));
		assertEquals("dj",icm.getUserSettings().get(IoTValues.ALARM_PASSCODE));
		
	}


	@Test
	public void testGetUserSettings() {
		String settingsPath = "d:\\";
		IoTControlManager icm = new IoTControlManager(null, settingsPath);
		icm.loadSettings();
		Hashtable<String, Object> testSetting = new Hashtable<String, Object>();
		testSetting.put(IoTValues.ALARM_DELAY, 1);
		testSetting.put(IoTValues.ALARM_PASSCODE, "dj");
		icm.updateSettings(testSetting);
		assertEquals(1,icm.getUserSettings().get(IoTValues.ALARM_DELAY));
		assertEquals("dj",icm.getUserSettings().get(IoTValues.ALARM_PASSCODE));
	}

	@Test
	public void testAddUser() {
		//IoTControlWindow mock = EasyMock.createMock(IoTControlWindow.class);
		//EasyMock.expect(mock.updateLog("text")).andReturn("");
		
		
		String settingsPath = "d:\\";
		IoTControlManager icm = new IoTControlManager(null, settingsPath);
		//icm.addUser("tester", "testee");
		
		
		Vector<UserLoginInfo> usersFromSource = new Vector<UserLoginInfo>();
		usersFromSource = icm.loadUsers(); 
		boolean find = false;
		for (int i =0; i < usersFromSource.size(); i++)
		{
			if (usersFromSource.get(i).getUserName().equals("tester") && 
					usersFromSource.get(i).getPassword().equals("testee"))
				find = true;
			
		} 
		assertEquals(true, find); 
	}

	@Test
	public void testProcessStateUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testConnectToHouse() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisconnectFromHouse() {
		fail("Not yet implemented");
	}

	@Test
	public void testEvaluateNewState() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsConnected() {
		fail("Not yet implemented");
	}

}
