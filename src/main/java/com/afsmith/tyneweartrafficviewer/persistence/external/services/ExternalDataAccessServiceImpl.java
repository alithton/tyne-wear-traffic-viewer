package com.afsmith.tyneweartrafficviewer.persistence.external.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import com.afsmith.tyneweartrafficviewer.persistence.external.client.OpenDataServiceClient;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.DynamicDataExternal;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.ExternalDataTypes;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficDataExternal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@inheritDoc}
 */
@RequiredArgsConstructor
@Service
public class ExternalDataAccessServiceImpl implements ExternalDataAccessService {

    private final OpenDataServiceClient client;

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends TrafficEntity> List<E> getData(TrafficDataTypes dataType) throws IOException {

        // Speed and camera data have dynamic and static components that need to be joined.
        if (dataType == TrafficDataTypes.SPEED || dataType == TrafficDataTypes.CAMERA) {
            return combineStaticAndDynamicData(dataType);
        } else {
            // For all other data types there is a 1:1 relationship between external data classes and entities.
            List<TrafficDataExternal<E>> externalData = getExternalData(dataType, false);
            return convertToEntities(externalData);
        }
    }

    /*
     * Given a provided (internal) data type, get the corresponding data type in the external Open Data
     * Service API. This is mostly the same as the internal data type, but data for traffic speed and
     * CCTV cameras are split between 'static' and 'dynamic' types, which have different API end points
     * and JSON data formats.
     */
    private ExternalDataTypes getExternalDataType(TrafficDataTypes dataType, boolean dynamic) {
        return switch (dataType) {
            case INCIDENT -> ExternalDataTypes.INCIDENT;
            case EVENT -> ExternalDataTypes.EVENT;
            case ACCIDENT -> ExternalDataTypes.ACCIDENT;
            case ROADWORKS -> ExternalDataTypes.ROADWORKS;
            case SPEED -> dynamic ? ExternalDataTypes.JOURNEY_TIME_DYNAMIC : ExternalDataTypes.JOURNEY_TIME_STATIC;
            case CAMERA -> dynamic ? ExternalDataTypes.CCTV_DYNAMIC : ExternalDataTypes.CCTV_STATIC;
            case TYPICAL_SPEED -> null;
        };
    }

    /*
     * Find all elements in a list of data whose system code number matches the provided
     * code.
     */
    private <E extends TrafficEntity> List<TrafficDataExternal<E>> findBySystemCode(String code, List<TrafficDataExternal<E>> data) {
        return data.stream()
                   .filter(element -> code.equals(element.getSystemCodeNumber()))
                   .toList();
    }

    /*
     * Convert a list of traffic data into a map, with system code numbers as keys.
     */
    private <E extends TrafficEntity> Map<String, TrafficDataExternal<E>>
    getSystemCodeMap(List<TrafficDataExternal<E>> data) {
        Map<String, TrafficDataExternal<E>> map = new HashMap<>();
        data.forEach(element -> map.put(element.getSystemCodeNumber(), element));
        return map;
    }

    /*
     * Get the entity constructed from the static element provided and its matching
     * dynamic data, if present. If no matching dynamic data is found, an entity
     * is constructed using only the static data. The provided static data must
     * be a subclass of DynamicDataExternal, otherwise a runtime exception is
     * thrown.
     */
    private <E extends TrafficEntity> E getEntity(TrafficDataExternal<E> element,
                                                Map<String, TrafficDataExternal<E>> map) {
        TrafficDataExternal<E> matching = map.get(element.getSystemCodeNumber());
        if (! (element instanceof DynamicDataExternal<E> staticElement)) {
            throw new RuntimeException("Expected a subclass of DynamicDataExternal, got " + element.getClass());
        }
        return matching instanceof DynamicDataExternal<E> dynamic ? staticElement.toEntity(dynamic)
                                                                  : staticElement.toEntity();
    }

    /*
     * Get entities for data types that have both static and dynamic components.
     */
    private <E extends TrafficEntity> List<E> combineStaticAndDynamicData(TrafficDataTypes dataType) throws IOException {
        List<TrafficDataExternal<E>> staticData = getExternalData(dataType, false);
        List<TrafficDataExternal<E>> dynamicData = getExternalData(dataType, true);
        Map<String, TrafficDataExternal<E>> dynamicMap = getSystemCodeMap(dynamicData);

        return staticData.stream()
                         .map(element -> getEntity(element, dynamicMap))
                         .toList();
    }

    /*
     * Convert a list of external traffic data to the corresponding entities.
     */
    private <E extends TrafficEntity> List<E> convertToEntities(List<TrafficDataExternal<E>> externalData) {
        return externalData.stream()
                           .map(TrafficDataExternal::toEntity)
                           .toList();
    }

    /*
     * Get the external data corresponding to the given data type from the client.
     */
    private <E extends TrafficEntity> List<TrafficDataExternal<E>> getExternalData(TrafficDataTypes dataType, boolean dynamic) throws IOException {
        var externalDataType = getExternalDataType(dataType, dynamic);
        return client.getData(externalDataType);
    }

}
