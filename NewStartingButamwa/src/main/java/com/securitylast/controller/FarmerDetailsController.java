package com.securitylast.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.securitylast.domain.FarmerDetails;
import com.securitylast.domain.Photos;
import com.securitylast.domain.User;
import com.securitylast.interfaces.UserInt;

@Controller
@RequestMapping("/details")
public class FarmerDetailsController {
	@Autowired
	private UserInt userint;
	User usere = new User();
	private List<Photos> uploadImage = new ArrayList<>();
	private static final String UPLOAD_FOLDER = System.getProperty("user.dir");

	@GetMapping("/admin")
	public String getAdmin(Model redirec, Principal principal, User usr) {

		String principala = principal.getName();
		User existing = userint.findbyname(principala);
		redirec.addAttribute("mine",
				existing.getUserfirstname().toUpperCase().charAt(0) + "."
						+ existing.getUserlastname().substring(0, 1).toUpperCase()
						+ existing.getUserlastname().substring(1).toLowerCase());
		return "admin/indexadmin.html";
	}

	@GetMapping("/farmerpg")
	public String farmePage(Model model, Principal principal, User usr) {
		model.addAttribute("myfarmer", new FarmerDetails());
		String principala = principal.getName();
		User existing = userint.findbyname(principala);
		model.addAttribute("mine", existing.getUserfirstname().substring(0, 1).toUpperCase()
				+ existing.getUserfirstname().substring(1).toLowerCase());

		return "userregistration/completeyourregistration";
	}

	@PostMapping("/farmerpg")
	public String saveDetails(@RequestParam("file") MultipartFile imagefile,
			@RequestParam("files") MultipartFile imagefilee, RedirectAttributes der,
			@Valid @ModelAttribute("myfarmer") FarmerDetails myfarmer, BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			return "userregistration/completeyourregistration";
		}
		if (imagefilee.isEmpty()) {
			der.addFlashAttribute("msgz", " :) please provide your image");
			return "redirect:/details/farmerpg";
		}
		if (imagefile.isEmpty()) {
			der.addFlashAttribute("msgz", " :) please provide nationalid pdf file");
			return "redirect:/details/farmerpg";
		} else if (imagefile.getSize() > 5242880) {
			der.addFlashAttribute("msgz", " :) sorry your nationalid pdf has large size");
			return "redirect:/details/farmerpg";
		}

		else if (!imagefile.isEmpty()) {

			String holla = imagefile.getOriginalFilename();
			if (holla.length() > 4) {
				String pp = holla.substring(holla.length() - 3);
				if (!pp.equalsIgnoreCase("pdf")) {
					der.addFlashAttribute("msgz", " :) for the  nationalid  file must be pdf format ");
					return "redirect:/details/farmerpg";
				}
			}
		}
		try {
			// User here = new User();
			String hs = principal.getName();
			User existing = userint.findbyname(hs);
			System.out.println("---------------------------" + principal.getName() + existing.getUserfirstname());

			String format = imagefilee.getContentType();
			String extension = (format.equalsIgnoreCase("image/jpeg") ? ".jpg"
					: (format.equalsIgnoreCase("image/png")) ? ".png" : "");
			String newName = UUID.randomUUID().toString() + extension;
			myfarmer.setIdcardimage(imagefile.getOriginalFilename());
			myfarmer.setYourpictureimage(newName);
			existing.getIdnum();
			myfarmer.setUser(existing);
			userint.saveImagesz(imagefile, UPLOAD_FOLDER + "\\src\\main\\resources\\static\\images\\portifolioImage\\",
					newName, imagefilee, myfarmer);
			existing.setRoles("farmerr");
			// here.setRoles("f");
			userint.saveUSer(existing);
		} catch (Exception e) {
			e.printStackTrace();
		}
		der.addFlashAttribute("msgz", "record saved successfully");
		der.addFlashAttribute("mz", "continue >>");
		return "redirect:/details/farmerpg";
	}
}
