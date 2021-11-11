package hospital.services.impl;

import hospital.dao.repositories.DoctorRepository;
import hospital.model.entities.Doctor;
import hospital.services.interfaces.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DoctorService implements IDoctorService {
    @Autowired
    public DoctorRepository doctorRepository;

    public DoctorService() { }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor readDoctor(int id) {
        return doctorRepository.getOne(id);
    }

    @Override
    public Doctor updateDoctor(String id, String parameter, String newValue) throws Exception {
        Doctor doctor = doctorRepository.findOne(Integer.parseInt(id));
        doctor.setValue(Doctor.class, doctor, parameter, newValue);
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(int id) {
        doctorRepository.delete(id);
    }

    @Override
    public List<Doctor> readAllDoctor() {
        return doctorRepository.findAll();
    }

}
