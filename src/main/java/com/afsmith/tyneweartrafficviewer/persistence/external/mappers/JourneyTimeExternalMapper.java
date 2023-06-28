package com.afsmith.tyneweartrafficviewer.persistence.external.mappers;

import com.afsmith.tyneweartrafficviewer.persistence.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.JourneytimeDynamicExternal;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.JourneytimeStaticExternal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface JourneyTimeExternalMapper {
    @Mapping(target="systemCodeNumber", source="staticData.systemCodeNumber")
    @Mapping(target="lastUpdated", source="staticData.lastUpdated")
    @Mapping(target="lastUpdatedDynamic", source="dynamicData.lastUpdated")
    JourneyTime externalToEntity(JourneytimeStaticExternal staticData, JourneytimeDynamicExternal dynamicData);

    @Mapping(target="lastUpdatedDynamic", source="dynamicData.lastUpdated")
    JourneyTime externalToEntity(JourneytimeDynamicExternal dynamicData);

    JourneyTime externalToEntity(JourneytimeStaticExternal staticData);
}
