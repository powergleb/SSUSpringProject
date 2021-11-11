package hospital.services.impl;

import hospital.dao.repositories.PatientRepository;
import hospital.model.entities.Patient;
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
    public Patient updatePatient(String id, String parameter, String newValue) throws Exception {
        Patient patient = patientRepository.findOne(Integer.parseInt(id));
        patient.setValue(Patient.class, patient, parameter, newValue);
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(int id) {
         patientRepository.delete(id);
    }

    @Override
    public List<Patient> readAllPatient() {
        return patientRepository.findAll();
    }
}

