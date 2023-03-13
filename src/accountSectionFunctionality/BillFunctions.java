package accountSectionFunctionality;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import Common.CommonProvider;
import Common.CommonUtil;
import SQLprovider.BillingRecordsProvider;
import SQLprovider.PatientProvider;
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
                printWriter.printf("Patient ID:\t\t\t%d\n", currPatient.getPatientId());
                printWriter.printf("Patient Name:\t\t%s\n",
                        currPatient.getFirstName() + " " + currPatient.getLastName());
                printWriter.printf("Bill Date:\t\t\t%s\n", currDate);
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

    public void chooseOperation() {
        System.out.println(
                "Choose an option: 1. Generate Bill ");
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
