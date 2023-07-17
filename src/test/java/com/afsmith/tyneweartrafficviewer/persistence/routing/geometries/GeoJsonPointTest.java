package com.afsmith.tyneweartrafficviewer.persistence.routing.geometries;

import com.afsmith.tyneweartrafficviewer.entities.GeoJsonPoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GeoJsonPointTest {

    @Test
    void orderingOfFieldsOnSerialisation() throws JsonProcessingException {
        GeoJsonPoint point = new GeoJsonPoint(55, -1.6);
        ObjectMapper mapper = new ObjectMapper();

        String serialisedPoint = mapper.writeValueAsString(point);
        System.out.println(serialisedPoint);

        assertThat(serialisedPoint.indexOf("55")).isLessThan(serialisedPoint.indexOf("-1.6"));
    }

}