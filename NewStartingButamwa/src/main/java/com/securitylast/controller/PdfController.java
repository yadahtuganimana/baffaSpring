package com.securitylast.controller;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.securitylast.domain.Selling;
import com.securitylast.interfaces.UserInt;
import com.securitylast.report.GeneratePdfReport;

@Controller
@RequestMapping("/selling")
public class PdfController {
	@Autowired
	private UserInt userint;
   	// @PathVariable("r")String r,@PathVariable("d")String d
	@GetMapping("/pdfreportt")
	public ResponseEntity<InputStreamResource> sellingReport(@RequestParam("r") String r, @RequestParam("d") String d)
			throws Exception {

//		String first = "2020-04-05";
//		String second = "2020-04-12";
//		SYSTEM.OUT.PRINTLN(R);
//		SYSTEM.OUT.PRINTLN(D);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 =format.parse(r);
		Date d2 = format.parse(d);
//		first =d1;
//		System.out.println(first);
//		System.out.println(d1);
//		System.out.println(d2);
		Iterable<Selling> selling = (Iterable<Selling>) userint.findRecorderonBetweenImp(d1, d2);
		List<Selling> firstvar = (List<Selling>) selling;
//		System.err.println("+++++++++++++++++++++++++++" + firstvar.size());
//		// Iterable<Selling> selling = (Iterable<Selling>) userint.findAllSelling();
		ByteArrayInputStream bis = GeneratePdfReport.sellingaReport(selling);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=report_ya_Amata.pdf");
		return ResponseEntity.ok().headers(headers)

				.body(new InputStreamResource(bis));
	}

	@GetMapping("/pdfreport")
	public String sellingDate(Model model) {
		model.addAttribute("mod", new Selling());
		return "userregistration/AmataReportCustom";
	}

}
