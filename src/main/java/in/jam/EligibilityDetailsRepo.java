package in.jam;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.jam.entity.EligibilityDetails;

public interface EligibilityDetailsRepo extends JpaRepository<EligibilityDetails,Integer> {
	
	@Query("select distinct(planName) from EligibilityDetails")
	public List<String> findPlanName();
	
	@Query("select distinct(planStatus) from EligibilityDetails")
	public List<String> findPlanStatus();
	

}
