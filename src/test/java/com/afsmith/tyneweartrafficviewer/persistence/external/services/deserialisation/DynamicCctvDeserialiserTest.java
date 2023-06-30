package com.afsmith.tyneweartrafficviewer.persistence.external.services.deserialisation;

import com.afsmith.tyneweartrafficviewer.persistence.external.data.CctvDynamicExternal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

class DynamicCctvDeserialiserTest {

    @Test
    public void readCctvDynamic() throws JsonProcessingException, MalformedURLException {
        String json = """
                {
                    "systemCodeNumber": "GH_A167J1",
                    "dynamics": [
                      {
                        "image": "https://www.netraveldata.co.uk/api/v1/cctv/images/GH_A167J1.jpg",
                        "lastUpdated": "2023-06-23T17:20:34.000+0100"
                      }
                    ]
                  }
                  """;

        ObjectMapper mapper = new ObjectMapper();
        CctvDynamicExternal cctvDynamic = mapper.readValue(json, CctvDynamicExternal.class);

        URL expectedUrl = new URL("https://www.netraveldata.co.uk/api/v1/cctv/images/GH_A167J1.jpg");

        assertThat(cctvDynamic.getSystemCodeNumber()).isEqualTo("GH_A167J1");
        assertThat(cctvDynamic.getImage()).isEqualTo(expectedUrl);
        assertThat(cctvDynamic.getLastUpdated().getYear()).isEqualTo(2023);
    }
}