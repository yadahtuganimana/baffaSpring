package com.securitylast.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.securitylast.domain.Clients;
import com.securitylast.domain.Selling;
import com.securitylast.domain.User;
import com.securitylast.interfaces.UserInt;

@Controller
@RequestMapping("/milk")
public class Milk {
	@Autowired
	UserInt userint;

	@GetMapping("/sellingmilk")
	public String myMilks(Model model,Principal principal) {
		String principala = principal.getName();
		User existing = userint.findbyname(principala);
		model.addAttribute("mine",
				existing.getUserfirstname().toUpperCase().charAt(0) + "."
						+ existing.getUserlastname().substring(0, 1).toUpperCase()
						+ existing.getUserlastname().substring(1).toLowerCase());
		model.addAttribute("amata", new Selling());
		model.addAttribute("clientslist", userint.findAllClients());
		// System.out.println(userint.findAllClients());
		return "userregistration/sellingamata";
	}

	@GetMapping("/sellingmilkview")
	public String ourMilks(Model model, Principal principal) {
		String principala = principal.getName();
		User existing = userint.findbyname(principala);
		model.addAttribute("mine",
				existing.getUserfirstname().toUpperCase().charAt(0) + "."
						+ existing.getUserlastname().substring(0, 1).toUpperCase()
						+ existing.getUserlastname().substring(1).toLowerCase());
		model.addAttribute("amatayose", userint.findAllSelling());

		return "userregistration/AmataYagurishijwe";
	}

	@PostMapping("/sellingmilk")
	public String registerMilk(@Valid @ModelAttribute("amata") Selling amata, BindingResult bin,
			@ModelAttribute("clientId") String clientid, Principal principal, RedirectAttributes red) {
	try {
		 if (bin.hasErrors()) {
				return "userregistration/sellingamata";
			}
			String email = principal.getName();
			User existing = userint.findbyname(email);
			String hre = existing.getUserfirstname().substring(0, 1).toUpperCase()
					+ existing.getUserfirstname().substring(1).toLowerCase() + " "
					+ existing.getUserlastname().substring(0, 1).toUpperCase()
					+ existing.getUserlastname().substring(1).toLowerCase();
			SimpleDateFormat newdate=new SimpleDateFormat("yyyy-MM-dd");
			String ok="date";
			String date = newdate.format(new Date());
			Date mynewdate= new SimpleDateFormat("yyyy-MM-dd").parse(date);
			Clients client = userint.findClientbyid(clientid);
			//amata.setRecorderon(mynewdate);
			amata.setRecordedby(hre);
			amata.setClients(client);
			userint.saveSelling(amata);
			red.addFlashAttribute("msg", "successfully successfully recorded");
	} catch (Exception e) {
		e.printStackTrace();
	}	
		return "redirect:/milk/sellingmilk";
	}

	@GetMapping("/updateAmata{stid}")
	public String updateAmata(@RequestParam("stid") String idnum, Model model) {
		model.addAttribute("clientobj", userint.findbySellingid(idnum));
		return "userregistration/AmataYagurishijwe";
	}

	@GetMapping("/deleteAmata")
	public String deleteAmata(@RequestParam("myid") String id, RedirectAttributes redire) {
		userint.deleteSelling(id);
		;
		redire.addFlashAttribute("msg", "record deleted successfully");
		return "redirect:/milk/sellingmilkview";

	}
}
