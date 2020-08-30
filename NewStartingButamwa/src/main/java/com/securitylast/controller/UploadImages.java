package com.securitylast.controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.securitylast.domain.Photos;
import com.securitylast.domain.User;
//import com.securitylast.emails.notification.service.StorageService;
import com.securitylast.interfaces.MyPrincipalInt;
import com.securitylast.interfaces.UserInt;
import com.securitylast.repository.UserRepository;
import com.securitylast.usersec.UserDetailsProp;

@Controller
@RequestMapping("/uploaded")
public class UploadImages {
	@Autowired
	UserInt userint;
	@Autowired
	MyPrincipalInt userinte;
	@Autowired
	UserRepository use;
//	@Autowired
//	StorageService storageService;

	@GetMapping("/uploads")
	public String imagesuploded(Model mode, Principal principal) {
		String principala = principal.getName();
		User existing = userint.findbyname(principala);
		mode.addAttribute("mine",
				existing.getUserfirstname().toUpperCase().charAt(0) + "."
						+ existing.getUserlastname().substring(0, 1).toUpperCase()
						+ existing.getUserlastname().substring(1).toLowerCase());
		mode.addAttribute("newobj", new Photos());
		return "upload/index";
	}

	@GetMapping("/viewAmakuru")
	public String imagesAmakuru(Model mode, Principal principal) {
		String principala = principal.getName();
		User existing = userint.findbyname(principala);
		mode.addAttribute("mine",
				existing.getUserfirstname().toUpperCase().charAt(0) + "."
						+ existing.getUserlastname().substring(0, 1).toUpperCase()
						+ existing.getUserlastname().substring(1).toLowerCase());
		mode.addAttribute("amakuru", userint.findAllAmakuru());
		return "upload/managerCows";
	}

//	@GetMapping("/uploadds")
//	public String farmerDetails(Model modee) {
//		modee.addAttribute("mystt", userint.findAllAmakuru());
//		return "uploadview/index";
//	}
	@PostMapping("/save")
	public String savingImages(@RequestParam("file") MultipartFile imagefile, RedirectAttributes der,
			@Valid @ModelAttribute("newobj") Photos newobj, Principal principal) {

		try {
			String hs = principal.getName();
			User existing = userint.findbyname(hs);
			System.out.println("---------------------------" + principal.getName() + existing.getUserfirstname());
			String hre = existing.getUserfirstname().substring(0, 1).toUpperCase()
					+ existing.getUserfirstname().substring(1).toLowerCase() + " "
					+ existing.getUserlastname().substring(0, 1).toUpperCase()
					+ existing.getUserlastname().substring(1).toLowerCase();
			newobj.setPhotFile(imagefile.getOriginalFilename());
			newobj.setWhodidaction(hre);
			userint.saveImages(imagefile, newobj);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "redirect:/butamwa/admin";
	}

	@GetMapping("/updatephoto{stid}")
	public String updatePhoto(@RequestParam("stid") String idnum, Model model) {
		model.addAttribute("newobj", userint.findbyPhotos(idnum));
		return "upload/index";
	}

	@GetMapping("/deletephoto")
	public String deletePhoto(@RequestParam("myid") String id, RedirectAttributes redire) {
		userint.deletePhotos(id);
		redire.addFlashAttribute("msg", "record deleted successfully");
		return "redirect:/uploaded/viewAmakuru";

	}

	// from baffalo
//	@PostMapping(value = "/imageUpload", produces = "application/json")
//	@ResponseBody
//	public String imageUpload(@RequestParam("file") MultipartFile file) {
//		List<Photos> uploadImage;
//		final String UPLOAD_FOLDER = System.getProperty("user.dir");
//		String format = file.getContentType();
//		String extension = (format.equalsIgnoreCase("image/jpeg") ? ".jpg"
//				: (format.equalsIgnoreCase("image/png")) ? ".png"
//						: format.equalsIgnoreCase("image/gif") ? ".gif"
//								: format.equalsIgnoreCase("application/pdf") ? ".pdf" : "");
//		String newName = UUID.randomUUID().toString() + extension;
//		Photos image = new Photos();
//		image.setImagebody(newName);
//		//uploadImage.add(image);
//		storageService.upload(file, UPLOAD_FOLDER + "\\src\\main\\resources\\static\\images\\portifolioImage\\",
//				newName);
//
//		return "{}";
//	}

}
