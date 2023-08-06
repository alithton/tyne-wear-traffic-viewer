package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.NewTrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficPointDataDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficDTO;
import com.afsmith.tyneweartrafficviewer.business.services.filter.FilterService;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import com.afsmith.tyneweartrafficviewer.entities.TrafficPointData;
import com.afsmith.tyneweartrafficviewer.exceptions.DataNotFoundException;
import com.afsmith.tyneweartrafficviewer.exceptions.InvalidTrafficDataException;
import com.afsmith.tyneweartrafficviewer.exceptions.NotAuthenticatedException;

import java.util.List;

/**
 * A service that provides requested traffic data in a format that is ready to be sent
 * to the frontend for display to the user. For most data types, this involves
 * mapping from the internal data model to a simplified data transfer model that
 * strips out details that are not needed by the frontend.
 */
public interface DtoService {

    /**
     * Get a list of all available data of the requested type in a data transfer format ready to
     * be served to the front end.
     * @param dataType The type of data requested.
     * @return A list of data transfer objects of the type requested.
     * @param <DTO> The type of data transfer object corresponding to the requested data type.
     * @param <T> The entity corresponding to the requested type.
     */
    <DTO extends TrafficDTO, T extends TrafficEntity> List<DTO> listAll(TrafficDataTypes dataType);

    /**
     * Get a list of all available data of the requested type and matching the filter criteria
     * in a data transfer format ready to be served to the front end.
     * @param dataType The type of data requested.
     * @param filter The filter service to be used for filtering the data.
     * @return A list of data transfer objects of the type requested.
     * @param <DTO> The type of data transfer object corresponding to the requested data type.
     * @param <T> The entity corresponding to the requested type.
     */
    <DTO extends TrafficDTO, T extends TrafficEntity> List<DTO> listAll(TrafficDataTypes dataType, FilterService filter);

    /**
     * Get the latest image from the camera specified by the provided system code number.
     * @param systemCodeNumber The system code number of the camera.
     * @return An array of bytes corresponding to the retrieved image.
     */
    byte[] getImage(String systemCodeNumber);

    /**
     * Get the traffic incident data specified by the provided system code number.
     * @param codeNumber The system code number of the data to be retrieved.
     * @return A data transfer object of retrieved data.
     * @param <DTO> The type of data transfer object.
     * @param <T> The type of the corresponding traffic entity.
     * @throws DataNotFoundException If no data could be found matching the provided code number.
     */
    <DTO extends TrafficPointDataDTO, T extends TrafficPointData> DTO getIncident(String codeNumber)
            throws DataNotFoundException;

    /**
     * Save the provided new created traffic incident data in the database.
     * @param trafficData The new traffic data to be saved.
     * @param token A user authentication token.
     * @throws NotAuthenticatedException If the token could not be authenticated.
     * @throws InvalidTrafficDataException If the provided traffic data is in an invalid format.
     */
    void save(NewTrafficDataDTO trafficData, String token)
            throws NotAuthenticatedException, InvalidTrafficDataException;
}
