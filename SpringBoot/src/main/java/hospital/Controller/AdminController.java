package hospital.Controller;


import hospital.entities.Appointment;
import hospital.entities.Doctor;
import hospital.entities.Patient;
import hospital.entities.Record;
import hospital.services.interfaces.IAppointmentService;
import hospital.services.interfaces.IDoctorService;
import hospital.services.interfaces.IPatientService;
import hospital.services.interfaces.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    IPatientService patientService;

    @Autowired
    IDoctorService doctorService;

    @Autowired
    IRecordService recordService;

    @Autowired
    IAppointmentService appointmentService;

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getPatients(){
        List<Patient> patients = patientService.readAllPatient();
        List<Patient> clientCopy = new ArrayList<>(patients);
        /*for(Patient patient:clientCopy){
            patient.setPass("");
        }*/
        return patients!=null && !patients.isEmpty()
                ? new ResponseEntity<>(clientCopy,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getDoctors(){
        List<Doctor> doctors = doctorService.readAllDoctor();
        List<Doctor> doctorCopy = new ArrayList<>(doctors);
        /*for(Doctor doctor:doctorCopy){
            doctor.setPass("");
        }*/
        return doctors!=null && !doctors.isEmpty()
                ? new ResponseEntity<>(doctorCopy, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("patients/delete/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Integer id){
        try{
            patientService.deletePatient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("doctors/delete/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Integer id){
        try{
            doctorService.deleteDoctor(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("patients/update/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Integer id,
                                          @RequestParam(value = "name") String name,
                                          @RequestParam(value = "secondName") String secondName,
                                          @RequestParam(value = "Patronymic") String patronymic,
                                          @RequestParam(value = "phone") String phone,
                                          @RequestParam(value = "mail") String mail
                                          ){
        try {
            Patient patient = patientService.readPatient(id);
            patient.setName(name);
            patient.setSecondName(secondName);
            patient.setPatronymic(patronymic);
            patient.setMail(mail);
            patient.setPhone(phone);
            patientService.updatePatient(patient);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("doctors/update/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Integer id,
                                          @RequestParam(value = "Name") String name,
                                          @RequestParam(value = "SecondName") String secondName,
                                          @RequestParam(value = "Patronymic") String patronymic,
                                          @RequestParam(value = "phone") String phone,
                                          @RequestParam(value = "mail") String mail
    ){
        try {
            Doctor doctor = doctorService.readDoctor(id);
            doctor.setName(name);
            doctor.setSecondName(secondName);
            doctor.setPatronymic(patronymic);
            doctor.setMail(mail);
            doctor.setPhone(phone);
            doctorService.updateDoctor(doctor);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("doctors/records/{id}")
    public ResponseEntity<List<Record>> getRecordsByDoctor(@PathVariable Integer id){
        Doctor doctor;
        try{
            doctor = doctorService.readDoctor(id);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Record> records = recordService.getRecordsByDoctor(doctor);
        return records!=null && !records.isEmpty()
                ? new ResponseEntity<>(records, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("patients/records/{id}")
    public ResponseEntity<List<Record>> getRecordsByPatient(@PathVariable Integer id){
        Patient patient;
        try{
            patient = patientService.readPatient(id);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Record> records = recordService.getRecordsByPatient(patient);
        return records!=null && !records.isEmpty()
                ? new ResponseEntity<>(records, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("doctors/{id}/appointments")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable Integer id){
        List<Appointment> appointments = appointmentService.getByDoctorId(id);
        return appointments!=null && !appointments.isEmpty()
                ? new ResponseEntity<>(appointments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("clients/{id}/appointments")
    public ResponseEntity<List<Appointment>> getAppointmentsByClient(@PathVariable Integer id){
        List<Appointment> appointments = appointmentService.getByPatientId(id);
        return appointments!=null && !appointments.isEmpty()
                ? new ResponseEntity<>(appointments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }










}
