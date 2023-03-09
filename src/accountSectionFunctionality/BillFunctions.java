package accountSectionFunctionality;

import Common.CommonUtil;

public class BillFunctions {
    private static void generateBill() {

    }

    private static void viewPatients() {

    }

    private static void editPatient() {

    }

    private static void removePatient() {

    }

    public void chooseOperation() {
        System.out.println(
                "Choose an option: 1. Generate Bill    2. getBillReceipt(); ");
        int option = CommonUtil.scan.nextInt();
        switch (option) {
            case 1:
                generateBill();
                break;
            case 2:
                viewPatients();
                break;
            case 3:
                editPatient();
                break;
            case 4:
                removePatient();
                break;
            case 5:
                viewPatients();
            default:
                break;
        }
    }


}
