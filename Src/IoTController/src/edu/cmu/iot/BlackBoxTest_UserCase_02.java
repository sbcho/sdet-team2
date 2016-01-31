package edu.cmu.iot;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BlackBoxTest_UserCase_02 {
	private static final String settingsDir = "D:\\Tmp";
	private LoginHandler loginHandler;
	private IoTControlManager controller;

	@BeforeClass
	public static void setupClass() {
		System.out.println("Start BlackBox Test for UserCase_02");
	}

	@AfterClass
	public static void tearDownClass() {
		System.out.println("End BlackBox Test for UserCase_02");
	}

	@Before
	public void setup() {
		controller = new IoTControlManager(null, settingsDir);
		Vector<UserLoginInfo> users = controller.loadUsers();
		loginHandler = new LoginHandler(users);
	}

	@After
	public void tearDown() {
		controller = null;
		loginHandler = null;
	}

	private void generateNLF_1() {
		try {
			// Fail case
			loginHandler.authenticate("admin", "1paSsword!");
			// Now, NLF == 1
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}

	private void generateNLF_3() {
		generateNLF_1();

		try {
			// Fail case
			loginHandler.authenticate("admin", "1paSsword!");
			// Now, NLF == 2
			// Fail case
			loginHandler.authenticate("admin", "1paSsword!");
			// Now, NLF == 3
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}

	private void generateNLF_4() {
		generateNLF_3();

		try {
			// Fail case
			loginHandler.authenticate("admin", "1paSsword!");
			// Now, NLF == 4
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}

	@Test
	public void testTC_01() {
		System.out.println("Start Test Case 01");

		try {
			boolean isLogin = false;
			isLogin = loginHandler.authenticate("admin", "password");
			assertTrue(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}

	}

	@Test
	public void testTC_02() {
		System.out.println("Start Test Case 02");

		try {
			boolean isLogin = false;
			isLogin = loginHandler.authenticate("admin", "1paSsword!");

			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}

	}

	@Test
	public void testTC_03() {
		System.out.println("Start Test Case 03");

		generateNLF_1();

		try {
			boolean isLogin = false;
			isLogin = loginHandler.authenticate("Jeff", "JeffGennari42!");
			assertTrue(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}

	@Test
	public void testTC_04() {
		System.out.println("Start Test Case 04");

		generateNLF_1();
		try {
			boolean isLogin = false;
			isLogin = loginHandler.authenticate("Jeff", "JffGennari42!");
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}

	@Test
	public void testTC_05() {
		System.out.println("Start Test Case 05");

		generateNLF_3();
		try {
			boolean isLogin = false;
			isLogin = loginHandler.authenticate("Jeff", "JeffGennari42!");
			assertTrue(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}

	@Test
	public void testTC_06() {
		System.out.println("Start Test Case 06");

		generateNLF_3();
		try {
			boolean isLogin = false;
			isLogin = loginHandler.authenticate("Jeff", "JffGennari42!");
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}

	@Test
	public void testTC_07() {
		System.out.println("Start Test Case 07");

		generateNLF_4();
		try {
			boolean isLogin = false;
			isLogin = loginHandler.authenticate("Jeff", "JeffGennari42!");
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			System.out.println("It is OK! Occure a Exception - LoginAttemptsExceededException");
		}
	}

	@Test
	public void testTC_08() {
		System.out.println("Start Test Case 08");

		generateNLF_4();
		try {
			boolean isLogin = false;
			isLogin = loginHandler.authenticate("Jeff", "JffGennari42!");
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			System.out.println("It is OK! Occure a Exception - LoginAttemptsExceededException");
		}
	}

}
