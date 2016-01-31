package edu.cmu.iot;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BlackBoxTest_UserCase_01 {
	private static final String settingsDir = "V:\\Data\\Development\\SDET\\SDET Education\\CMU\\TeamProjects\\Sources\\Data Stores";
	private LoginHandler loginHandler;
	private IoTControlManager controller;

	@BeforeClass
	public static void setupClass() {
		System.out.println("Start BlackBox Test for UserCase_01");
	}

	@AfterClass
	public static void tearDownClass() {
		System.out.println("End BlackBox Test for UserCase_01");
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

	private String getMatchUserId() {
		return "newAdmin";
	}

	private String getMismatchUserId() {
		return "oldAdmin";
	}

	private String getMatchUserPassword() {
		return "412Ooooooo!";
	}

	private String getNumberMismatchUserPassword() {
		return "142Ooooooo!";
	}

	private String getCaseMismatchUserPassword() {
		return "412OOooooo!";
	}

	private String getSymbolMismatchUserPassword() {
		return "412Ooooooo*";
	}

	private String getFirstCharMismatchhUserPassword() {
		return "w12Ooooooo!";
	}

	private String getLastCharMismatchhUserPassword() {
		return "412Oooooool";
	}

	private String getOverOneCharMismatchhUserPassword() {
		return "4l2OoWo*oo!";
	}

	@Test
	public void testTC_01() {
		System.out.println("Start Test Case 01");

		try {
			boolean isLogin = loginHandler.authenticate(getMatchUserId(), getMatchUserPassword());
			assertTrue(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testTC_02() {
		System.out.println("Start Test Case 01");

		try {
			boolean isLogin = loginHandler.authenticate(getMismatchUserId(), getMatchUserPassword());
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testTC_03() {
		System.out.println("Start Test Case 03");

		try {
			boolean isLogin = loginHandler.authenticate(getMatchUserId(), getNumberMismatchUserPassword());
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testTC_04() {
		System.out.println("Start Test Case 03");

		try {
			boolean isLogin = loginHandler.authenticate(getMismatchUserId(), getNumberMismatchUserPassword());
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}

	@Test
	public void testTC_05() {
		System.out.println("Start Test Case 05");

		try {
			boolean isLogin = loginHandler.authenticate(getMatchUserId(), getCaseMismatchUserPassword());
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testTC_06() {
		System.out.println("Start Test Case 06");

		try {
			boolean isLogin = loginHandler.authenticate(getMismatchUserId(), getCaseMismatchUserPassword());
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testTC_07() {
		System.out.println("Start Test Case 07");

		try {
			boolean isLogin = loginHandler.authenticate(getMatchUserId(), getSymbolMismatchUserPassword());
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testTC_08() {
		System.out.println("Start Test Case 08");

		try {
			boolean isLogin = loginHandler.authenticate(getMismatchUserId(), getSymbolMismatchUserPassword());
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testTC_09() {
		System.out.println("Start Test Case 07");

		try {
			boolean isLogin = loginHandler.authenticate(getMatchUserId(), getFirstCharMismatchhUserPassword());
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testTC_10() {
		System.out.println("Start Test Case 08");

		try {
			boolean isLogin = loginHandler.authenticate(getMismatchUserId(), getFirstCharMismatchhUserPassword());
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testTC_11() {
		System.out.println("Start Test Case 11");

		try {
			boolean isLogin = loginHandler.authenticate(getMatchUserId(), getLastCharMismatchhUserPassword());
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testTC_12() {
		System.out.println("Start Test Case 12");

		try {
			boolean isLogin = loginHandler.authenticate(getMismatchUserId(), getLastCharMismatchhUserPassword());
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testTC_13() {
		System.out.println("Start Test Case 13");

		try {
			boolean isLogin = loginHandler.authenticate(getMatchUserId(), getOverOneCharMismatchhUserPassword());
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testTC_14() {
		System.out.println("Start Test Case 14");

		try {
			boolean isLogin = loginHandler.authenticate(getMismatchUserId(), getOverOneCharMismatchhUserPassword());
			assertFalse(isLogin);
		} catch (LoginAttemptsExceededException e) {
			fail("Occure a Exception - " + e.getMessage());
		}
	}
}
