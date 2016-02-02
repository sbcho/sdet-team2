package edu.cmu.iot;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WhiteBoxTest_LoginHandlerTest {
	private String settingsPath = "C:\\";
	private Vector<UserLoginInfo> users = new Vector<UserLoginInfo>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
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

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoginHandler() {
		LoginHandler lh = new LoginHandler(users);
		assertEquals(0, lh.getTimes());
		assertSame(users, lh.getValidUsers());
	}

	@Test
	public void testResetHandler() {
		LoginHandler lh = new LoginHandler(users);
		lh.resetHandler();
		assertEquals(0, lh.getTimes());
	}

	@Test
	public void testAuthenticateFFCase() throws Exception {
		LoginHandler lh = new LoginHandler(users);
		assertFalse(lh.authenticate("username", "psword")); // ID PW 틀릴때
		assertEquals(1, lh.getTimes());
	}

	@Test
	public void testAuthenticateFTCase() throws Exception {
		LoginHandler lh = new LoginHandler(users);
		assertFalse(lh.authenticate("username", "password")); // ID PW 틀릴때
		assertEquals(1, lh.getTimes());
	}

	@Test
	public void testAuthenticateTFCase() throws Exception {
		LoginHandler lh = new LoginHandler(users);
		assertFalse(lh.authenticate("admin", "p ")); // ID PW 틀릴때
		assertEquals(1, lh.getTimes());
	}

	@Test
	public void testAuthenticateTTCase() throws Exception {
		LoginHandler lh = new LoginHandler(users);
		assertTrue(lh.authenticate("admin", "password")); // ID PW 틀릴때
		assertEquals(0, lh.getTimes());
	}

	@Test(expected = LoginAttemptsExceededException.class)
	public void testAuthenticate3Times() throws Exception {
		LoginHandler lh = new LoginHandler(users);
		assertFalse(lh.authenticate("username", "psword")); // ID PW 틀릴때
		assertEquals(1, lh.getTimes());
		
		assertFalse(lh.authenticate("username", "password")); // ID PW 틀릴때
		assertEquals(2, lh.getTimes());
		
		assertFalse(lh.authenticate("admin", "p ")); // ID PW 틀릴때
		assertEquals(3, lh.getTimes());
		
		assertFalse(lh.authenticate("admin", "p ")); // ID PW 틀릴때
		assertEquals(4, lh.getTimes());

		assertFalse(lh.authenticate("admin", "p ")); // ID PW 틀릴때
		assertEquals(5, lh.getTimes());

	}
	
}
