package com.opengroup.res.core.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opengroup.res.core.EquipementServices;
import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainHistoryLog;
import com.opengroup.res.core.domain.DomainLocation;
import com.opengroup.res.core.impl.mappers.EquipementMapper;
import com.opengroup.res.core.impl.mappers.LocationMapper;

import com.opengroup.res.jpa.EquipementRepository;
import com.opengroup.res.jpa.HistoryLogRepository;
import com.opengroup.res.jpa.LocationRepository;
import com.opengroup.res.jpa.entities.Equipement;
import com.opengroup.res.jpa.entities.HistoryLog;
import com.opengroup.res.jpa.entities.Location;

/**
 * Implementation to manage Equipement service
 *
 * @author Open groupe
 * @since 1.0.0
 */
@Service
public class EquipementServicesImpl implements EquipementServices {

    @Autowired
    private HistoryLogRepository historyLogRepository;

    @Autowired
    private EquipementRepository equipementRepository;

    @Autowired
    private EquipementMapper equipementMapper;
    
    @Autowired
    private LocationRepository locationRepository;

   	@Autowired
	private LocationMapper locationMapper;
   	
    @Override
    @Transactional
    public void createEquipement(String stationNameEquipement, String serialNumberEquipement, String markEquipement,
    		String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement,
    		Date expectedDateEquipement, String commentsEquipement, DomainEquipement.EquipementType equipementTypeEquipement,
    		DomainEquipement.StateType stateTypeEquipement,DomainLocation domainLocation) throws DomainException {
    
    	Long id= null;
    	Location location = locationMapper.toOneEntity(domainLocation);
    	if(domainLocation.getId() == null)
    	{
    		locationRepository.save(location);
    	}
    	domainLocation = locationMapper.toOneDomain(location);
        DomainEquipement domainEquipement = DomainEquipement.newCreatedStateInstance(stationNameEquipement,
        		serialNumberEquipement,markEquipement, modelEquipement, attributionDateEquipement, returnDateEquipement, 
        		purchaseDateEquipement, expectedDateEquipement, commentsEquipement,  equipementTypeEquipement,  stateTypeEquipement, 
        		domainLocation,id);
        equipementRepository.save(equipementMapper.toOneEntity(domainEquipement));
       // logTrackEquipement(now,"DEFAULT CREATION MESSAGE", DomainHistoryLog.newEquipementInstance(domainEquipement));
    }

    @Override
    @Transactional
    public void updateEquipement(Long id, String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, DomainEquipement.EquipementType equipementTypeEquipement, DomainEquipement.StateType stateTypeEquipement, DomainLocation domainLocation) throws DomainException {
        Date now = new Date();
        DomainEquipement domainEquipement = DomainEquipement.newModifiedStateInstance( stationNameEquipement,
        		serialNumberEquipement,markEquipement, modelEquipement, attributionDateEquipement, 
        		returnDateEquipement, purchaseDateEquipement, expectedDateEquipement, commentsEquipement,
        		equipementTypeEquipement,  stateTypeEquipement, domainLocation, id);
        Equipement Equipement = equipementRepository.findOne(domainEquipement.getId());
        if (Equipement == null) {
            throw new DomainException("This Equipement does not exist");
        }
        Equipement.setStationName(domainEquipement.getStationNameEquipement());
		Equipement.setSerialNumber(domainEquipement.getSerialNumberEquipement());
		Equipement.setMark(domainEquipement.getMarkEquipement());
		Equipement.setModel(domainEquipement.getModelEquipement());
		Equipement.setAttributionDate(domainEquipement.getAttributionDateEquipement());
		Equipement.setReturnDate(domainEquipement.getReturnDateEquipement());
		Equipement.setPurchaseDate(domainEquipement.getPurchaseDateEquipement());
		Equipement.setExpectedDate(domainEquipement.getExpectedDateEquipement());
		Equipement.setComments(domainEquipement.getCommentsEquipement());

		//Equipement.setEquipmentType(domainEquipement.getEquipementTypeEquipement());
         Equipement.setEquipmentType(domainEquipement.getEquipementType().toString());
		Equipement.setStateType(domainEquipement.getStateType().toString());
		
		// debut le 28/12/2016
		
		Equipement.setLocation(locationMapper.toOneEntity(domainEquipement.getDomainLocation()));
        
        
    }
    
    @Transactional
    public DomainEquipement findEquipement(Long id) throws DomainException
    {
    	Equipement equipement = new Equipement();
    	equipement = equipementRepository.findOne(id);
    	DomainEquipement domainEquipement = equipementMapper.toOneDomain(equipement);
    	return domainEquipement;
    }

  
    @Override
    @Transactional
    public void deleteEquipement(Long id) throws DomainException {
        Date now = new Date();
        //DomainEquipement domainEquipement = DomainEquipement.newSuppressedStateInstance(stationNameEquipement,serialNumberEquipement,markEquipement, modelEquipement, attributionDateEquipement, returnDateEquipement, purchaseDateEquipement, expectedDateEquipement, commentsEquipement,  equipementTypeEquipement,  stateTypeEquipement);
       
       
        Equipement equipement = new Equipement();
        Equipement existingEquipement = equipementRepository.findOne(equipement.getId());
        if (existingEquipement == null) {
            throw new DomainException("This Equipement has not be found");
        } else {
          //  logTrackEquipement(now, "DEFAULT DELETE MESSAGE", DomainHistoryLog.newEquipementInstance(domainEquipement));
            equipementRepository.delete(existingEquipement);
        }
    }


    /**
     * Track an history log - Can be provide as an internal transactional service
     * @param now
     * @param message
     * @param domainHistoryLog
     */
 /*   private void logTrackEquipement(Date now, String message, DomainHistoryLog domainHistoryLog) {
        HistoryLog historyLog = new HistoryLog(domainHistoryLog.getDynRootContextName(),
                domainHistoryLog.getSourceId(),
                now,
                domainHistoryLog.getUserId(),
                domainHistoryLog.getNewState(),
                domainHistoryLog.getMessage());

        historyLog.setMessage(message);
        historyLogRepository.save(historyLog);
    }*/

    @Override
    @Transactional
    public Set<DomainEquipement> listAll() throws DomainException {
        return new HashSet<>(equipementMapper.toDomains(equipementRepository.findAll()));
    }

    
	@Override
	public <T extends DomainEquipement> void createEquipement(T typedEquipement) throws DomainException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends DomainEquipement> void updateEquipement(T typedEquipement) throws DomainException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends DomainEquipement> void deleteEquipement(T typedEquipement) throws DomainException {
		// TODO Auto-generated method stub
		
	}
  
	  public DomainEquipement  findOne(Long id) throws DomainException {
	    	//DomainEquipement domainEquipement= new DomainEquipement();
	    	Equipement Equipement = equipementRepository.findOne(id);
	    	DomainEquipement domainEquipement=	equipementMapper.toOneDomain(Equipement);
	    	return domainEquipement;
	    }
	
}