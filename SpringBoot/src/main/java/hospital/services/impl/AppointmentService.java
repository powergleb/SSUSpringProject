package hospital.services.impl;

import hospital.repositories.AppointmentRepository;
import hospital.entities.Appointment;
import hospital.entities.Doctor;
import hospital.services.interfaces.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<Appointment> readAllAppointment() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getByPatientId(int id) {
        return appointmentRepository.findAll().stream().filter(a->a.getRecord().getPatient().getId() == id).collect(Collectors.toList());
    }

    @Override
    public List<Appointment> getByDoctorId(int id) {
        return appointmentRepository.findAll().stream().filter(a->a.getRecord().getDoctor().getId() == id).collect(Collectors.toList());
    }

}
