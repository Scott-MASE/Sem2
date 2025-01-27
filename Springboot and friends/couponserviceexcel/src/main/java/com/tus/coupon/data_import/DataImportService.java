package com.tus.coupon.data_import;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import com.tus.coupon.model.Coupon;
import com.tus.coupon.repos.CouponRepo;
import org.apache.poi.ss.usermodel.DataFormatter;


@Service
public class DataImportService {
	
	@Autowired
	CouponRepo repo;
	
	/*
	 * @Autowired WineValidator wineValidator;
	 */
	
	public List<Coupon> importFile(String fileLocation ) {
		Workbook workbook =null;
		List<Coupon> coupons = new ArrayList<>();
		repo.deleteAllEntities();
		try {
			// Creating a Workbook from an Excel file (.xls or .xlsx)
			workbook = WorkbookFactory.create(new File(fileLocation));
			// Retrieving the number of sheets in the Workbook
			System.out.println("Number of sheets: "+ workbook.getNumberOfSheets());
			// Print all sheets name
			workbook.forEach(sheet -> {
				System.out.println(" => " + sheet.getSheetName());

				// Create a DataFormatter to format and get each cell's value as String
				DataFormatter dataFormatter = new DataFormatter();

				//loop through all rows and columns and create Coupon object
				int index = 0;
				for(Row row : sheet) {
					if(index++ == 0) continue;
					Coupon coupon = new Coupon();
					coupon.setCode(dataFormatter.formatCellValue(row.getCell(0)));
					String cellValue = dataFormatter.formatCellValue(row.getCell(1));
					try {
					    BigDecimal discount = new BigDecimal(cellValue);
					    coupon.setDiscount(discount);
					} catch (NumberFormatException e) {
					    // Handle the case where the cell value is not a valid number
					    System.err.println("Invalid number format in cell: " + cellValue);
					}
					coupon.setExpDate(dataFormatter.formatCellValue(row.getCell(2)));
					Coupon savedCoupon=repo.save(coupon);
					coupons.add(savedCoupon);
				}
			});
		} catch (EncryptedDocumentException | IOException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(workbook != null) workbook.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return coupons;	
	}

}
