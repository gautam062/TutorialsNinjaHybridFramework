package com.tutorialsninja.qa.reports;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener{

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of Project started "+context);
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		System.out.println("Test Name: "+result.getName()+" started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Name: "+result.getName()+" got PASSED");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Name: "+result.getName()+" got FAILED");
		System.out.println(result.getThrowable());
		System.out.println("Take screeshot");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Name: "+result.getName()+" got SKIPPED");
		System.out.println(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Execution of Project Finished "+context);
	}
	

	
	
}
