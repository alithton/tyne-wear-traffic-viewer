package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.*;
import com.afsmith.tyneweartrafficviewer.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;
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
        assertThat(entity.getEndPoint()).isNull();
    }

    @Test
    public void entityToDto() {
        JourneyTime entity = MockData.getJourneyTime(CODE);
        JourneyTimeDTO dto = mapper.entityToDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getSystemCodeNumber()).isEqualTo(CODE);
//        assertThat(dto.getEndPoint()).isInstanceOf(PointDTO.class);
        assertThat(dto.getSpeed()).isEqualTo(0.0);
    }

    @Test
    public void entityToDtoWithRoute() {
        JourneyTime entity = MockData.getJourneyTimeWithRoute(CODE);
        JourneyTimeDTO dto = mapper.entityToDto(entity);

        assertThat(dto.getRoute()).isNotNull();
        assertThat(dto.getRoute().getCoordinates().size()).isEqualTo(2);
        assertThat(dto.getSpeed()).isGreaterThan(0.0);
    }

    @Test
    public void comparison() {
        JourneyTime entity = MockData.getJourneyTimeWithRoute(CODE);
        TypicalJourneyTime typical = MockData.getTypicalJourneyTime(CODE);
        ComparisonDTO dto = mapper.comparison(entity, typical);
        JourneyTimeDTO currentDTO = mapper.entityToDto(entity);
        JourneyTimeDTO typicalDTO = mapper.entityToDto(entity, typical);
        System.out.println(dto.getSpeed() + " : " + dto.getTypicalSpeed());
        assertThat(dto.getSpeed()).isEqualTo(currentDTO.getSpeed());
        assertThat(dto.getTypicalSpeed()).isEqualTo(typicalDTO.getSpeed());
        assertThat(dto.getComparison()).isEqualTo(ComparisonResult.MUCH_SLOWER);
    }
}