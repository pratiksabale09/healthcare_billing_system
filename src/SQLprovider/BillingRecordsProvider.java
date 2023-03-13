package SQLprovider;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import accountSectionFunctionality.EquipmentFunctions;
import accountSectionFunctionality.MedTestFunctions;
import accountSectionFunctionality.MedicineFunctions;
import accountSectionFunctionality.RoomFunctions;
import accountSectionFunctionality.TreatmentFunctions;
import connection.DBConnection;
import usageModels.EquipmentUsage;
import usageModels.MedicalTestConducted;
import usageModels.MedicineUsage;
import usageModels.RoomUsage;
import usageModels.TreatmentUsage;
public class BillingRecordsProvider extends DBConnection {
    protected Map<String, Float> generateBillInSegments(int patientId) {
        LinkedHashMap<String, Float> billSegmensts = new LinkedHashMap<>();
        float Charge = generateEquipmentBill(patientId);
        if (Charge > 0) {
            billSegmensts.put("Equipment Charge", Charge);
        }
        Charge = generateRoomBill(patientId);
        if (Charge > 0) {
            billSegmensts.put("Room Charge", Charge);
        }
        Charge = generateTreatmentBill(patientId);
        if (Charge > 0) {
            billSegmensts.put("Treatment Charge", Charge);
        }
        Charge = generateMedicaltestBill(patientId);
        if (Charge > 0) {
            billSegmensts.put("medical Test Charge", Charge);
        }
        Charge = generatemedicineBill(patientId);
        if (Charge > 0) {
            billSegmensts.put("Medicine Charge", Charge);
        }
        PreparedStatement preparedStatement = null;
        try {
            Date d = new Date(new java.util.Date().getTime());
            String insertTotalEquipmentCharge = "INSERT INTO BILL_SEGMENT (PATIENT_ID,SEGMENT_NAME,SEGMENT_BILL_AMOUNT, STATUS, BILL_DATE) VALUES(?,?,?,?,?)";
            preparedStatement = getConnection().prepareStatement(insertTotalEquipmentCharge);
            for (Map.Entry<String, Float> e : billSegmensts.entrySet()) {
                preparedStatement.setInt(1, patientId);
                preparedStatement.setString(2, e.getKey());
                preparedStatement.setFloat(3, e.getValue());
                preparedStatement.setInt(4, 0);
                preparedStatement.setDate(5, d);
                preparedStatement.addBatch();
            }
            preparedStatement.executeUpdate();
            System.out.println("Bill segments are generated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                closeConnection();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return billSegmensts;
    }
    private float generateEquipmentBill(int patientId) {
        EquipmentFunctions ef = new EquipmentFunctions();
        ArrayList<EquipmentUsage> l = ef.getAllocatedEquipments(patientId);
        float charge = 0;
        if (l.size() >= 0) {
            for (EquipmentUsage e : l) {
                charge = (float) (charge + e.getUsageCount() * e.getEquipmentChargePerUnit());
            }
        }
        return charge;
    }
    private float generatemedicineBill(int patientId) {
        MedicineFunctions mf = new MedicineFunctions();
        ArrayList<MedicineUsage> l = mf.showMedicinesByPatientID(patientId);
        float charge = 0;
        if (l.size() >= 0) {
            for (MedicineUsage m : l) {
                charge = (float) (charge + m.getMedicineChargePerUnit() * m.getUnits());
            }
        }
        return charge;
    }
    private float generateMedicaltestBill(int patientId) {
        MedTestFunctions mtf = new MedTestFunctions();
        ArrayList<MedicalTestConducted> l = mtf.viewAllConductedTests(patientId);
        float charge = 0;
        if (l.size() >= 0) {
            for (MedicalTestConducted m : l) {
                charge = charge + m.getTestCharge();
            }
        }
        return charge;
    }
    private float generateTreatmentBill(int patientId) {
        TreatmentFunctions tf = new TreatmentFunctions();
        ArrayList<TreatmentUsage> l = tf.showTreatmentsByPatientID(patientId);
        float charge = 0;
        if (l.size() >= 0) {
            for (TreatmentUsage t : l) {
                charge = (float) (charge + t.getTreatmentCharge() + t.getDoctor().getConsultationCharge());
            }
        }
        return charge;
    }
    private float generateRoomBill(int patientId) {
        RoomFunctions rf = new RoomFunctions();
        ArrayList<RoomUsage> l = rf.viewAllocatedRooms(patientId);
        float charge = 0;
        if (l.size() > 0) {
            for (RoomUsage r : l) {
                charge = (float) (charge + r.getDurationInDays() * r.getRoomCharge());
            }
        }
        return charge;
    }
    protected Map<String, Float> getBillById(int patientId) {
        PreparedStatement preparedStatement = null;
        Map<String, Float> billsegments = new LinkedHashMap<>();
        ResultSet result = null;
        try {
            String segmentsSql = "SELECT SEGMENT_NAME, SEGMENT_BILL_AMOUNT FROM BILL_SEGMENT WHERE PATIENT_ID = ? AND STATUS = 0";
            preparedStatement = getConnection().prepareStatement(segmentsSql);
            preparedStatement.setInt(1,patientId);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                billsegments.put(result.getString("SEGMENT_NAME"), result.getFloat("SEGMENT_BILL_AMOUNT"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (result != null) {
                    result.close();
                }
                closeConnection();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        if (billsegments.size() == 0) {
            billsegments = generateBillInSegments(patientId);
        }
        return billsegments;
    }
}