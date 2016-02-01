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

	private void generateNLF_1() throws LoginAttemptsExceededException {
		// Fail case
		loginHandler.authenticate("admin", "1paSsword!");
		// Now, NLF == 1
	}

	private void generateNLF_2() throws LoginAttemptsExceededException {
		generateNLF_1();

		// Fail case
		loginHandler.authenticate("admin", "1paSsword!");
		// Now, NLF == 2
	}

	private void generateNLF_3() throws LoginAttemptsExceededException {
		generateNLF_2();

		// Fail case
		loginHandler.authenticate("admin", "1paSsword!");
		// Now, NLF == 3
	}

	@Test
	public void testTC_01() throws LoginAttemptsExceededException {
		System.out.println("Start Test Case 01");

		boolean isLogin = loginHandler.authenticate("admin", "password");
		assertTrue(isLogin);
	}

	@Test
	public void testTC_02() throws LoginAttemptsExceededException {
		System.out.println("Start Test Case 02");

		boolean isLogin = loginHandler.authenticate("admin", "1paSsword!");
		assertFalse(isLogin);
	}

	@Test
	public void testTC_03() throws LoginAttemptsExceededException {
		System.out.println("Start Test Case 03");

		generateNLF_1();
		boolean isLogin = loginHandler.authenticate("Jeff", "JeffGennari42!");
		assertTrue(isLogin);
	}

	@Test
	public void testTC_04() throws LoginAttemptsExceededException {
		System.out.println("Start Test Case 04");

		generateNLF_1();
		boolean isLogin = loginHandler.authenticate("Jeff", "JffGennari42!");
		assertFalse(isLogin);
	}

	@Test
	public void testTC_05() throws LoginAttemptsExceededException {
		System.out.println("Start Test Case 05");

		generateNLF_2();
		boolean isLogin = loginHandler.authenticate("Jeff", "JeffGennari42!");
		assertTrue(isLogin);
	}

	@Test
	public void testTC_06() throws LoginAttemptsExceededException {
		System.out.println("Start Test Case 06");

		generateNLF_2();
		boolean isLogin = loginHandler.authenticate("Jeff", "JffGennari42!");
		assertFalse(isLogin);
	}

	@Test(expected = LoginAttemptsExceededException.class)
	public void testTC_07() throws LoginAttemptsExceededException {
		System.out.println("Start Test Case 07");

		generateNLF_3();
		boolean isLogin = loginHandler.authenticate("Jeff", "JeffGennari42!");
		assertFalse(isLogin);
	}

	@Test(expected = LoginAttemptsExceededException.class)
	public void testTC_08() throws LoginAttemptsExceededException {
		System.out.println("Start Test Case 08");

		generateNLF_3();
		boolean isLogin = loginHandler.authenticate("Jeff", "JffGennari42!");
		assertFalse(isLogin);
	}

}
