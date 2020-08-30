package com.securitylast.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.securitylast.domain.LocationType;
import com.securitylast.domain.Selling;
import com.securitylast.domain.User;
import com.securitylast.interfaces.UserInt;

@Controller
@RequestMapping("/client")
public class ClientController {
	@Autowired
	UserInt userint;

	@GetMapping("/reg")
	public String haveClients(Model model,Principal principal) {
		String principala = principal.getName();
		User existing = userint.findbyname(principala);
		model.addAttribute("mine",
				existing.getUserfirstname().toUpperCase().charAt(0) + "."
						+ existing.getUserlastname().substring(0, 1).toUpperCase()
						+ existing.getUserlastname().substring(1).toLowerCase());
		model.addAttribute("clientobj", new Clients());

		model.addAttribute("parentLocation",userint.findbyLocType(LocationType.PROVINCE));
		//model.addAttribute("province", userint.findbyLocType("PROVINCE"));
		return "clientregistration/index";
	}

	@GetMapping("/ourCustomers")
	public String viewClients(Model model, Principal principal) {
		String principala = principal.getName();
		User existing = userint.findbyname(principala);
		model.addAttribute("mine",
				existing.getUserfirstname().toUpperCase().charAt(0) + "."
						+ existing.getUserlastname().substring(0, 1).toUpperCase()
						+ existing.getUserlastname().substring(1).toLowerCase());
		model.addAttribute("customer", userint.findAllClients());
		return "clientregistration/viewCustomers";
	}

	@PostMapping("/reg")
	public String registerClientHere(@Valid @ModelAttribute("clientobj") Clients clientobj, BindingResult bindi,
			RedirectAttributes red) {
		if (bindi.hasErrors()) {
			return "clientregistration/index";
		}
		Clients existing = userint.clientFindByContact(clientobj.getContact());
		System.out.println(existing);
		if (existing != null) {
			bindi.rejectValue("contact", null, "There is already an account registered with that number");
			return "clientregistration/index";
		}
		userint.saveClient(clientobj);
//		Selling sell = new Selling();
////		String client=clientobj.getFullname();
////		sell.setRegisterdclients(client);
//		Clients client=userint.clientFindByContact(clientobj.getContact());
//		client.getIdnum();
//		sell.setClients(client);;
//		userint.saveSelling(sell);
		red.addFlashAttribute("ms", "successfully registered");
		return "redirect:/client/reg";
	}

	@GetMapping("/updateclient{stid}")
	public String updateClient(@RequestParam("stid") String idnum, Model model) {
		model.addAttribute("clientobj", userint.findClientbyid(idnum));
		return "clientregistration/index";
	}

	@GetMapping("/deleteclient")
	public String deleteClient(@RequestParam("myid") String id, RedirectAttributes redire) {
		userint.deleteClient(id);
		redire.addFlashAttribute("msg", "record deleted successfully");
		return "redirect:/client/ourCustomers";

	}
}
