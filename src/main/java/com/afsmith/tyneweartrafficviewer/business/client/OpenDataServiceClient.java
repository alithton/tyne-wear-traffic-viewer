package com.afsmith.tyneweartrafficviewer.business.client;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.services.TrafficDataReader;
import com.afsmith.tyneweartrafficviewer.business.services.TrafficDataReaderImpl;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class OpenDataServiceClient {
    private final RestTemplate restTemplate;
    private final TrafficDataReader dataReader = new TrafficDataReaderImpl();

    public OpenDataServiceClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public List<TrafficDataDTO> getData(TrafficDataTypes dataType) throws IOException {
        String dataUrl = dataType.getUrl();

        ResponseEntity<String> response = restTemplate.getForEntity(dataUrl, String.class);
        String body = response.getBody();

        return dataReader.readFromString(body, dataType.getDtoClass());
    }

}
