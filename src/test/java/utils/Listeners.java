package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result){
        CommonMethods.takeScreenShot("passed/"+result.getName());
    }
    @Override
    public void onTestFailure(ITestResult result){
        CommonMethods.takeScreenShot("failed/"+result.getName());
    }
}
