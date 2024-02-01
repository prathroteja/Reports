package in.jam.service;

import java.util.List;

import in.jam.request.SearchRequest;
import in.jam.response.SearchReponse;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {
	public List<String> getUniquePlanName();
	public List<String> getUniquePlanStatus();
	public List<SearchReponse> search(SearchRequest request);
	public void generateExcel(HttpServletResponse response)throws Exception;
	//public HttpServletResponse generateExcel();
	public void generatePdf(HttpServletResponse response)throws Exception;

}
