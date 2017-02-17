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

import com.opengroup.res.core.LocationServices;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainLocation;
import com.opengroup.res.core.impl.mappers.LocationMapper;
import com.opengroup.res.jpa.LocationRepository;
import com.opengroup.res.mappers.LocationRepresentationMapper;
import com.opengroup.res.model.LocationRepresentation;
/**
 * A REST Controller which provides API to manage application parameters
 *
 * @author Open group
 * @since 1.0.0
 */
@RestController
public class LocationResController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationResController.class);

    @Autowired
    private LocationServices locationServices;

    @Autowired
    private LocationRepresentationMapper locationRepresentationMapper;
    
    @Autowired
    private LocationMapper locationMapper;

    
    @Autowired
    private  LocationRepository locationRepository;
    
    /**
     *
     * @return
     * @throws DomainException
     */
  //methode get
    @RequestMapping(value = "/services/location/", method = RequestMethod.GET)
    public ResponseEntity<List<LocationRepresentation>> listAll() throws DomainException {
    
    List<LocationRepresentation> locationRepresentation = locationRepresentationMapper.toRepresentations(locationServices.listAll());
    if (locationRepresentation == null) {
    return new ResponseEntity<List<LocationRepresentation>>(HttpStatus.NO_CONTENT);
    } else {
    return new ResponseEntity<List<LocationRepresentation>>(locationRepresentation, HttpStatus.OK);
    }
    }

   //methode get by id 
    @RequestMapping(value = "/services/location/{id}", method = RequestMethod.GET)
    public ResponseEntity<LocationRepresentation> get(@PathVariable("id") Long id) throws DomainException {
    	DomainLocation domaineLocation = locationServices.findOne(id);
		if (domaineLocation == null) {
			return new ResponseEntity<LocationRepresentation>(HttpStatus.NOT_FOUND);
		} else {
			LocationRepresentation locationRepresentation = locationRepresentationMapper.toOneRepresentation(domaineLocation);
			return new ResponseEntity<LocationRepresentation>(locationRepresentation, HttpStatus.OK);
		
	}
    }
    
    //methode delete
    @RequestMapping(value = "/services/location/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<LocationRepresentation> delete(@PathVariable("id") Long id) throws DomainException {
    LocationRepresentation locationRepresentation = locationRepresentationMapper.toOneRepresentation(locationServices.findOne(id));
    if (locationRepresentation == null) {
    return new ResponseEntity<LocationRepresentation>(HttpStatus.NOT_FOUND);
    }
    locationServices.deleteLocation(id);
    return new ResponseEntity<LocationRepresentation>(HttpStatus.NO_CONTENT);
    }

    
  // methode post  
  

	
@RequestMapping(value = "/services/location/", method = RequestMethod.POST)
public ResponseEntity<Void> create(@RequestBody LocationRepresentation locationRepresentation, UriComponentsBuilder ucBuilder) throws DomainException {
	if(locationRepresentation.getId() != null && locationServices.findLocation(locationRepresentation.getId()) != null )
	{
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
	else {
	//DomainLocation domainLocation = locationRepresentationMapper.toOneDomain(locationRepresentation);
	 
	
    DomainLocation domainLocation = DomainLocation.newCreatedStateInstance(locationRepresentation.getNameLocation(),
    		locationRepresentation.getBlockLocation(), 
    		locationRepresentation.getPlaceLocation(),
    		locationRepresentation.getId());
	
	//System.out.println(domainLocation);
	 locationServices.createLocation(locationRepresentation.getNameLocation(),
	    		locationRepresentation.getBlockLocation(), 
	    		locationRepresentation.getPlaceLocation()
	    		);
	// locationRepository.save(locationMapper.toOneEntity(domainLocation));
	 
	 HttpHeaders headers = new HttpHeaders();
	 	headers.setLocation(ucBuilder.path("/services/location/{id}").buildAndExpand(locationRepresentation.getId()).toUri());
	 	return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	
}

  //methose put
    @RequestMapping(value = "/services/location/{id}", method = RequestMethod.PUT)
    public ResponseEntity<LocationRepresentation> update(@PathVariable("id") Long id, @RequestBody LocationRepresentation locationRepresentation) throws DomainException {
    DomainLocation domainLocationRepresentation = locationServices.findOne(id);
    if (domainLocationRepresentation == null) {
    return new ResponseEntity<LocationRepresentation>(HttpStatus.NOT_FOUND);
    } else {
     		
 
    locationServices.updateLocation(locationRepresentation.getNameLocation(),
    		locationRepresentation.getBlockLocation(), 
    		locationRepresentation.getPlaceLocation(),
    		locationRepresentation.getId());
    return new ResponseEntity<LocationRepresentation>(HttpStatus.OK);
    }
    }
}
