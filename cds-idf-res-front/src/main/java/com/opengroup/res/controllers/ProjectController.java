package com.opengroup.res.controllers;

import com.opengroup.res.core.ProjectServices;
import com.opengroup.res.core.domain.DomainProject;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.mappers.ProjectRepresentationMapper;
import com.opengroup.res.model.ProjectRepresentation;
import com.opengroup.res.util.FrontException;
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

import java.util.HashSet;
import java.util.Set;

/**
 * A REST Controller which provides API to manage application parameters
 *
 * @author Open group
 * @since 1.0.0
 */
@RestController
public class ProjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectServices projectServices;

    @Autowired
    private ProjectRepresentationMapper projectRepresentationMapper;

    /**
     *
     * @return
     * @throws DomainException
     */
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public Set<ProjectRepresentation> list() throws FrontException {
        Set<ProjectRepresentation> projects;
        try {
            projects = new HashSet<>(projectRepresentationMapper.toRepresentations(projectServices.listAll()));
        } catch (DomainException e) {
            String message = "Internal error : "+ e.getMessage();
            LOGGER.error(message, e);
            throw new FrontException(message, e);
        }
        return projects;
    }
    
    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProjectRepresentation> get(@PathVariable("id") Long id) throws DomainException,FrontException {
		ProjectRepresentation 
		projectRepresentation = projectRepresentationMapper.toOneRepresentation(projectServices.findProject(id));
		if (projectRepresentation == null) {
			return new ResponseEntity<ProjectRepresentation>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ProjectRepresentation>(projectRepresentation, HttpStatus.OK);
		}
	}
    
    @RequestMapping(value = "/project/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody ProjectRepresentation projectRepresentation, UriComponentsBuilder ucBuilder) throws DomainException, FrontException {
		if (projectRepresentation.getId() != null && projectServices.findProject(projectRepresentation.getId()) != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			
			DomainProject domainProject = DomainProject.newInstance(
					projectRepresentation.getProjectName(),  
					projectRepresentation.getPeriodStart(), 
					projectRepresentation.getPeriodEnd(), 
					projectRepresentation.getId()
					);
			
			projectServices.createProject(domainProject.getProjectName(), domainProject.getPeriodStart(), domainProject.getPeriodEnd());
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/project/{id}").buildAndExpand(projectRepresentation.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	}
    
    @RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE)
   	public ResponseEntity<ProjectRepresentation> delete(@PathVariable("id") Long id) throws DomainException {
   		ProjectRepresentation projectRepresentation = 
   				projectRepresentationMapper.toOneRepresentation(projectServices.findProject(id));
   		if (projectRepresentation.getId() == null) {
   			return new ResponseEntity<ProjectRepresentation>(HttpStatus.NOT_FOUND);
   		}
   		DomainProject domainProject = DomainProject.newInstance(
   				projectRepresentation.getProjectName(), 
   				projectRepresentation.getPeriodStart(), 
   				projectRepresentation.getPeriodEnd(), 
   				projectRepresentation.getId()
   				);
   		System.out.println(domainProject.toString());
   		projectServices.deleteProject(
   				projectRepresentation.getId(),
   				projectRepresentation.getProjectName(), 
   				projectRepresentation.getPeriodStart(), 
   				projectRepresentation.getPeriodEnd()
   				);
   		return new ResponseEntity<ProjectRepresentation>(HttpStatus.NO_CONTENT);
   	}
    
    @RequestMapping(value = "/project/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ProjectRepresentation> update(@PathVariable("id") Long id, @RequestBody ProjectRepresentation projectRepresentation) throws DomainException {
		ProjectRepresentation currentProjectRepresentation = 
				projectRepresentationMapper.toOneRepresentation(projectServices.findProject(id));

		if (currentProjectRepresentation.getId() == null) {
			return new ResponseEntity<ProjectRepresentation>(HttpStatus.NOT_FOUND);
		} 
		else 
		{
			DomainProject domainProject = DomainProject.newInstance(
   				projectRepresentation.getProjectName(), 
   				projectRepresentation.getPeriodStart(), 
   				projectRepresentation.getPeriodEnd(), 
   				projectRepresentation.getId()
   				);
   		System.out.println(domainProject.toString());
   		projectServices.updateProject(
   				projectRepresentation.getId(),
   				projectRepresentation.getProjectName(), 
   				projectRepresentation.getPeriodStart(), 
   				projectRepresentation.getPeriodEnd()
   				);
			currentProjectRepresentation = 
					projectRepresentationMapper.toOneRepresentation(projectServices.findProject(currentProjectRepresentation.getId()));
			return new ResponseEntity<ProjectRepresentation>(currentProjectRepresentation, HttpStatus.OK);
		}
	}
}
