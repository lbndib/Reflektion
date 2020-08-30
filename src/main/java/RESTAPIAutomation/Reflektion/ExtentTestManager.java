package RESTAPIAutomation.Reflektion;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.*;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
	static Map extentTestMap = new HashMap();
    static ExtentReports extent = ExtentManager.getReporter();
 
    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }
 
    public static synchronized void endTest() {
        //extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    	extent.flush();
    }
 
    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }

}
