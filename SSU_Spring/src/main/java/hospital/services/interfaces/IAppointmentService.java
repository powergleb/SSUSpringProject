package hospital.services.interfaces;

import hospital.model.entities.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IAppointmentService {

    Appointment createAppointment(Appointment appointment);

    Appointment readAppointment(int id);

    Appointment updateAppointment(String id, String parameter, String newValue) throws Exception;

    void deleteAppointment(int id);

    List<Appointment> readAllAppointment();

}
