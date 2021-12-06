package hospital.Controller;


import hospital.entities.Appointment;
import hospital.entities.Doctor;
import hospital.entities.Patient;
import hospital.entities.Record;
import hospital.entities.help.Specialties;
import hospital.entities.help.StatusAppointment;
import hospital.services.interfaces.IAppointmentService;
import hospital.services.interfaces.IDoctorService;
import hospital.services.interfaces.IPatientService;
import hospital.services.interfaces.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/client")
public class PatientController {

    @Autowired
    IDoctorService doctorService;

    @Autowired
    IRecordService recordService;

    @Autowired
    IPatientService patientService;

    @Autowired
    IAppointmentService appointmentService;

    @GetMapping(value = "/doctors")
    public ResponseEntity<List<Doctor>> getDoctors(){
        List<Doctor> doctors = doctorService.readAllDoctor();
        List<Doctor> doctorCopy = new ArrayList<>(doctors);
        for(Doctor doctor:doctorCopy){
            doctor.setPass("");
        }
        return !doctors.isEmpty()
                ? new ResponseEntity<>(doctorCopy, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/records")
    public ResponseEntity<List<Record>> getRecords(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Patient patient = patientService.getPatientByUsername(username);
        List<Record> records = recordService.getRecordsByPatient(patient);
        return records != null && !records.isEmpty()
                ? new ResponseEntity<>(records, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping(value = "/addRecord")
    public ResponseEntity<?> addRecord(@RequestParam(value="doctorId") Integer doctorId,
                                      @RequestParam(value= "gap") String gap,
                                      @RequestParam(value="patientId") Integer patientId) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date parsed = df.parse(gap);
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(parsed);
        Record record = new Record();
        record.setDate(date);
        record.setPatient(patientService.readPatient(patientId));
        record.setDoctor(doctorService.readDoctor(doctorId));
        Appointment appointment = new Appointment();
        appointment.setDescription("");
        appointment.setRecord(record);
        appointment.setStatus(StatusAppointment.inProgress);
        Appointment addedAppointment = appointmentService.createAppointment(appointment);
        record.setAppointment(addedAppointment);
        recordService.createRecord(record);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/appointments")
    public ResponseEntity<List<Appointment>> getAppointments(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Patient patient = patientService.getPatientByUsername(username);
        List<Appointment> currentAppointments = appointmentService.
                getByPatientId(patient.getId()).stream().
                filter(a -> a.getDescription().length()!=0).
                collect(Collectors.toList());
        return !currentAppointments.isEmpty()
                ? new ResponseEntity<>(currentAppointments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/gapsByDoctorOnDay/{id}")
    public ResponseEntity<List<GregorianCalendar>> getGapsByDoctorOnDay(@PathVariable Integer id){
        Doctor doctor = doctorService.readDoctor(id);
        GregorianCalendar date = new GregorianCalendar();
        List<GregorianCalendar> gaps = recordService.GetFreeGapsByDoctorOnDay(doctor,date);
        return new ResponseEntity<>(gaps,HttpStatus.OK);
    }

    @GetMapping(value = "/gapsByDoctorOnWeek/{id}")
    public ResponseEntity<List<GregorianCalendar>> getGapsByDoctorOnWeek(@PathVariable Integer id){
        Doctor doctor = doctorService.readDoctor(id);
        GregorianCalendar date = new GregorianCalendar();
        List<GregorianCalendar> gaps = recordService.GetFreeGapsByDoctorOnWeek(doctor,date);
        return new ResponseEntity<>(gaps,HttpStatus.OK);
    }

    @GetMapping(value = "/gapsBySpecialityOnDay")
    public ResponseEntity<Map<Integer, List<GregorianCalendar>>> getGapsBySpecialityOnDay(@RequestParam String speciality){
        Specialties specialityDoctor = Specialties.valueOf(speciality);
        GregorianCalendar date = new GregorianCalendar();
        date.set(Calendar.DAY_OF_MONTH,date.get(Calendar.DAY_OF_MONTH)+1);
        Map<Integer, List<GregorianCalendar>> doctorTimeMap = new HashMap<>();
        for(Map.Entry<Doctor,List<GregorianCalendar>> entry :recordService.GetFreeGapsByDoctorTypeOnDay(specialityDoctor, date).entrySet()){
            doctorTimeMap.put(entry.getKey().getId(),entry.getValue());
        }
        return new ResponseEntity<>(doctorTimeMap,HttpStatus.OK);

    }

    @GetMapping(value = "/gapsBySpecialityOnWeek")
    public ResponseEntity<Map<Integer, List<GregorianCalendar>>> getGapsBySpecialityOnWeek(@RequestParam String speciality){
        Specialties specialityDoctor = Specialties.valueOf(speciality);
        GregorianCalendar date = new GregorianCalendar();
        Map<Integer, List<GregorianCalendar>> doctorTimeMap = new HashMap<>();
        for(Map.Entry<Doctor,List<GregorianCalendar>> entry :recordService.GetFreeGapsByDoctorTypeOnWeek(specialityDoctor, date).entrySet()){
            doctorTimeMap.put(entry.getKey().getId(),entry.getValue());
        }
        return new ResponseEntity<>(doctorTimeMap,HttpStatus.OK);

    }


}
