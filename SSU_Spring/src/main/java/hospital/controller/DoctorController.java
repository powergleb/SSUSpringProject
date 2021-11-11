package hospital.controller;

import hospital.model.entities.Doctor;
import hospital.model.entities.help.Specialties;
import hospital.services.impl.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value="/list-doctor", method= RequestMethod.GET)
    public String getOrderPage(Model model) {
        model.addAttribute("doctorsList",doctorService.readAllDoctor());
        return "list_doctor";
    }

    @RequestMapping(value="/register/doctor", method= RequestMethod.GET)
    public String getClientRegistrationPage(Model model) {
        return "doctorRegistration";
    }

    @RequestMapping(value="/register/register-new-doctor", method=RequestMethod.POST)
    public String addNewClient(@RequestParam(value="name") String name,
                               @RequestParam(value="secondName") String secondName,
                               @RequestParam(value="patronymic") String patronymic,
                               @RequestParam(value="login") String login,
                               @RequestParam(value="pass") String pass,
                               @RequestParam(value="phone") String phone,
                               @RequestParam(value="address") String address,
                               @RequestParam(value="mail") String mail,
                               @RequestParam(value="speciality") String speciality) {

        Specialties doctorSpeciality = Specialties.valueOf(speciality);
        Doctor doctor = new Doctor(name, secondName, patronymic, pass, login, phone, address, mail, doctorSpeciality);
        doctorService.createDoctor(doctor);
        return "redirect:/";
    }




}
