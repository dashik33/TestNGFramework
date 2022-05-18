package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeTest extends CommonMethods {

    @Test
    //adding a single employee
    public void addEmployee() {
        login.loginMethod(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));
        click(employeeSearch.pimOption);
        click(employeeSearch.addEmployeeOption);
        sendText(addEmployee.firstname, "Elon");
        sendText(addEmployee.middlename, "Gavrilovich");
        sendText(addEmployee.lastname, "Musk");
        String emplID = addEmployee.employeeID.getAttribute("value");
        click(addEmployee.saveBtn);

        click((employeeSearch.pimOption));
        click(employeeSearch.empListOption);
        sendText(employeeSearch.searchID, emplID);
        click(employeeSearch.searchBtn);

        List<WebElement> rowData = addEmployee.rowData;
        for (WebElement data : rowData) {
            String actualID = data.getText();
            System.out.println(emplID + " " + actualID);
            Assert.assertEquals(emplID, actualID);
        }

    }

    @Test
    //adding employees from Excel file
    public void addMultipleEmployeesFromExcel() throws InterruptedException {
        login.loginMethod(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));
        click(employeeSearch.pimOption);
        click(employeeSearch.addEmployeeOption);

        List<Map<String, String>> employeeNames = ExcelReader.excelIntoMap(Constants.TESTDATA_FILEPATH, "EmployeeData");
        Iterator<Map<String, String>> itr = employeeNames.iterator();
        while (itr.hasNext()) {
            //it returns a key and value for an employee
            Map<String, String> mapNewEmp = itr.next();
            //filling up all the fields with data coming from excel file
            sendText(addEmployee.firstname, mapNewEmp.get("FirstName"));
            sendText(addEmployee.middlename, mapNewEmp.get("MiddleName"));
            sendText(addEmployee.lastname, mapNewEmp.get("LastName"));

            //it will fetch the employee id from attribute
            String empIDValue = addEmployee.employeeID.getAttribute("value");

            //uploading the photo
            sendText(addEmployee.uploadPhoto, mapNewEmp.get("Photograph"));

            //clicking on a checkbox
            if (!addEmployee.checkbox.isSelected()) {
                click(addEmployee.checkbox);
            }

            //filling up other fields
            sendText(addEmployee.createUsername, mapNewEmp.get("Username"));
            sendText(addEmployee.createPassword, mapNewEmp.get("Password"));
            sendText(addEmployee.confirmPassword, mapNewEmp.get("Password"));

            click(addEmployee.saveBtn);

            Thread.sleep(2000);
            //verifying the employee
            click(employeeSearch.empListOption);
            sendText(employeeSearch.searchID, empIDValue);
            click(employeeSearch.searchBtn);

            List<WebElement> rowData = addEmployee.rowData;
            for (WebElement data : rowData) {
                String actualID = data.getText();
                Assert.assertEquals(actualID, empIDValue);
            }
            click(employeeSearch.addEmployeeOption);


        }
    }
}