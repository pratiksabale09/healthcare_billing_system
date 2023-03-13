import accountSectionFunctionality.BillFunctions;
import accountSectionFunctionality.EquipmentFunctions;
import accountSectionFunctionality.MedTestFunctions;
import accountSectionFunctionality.MedicineFunctions;
import accountSectionFunctionality.PatientFunctions;
import accountSectionFunctionality.RoomFunctions;
import accountSectionFunctionality.TreatmentFunctions;
import common.CommonUtil;

public class App {
    static void runApp() {
        int bLoop = 1;
        while (bLoop == 1) {
            System.out.println("Please select an option to continue:");
            System.out.println("1. Patient:");
            System.out.println("2. Treatment and Services:");
            System.out.println("3. Medical Test:");
            System.out.println("4. Rooms:");
            System.out.println("5. Equipments:");
            System.out.println("6. Medicines:");
            System.out.println("7. Bills:\n");
            int accountCase = CommonUtil.scan.nextInt();
            switch (accountCase) {
                case 1:
                    PatientFunctions patientFunctions = new PatientFunctions();
                    patientFunctions.chooseOperation();
                    break;
                case 2:
                    TreatmentFunctions treatmentFunctions = new TreatmentFunctions();
                    treatmentFunctions.chooseOperation();
                    break;

                case 3:
                    MedTestFunctions medTestFunctions = new MedTestFunctions();
                    medTestFunctions.chooseOperation();
                    break;

                case 4:
                    RoomFunctions roomFunctions = new RoomFunctions();
                    roomFunctions.chooseOperation();
                    break;

                case 5:
                    EquipmentFunctions equipmentFunctions = new EquipmentFunctions();
                    equipmentFunctions.chooseOperation();
                    break;
                case 6:
                    MedicineFunctions medicineFunctions = new MedicineFunctions();
                    medicineFunctions.chooseOperation();
                    break;
                case 7:
                    BillFunctions billFunctions = new BillFunctions();
                    billFunctions.chooseOperation();
                    break;
                default:
                    break;
            }
            System.out.println("Please Enter 1 to continue in Account Section and  0 to go back to main section\n");
            bLoop = CommonUtil.scan.nextInt();
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Healthcare Billing System");
        runApp();
    }
}
