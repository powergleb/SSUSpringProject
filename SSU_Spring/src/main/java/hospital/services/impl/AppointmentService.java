package hospital.services.impl;

import hospital.dao.repositories.AppointmentRepository;
import hospital.model.entities.Appointment;
import hospital.model.entities.Doctor;
import hospital.services.interfaces.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppointmentService implements IAppointmentService {

    @Autowired
    public AppointmentRepository appointmentRepository;

    public AppointmentService() { }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment readAppointment(int id) {
        return appointmentRepository.getOne(id);
    }

    @Override
    public Appointment updateAppointment(String id, String parameter, String newValue) throws Exception {
        Appointment appointment = appointmentRepository.findOne(Integer.parseInt(id));
        appointment.setValue(Doctor.class, appointment, parameter, newValue);
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(int id) {
        appointmentRepository.delete(id);
    }

    @Override
    public List<Appointment> readAllAppointment() {
        return appointmentRepository.findAll();
    }
}
