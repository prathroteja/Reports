package in.jam.request;

import java.time.LocalDate;

import lombok.Data;

//@Data
public class SearchRequest {
	private String planName;
	private String planStatus;
	private LocalDate planStartaDate;
	private LocalDate planEndDate;
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	public LocalDate getPlanStartaDate() {
		return planStartaDate;
	}
	public void setPlanstartaDate(LocalDate planstartaDate) {
		this.planStartaDate = planstartaDate;
	}
	public LocalDate getPlanEndDate() {
		return planEndDate;
	}
	public void setPlanEndDate(LocalDate planEndDate) {
		this.planEndDate = planEndDate;
	}
	@Override
	public String toString() {
		return "SearchRequest [planName=" + planName + ", planStatus=" + planStatus + ", planStartaDate="
				+ planStartaDate + ", planEndDate=" + planEndDate + "]";
	}
	
	
	

}
