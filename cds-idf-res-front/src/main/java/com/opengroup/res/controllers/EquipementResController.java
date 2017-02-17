package com.opengroup.res.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.opengroup.res.core.EquipementServices;
import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainLocation;
import com.opengroup.res.core.impl.mappers.EquipementMapper;
import com.opengroup.res.core.impl.mappers.LocationMapper;
import com.opengroup.res.jpa.EquipementRepository;
import com.opengroup.res.mappers.EquipementRepresentationMapper;
import com.opengroup.res.mappers.LocationRepresentationMapper;
import com.opengroup.res.model.EquipementRepresentation;
import com.opengroup.res.model.LocationRepresentation;

/**
 * A REST Controller which provides API to manage application parameters
 *
 * @author Open group
 * @since 1.0.0
 */
@RestController
public class EquipementResController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EquipementResController.class);

    @Autowired
    private EquipementServices equipementServices;

    @Autowired
    private EquipementRepresentationMapper equipementRepresentationMapper;
    
    @Autowired
    private EquipementMapper equipementMapper;

    
    @Autowired
    private  EquipementRepository equipementRepository;
    
    @Autowired
    private LocationMapper locationMapper;
    
    @Autowired
    private LocationRepresentationMapper locationRepresentationMapper;

    /**
     *
     * @return
     * @throws DomainException
     */
  //methode get
    @RequestMapping(value = "/services/equipement/", method = RequestMethod.GET)
    public ResponseEntity<List<EquipementRepresentation>> listAll() throws DomainException {
    
    List<EquipementRepresentation> equipementRepresentation = equipementRepresentationMapper.toRepresentations(equipementServices.listAll());
    if (equipementRepresentation == null) {
    return new ResponseEntity<List<EquipementRepresentation>>(HttpStatus.NO_CONTENT);
    } else {
    return new ResponseEntity<List<EquipementRepresentation>>(equipementRepresentation, HttpStatus.OK);
    }
    }

   //methode get by id 
    @RequestMapping(value = "/services/equipement/{id}", method = RequestMethod.GET)
    public ResponseEntity<EquipementRepresentation> get(@PathVariable("id") Long id) throws DomainException {
    	DomainEquipement domaineEquipement = equipementServices.findOne(id);
		if (domaineEquipement == null) {
			return new ResponseEntity<EquipementRepresentation>(HttpStatus.NOT_FOUND);
		} else {
			EquipementRepresentation equipementRepresentation = equipementRepresentationMapper.toOneRepresentation(domaineEquipement);
			return new ResponseEntity<EquipementRepresentation>(equipementRepresentation, HttpStatus.OK);
		
	}
    }
    
    //methode delete
    @RequestMapping(value = "/services/equipement/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<EquipementRepresentation> delete(@PathVariable("id") Long id) throws DomainException {
    EquipementRepresentation equipementRepresentation = equipementRepresentationMapper.toOneRepresentation(equipementServices.findOne(id));
    if (equipementRepresentation == null) {
    return new ResponseEntity<EquipementRepresentation>(HttpStatus.NOT_FOUND);
    }
    equipementServices.deleteEquipement(id);
    return new ResponseEntity<EquipementRepresentation>(HttpStatus.NO_CONTENT);
    }

    
  // methode post  

@RequestMapping(value = "/services/equipement/", method = RequestMethod.POST)
public ResponseEntity<Void> create(@RequestBody EquipementRepresentation equipementRepresentation, UriComponentsBuilder ucBuilder) throws DomainException {
	// DomainEquipement domainEquipement = equipementRepresentationMapper.toOneDomain(equipementRepresentation);
	if(equipementRepresentation.getId() != null && equipementServices.findEquipement(equipementRepresentation.getId()) != null )
	{
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
	else {

		LocationRepresentation locationRepresentation = equipementRepresentation.getLocationRepresentation();
		DomainLocation domainLocation = locationRepresentationMapper.toOneDomain(locationRepresentation);
		// System.out.println("domainLocation :"+domainLocation.toString());
	    DomainEquipement domainEquipement = DomainEquipement.newCreatedStateInstance(equipementRepresentation.getStationNameEquipement(),equipementRepresentation.getSerialNumberEquipement(),
	    		equipementRepresentation.getMarkEquipement(),equipementRepresentation.getModelEquipement(), 
	    		equipementRepresentation.getAttributionDateEquipement(), equipementRepresentation.getReturnDateEquipement(),
	    		equipementRepresentation.getPurchaseDateEquipement(),equipementRepresentation.getExpectedDateEquipement(), 
	    		equipementRepresentation.getCommentsEquipement(), equipementRepresentation.getEquipementType(), 
	    		 equipementRepresentation.getStateType(), domainLocation ,equipementRepresentation.getId());
		
	//	 System.out.println("domainLocation :"+domainLocation.toString());
	//	 System.out.println("domainequipement :"+domainEquipement.toString());
		 equipementServices.createEquipement(
				 equipementRepresentation.getStationNameEquipement(),equipementRepresentation.getSerialNumberEquipement(),
		    		equipementRepresentation.getMarkEquipement(),equipementRepresentation.getModelEquipement(), 
		    		equipementRepresentation.getAttributionDateEquipement(), equipementRepresentation.getReturnDateEquipement(),
		    		equipementRepresentation.getPurchaseDateEquipement(),equipementRepresentation.getExpectedDateEquipement(), 
		    		equipementRepresentation.getCommentsEquipement(), equipementRepresentation.getEquipementType(), 
		    		 equipementRepresentation.getStateType(), domainLocation 
		    		 );

	}
	HttpHeaders headers = new HttpHeaders();
	 headers.setLocation(ucBuilder.path("/services/equipement/{id}").buildAndExpand(equipementRepresentation.getId()).toUri());
	 return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
}
	


  //methose put
    @RequestMapping(value = "/services/equipement/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EquipementRepresentation> update(@PathVariable("id") Long id, @RequestBody EquipementRepresentation equipementRepresentation) throws DomainException {
    DomainEquipement domainEquipementRepresentation = equipementServices.findOne(id);
    if (domainEquipementRepresentation == null) {
    return new ResponseEntity<EquipementRepresentation>(HttpStatus.NOT_FOUND);
    } else {

    	LocationRepresentation locationRepresentation = equipementRepresentation.getLocationRepresentation();
		DomainLocation domainLocation = locationRepresentationMapper.toOneDomain(locationRepresentation);
    equipementServices.updateEquipement(equipementRepresentation.getId(), equipementRepresentation.getStationNameEquipement(),equipementRepresentation.getSerialNumberEquipement(),
    		equipementRepresentation.getMarkEquipement(),equipementRepresentation.getModelEquipement(), 
    		equipementRepresentation.getAttributionDateEquipement(), equipementRepresentation.getReturnDateEquipement(),
    		equipementRepresentation.getPurchaseDateEquipement(),equipementRepresentation.getExpectedDateEquipement(), 
    		equipementRepresentation.getCommentsEquipement(),  equipementRepresentation.getEquipementType(), 
    		equipementRepresentation.getStateType(),domainLocation);
    return new ResponseEntity<EquipementRepresentation>(HttpStatus.OK);
    }
    }
}
