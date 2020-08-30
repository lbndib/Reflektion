package RESTAPIAutomation.Reflektion;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
 
//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.
public class ExtentManager {
 
    
    static String projectPath = "C:\\Users\\laksh\\OneDrive\\Documents\\GitHub\\Reflektion\\test-output\\extent.html";
    
	static ExtentHtmlReporter htmlReporter; 
	static ExtentReports extent; 

 
    public synchronized static ExtentReports getReporter() {
    	htmlReporter = new ExtentHtmlReporter(projectPath);
		extent= new ExtentReports();
		extent.attachReporter(htmlReporter);                     
        
        return extent;
    }
}