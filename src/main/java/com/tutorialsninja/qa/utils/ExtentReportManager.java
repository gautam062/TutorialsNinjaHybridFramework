package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager{
	
	public static ExtentReports generateExtentReport() {
		
		/*
		 * Date date = new Date(); String timestamp = date.toString().replace(" ",
		 * "_").replace(":", "_");
		 */
		
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsNinja Test Automation Results Report");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties configProp = new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		try {
			FileInputStream fisConfigProp = new FileInputStream(configPropFile);
			configProp.load(fisConfigProp);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application", "Tutorials Ninja");
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browser"));
		extentReport.setSystemInfo("Application URL",configProp.getProperty("url"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Environment", "QA");
		extentReport.setSystemInfo("Username", "Gautam Kumar");
		
		return extentReport;
	}
	
}
