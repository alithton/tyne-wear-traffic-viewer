package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDTO;
import com.afsmith.tyneweartrafficviewer.business.data.NewTrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficPointDataDTO;
import com.afsmith.tyneweartrafficviewer.business.mappers.*;
import com.afsmith.tyneweartrafficviewer.business.services.filter.FilterService;
import com.afsmith.tyneweartrafficviewer.entities.*;
import com.afsmith.tyneweartrafficviewer.exceptions.DataNotFoundException;
import com.afsmith.tyneweartrafficviewer.exceptions.InvalidTrafficDataException;
import com.afsmith.tyneweartrafficviewer.exceptions.NotAuthenticatedException;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.afsmith.tyneweartrafficviewer.util.TypeConversionLibrary.downcastList;
import static com.afsmith.tyneweartrafficviewer.business.mappers.NewTrafficDataMapper.convert;

/**
 * A service provides requested traffic data in a format that is ready to be sent
 * to the frontend for display to the user. For most data types, this involves
 * mapping from the internal data model to a simplified data transfer model that
 * strips out details that are not needed by the frontend.
 */
@RequiredArgsConstructor
@Service
public class DtoServiceImpl implements DtoService {
    private final TrafficIncidentMapper incidentMapper;
    private final TrafficEventMapper eventMapper;
    private final TrafficAccidentMapper accidentMapper;
    private final TrafficRoadworkMapper roadworkMapper;
    private final JourneyTimeMapper journeyTimeMapper;
    private final CameraMapper cameraMapper;
    private final TrafficDataPersistence dataPersistence;
    private final UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <DTO extends TrafficDTO, T extends TrafficEntity>
            List<DTO> listAll(TrafficDataTypes dataType) {

        // These casts are known to be safe as there is a finite number of possible return values (based on the
        // enum dataType), all of which are safe.
        var mapper = (TrafficDataMapper<DTO, T>) getMapper(dataType);
        List<T> entities = listEntities(dataType);
        return mapper.entityToDto(entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <DTO extends TrafficDTO, T extends TrafficEntity> List<DTO> listAll(TrafficDataTypes dataType, FilterService filter) {
        // These casts are known to be safe as there is a finite number of possible return values (based on the
        // enum dataType), all of which are safe.
        var mapper = (TrafficDataMapper<DTO, T>) getMapper(dataType);
        List<T> entities = listEntities(dataType);
        List<T> filtered = filter.filter(entities);
        return mapper.entityToDto(filtered);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getImage(String systemCodeNumber) {
        return dataPersistence.getImage(systemCodeNumber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <DTO extends TrafficPointDataDTO, T extends TrafficPointData>
            DTO getIncident(String codeNumber)
            throws DataNotFoundException {
        T entity = dataPersistence.find(codeNumber);
        @SuppressWarnings("unchecked")
        var mapper = (TrafficDataMapper<DTO, T>) getMapper(entity.getType());
        return mapper.entityToDto(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(NewTrafficDataDTO trafficData, String token)
            throws NotAuthenticatedException, InvalidTrafficDataException {
        User user = userService.findByToken(token);
        if (!trafficData.isValid()) throw new InvalidTrafficDataException("Provided traffic data is not valid.");

        TrafficPointData pointData = toEntity(trafficData);
        pointData.setCreatedBy(user);

        dataPersistence.persist(pointData, trafficData.getType());
    }

    // Get the appropriate mapper for the requested data type.
    private TrafficDataMapper<? extends TrafficDTO, ? extends TrafficEntity> getMapper(TrafficDataTypes dataType) {
        return switch(dataType) {
            case INCIDENT -> incidentMapper;
            case EVENT -> eventMapper;
            case ACCIDENT -> accidentMapper;
            case ROADWORKS -> roadworkMapper;
            case SPEED, TYPICAL_SPEED -> journeyTimeMapper;
            case CAMERA -> cameraMapper;
        };
    }

    // Convert the provided new traffic data to an entity of the appropriate type.
    private TrafficPointData toEntity(NewTrafficDataDTO newTrafficData) {
        TrafficDataTypes dataType = newTrafficData.getType();
        return switch(dataType) {
            case INCIDENT -> convert(newTrafficData).toIncident();
            case EVENT -> convert(newTrafficData).toEvent();
            case ACCIDENT -> convert(newTrafficData).toAccident();
            case ROADWORKS -> convert(newTrafficData).toRoadwork();
            default -> throw new UnsupportedOperationException("Unable to save traffic data of type " + dataType + ".");
        };
    }

    // Get a list of all entities in the database of the specified traffic data type.
    @SuppressWarnings("unchecked")
    private <T extends TrafficEntity>
    List<T> listEntities(TrafficDataTypes dataType) {
        Class<T> entityClass = (Class<T>) dataType.getEntityClass();
        List<TrafficEntity> entities = dataPersistence.listAll(dataType);
        return downcastList(entities, entityClass);
    }

}
