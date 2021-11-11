package hospital.services.interfaces;

import hospital.model.entities.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IPatientService {

    Patient createPatient(Patient patient);

    Patient readPatient(int id);

    Patient updatePatient(String id, String parameter, String newValue) throws Exception;

    void deletePatient(int id);

    List<Patient> readAllPatient();

}
