package hospital.dao.interfaces;

import hospital.model.entities.Doctor;

import java.util.List;

public interface IDoctorDAO {
    boolean createDoctor(Doctor doctor);

    Doctor readDoctor(int id);

    boolean updateDoctor(Doctor doctor);

    boolean deleteDoctor(int id);

    List<Doctor> readAllDoctor();
}
