package accountSectionFunctionality;

import java.util.ArrayList;

import SQLprovider.MedicalTestProvider;
import common.CommonUtil;
import models.MedicalTest;
import usageModels.MedicalTestConducted;

public class MedTestFunctions extends MedicalTestProvider {
    private void conductMedicalTest() {
        // tested
        System.out.println("Enter medical test ID:");
        int medTestID = CommonUtil.scan.nextInt();
        System.out.println("Enter patientID:");
        int patientID = CommonUtil.scan.nextInt();
        int statusCode = conductMedicalTest(medTestID, patientID);
        if (statusCode == 0) {
            System.out.println("\nInvalid patientId\n");
        } else if (statusCode == 1) {
            System.out.println("\nInvalid Medical test Id\n");
        } else {
            System.out.println("\nMedical test Added SucessFully\n");
        }
    }

    private void viewMedicalTests() {
        ArrayList<MedicalTest> medicalTests = getAllMedicalTests();
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.printf("| %-20s | %-20s | %-36s |\n", "Medical Test ID", "Test Charge", "Test Name");
        System.out.println("+------------------------------------------------------------------------------------+");
        for (MedicalTest mt : medicalTests) {
            System.out.printf("| %-20d | %-20.2f | %-36s |\n", mt.getMedicalTestId(), mt.getTestCharge(),
                    mt.getTestName());
        }
        System.out.println("+------------------------------------------------------------------------------------+");
    }

    private void viewConductedTests() {

        System.out.println("Enter patient ID to view medical tests:");
        int patientID = CommonUtil.scan.nextInt();
        ArrayList<MedicalTestConducted> allConductedTests = viewAllConductedTests(patientID);
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-10s | %-36s | %-10s | %-10s | %-10s | %-10s |\n",
                "Test ID", "Test Name", "Charge", "ID", "Patient ID", "Date");
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------+");
        for (MedicalTestConducted medicalTest : allConductedTests) {
            System.out.printf("| %-10d | %-36s | %-10.2f | %-10d | %-10d | %-10s |\n",
                    medicalTest.getMedicalTestId(), medicalTest.getTestName(), medicalTest.getTestCharge(),
                    medicalTest.getTestConductedId(), medicalTest.getPatientId(), medicalTest.getDate());
        }
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------+");
    }

    public void chooseOperation() {
        System.out.println( 
                "Choose an option:\n1. Conduct Medical Test\n2. View All Available Medical Tests\n3. View Medical Tests Used by patient ID");
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
