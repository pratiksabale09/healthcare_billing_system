package SQLprovider;
import connection.DBConnection;
import models.BillingRecords;
import models.Patient;
import usageModels.BillSegment;

public class BillingRecordsProvider extends DBConnection{
    protected  BillSegment showBillById(int patientID) {
        //SQL Query goes here
        return new BillSegment(patientID, null, patientID, null);
    }

    // protected  void delete(BillingRecords billRec) {
    //      //SQL Query goes here
    // }

    // protected  void update(BillingRecords billRec) {
    //      //SQL Query goes here
    // }
}
