package edu.cmu.iot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BlackBoxTest_UserCase_12 {
	private static final String settingsDir = "D:\\tmp";
	private LoginHandler loginHandler;
	private IoTControlManager controller;
//	private IoTControlWindow controlWin;
	
	@Before
	public void setup() {
		controller = new IoTControlManager(null, settingsDir);
	}

	@After
	public void tearDown() {
		controller = null;
	}
	
	@Test
	public void testTC_01() throws LoginAttemptsExceededException {
		System.out.println("Start UC12 Test Case 01");

		controller.addUser("uc1201", "8abC!ef");
		
		Vector<UserLoginInfo> users = controller.loadUsers();
		loginHandler = new LoginHandler(users);
		
		boolean isLogin = loginHandler.authenticate("uc1201", "8abC!ef");
		
		assertFalse(isLogin);
	}
	
	@Test
	public void testTC_02() throws LoginAttemptsExceededException {
		System.out.println("Start UC12 Test Case 02");

		controller.addUser("uc1202", "zabcef7");
		
		Vector<UserLoginInfo> users = controller.loadUsers();
		loginHandler = new LoginHandler(users);
		
		boolean isLogin = loginHandler.authenticate("uc1202", "zabcef7");
		
		assertFalse(isLogin);
	}
	
	@Test
	public void testTC_03() throws LoginAttemptsExceededException {
		System.out.println("Start UC12 Test Case 03");

		controller.addUser("uc1203", "4abcdefG");
		
		Vector<UserLoginInfo> users = controller.loadUsers();
		loginHandler = new LoginHandler(users);
		
		boolean isLogin = loginHandler.authenticate("uc1203", "4abcdefG");
		
		assertFalse(isLogin);
	}
	
	@Test
	public void testTC_04() throws LoginAttemptsExceededException {
		System.out.println("Start UC12 Test Case 04");

		controller.addUser("uc1204", "!abcd7ef");
		
		Vector<UserLoginInfo> users = controller.loadUsers();
		loginHandler = new LoginHandler(users);
		
		boolean isLogin = loginHandler.authenticate("uc1204", "!abcd7ef");
		
		assertFalse(isLogin);
	}
	
	@Test
	public void testTC_05() throws LoginAttemptsExceededException {
		System.out.println("Start UC12 Test Case 05");

		controller.addUser("uc1205", "Abc!def8");
		
		Vector<UserLoginInfo> users = controller.loadUsers();
		loginHandler = new LoginHandler(users);
		
		boolean isLogin = loginHandler.authenticate("uc1205", "Abc!def8");
		
		assertTrue(isLogin);
	}
	
	@Test
	public void testTC_06() throws LoginAttemptsExceededException {
		System.out.println("Start UC12 Test Case 06");

		controller.addUser("uc1206", "abcDefg@");
		
		Vector<UserLoginInfo> users = controller.loadUsers();
		loginHandler = new LoginHandler(users);
		
		boolean isLogin = loginHandler.authenticate("uc1206", "abcDefg@");
		
		assertFalse(isLogin);
	}
	
	@Test
	public void testTC_07() throws LoginAttemptsExceededException {
		System.out.println("Start UC12 Test Case 07");

		controller.addUser("uc1207", "9abcdefg@");
		
		Vector<UserLoginInfo> users = controller.loadUsers();
		loginHandler = new LoginHandler(users);
		
		boolean isLogin = loginHandler.authenticate("uc1207", "9abcdefg@");
		
		assertFalse(isLogin);
	}
	
	@Test
	public void testTC_08() throws LoginAttemptsExceededException {
		System.out.println("Start UC12 Test Case 08");

		controller.addUser("uc1208", "Abcd@efgh");
		
		Vector<UserLoginInfo> users = controller.loadUsers();
		loginHandler = new LoginHandler(users);
		
		boolean isLogin = loginHandler.authenticate("uc1208", "Abcd@efgh");
		
		assertFalse(isLogin);
	}
	
	@Test
	public void testTC_09() throws LoginAttemptsExceededException {
		System.out.println("Start UC12 Test Case 09");

		controller.addUser("uc1209", "@Abcdefg9");
		
		Vector<UserLoginInfo> users = controller.loadUsers();
		loginHandler = new LoginHandler(users);
		
		boolean isLogin = loginHandler.authenticate("uc1209", "@Abcdefg9");
		
		assertTrue(isLogin);
	}
	
	@Test
	public void testTC_10() throws LoginAttemptsExceededException {
		System.out.println("Start UC12 Test Case 10");

		controller.addUser("uc1210", "ab@cdefgH");
		
		Vector<UserLoginInfo> users = controller.loadUsers();
		loginHandler = new LoginHandler(users);
		
		boolean isLogin = loginHandler.authenticate("uc1210", "ab@cdefgH");
		
		assertFalse(isLogin);
	}
	
	@Test
	public void testTC_11() throws LoginAttemptsExceededException {
		System.out.println("Start UC12 Test Case 11");

		controller.addUser("uc1211", "ab@c7de");
		
		Vector<UserLoginInfo> users = controller.loadUsers();
		loginHandler = new LoginHandler(users);
		
		boolean isLogin = loginHandler.authenticate("uc1211", "ab@c7de");
		
		assertFalse(isLogin);
	}
	
	@Test
	public void testTC_12() throws LoginAttemptsExceededException {
		System.out.println("Start UC12 Test Case 12");

		controller.addUser("uc1212", "Abcdefghi");
		
		Vector<UserLoginInfo> users = controller.loadUsers();
		loginHandler = new LoginHandler(users);
		
		boolean isLogin = loginHandler.authenticate("uc1212", "Abcdefghi");
		
		assertFalse(isLogin);
	}

	@Test
	public void testTC_13() throws LoginAttemptsExceededException {
		System.out.println("Start UC12 Test Case 13");

		controller.addUser("uc1213", "");
		
		Vector<UserLoginInfo> users = controller.loadUsers();
		loginHandler = new LoginHandler(users);
		
		boolean isLogin = loginHandler.authenticate("uc1213", "");
		
		assertFalse(isLogin);
	}
}
