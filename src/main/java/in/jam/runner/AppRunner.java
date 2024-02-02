package in.jam.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.jam.EligibilityDetailsRepo;
import in.jam.entity.EligibilityDetails;

@Component
public class AppRunner implements ApplicationRunner {
	
@Autowired
private EligibilityDetailsRepo repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		EligibilityDetails	entity1= new EligibilityDetails();
		entity1.setEligId(1);
		entity1.setName("teju");
		entity1.setMobile(123456789);
		entity1.setGender('F');
		entity1.setSsn(12344);
		entity1.setPlanName("SNAP");
		entity1.setPlanStatus("Approved");
		
		repo.save(entity1);
		
		
		EligibilityDetails	entity2= new EligibilityDetails();
		entity2.setEligId(2);
		entity2.setName("mahi");
		entity2.setMobile(1234589);
		entity2.setGender('M');
		entity2.setSsn(1244);
		entity2.setPlanName("CCP");
		entity2.setPlanStatus("Denied");
		
		repo.save(entity2);
		

		EligibilityDetails	entity3= new EligibilityDetails();
		entity3.setEligId(3);
		entity3.setName("sai");
		entity3.setMobile(5234589);
		entity3.setGender('M');
		entity3.setSsn(1246);
		entity3.setPlanName("JAVA");
		entity3.setPlanStatus("closed");
		
		repo.save(entity3);
		
	}

}
