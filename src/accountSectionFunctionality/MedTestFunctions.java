package accountSectionFunctionality;

import java.util.ArrayList;

import Common.CommonUtil;
import SQLprovider.MedicalTestProvider;
import models.MedicalTest;

public class MedTestFunctions extends MedicalTestProvider{
    private  void conductMedicalTest() {
        System.out.println("Enter medical test ID:");
        int medTestID = CommonUtil.scan.nextInt();
        System.out.println("Enter patientID:");
        int patientID = CommonUtil.scan.nextInt();
        conductMedicalTest(medTestID, patientID);
    }

    private  void viewMedicalTests() {
        ArrayList<MedicalTest> allTests = viewAllMedicalTests();
        for(MedicalTest test: allTests)
        {
            System.out.println(test.toString());
        }
    }

    private  void viewConductedTests() {
        System.out.println("Enter patient ID to view medical tests:");
        int patientID = CommonUtil.scan.nextInt();
        ArrayList<MedicalTest> allConductedTests = viewAllConductedTests(patientID);
        for(MedicalTest test: allConductedTests)
        {
            System.out.println(test.toString());
        }
    }

    public  void chooseOperation() {
        System.out.println(
                "Choose an option:\n1. Conduct MedicalTest\n2. View MedicalTest\n 3. View MedicalTests by patient ID");
        int option = CommonUtil.scan.nextInt();
        switch (option) {
            case 1:
                conductMedicalTest();
                break;
            case 2:
                viewMedicalTests();
                break;
            case 3:
                viewConductedTests();
                break;
            default:
                break;
        }
    }
}
