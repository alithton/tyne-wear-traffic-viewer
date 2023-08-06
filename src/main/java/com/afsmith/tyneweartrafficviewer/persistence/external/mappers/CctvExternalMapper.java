package com.afsmith.tyneweartrafficviewer.persistence.external.mappers;

import com.afsmith.tyneweartrafficviewer.entities.Camera;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.CctvDynamicExternal;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.CctvStaticExternal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Map traffic camera data received from the Open Data Service to its internal entity
 * representation.
 */
@Mapper
public interface CctvExternalMapper {

    /**
     * Combine static and dynamic traffic camera data into a single Camera entity.
     * The returned entity will use the system code number from the static data.
     * @param staticData The static traffic camera data.
     * @param dynamicData The dynamic traffic camera data.
     * @return A single entity representing the traffic camera data.
     */
    @Mapping(target = "systemCodeNumber", source = "staticData.systemCodeNumber")
    @Mapping(target="lastUpdated", source="staticData.lastUpdated")
    @Mapping(target="lastUpdatedDynamic", source="dynamicData.lastUpdated")
    Camera externalToEntity(CctvStaticExternal staticData, CctvDynamicExternal dynamicData);

    /**
     * Construct a traffic camera entity using only dynamic data.
     * @param dynamicData Dynamic traffic camera data.
     * @return A traffic camera entity.
     */
    @Mapping(target="lastUpdatedDynamic", source="dynamicData.lastUpdated")
    Camera externalToEntity(CctvDynamicExternal dynamicData);

    /**
     * Construct a traffic camera entity using only static data.
     * @param staticExternal Static traffic camera data.
     * @return A traffic camera entity.
     */
    Camera externalToEntity(CctvStaticExternal staticExternal);
}
