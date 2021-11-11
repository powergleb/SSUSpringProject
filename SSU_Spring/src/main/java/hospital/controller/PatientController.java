package hospital.controller;

import hospital.model.entities.Patient;
import hospital.services.impl.PatientService;
import hospital.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class PatientController {

    private final PatientService patientService;

    @Autowired
    private IUserService IUserService;
    public PatientController(PatientService patientService1) {
        this.patientService = patientService1;
    }

    @GetMapping("/")
    public String welcome(HttpServletRequest request, HttpServletResponse response) {

        return "index";
    }

    @GetMapping("/list-patient")
    public ModelAndView getPatientRequest() {
        List<Patient> list = patientService.readAllPatient();
        ModelAndView model = new ModelAndView("list_patient");
        model.addObject("list", list);
        return model;
    }


    @RequestMapping(value="/register/patient", method= RequestMethod.GET)
    public String getPatientRegistrationPage(Model model) {
        return "patientRegistration";
    }

    @RequestMapping(value="/register/register-new-patient", method=RequestMethod.POST)
    public String addNewClient(@RequestParam(value="name") String name,
                               @RequestParam(value="secondName") String secondName,
                               @RequestParam(value="patronymic") String patronymic,
                               @RequestParam(value="login") String login,
                               @RequestParam(value="pass") String pass,
                               @RequestParam(value="phone") String phone,
                               @RequestParam(value="address") String address,
                               @RequestParam(value="mail") String mail) throws Exception {
        Patient patient = new Patient(name, secondName, patronymic, pass, login, phone, address, mail);
        IUserService.registerUser(patient,false);

        return "redirect:/";
    }


    @GetMapping("/add-patient")
    public String addUserPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "add_patient";
    }

    @GetMapping("/update-patient")
    public String updateUserPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "update_patient";
    }

    @GetMapping("/delete-patient")
    public String deletePatientPage() throws IOException {
        return "delete_patient";
    }


    @PostMapping("/add-patient")
    public String addPatientRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String number = request.getParameter("number");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        addNewPatient(name, number, email, login, password);
        addUserPage(request, response);
        return "index";
    }


    @PostMapping("/update-patient")
    public String updatePatientRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String type = request.getParameter("parameter");
        String newValue = request.getParameter("newValue");
        updatePatient(id, type, newValue);
        updateUserPage(request, response);
        return "index";
    }

    @PostMapping("/delete-patient")
    public String deletePatientRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        deletePatient(id);
        return "index";
    }

    private void addNewPatient(String name, String number, String mail, String login, String pass) throws Exception {
        Patient patient = new Patient();
        patient.setName(name);
        patient.setPhone(number);
        patient.setMail(mail);
        patient.setLogin(login);
        patient.setPass(pass);

        patientService.createPatient(patient);
    }
    private void updatePatient(String id, String parameter, String newValue) throws Exception {
        patientService.updatePatient(id, parameter, newValue);
    }

    private void deletePatient(String id) {
        patientService.deletePatient(Integer.parseInt(id));
    }
}
