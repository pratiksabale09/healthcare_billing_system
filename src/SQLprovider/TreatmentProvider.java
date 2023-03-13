package SQLprovider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import common.CommonProvider;
import connection.DBConnection;
import models.Doctor;
import models.Treatment;
import usageModels.TreatmentUsage;

public class TreatmentProvider extends DBConnection {

    public int isValidDoctor(int doctorId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int doctorCount = 0;
        try {
            String checkDoctor = "SELECT COUNT(DOCTOR_ID) FROM DOCTOR_DETAILS WHERE DOCTOR_ID = ?";
            preparedStatement = getConnection().prepareStatement(checkDoctor);
            preparedStatement.setInt(1, doctorId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            doctorCount = resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                closeConnection();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return doctorCount;
    }

    public int isValidTreatment(int treatmentId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int treatmentCount = 0;
        try {
            String checkTreatment = "SELECT COUNT(TREATMENT_ID) FROM TREATMENT_SERVICES_DETAILS WHERE TREATMENT_ID = ?";
            preparedStatement = getConnection().prepareStatement(checkTreatment);
            preparedStatement.setInt(1, treatmentId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            treatmentCount = resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                closeConnection();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return treatmentCount;
    }

    protected int useTreatment(int treatmentID, int patientID, int doctorID) {
        int resultCode = 0;
        if (CommonProvider.isValidPatient(patientID) <= 0) {
            return resultCode;
        }
        if (isValidTreatment(treatmentID) <= 0) {
            resultCode = 1;
            return resultCode;
        }
        if (isValidDoctor(doctorID) <= 0) {
            resultCode = 2;
            return resultCode;
        }
        PreparedStatement preparedStatement = null;
        try {
            String insertQuery = "INSERT INTO TREATMENT_SERVICES_USAGE (TREATMENT_ID, PATIENT_ID, DOCTOR_ID, U_DATE) VALUES (?, ?, ?, ?)";
            preparedStatement = getConnection().prepareStatement(insertQuery);
            preparedStatement.setInt(1, treatmentID);
            preparedStatement.setInt(2, patientID);
            preparedStatement.setInt(3, doctorID);
            preparedStatement.setDate(4, new java.sql.Date(new Date().getTime()));
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                resultCode = 3;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
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
        return resultCode;
    }

    protected ArrayList<Treatment> getAllTreatments() {
        ArrayList<Treatment> treatmentList = new ArrayList<Treatment>();
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            String sql = "SELECT * FROM TREATMENT_SERVICES_DETAILS";
            preparedStatement = getConnection().prepareStatement(sql);
            result = preparedStatement.executeQuery();
            Treatment treatment = null;
            while (result.next()) {
                treatment = new Treatment();
                treatment.setTreatmentId(result.getInt("TREATMENT_ID"));
                treatment.setTreatmentName(result.getString("TREATMENT_NAME"));
                treatment.setTreatmentCharge(result.getDouble("TREATMENT_CHARGE"));
                treatmentList.add(treatment);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
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
        return treatmentList;
    }

    protected ArrayList<TreatmentUsage> showTreatmentsByPatientID(int patientID) {

        ArrayList<TreatmentUsage> TreatmentUsage = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            sb.append("TSU.TREATMENT_USAGE_ID, TSU.TREATMENT_ID, TSU.PATIENT_ID, TSU.DOCTOR_ID, TSU.U_DATE, ");
            sb.append("TSD.TREATMENT_NAME, TSD.TREATMENT_CHARGE, ");
            sb.append("DD.DOCTOR_NAME, DD.CONSULTATION_CHARGE,DD.QUALIFICATION, DD.SPECIALTY ");
            sb.append("FROM TREATMENT_SERVICES_USAGE TSU ");
            sb.append("INNER JOIN TREATMENT_SERVICES_DETAILS TSD ");
            sb.append("ON TSU.TREATMENT_ID = TSD.TREATMENT_ID ");
            sb.append("INNER JOIN DOCTOR_DETAILS DD ");
            sb.append("ON TSU.DOCTOR_ID = DD.DOCTOR_ID ");
            sb.append("WHERE TSU.PATIENT_ID = ?");
            String sql = sb.toString();
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, patientID);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                int treatmentDetailsId = result.getInt("TREATMENT_USAGE_ID");
                int patientId = result.getInt("PATIENT_ID");
                int doctorId = result.getInt("DOCTOR_ID");
                String doctorName = result.getString("DOCTOR_NAME");
                String spec = result.getString("SPECIALTY");
                String qual = result.getString("QUALIFICATION");
                Date date = result.getDate("U_DATE");
                int treatmentId = result.getInt("TREATMENT_ID");
                String treatmentName = result.getString("TREATMENT_NAME");
                double treatmentCharge = result.getDouble("TREATMENT_CHARGE");
                double consultationCharge = result.getDouble("CONSULTATION_CHARGE");

                Doctor doctor = new Doctor(doctorId, doctorName, qual, spec, consultationCharge);
                Treatment treatment = new Treatment(treatmentId, treatmentName, treatmentCharge);

                TreatmentUsage treatmentUsage = new TreatmentUsage(treatmentDetailsId, patientId, doctor, date,
                        treatment.getTreatmentId(), treatment.getTreatmentName(), treatmentCharge);
                TreatmentUsage.add(treatmentUsage);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
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
        return TreatmentUsage;
    }

    protected ArrayList<Doctor> showAvailableDoctors() {
        ArrayList<Doctor> doctorList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            String sql = "SELECT * FROM DOCTOR_DETAILS ORDER BY DOCTOR_ID ASC";
            preparedStatement = getConnection().prepareStatement(sql);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                Doctor doctor = new Doctor(
                        result.getInt("DOCTOR_ID"),
                        result.getString("DOCTOR_NAME"),
                        result.getString("QUALIFICATION"),
                        result.getString("SPECIALTY"),
                        result.getDouble("CONSULTATION_CHARGE"));
                doctorList.add(doctor);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
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
        return doctorList;
    }

}
