package edu.cmu.iot;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class BlackBoxTestRunnerJUnit {

	public static void main(String[] args) {
		// Load and run the tests defined in the BlackBoxTest_UserCase_01 class
		Result result = JUnitCore.runClasses(BlackBoxTest_UserCase_01.class);

		// Report the failures and successses
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		// Load and run the tests defined in the BlackBoxTest_UserCase_01 class
		result = JUnitCore.runClasses(BlackBoxTest_UserCase_02.class);

		// Report the failures and successses
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		result = JUnitCore.runClasses(BlackBoxTest_UserCase_07.class);

		// Report the failures and successses
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		
		result = JUnitCore.runClasses(BlackBoxTest_UserCase_12.class);

		// Report the failures and successses
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}

}
