package com.securitylast.controller;

import java.lang.ProcessBuilder.Redirect;
import java.security.Principal;

import javax.validation.Valid;

import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.securitylast.domain.Clients;
import com.securitylast.domain.FarmerDetails;
import com.securitylast.domain.Selling;
import com.securitylast.domain.User;
import com.securitylast.emails.notification.service.ApprovedNotification;
import com.securitylast.emails.notification.service.EmailNotification;
import com.securitylast.interfaces.UserInt;
import com.securitylast.repository.FarmerDetailsRepository;
import com.securitylast.repository.UserRepository;

@Controller
@RequestMapping("/butamwa")
public class ButamwaController {

	private Logger logger = LoggerFactory.getLogger(ButamwaController.class);
	@Autowired
	private UserRepository userrep;
	@Autowired
	private UserInt userint;
	@Autowired
	private EmailNotification notification;
	@Autowired
	private ApprovedNotification notificate;
	@Autowired
	private FarmerDetailsRepository famrep;

	@GetMapping("/admin")
	public String getAdmin(Model redirec, Principal principal, User usr) {

		String principala = principal.getName();
		User existing = userint.findbyname(principala);
		redirec.addAttribute("mine",
				existing.getUserfirstname().toUpperCase().charAt(0) + "."
						+ existing.getUserlastname().substring(0, 1).toUpperCase()
						+ existing.getUserlastname().substring(1).toLowerCase());
		redirec.addAttribute("fam", userint.findAbashumbaDetails());
		redirec.addAttribute("mystt", userint.findAllAmakuru());
		redirec.addAttribute("newusers", userrep.countByNewuser("New"));
		return "admin/indexadmin.html";
	}

	@GetMapping("/login")
	public String getLogin() {
		return "Login/home";
	}

	@GetMapping("/admindashboard")
	public String AdministratorDashboard(Model model, Principal principal) {
		String principala = principal.getName();
		User existing = userint.findbyname(principala);
		model.addAttribute("mine",
				existing.getUserfirstname().toUpperCase().charAt(0) + "."
						+ existing.getUserlastname().substring(0, 1).toUpperCase()
						+ existing.getUserlastname().substring(1).toLowerCase());
		model.addAttribute("visitor", userrep.countByNewuserAndRolesEquals("", "VISITOR"));
		model.addAttribute("visitorpending", userrep.countByNewuserAndRolesEquals("New", "VISITOR"));
		model.addAttribute("admin", userrep.countByNewuserAndRolesEquals("", "ADMIN"));
		model.addAttribute("adminpending", userrep.countByNewuserAndRolesEquals("New", "ADMIN"));
		model.addAttribute("farmer", userrep.countByNewuserAndRolesEquals("", "farmerr"));
		model.addAttribute("farmerpending", userrep.countByNewuserAndRolesEquals("New", "FARMER"));
		return "admin/dashboard";

	}

	@GetMapping("/detailsFarmer")
	public String getFarnmerd(@RequestParam("myidd") String id, Model model) {
		model.addAttribute("famdtl", userint.findAllFarmerbyid(id));
		return "userview/indexFarmerDetails";
	}

	@GetMapping("/registeruser")
	public String getAdmin(Model model) {
		model.addAttribute("myobj", new User());
		return "userregistration/index";
	}

	@PostMapping("/registeruser")
	public String saveUser(@Valid @ModelAttribute("myobj") User myobj, BindingResult bind,
			RedirectAttributes redirect) {
		if (bind.hasErrors()) {

			return "userregistration/index";

		}

		User existing = userint.findbyname(myobj.getUsername());
		if (existing != null) {
			bind.rejectValue("username", null, "There is already an account registered with that email");
		}

		try {
			myobj.setUsername(myobj.getUsername());
			notification.sendNotification(myobj);
		} catch (MailException e) {

			logger.info("message errore for email here" + e.getMessage());
		}

		BCryptPasswordEncoder ki = new BCryptPasswordEncoder();
		myobj.setPasswordd(ki.encode(myobj.getPasswordd()));
		// firmServe.saveAdmin(user);
		userint.saveUSer(myobj);
		redirect.addFlashAttribute("msg", "saved successfully wait for admin approval!!! ");

		return "redirect:/butamwa/registeruser";
	}

	@GetMapping("/ViewAllFarmer")
	public String viewAllUserHere(Model model, Principal principal) {
		String principala = principal.getName();
		User existing = userint.findbyname(principala);
		model.addAttribute("mine",
				existing.getUserfirstname().toUpperCase().charAt(0) + "."
						+ existing.getUserlastname().substring(0, 1).toUpperCase()
						+ existing.getUserlastname().substring(1).toLowerCase());
		model.addAttribute("objc", userint.findAllUSersRolesFarmer());
		model.addAttribute("fam", userint.findAbashumbaDetails());
		return "userview/indexViewFarmers";
	}

	@GetMapping("/ViewNew")
	public String userNew(Model model, User user) {
		if (user.getNewuser().equals("New")) {
			user.getNewuser();
			user.setNewuser("");
			model.addAttribute("hasta", userint.saveUSer(user));
		}

		return "userview/index";
	}

	@GetMapping("/ViewAlluser")
	public String viewAllFarmerHere(Model model, Principal principal) {
		String principala = principal.getName();
		User existing = userint.findbyname(principala);
		model.addAttribute("mine",
				existing.getUserfirstname().toUpperCase().charAt(0) + "."
						+ existing.getUserlastname().substring(0, 1).toUpperCase()
						+ existing.getUserlastname().substring(1).toLowerCase());
		model.addAttribute("obj", userint.findAllUSers());
		model.addAttribute("visitor", userrep.countByNewuserAndRolesEquals("New", "VISITOR"));
		model.addAttribute("admin", userrep.countByNewuserAndRolesEquals("New", "ADMIN"));
		model.addAttribute("farmer", userrep.countByNewuserAndRolesEquals("New", "farmer"));
		return "userview/index";
	}

	@GetMapping("/deleteusr")
	public String deleteUsers(@RequestParam("myid") String id, RedirectAttributes redire) {
		userint.deleteUser(id);
		redire.addFlashAttribute("msg", "record deleted successfully");
		return "redirect:/butamwa/ViewAlluser";

	}

	@GetMapping("/deletefarmer")
	public String deleteFarmer(@RequestParam("myid") String id, RedirectAttributes redire) {
		userint.deleteFarmer(id);
		redire.addFlashAttribute("msg", "record deleted successfully");
		return "redirect:/butamwa/ViewAllFarmer";

	}

	String mess = "";

	@GetMapping("/update")
	public String updateUsers(@RequestParam("myidd") String id, User user, RedirectAttributes redire) {

		User usage = new User();
		usage = userint.findbyid(id);

		if (usage.getActive().equals("BLOCK")) {
			usage.setActive("ACTIVE");
			usage.setNewuser("");

			try {
				usage.setUsername(usage.getUsername());
				notificate.sendMyEmail(usage);
			} catch (MailException e) {

				logger.info("message errore for email here" + e.getMessage());
			}
		} else {
			usage.setActive("BLOCK");

		}

		userint.saveUSer(usage);
		redire.addFlashAttribute("msg", "record updated successfully");
		return "redirect:/butamwa/ViewAlluser";

	}

	@GetMapping("/updatee")
	public String updateUsersF(@RequestParam("myidd") String id, User user, RedirectAttributes redire) {

		User usage = new User();
		usage = userint.findbyid(id);

		if (usage.getActive().equals("BLOCK")) {
			usage.setActive("ACTIVE");
			try {
				usage.setUsername(usage.getUsername());
				notificate.sendMyEmail(usage);
			} catch (MailException e) {

				logger.info("message errore for email here" + e.getMessage());
			}
		} else {
			usage.setActive("BLOCK");

		}

		userint.saveUSer(usage);
		redire.addFlashAttribute("msg", "record updated successfully");
		return "redirect:/butamwa/ViewAllFarmer";

	}

	@GetMapping("/updateuser{stid}")
	public String updateStudent(@RequestParam("stid") String idnum, Model model) {
		model.addAttribute("myobj", userint.findbyid(idnum));
		return "userregistration/index";
	}

	@GetMapping("/reg")
	public String haveClients(Model model) {
		model.addAttribute("client", new Clients());
		return "clientregistration/index";
	}

}
