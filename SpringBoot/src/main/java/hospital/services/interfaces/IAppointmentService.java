package hospital.services.interfaces;

import hospital.entities.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAppointmentService {

    Appointment createAppointment(Appointment appointment);

    Appointment readAppointment(int id);

    Appointment updateAppointment(Appointment appointment);

    void deleteAppointment(int id);

    List<Appointment> readAllAppointment();

    List<Appointment> getByPatientId(int id);

    List<Appointment> getByDoctorId(int id);

}
