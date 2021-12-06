package hospital.services.impl;

import hospital.repositories.DoctorRepository;
import hospital.entities.Doctor;
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
    public Doctor updateDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(int id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public List<Doctor> readAllDoctor() {
        return doctorRepository.findAll();
    }
    @Override
    public Doctor getDoctorByUsername(String username) {
        return  doctorRepository.findAll().stream().filter(d->d.getLogin().equals(username)).findFirst().orElse(null);
    }

}
