package com.opengroup.res.core.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opengroup.res.core.CollaboratorServices;
import com.opengroup.res.core.EquipementServices;
import com.opengroup.res.core.LocationServices;
import com.opengroup.res.core.domain.DomainAutorisation;
import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.domain.DomainException;
//import com.opengroup.res.core.domain.DomainHistoryLog;
import com.opengroup.res.core.domain.DomainLocation;
import com.opengroup.res.core.impl.mappers.CollaboratorMapper;
import com.opengroup.res.core.impl.mappers.EquipementMapper;
import com.opengroup.res.core.impl.mappers.LocationMapper;
import com.opengroup.res.jpa.CollaboratorRepository;
import com.opengroup.res.jpa.EquipementRepository;
import com.opengroup.res.jpa.HistoryLogRepository;
import com.opengroup.res.jpa.LocationRepository;
import com.opengroup.res.jpa.entities.Collaborator;
import com.opengroup.res.jpa.entities.Equipement;
//import com.opengroup.res.jpa.entities.HistoryLog;
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
   	
   	@Autowired
	private CollaboratorMapper collaboratorMapper;
   	
   	@Autowired
	private CollaboratorRepository collaboratorRepository;
   	@Autowired
	private CollaboratorServices collaboratorServices;
   	
   	@Autowired
	private LocationServices locationServices;
   	
    @Override
    @Transactional
    public void createEquipement(String stationNameEquipement, String serialNumberEquipement, String markEquipement,
    		String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement,
    		Date expectedDateEquipement, String commentsEquipement, DomainEquipement.EquipementType equipementTypeEquipement,
    		DomainEquipement.StateType stateTypeEquipement,DomainLocation domainLocation,DomainCollaborator domainCollaborator) throws DomainException {
    
    	Long id= null;
//    	Location location = locationMapper.toOneEntity(domainLocation);
//    	if(domainLocation.getId() == null)
//    	{
//    		locationRepository.save(location);
//    	}
    	
    	Collaborator collaborator = new Collaborator();
    	Collaborator existingCollaborator = collaboratorServices.findCollaborator(domainCollaborator.getLoginOpen(), domainCollaborator.getEmailOpen()); // recherche du collaborateur en base sinon l'inserer

    	collaborator = (existingCollaborator.getId() != null) ? existingCollaborator : collaboratorMapper.toOneEntity(domainCollaborator);
    	
    	if(collaborator.getId()== null)
    	{
    		collaboratorRepository.save(collaborator);   // creation du collaborator s'il n'existe pas
    	}
    	
    	Location location = new Location();
    	Location existingLocation = locationServices.findLocation(domainLocation.getNameLocation(), domainLocation.getBlockLocation(), domainLocation.getPlaceLocation()); // recherche du collaborateur en base sinon l'inserer

    	location = (existingLocation.getId() != null) ? existingLocation : locationMapper.toOneEntity(domainLocation);
    	
    	if(location.getId()== null)
    	{
    		locationRepository.save(location);   // creation du collaborator s'il n'existe pas
    	}
    	
    	domainLocation = locationMapper.toOneDomain(location);
    	domainCollaborator = collaboratorMapper.toOneDomain(collaborator); // transformation du collaborator crée en domainCollaborator
    	
        DomainEquipement domainEquipement = DomainEquipement.newCreatedStateInstance(stationNameEquipement,
        		serialNumberEquipement,markEquipement, modelEquipement, attributionDateEquipement, returnDateEquipement, 
        		purchaseDateEquipement, expectedDateEquipement, commentsEquipement,  equipementTypeEquipement,  stateTypeEquipement, 
        		domainLocation,domainCollaborator,id);
        equipementRepository.save(equipementMapper.toOneEntity(domainEquipement));
       // logTrackEquipement(now,"DEFAULT CREATION MESSAGE", DomainHistoryLog.newEquipementInstance(domainEquipement));
    }

    @Override
    @Transactional
    public void updateEquipement(Long id, String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, DomainEquipement.EquipementType equipementTypeEquipement, DomainEquipement.StateType stateTypeEquipement, DomainLocation domainLocation,DomainCollaborator domainCollaborator) throws DomainException {
        Date now = new Date();
        DomainEquipement domainEquipement = DomainEquipement.newModifiedStateInstance( stationNameEquipement,
        		serialNumberEquipement,markEquipement, modelEquipement, attributionDateEquipement, 
        		returnDateEquipement, purchaseDateEquipement, expectedDateEquipement, commentsEquipement,
        		equipementTypeEquipement,  stateTypeEquipement, domainLocation, domainCollaborator,id);
        Equipement equipement = equipementRepository.findOne(domainEquipement.getId());
        if (equipement == null) {
            throw new DomainException("This Equipement does not exist");
        }
        equipement.setStationName(domainEquipement.getStationNameEquipement());
		equipement.setSerialNumber(domainEquipement.getSerialNumberEquipement());
		equipement.setMark(domainEquipement.getMarkEquipement());
		equipement.setModel(domainEquipement.getModelEquipement());
		equipement.setAttributionDate(domainEquipement.getAttributionDateEquipement());
		equipement.setReturnDate(domainEquipement.getReturnDateEquipement());
		equipement.setPurchaseDate(domainEquipement.getPurchaseDateEquipement());
		equipement.setExpectedDate(domainEquipement.getExpectedDateEquipement());
		equipement.setComments(domainEquipement.getCommentsEquipement());

		//Equipement.setEquipmentType(domainEquipement.getEquipementTypeEquipement());
         equipement.setEquipmentType(domainEquipement.getEquipementType().toString());
		equipement.setStateType(domainEquipement.getStateType().toString());
		
		// debut le 28/12/2016
		
		equipement.setLocation(locationMapper.toOneEntity(domainEquipement.getDomainLocation()));
		equipement.setCollaborator(collaboratorMapper.toOneEntity(domainEquipement.getDomainCollaborator()));
        
        
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
    @Transactional
    public List<DomainEquipement> fullListAllWithoutCollab() throws DomainException {
    	//System.out.println("je recupère la liste des equipements en entity");
    	List<Equipement> equipements = equipementRepository.findAllWithoutCollab();
    	//System.out.println(" le nombre equipements : "+equipements.size());
    	//System.out.println("nombre des domainEquipements :");
    	try{
    		List<DomainEquipement> domainEquipements = equipementMapper.convertEntityListToDomainList(equipements);
    	//System.out.println("nombre des domainEquipements"+domainEquipements.size());
    		return domainEquipements;
    	}
    	catch(NullPointerException e){//System.out.println("null pointer");
    		return null;
    	}
       
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