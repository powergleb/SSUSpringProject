package hospital.services.interfaces;

import hospital.entities.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDoctorService {

    Doctor createDoctor(Doctor doctor);

    public Doctor readDoctor(int id) ;

    public Doctor updateDoctor(Doctor doctor);

    public void deleteDoctor(int id) ;

    public List<Doctor> readAllDoctor() ;

    public Doctor getDoctorByUsername(String username);

}
