package accountSectionFunctionality;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.io.FileReader;
import java.io.IOException;

import SQLprovider.BillingRecordsProvider;
import SQLprovider.PatientProvider;
import common.CommonProvider;
import common.CommonUtil;
import models.Patient;

public class BillFunctions extends BillingRecordsProvider {

    private void generateBill() {
        System.out.println("Enter patient ID to generate bill for: ");
        int patientID = CommonUtil.scan.nextInt();
        if (CommonProvider.isValidPatient(patientID) > 0) {
            Map<String, Float> bill = getBillById(patientID);
            File file = null;
            PrintWriter printWriter = null;
            String fileName = Integer.toString(patientID);
            PatientProvider patientProvider = new PatientProvider();
            Patient currPatient = patientProvider.getPatientById(patientID);
            fileName = fileName + "_" + currPatient.getFirstName() + "_" + currPatient.getLastName();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String currDate = formatter.format(date);

            try {
                file = new File(CommonUtil.filePath + fileName + ".txt");
                printWriter = new PrintWriter(file);
                printWriter.print("----------------------------------------\n");
                printWriter.printf("%-25s %10d\n", "Patient ID:", currPatient.getPatientId());
                printWriter.printf("%-25s %10s\n", "Patient Name:",
                        currPatient.getFirstName() + " " + currPatient.getLastName());
                printWriter.printf("%-17s %10s\n", "Bill Date:", currDate);
                printWriter.write("----------------------------------------\n");
                printWriter.write("Segment Name            Segment Amount\n");
                float totalAmount = 0;
                for (Map.Entry<String, Float> billSegment : bill.entrySet()) {
                    String segmentName = billSegment.getKey();
                    float amount = billSegment.getValue();
                    printWriter.printf("%-25s %10.2f\n", segmentName, amount);
                    totalAmount = totalAmount + amount;
                }
                printWriter.print("----------------------------------------\n");
                printWriter.printf("%-25s %10.2f\n", "Total Amount:", totalAmount);
                printWriter.print("----------------------------------------\n");
                printWriter.flush();
                System.out.println("Bill Generated Successfully, please check the bills folder!");
                readAndPrintFile(file);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    printWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                CommonUtil.convertTextToPDF(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Not valid Patient!");
        }
    }

    public void readAndPrintFile(File filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void chooseOperation() {
        System.out.println(
                "Choose an option: \n1. Generate Bill ");
        int option = CommonUtil.scan.nextInt();
        switch (option) {
            case 1:
                generateBill();
                break;

            default:
                break;
        }
    }
}