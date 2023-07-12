package com.afsmith.tyneweartrafficviewer.persistence.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.JourneyTimeDTO;
import com.afsmith.tyneweartrafficviewer.business.data.PointDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.entities.Point;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class JourneyTimeMapperTest {
    JourneyTimeMapper mapper = Mappers.getMapper(JourneyTimeMapper.class);
    String CODE = "code1";

    @Test
    public void dtoToEntity() {
        JourneyTimeDTO dto = MockData.getJourneyTimeDto(CODE);
        JourneyTime entity = mapper.dtoToEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getSystemCodeNumber()).isEqualTo(CODE);
        assertThat(entity.getEndPoint()).isInstanceOf(Point.class);
    }

    @Test
    public void entityToDto() {
        JourneyTime entity = MockData.getJourneyTime(CODE);
        JourneyTimeDTO dto = mapper.entityToDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getSystemCodeNumber()).isEqualTo(CODE);
        assertThat(dto.getEndPoint()).isInstanceOf(PointDTO.class);
        assertThat(dto.getAverageSpeed()).isEqualTo(0.0);
    }

    @Test
    public void entityToDtoWithRoute() {
        JourneyTime entity = MockData.getJourneyTimeWithRoute(CODE);
        JourneyTimeDTO dto = mapper.entityToDto(entity);

        assertThat(dto.getRoute()).isNotNull();
        assertThat(dto.getRoute().getCoordinates().size()).isEqualTo(2);
        assertThat(dto.getAverageSpeed()).isGreaterThan(0.0);
    }
}