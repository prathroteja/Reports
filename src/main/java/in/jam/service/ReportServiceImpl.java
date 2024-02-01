package in.jam.service;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.jam.EligibilityDetailsRepo;
import in.jam.entity.EligibilityDetails;
import in.jam.request.SearchRequest;
import in.jam.response.SearchReponse;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	private EligibilityDetailsRepo elibilityRepo;
	
	@Override
	public List<String> getUniquePlanName() {
		return elibilityRepo.findPlanName();
		
	}

	@Override
	public List<String> getUniquePlanStatus() {
		
		return elibilityRepo.findPlanStatus() ;
	}

	@Override
	public List<SearchReponse> search(SearchRequest request) {
      List<SearchReponse> response = new ArrayList();
      
      EligibilityDetails queryBuilder = new EligibilityDetails();
      
      String planName = request.getPlanName();
      if(planName != null && !planName.equals("")) {
    	  queryBuilder.setPlanName(planName);
      }
      
      String planStatus = request.getPlanStatus();
      if(planStatus != null && !planStatus.equals("")) {
    	  queryBuilder.setPlanStatus(planStatus);
      }
      LocalDate planStartDate = request.getPlanStartaDate();
      if(planStartDate != null) {
    	  queryBuilder.setPlanStartDate(planStartDate);
    	  
      }
      
      LocalDate planEndDate = request.getPlanEndDate();
      if(planEndDate != null) {
    	  queryBuilder.setPlanEndDate(planEndDate);
    	  
      }
      Example<EligibilityDetails> example = Example.of(queryBuilder);
      
      List<EligibilityDetails> entities = elibilityRepo.findAll(example);
      for(EligibilityDetails entity : entities) {
    	  SearchReponse sr = new SearchReponse();
    	  BeanUtils.copyProperties(entity,sr);
    	  response.add(sr);
      }
		return response;
	}  

	@Override
	public void generateExcel(HttpServletResponse response)throws Exception {
		
		List<EligibilityDetails> entities = elibilityRepo.findAll();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow headerRow = sheet.createRow(0);
		

        //headerRow.createCell(1).setCellValue("S.no");
		headerRow.createCell(0).setCellValue("Name");
         headerRow.createCell(1).setCellValue("Email");
		headerRow.createCell(2).setCellValue("Mobile");
		headerRow.createCell(3).setCellValue("Gender");
		headerRow.createCell(4).setCellValue("SSN");
		
		entities.forEach(entity -> {
			int i=1;
			
			HSSFRow dataRow = sheet.createRow(i);
			dataRow.getCell(0).setCellValue(entity.getName());
			dataRow.getCell(1).setCellValue(entity.getEmail());
			dataRow.getCell(2).setCellValue(entity.getMobile());
			dataRow.getCell(3).setCellValue(entity.getGender());
			dataRow.getCell(4).setCellValue(entity.getSsn());
			i++;
			
			
		});
		
		ServletOutputStream outputStream=response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	@Override
	public void generatePdf(HttpServletResponse response)throws Exception{
		List<EligibilityDetails> entities = elibilityRepo.findAll();
		
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Font font= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		Paragraph p = new Paragraph("Search Report",font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(p);
//		
//		PdfPTable table = new PdfPTable(3);
//	
//		table.setWidthPercentage(100f);
//		table.setWidths(new int[] { 3, 3, 3 });
//		table.setSpacingBefore(10);
//		
		
		// Creating a table of 3 columns
				PdfPTable table = new PdfPTable(3);

				// Setting width of table, its columns and spacing
				table.setWidthPercentage(100f);
				table.setWidths(new int[] { 3, 3, 3 });
				table.setSpacingBefore(5);
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
		Font font1= FontFactory.getFont(FontFactory.HELVETICA);
		font1.setColor(Color.WHITE);
		

		cell.setPhrase(new Phrase("Name",font));
         table.addCell(cell); 
         
         cell.setPhrase(new Phrase("Email",font));
         table.addCell(cell);
		
         cell.setPhrase(new Phrase("Mobile",font));
         table.addCell(cell);
         
         cell.setPhrase(new Phrase("Gender",font));
         table.addCell(cell);
         
         cell.setPhrase(new Phrase("SSN",font));
         table.addCell(cell);
         
         
         for(EligibilityDetails entity:entities) {
        	 table.addCell(entity.getName());
        	 table.addCell(entity.getEmail());
        	 table.addCell(String.valueOf(entity.getMobile()));
        	 table.addCell(String.valueOf(entity.getGender()));
        	 table.addCell(String.valueOf(entity.getSsn()));
         }
		
		document.close();
		
		
		
		
	}

}
