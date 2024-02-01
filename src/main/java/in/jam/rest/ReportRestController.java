package in.jam.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.jam.request.SearchRequest;
import in.jam.response.SearchReponse;
import in.jam.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportRestController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/plans")
	public ResponseEntity<List<String>> getPlanName(){
		List<String> planNames=service.getUniquePlanName();
		return new ResponseEntity<>(planNames,HttpStatus.OK);
		
		
	}
	@GetMapping("/status")
	public ResponseEntity<List<String>> getPlanStatus(){
		List<String> status=service.getUniquePlanStatus();
		return new ResponseEntity<>(status,HttpStatus.OK);
		
		
	}
	@PostMapping("/search")
	public ResponseEntity<List<SearchReponse>> search(@RequestBody SearchRequest request){
		List<SearchReponse> response = service.search(request);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public void excelReport(HttpServletResponse response) throws Exception{
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.xls";
		
		response.setHeader(headerKey, headerValue);
		service.generateExcel(response);
	}
	
	@GetMapping("/pdf")
	public void pdfReport(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		
		
		String headerKey= "Content-Disposition";
		String headerValue = "attachment;filename=data.pdf";
		
		response.setHeader(headerKey, headerValue);
		service.generatePdf(response);
		
	}

}
