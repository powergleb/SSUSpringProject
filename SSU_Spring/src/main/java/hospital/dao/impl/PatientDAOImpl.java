package hospital.dao.impl;

import hospital.dao.interfaces.IPatientDAO;
import hospital.model.entities.Patient;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PatientDAOImpl implements IPatientDAO {


    public List<Patient> patientList;
    @Override
    public boolean createPatient(Patient patient) {
        if (patientList.add(patient))
            return true;
        else return false;
    }

    @Override
    public Patient readPatient(int id) {
        Optional<Patient> patients = patientList.stream().filter(el -> el.getId() == id).findFirst();
        return patients.orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean updatePatient(Patient patient) {
        Patient workPatient = readPatient(patient.getId());
        if (workPatient == null) return false;
        else {
            deletePatient(workPatient.getId());
            createPatient(workPatient);
            return true;
        }
    }

    @Override
    public boolean deletePatient(int id) {
        Iterator<Patient> iterator = patientList.iterator();

        while (iterator.hasNext()) {
            Patient patient = iterator.next();
            if (id == patient.getId()) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }


    @Override
    public List<Patient> readAllPatient() {
        return patientList;
    }
}
