package hospital.dao.interfaces;

import hospital.model.entities.Patient;

import java.util.List;

public interface IPatientDAO {
    boolean createPatient(Patient patient);

    Patient readPatient(int id);

    boolean updatePatient(Patient patient);

    boolean deletePatient(int id);

    List<Patient> readAllPatient();
}
