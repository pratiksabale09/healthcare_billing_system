import Common.CommonUtil;
import accountSectionFunctionality.BillFunctions;
import accountSectionFunctionality.EquipmentFunctions;
import accountSectionFunctionality.MedTestFunctions;
import accountSectionFunctionality.PatientFunctions;
import accountSectionFunctionality.RoomFunctions;
import accountSectionFunctionality.TreatmentFunctions;

public class App {
    static void accountSection() {
        int bLoop = 1;
        while (bLoop == 1) {
            System.out.println("Please select an option to continue:");
            System.out.println("1. Patient:");
            System.out.println("2. Treatment and Services:");
            System.out.println("3. Medical Test:");
            System.out.println("4. Rooms:");
            System.out.println("5. Equipments:");
            System.out.println("6. Bills:\n");
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

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the Healthcare Billing System");
        int aLoop = 1;
        while (aLoop == 1) {
            System.out.println("Please select your Role to continue:");
            System.out.println("1. Admin Section \n2. Account Section\n");
            int a = CommonUtil.scan.nextInt();
            switch (a) {
                case 1:
                
                    break;

                case 2:
                    accountSection();
                    break;
                default:
                    System.out.println("Choose correct option!");
            }
            System.out.println("Press 1 to continue, 0 to exit Application");
            aLoop = CommonUtil.scan.nextInt();
        }
    }
}
