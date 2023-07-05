package com.afsmith.tyneweartrafficviewer.persistence.external.mappers;

import com.afsmith.tyneweartrafficviewer.persistence.entities.Camera;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.CctvDynamicExternal;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.CctvStaticExternal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CctvExternalMapper {

    @Mapping(target = "systemCodeNumber", source = "staticData.systemCodeNumber")
    @Mapping(target="lastUpdated", source="staticData.lastUpdated")
    @Mapping(target="lastUpdatedDynamic", source="dynamicData.lastUpdated")
    Camera externalToEntity(CctvStaticExternal staticData, CctvDynamicExternal dynamicData);

    @Mapping(target="lastUpdatedDynamic", source="dynamicData.lastUpdated")
    Camera externalToEntity(CctvDynamicExternal dynamicData);

    Camera externalToEntity(CctvStaticExternal staticExternal);
}
