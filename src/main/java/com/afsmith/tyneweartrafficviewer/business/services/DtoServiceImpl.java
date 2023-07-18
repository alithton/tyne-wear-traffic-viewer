package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.mappers.*;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.afsmith.tyneweartrafficviewer.util.TypeConversionLibrary.downcastList;

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

    /**
     * Get a list of all available data of the requested type in a data transfer format ready to
     * be served to the front end.
     * @param dataType The type of data requested.
     * @return A list of data transfer objects of the type requested.
     * @param <DTO> The type of data transfer object corresponding to the requested data type.
     * @param <T> The entity corresponding to the requested type.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <DTO extends MappableDTO, T extends TrafficEntity> List<DTO> listAll(TrafficDataTypes dataType) {

        // These casts are known to be safe as there is a finite number of possible return values (based on the
        // enum dataType), all of which are safe.
        var mapper = (TrafficDataMapper<DTO, T>) getMapper(dataType);
        Class<T> entityClass = (Class<T>) dataType.getEntityClass();

        List<TrafficEntity> entities = dataPersistence.listAll(dataType);
        List<T> validatedEntities = downcastList(entities, entityClass);
        return mapper.entityToDto(validatedEntities);
    }

    /**
     * Get the most recent image from the traffic camera specified by the system
     * code number.
     * @param systemCodeNumber ID of the requested traffic camera.
     * @return An array of bytes corresponding to the image.
     */
    @Override
    public byte[] getImage(String systemCodeNumber) {
        return dataPersistence.getImage(systemCodeNumber);
    }

    // Get the appropriate mapper for the requested data type.
    private TrafficDataMapper<? extends MappableDTO, ? extends TrafficEntity> getMapper(TrafficDataTypes dataType) {
        return switch(dataType) {
            case INCIDENT -> incidentMapper;
            case EVENT -> eventMapper;
            case ACCIDENT -> accidentMapper;
            case ROADWORKS -> roadworkMapper;
            case SPEED, TYPICAL_SPEED -> journeyTimeMapper;
            case CAMERA -> cameraMapper;
        };
    }
}
