package com.tus.coupon.data_import;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.tus.coupon.model.Coupon;
import com.tus.coupon.repos.CouponRepo;

@RestController
@RequestMapping("/dataimport")
public class DataImportRestController {
	private static final String CSV_FILE_LOCATION = "C:\\DevOps\\coupon.xlsx";
	
	@Autowired
	CouponRepo repo;
	
	@Autowired
	DataImportService dataImportService;

	Workbook workbook=null;
	
	@PostMapping(value = "/import")
	public List<Coupon> processString(@RequestBody String inputString) {
        // Process the input string
        String response = "Received: " + inputString;
        List<Coupon> coupons=dataImportService.importFile(CSV_FILE_LOCATION);
        // Return a response
        return coupons;
	}
	
	@GetMapping(value = "/import")
	public String returnString() {
        // Process the input string
       System.out.println("test");
        // Return a response
        return "ok";
	}
	
}
