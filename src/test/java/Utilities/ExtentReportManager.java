package Utilities;

import TestBase.BaseClass;
import org.testng.ITestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {

        public ExtentSparkReporter sparkreporter;   //UI of the report
        public ExtentReports extent;  //populate common info of the report
        public ExtentTest test;  //creates test case entries in the report and update status of the test method

        String repName;
        public void onStart(ITestContext testcontext) {

          /*  SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
            Date dt=new Date();
            String currentdatetimestamp= df.format(dt);*/

            String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

            repName = "Test-Report-"+timestamp+".html";
            sparkreporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/repName");

            sparkreporter.config().setDocumentTitle("Automation Report");//Title of the report
            sparkreporter.config().setReportName("Functionall Testing"); //name f the report
            sparkreporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(sparkreporter);

            extent.setSystemInfo("Computer Name","HP");
            extent.setSystemInfo("Environement","QA");
            extent.setSystemInfo("Tester Name","SHIVA");
            extent.setSystemInfo("os","Windows 8");
            extent.setSystemInfo("Browser name","Chrome");

            List<String> includedGroups = testcontext.getCurrentXmlTest().getIncludedGroups();
            if(!includedGroups.isEmpty()){
                extent.setSystemInfo("Groups",includedGroups.toString());
            }
        }
        public void onTestSuccess(ITestResult result) {

            test = extent.createTest(result.getTestClass().getName()); //create a new entry in the report
            test.assignCategory(result.getMethod().getAfterGroups());
            test.log(Status.PASS,  result.getName()+"Got Successfully Executed ");  //update status pass or fail

        }
        public void onTestFailure(ITestResult result) {

            test = extent.createTest(result.getTestClass().getName());
            test.assignCategory(result.getMethod().getAfterGroups());

            test.log(Status.FAIL, result.getName()+"Got failed ");
            test.log(Status.INFO,result.getThrowable().getMessage());
            try {
                String imgpath = new BaseClass().capturescreen(result.getName());
                test.addScreenCaptureFromPath(imgpath);
            }catch (IOException e1){
                e1.printStackTrace();
            }
            }


        public void onTestSkipped(ITestResult result) {
            test = extent.createTest(result.getTestClass().getName());
            test.assignCategory(result.getMethod().getGroups());
            test.log(Status.SKIP, result.getName()+"Got Skipped");
            test.log(Status.INFO,result.getThrowable().getMessage());
        }
        public void onFinish(ITestContext context) {

            extent.flush();
            String pathofExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
            File extentReport = new File(pathofExtentReport);

            try{
                Desktop.getDesktop().browse(extentReport.toURI());
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }


