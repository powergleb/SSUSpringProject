package hospital.dao.interfaces;

import hospital.model.entities.Appointment;

public interface IAppointmentDAO {
    boolean createAppointment(Appointment appointment);

    Appointment readAppointment(int id);

    boolean updateAppointment(Appointment appointment);

    boolean deleteAppointment(int id);

    Appointment findAppointmentById(int id);
}
