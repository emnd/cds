package com.opengroup.res.core;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.impl.mappers.EquipementMapper;
import com.opengroup.res.core.impl.mappers.LocationMapper;
import com.opengroup.res.jpa.EquipementRepository;
import com.opengroup.res.jpa.LocationRepository;
/**
 * Integration test on parameter
 *
 * @author Open groupe
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EquipementServicesIntegrationTest {

    @Autowired
    private EquipementServices equipementServices;

    @Autowired
    private EquipementRepository EquipementRepository;

    @Autowired
    private EquipementMapper EquipementMapper;

   	@Autowired
	private LocationMapper locationMapper;
    @Autowired
    private LocationRepository locationRepository;


SimpleDateFormat sdf=new SimpleDateFormat("YYYY/MM/DD");
    
    @Test
   @Commit
    public void testCreate() throws Exception {
    	
    	equipementServices.createEquipement("PAR1LAP45678", "1234AZERTY", "dell", "Longitude E4578", sdf.parse("2017/01/31"),sdf.parse("2017/02/31"), sdf.parse("2017/01/01"), sdf.parse("2017/01/31"), "lol", DomainEquipement.EquipementType.Laptop, DomainEquipement.StateType.Available,locationMapper.toOneDomain(locationRepository.findOne(25L)));
    	
    
        
    	equipementServices.createEquipement("PAR1LAP45675", "1234AZERTY", "acer", "Longitude E4578", sdf.parse("2017/01/31"),sdf.parse("2017/02/31"), sdf.parse("2017/01/01"), sdf.parse("2017/01/31"), "lol",DomainEquipement.EquipementType.Desktop ,DomainEquipement.StateType.Loaned,locationMapper.toOneDomain(locationRepository.findOne(26L)));
        
    	equipementServices.createEquipement("PAR1LAP45677", "1234AZERTY", "hp", "Longitude E4578", sdf.parse("2017/01/31"),sdf.parse("2017/02/31"), sdf.parse("2017/01/01"), sdf.parse("2017/01/31"), "pile",DomainEquipement.EquipementType.Desktop ,DomainEquipement.StateType.Available,locationMapper.toOneDomain(locationRepository.findOne(27L)));
    }
    
//    @Test
//    @Commit
//    public void testUpdate() throws Exception {
//        equipementServices.updateEquipement("PAR1LAP45678", "1234AZERTY", "dell", "Longitude E4578", sdf.parse("2017/01/31"),sdf.parse("2017/02/31"), sdf.parse("2017/01/01"), sdf.parse("2017/01/31"), "lol", DomainEquipement.EquipementType.Laptop, DomainEquipement.StateType.Available, locationMapper.toOneDomain(locationRepository.findOne(23L)));
//    }
//
    
//    @Test
//    @Commit
//    public void testDelete() throws Exception {
//       equipementServices.deleteEquipement(1l);
//    }
}