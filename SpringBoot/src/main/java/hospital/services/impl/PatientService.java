package hospital.services.impl;

import hospital.repositories.PatientRepository;
import hospital.entities.Patient;
import hospital.services.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientService implements IPatientService {


    @Autowired
    public PatientRepository patientRepository;

    public PatientService() { }

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient readPatient(int id) {
        return patientRepository.getOne(id);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(int id) {
         patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> readAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientByUsername(String username) {
        return patientRepository.findAll().stream().filter(c->c.getLogin().equals(username)).findFirst().orElse(null);
    }
}

