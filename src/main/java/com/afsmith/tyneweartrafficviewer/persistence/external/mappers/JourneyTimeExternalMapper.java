package com.afsmith.tyneweartrafficviewer.persistence.external.mappers;

import com.afsmith.tyneweartrafficviewer.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.JourneytimeDynamicExternal;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.JourneytimeStaticExternal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *  Map journey time data received from the Open Data Service to its internal entity
 *  representation.
 */
@Mapper
public interface JourneyTimeExternalMapper {

    /**
     * Combine static and dynamic journey time data into a single journey time data entity.
     * The returned entity will use the system code number from the static data.
     * @param staticData The static journey time data.
     * @param dynamicData The dynamic journey time data.
     * @return A single entity representing the journey time data.
     */
    @Mapping(target="systemCodeNumber", source="staticData.systemCodeNumber")
    @Mapping(target="lastUpdated", source="staticData.lastUpdated")
    @Mapping(target="lastUpdatedDynamic", source="dynamicData.lastUpdated")
    JourneyTime externalToEntity(JourneytimeStaticExternal staticData, JourneytimeDynamicExternal dynamicData);

    /**
     * Construct a journey time entity using only dynamic data.
     * @param dynamicData Dynamic journey time data.
     * @return A journey time entity.
     */
    @Mapping(target="lastUpdatedDynamic", source="dynamicData.lastUpdated")
    JourneyTime externalToEntity(JourneytimeDynamicExternal dynamicData);

    /**
     * Construct a journey time entity using only static data.
     * @param staticData Static journey time data.
     * @return A journey time entity.
     */
    JourneyTime externalToEntity(JourneytimeStaticExternal staticData);
}
