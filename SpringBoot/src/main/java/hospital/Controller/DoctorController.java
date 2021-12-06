package hospital.Controller;



import hospital.entities.Appointment;
import hospital.entities.Doctor;
import hospital.entities.Record;
import hospital.entities.help.StatusAppointment;
import hospital.services.interfaces.IAppointmentService;
import hospital.services.interfaces.IDoctorService;
import hospital.services.interfaces.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/doctor")
public class DoctorController {

    @Autowired
    IDoctorService doctorService;

    @Autowired
    IAppointmentService appointmentService;

    @Autowired
    IRecordService recordService;

    @GetMapping(value = "/appointments")
    public ResponseEntity<List<Appointment>> getAppointments(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Doctor doctor = doctorService.getDoctorByUsername(username);
        List<Appointment> appointments = appointmentService.getByDoctorId(doctor.getId());
        return appointments != null && !appointments.isEmpty()
                ? new ResponseEntity<>(appointments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/appointments/update/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Integer id,
                                               @RequestParam(value = "description") String description,
                                               @RequestParam(value = "status") StatusAppointment status){
        Appointment appointment = appointmentService.readAppointment(id);
        appointment.setDescription(description);
        appointment.setStatus(status);
        appointmentService.updateAppointment(appointment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/records")
    public ResponseEntity<List<Record>> getRecords(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Doctor doctor = doctorService.getDoctorByUsername(username);
        List<Record> records = recordService.getRecordsByDoctor(doctor);
        return records!=null && !records.isEmpty()
            ? new ResponseEntity<>(records,HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
