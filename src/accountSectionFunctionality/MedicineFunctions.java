package accountSectionFunctionality;

import java.util.ArrayList;

import Common.CommonUtil;
import SQLprovider.MedicineProvider;
import models.Medicine;

public class MedicineFunctions extends MedicineProvider{
    private  void useMedicine() {
        System.out.println("Enter Medicine ID:");
        int treatmentID = CommonUtil.scan.nextInt();
        System.out.println("Enter patient ID:");
        int patientID = CommonUtil.scan.nextInt();
        useMedicine(treatmentID, patientID);
    }
    private void showMedicines() {
        ArrayList<Medicine> allMedicines =  showAllMedicines();
        for(Medicine medicine: allMedicines)
        {
            System.out.println(medicine.toString());
        }
    }

    private void getMedicinesByPatientID()
    {
        System.out.println("Enter patient ID:");
        int patientID = CommonUtil.scan.nextInt();
        ArrayList<Medicine> allMedicines =  showMedicinesByPatientID(patientID);
        for(Medicine medicine: allMedicines)
        {
            System.out.println(medicine.toString());
        }
    }

    public  void chooseOperation() {
        System.out.println(
                "Choose an option:\n1. Use Medicine\n2. Show all available medicines\n 3. Show consumed medicines by patient ID");
        int option = CommonUtil.scan.nextInt();
        switch (option) {
            case 1:
                useMedicine();
                break;
            case 2:
                showMedicines();
                break;
            case 3:
                getMedicinesByPatientID();
                break;
            default:
                break;
        }
    }
    
}
