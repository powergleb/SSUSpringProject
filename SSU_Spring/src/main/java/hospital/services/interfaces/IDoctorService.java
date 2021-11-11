package hospital.services.interfaces;

import hospital.model.entities.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IDoctorService {

    Doctor createDoctor(Doctor doctor);

    public Doctor readDoctor(int id) ;

    public Doctor updateDoctor(String id, String parameter, String newValue) throws Exception;

    public void deleteDoctor(int id) ;

    public List<Doctor> readAllDoctor() ;

}
