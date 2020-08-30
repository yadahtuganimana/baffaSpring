package com.securitylast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.securitylast.domain.Location;
import com.securitylast.interfaces.UserInt;

@Controller
@RequestMapping("/loc")
public class LocationController {
	@Autowired
	UserInt locate;

	// LocationType loc=LocationType.valueOf(LocationType, name);

	@GetMapping("/locational")
	public String getLocation(Model model) {
		model.addAttribute("locations", new Location());
		model.addAttribute("locationlist", locate.findAllLocation());
		return "location/location";
	}

	@PostMapping("/locational")
	public String createLocation(@RequestParam("parent") String parenti, Location locationn,
			RedirectAttributes redirect) {

		if (!parenti.equals("none")) {
			Location locked = locate.findbyidloc(parenti);
			locked.setUuid(locked.getUuid());
			locationn.setLocation(locked);
			locate.saveLocation(locationn);
		} else {
			locate.saveLocation(locationn);
		}
		redirect.addFlashAttribute("msg", "successfully done");
		return "redirect:/loc/locational";

	}

	@GetMapping(path = "/getLocation{parentId}")
	@ResponseBody
	public Iterable<Location> findLocationByParent(@RequestParam String parentId) {

		return locate.findAllLocation();
	}

}
