package accountSectionFunctionality;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import Common.CommonProvider;
import Common.CommonUtil;
import SQLprovider.BillingRecordsProvider;
import SQLprovider.PatientProvider;
import models.Patient;

public class BillFunctions extends BillingRecordsProvider{
    private void generateBill() {
        System.out.println("Enter patient ID to generate bill for: ");
        int patientID = CommonUtil.scan.nextInt();
        
        if(CommonProvider.isValidPatient(patientID)>0)
        {
            Map<String, Float> bill = getBillById(patientID);
            File file;
            FileWriter filewriter = null;
            String fileName = Integer.toString(patientID);
            PatientProvider patientProvider = new PatientProvider();
            Patient currPatient = patientProvider.getPatientById(patientID);
            fileName = fileName+"_"+currPatient.getFirstName()+"_"+currPatient.getLastName();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
            Date date = new Date();  
            String currDate = formatter.format(date);
            try {
                file = new File("C:\\Java\\healthcare_billing_system\\healthcare_billing_system\\bills\\"+fileName+".txt");
                filewriter = new FileWriter(file);
                filewriter.write("Patient ID:                "+currPatient.getPatientId()+"\n");
                filewriter.write("Patient Name:              "+currPatient.getFirstName()+" "+currPatient.getLastName()+"\n");
                filewriter.write("Bill Date:                 "+currDate+"\n");
                filewriter.write("----------------------------------------\n");
                filewriter.write("Segment Name            Segment Amount\n");
                float totalAmount = 0;
                for(Map.Entry<String, Float>  billSegment: bill.entrySet())
                {
                    String segmentName = billSegment.getKey();
                    float amount = billSegment.getValue();
                    filewriter.write(segmentName+"           "+amount+"\n");
                    totalAmount = totalAmount + amount;
                }
                filewriter.write("----------------------------------------\n");
                filewriter.write("Total Amount:              "+totalAmount+"\n");
                filewriter.write("----------------------------------------\n");
                filewriter.flush();
                System.out.println("Bill Generated Successfully, please check the bills folder!");
                
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                try {
                    filewriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
       else
       {
        System.out.println("Not valid Patient!");
       }
    }

    // private static void getBillReceipt() {

    // }

    public void chooseOperation() {
        System.out.println(
                "Choose an option: 1. Generate Bill ");
        int option = CommonUtil.scan.nextInt();
        switch (option) {
            case 1:
                generateBill();
                break;
            // case 2:
            //     getBillReceipt();
            //     break;
            default:
                break;
        }
    }


}
