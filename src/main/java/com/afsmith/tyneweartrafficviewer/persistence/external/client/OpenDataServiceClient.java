package com.afsmith.tyneweartrafficviewer.persistence.external.client;

import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.ExternalDataTypes;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficDataExternal;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.TrafficDataReader;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.TrafficDataReaderImpl;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Client for accessing data from the {@see https://www.netraveldata.co.uk/} API.
 * The client assumes that a valid username and password for accessing the API
 * are present in the environment under the following environment variables:
 * - UTMCODS_USERNAME
 * - UTMCODS_PASSWORD
 */
@Service
public class OpenDataServiceClient {
    // Configuration for the client, including setting up HTTP Basic authorisation.
    private final RestTemplate restTemplate;
    private final TrafficDataReader dataReader = new TrafficDataReaderImpl();

    public OpenDataServiceClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    /**
     * Get data of the requested type from the open data service.
     * @param dataType The type of data to be fetched.
     * @return A list of data of the requested type.
     * @throws IOException In the event that data cannot be accessed.
     */
    public <E extends TrafficEntity> List<TrafficDataExternal<E>> getData(ExternalDataTypes dataType) throws IOException {
        String dataUrl = dataType.getUrl();

        ResponseEntity<String> response = restTemplate.getForEntity(dataUrl, String.class);
        String body = response.getBody();

        return dataReader.readFromString(body, dataType.getExternalClass());
    }

    /**
     * Get the image specified by the provided URL from the open data service.
     * @param imageUrl The URL of the requested image.
     * @return The image as an array of bytes.
     */
    public byte[] getImage(URL imageUrl) {
        return restTemplate.getForObject(imageUrl.toString(), byte[].class);
    }

}
