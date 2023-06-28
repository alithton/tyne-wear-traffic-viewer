package com.afsmith.tyneweartrafficviewer.persistence.external.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.persistence.external.client.OpenDataServiceClient;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.DynamicDataExternal;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.ExternalDataTypes;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficDataExternal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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
    public <E extends TrafficData> List<E> getData(TrafficDataTypes dataType) throws IOException {

        if (dataType == TrafficDataTypes.SPEED || dataType == TrafficDataTypes.CAMERA) {
            ExternalDataTypes staticDataType = getExternalDataType(dataType, false);
            ExternalDataTypes dynamicDataType = getExternalDataType(dataType, true);

            List<TrafficDataExternal<E>> staticData = client.getData(staticDataType);
            List<TrafficDataExternal<E>> dynamicData = client.getData(dynamicDataType);

            return staticData.stream()
                    .map(element -> {
                        DynamicDataExternal<E> staticElement = (DynamicDataExternal<E>) element;
                        List<TrafficDataExternal<E>> dynamic = findBySystemCode(element.getSystemCodeNumber(), dynamicData);
                        return dynamic.size() > 0 ? staticElement.toEntity( (DynamicDataExternal<E>) dynamic.get(0))
                                                  : staticElement.toEntity();
                    })
                    .toList();

        } else {
            ExternalDataTypes externalDataType = getExternalDataType(dataType, false);
            List<TrafficDataExternal<E>> externalData = client.getData(externalDataType);
            return externalData.stream()
                               .map(TrafficDataExternal::toEntity)
                               .toList();
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
        };
    }

    /*
     * Find all elements in a list of data whose system code number matches the provided
     * code.
     */
    private <E extends TrafficData> List<TrafficDataExternal<E>> findBySystemCode(String code, List<TrafficDataExternal<E>> data) {
        return data.stream()
                   .filter(element -> code.equals(element.getSystemCodeNumber()))
                   .toList();
    }

}
