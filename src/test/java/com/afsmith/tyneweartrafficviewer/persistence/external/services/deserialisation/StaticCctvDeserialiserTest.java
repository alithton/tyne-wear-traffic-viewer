package com.afsmith.tyneweartrafficviewer.persistence.external.services.deserialisation;

import com.afsmith.tyneweartrafficviewer.persistence.external.data.CctvStaticExternal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class StaticCctvDeserialiserTest {

    @Test
    public void readCctvStatic() throws JsonProcessingException {
        String json = """
                {
                    "systemCodeNumber": "CM_A69A1",
                    "definitions": [
                      {
                        "shortDescription": "A69 / Corby Hill",
                        "longDescription": "Cumberland - A69 / Corby Hill",
                        "point": {
                          "easting": 347903,
                          "northing": 557086,
                          "latitude": 54.9057618392964000,
                          "longitude": -2.8140275427280400
                        },
                        "lastUpdated": "2018-11-07T14:49:22.433+0000"
                      }
                    ]
                  }
                  """;

        ObjectMapper mapper = new ObjectMapper();
        CctvStaticExternal cctvStatic = mapper.readValue(json, CctvStaticExternal.class);

        assertThat(cctvStatic.getSystemCodeNumber()).isEqualTo("CM_A69A1");
        assertThat(cctvStatic.getShortDescription()).isEqualTo("A69 / Corby Hill");
        assertThat(cctvStatic.getLongDescription()).isEqualTo("Cumberland - A69 / Corby Hill");
        assertThat(cctvStatic.getPoint().easting()).isEqualTo(347903L);
        assertThat(cctvStatic.getPoint().longitude()).isLessThan(-2.8);
        assertThat(cctvStatic.getLastUpdated().getYear()).isEqualTo(2018);
    }
}