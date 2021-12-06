package hospital.Controller;


import hospital.entities.Appointment;
import hospital.entities.Doctor;
import hospital.entities.Patient;

import hospital.entities.help.Specialties;
import hospital.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/register")
public class RegisterController {

    @Autowired
    private IUserService userService;



    @PostMapping(value = "/patient")
    public ResponseEntity<?> registerClient(@Valid @RequestBody Patient patient) throws Exception {
        userService.registerUser(patient, false);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping(value = "/admin")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody Patient patient) throws Exception {
        userService.registerUser(patient, true);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping("/doctor")
    public ResponseEntity<?> registerDoctor(@Valid @RequestBody Doctor doctor) throws Exception {
        userService.registerUser(doctor, false);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

}
