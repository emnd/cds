package com.opengroup.res.core.impl;

import com.opengroup.res.core.EquipementServices;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainHistoryLog;
import com.opengroup.res.core.domain.DomainParameter;
import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.impl.mappers.EquipementMapper;
import com.opengroup.res.core.impl.mappers.LocationMapper;
import com.opengroup.res.enums.EquipementType;
import com.opengroup.res.enums.StateType;
import com.opengroup.res.jpa.HistoryLogRepository;
import com.opengroup.res.jpa.EquipementRepository;
import com.opengroup.res.jpa.entities.AuditState;
import com.opengroup.res.jpa.entities.HistoryLog;
import com.opengroup.res.jpa.entities.Parameter;

import com.opengroup.res.jpa.entities.Equipement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private EquipementRepository EquipementRepository;

    @Autowired
    private EquipementMapper EquipementMapper;

   	@Autowired
	private LocationMapper locationMapper;
   	
    @Override
    @Transactional
    public void createEquipement(String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, EquipementType equipementTypeEquipement, StateType stateTypeEquipement) throws DomainException {
        Date now = new Date();
        DomainEquipement domainEquipement = DomainEquipement.newCreatedStateInstance(stationNameEquipement,serialNumberEquipement,markEquipement, modelEquipement, attributionDateEquipement, returnDateEquipement, purchaseDateEquipement, expectedDateEquipement, commentsEquipement,  equipementTypeEquipement,  stateTypeEquipement);
        EquipementRepository.save(EquipementMapper.toOneEntity(domainEquipement));
        //logTrackEquipement(now,"DEFAULT CREATION MESSAGE", DomainHistoryLog.newEquipementInstance(domainEquipement));
    }

    @Override
    @Transactional
    public void updateEquipement(Long idEquipement, String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, EquipementType equipementTypeEquipement, StateType stateTypeEquipement) throws DomainException {
        Date now = new Date();
        DomainEquipement domainEquipement = DomainEquipement.newModifiedStateInstance(stationNameEquipement,serialNumberEquipement,markEquipement, modelEquipement, attributionDateEquipement, returnDateEquipement, purchaseDateEquipement, expectedDateEquipement, commentsEquipement,  equipementTypeEquipement,  stateTypeEquipement);
        Equipement Equipement = EquipementRepository.findOne(domainEquipement.getIdEquipement());
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

		Equipement.setEquipmentType(domainEquipement.getEquipementTypeEquipement());
		Equipement.setStateType(domainEquipement.getStateTypeEquipement());
		
		// debut le 28/12/2016
		
		Equipement.setLocation(locationMapper.toOneEntity(domainEquipement.getdomainLocation()));
        
        //logTrackEquipement(now,  "DEFAULT UPDATE MESSAGE", DomainHistoryLog.newEquipementInstance(domainEquipement));
        EquipementRepository.save(Equipement);
    }

    @Override
    @Transactional
    public void deleteEquipement(Long idEquipement,String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, EquipementType equipementTypeEquipement, StateType stateTypeEquipement) throws DomainException {
        Date now = new Date();
        DomainEquipement domainEquipement = DomainEquipement.newSuppressedStateInstance(stationNameEquipement,serialNumberEquipement,markEquipement, modelEquipement, attributionDateEquipement, returnDateEquipement, purchaseDateEquipement, expectedDateEquipement, commentsEquipement,  equipementTypeEquipement,  stateTypeEquipement);
       
       
        Equipement equipement = new Equipement();
        Equipement existingEquipement = EquipementRepository.findOne(equipement.getId());
        if (existingEquipement == null) {
            throw new DomainException("This Equipement has not be found");
        } else {
           // logTrackEquipement(now, "DEFAULT DELETE MESSAGE", DomainHistoryLog.newEquipementInstance(domainEquipement));
            EquipementRepository.delete(existingEquipement);
        }
    }


    /**
     * Track an history log - Can be provide as an internal transactional service
     * @param now
     * @param message
     * @param domainHistoryLog
     */
    private void logTrackEquipement(Date now, String message, DomainHistoryLog domainHistoryLog) {
        HistoryLog historyLog = new HistoryLog(domainHistoryLog.getDynRootContextName(),
                domainHistoryLog.getSourceId(),
                now,
                domainHistoryLog.getUserId(),
                domainHistoryLog.getNewState(),
                domainHistoryLog.getMessage());

        historyLog.setMessage(message);
        historyLogRepository.save(historyLog);
    }

    @Override
    @Transactional
    public Set<DomainEquipement> listAll() throws DomainException {
        return new HashSet<>(EquipementMapper.toDomains(EquipementRepository.findAll()));
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
   
}