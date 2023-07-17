package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.mappers.*;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.afsmith.tyneweartrafficviewer.util.TypeConversionLibrary.downcastList;

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
