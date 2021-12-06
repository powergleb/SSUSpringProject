package hospital.services.interfaces;

import hospital.entities.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPatientService {

    Patient createPatient(Patient patient);

    Patient readPatient(int id);

    Patient updatePatient(Patient patient);

    void deletePatient(int id);

    List<Patient> readAllPatient();

    Patient getPatientByUsername(String username);


}
