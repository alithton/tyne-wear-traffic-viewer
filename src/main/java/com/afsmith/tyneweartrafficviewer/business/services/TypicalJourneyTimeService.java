package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.JourneyTimeDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.mappers.JourneyTimeMapper;
import com.afsmith.tyneweartrafficviewer.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;


@RequiredArgsConstructor
@Service
public class TypicalJourneyTimeService {
    private final TrafficDataPersistence dataPersistence;
    private final JourneyTimeMapper mapper;

    public List<JourneyTimeDTO> listAll() {
        List<JourneyTime> journeyTimes = dataPersistence.listAll(TrafficDataTypes.SPEED);

        LocalTime currentTime = LocalTime.now();
        boolean isWeekend = checkIsWeekend();

        List<TypicalJourneyTime> typicalJourneyTimes = dataPersistence.findTypicalJourneyTimesByTime(currentTime, isWeekend);
        Map<String, TypicalJourneyTime> typicalJourneyTimeMap = getTypicalJourneyTimeMap(typicalJourneyTimes);

        List<JourneyTimeDTO> dtos = new ArrayList<>();

        for (var journeyTime : journeyTimes) {
            var typicalJourneyTime = typicalJourneyTimeMap.get(journeyTime.getSystemCodeNumber());
            if (typicalJourneyTime == null) continue;
            JourneyTimeDTO dto = mapper.entityToDto(journeyTime, typicalJourneyTime);
            dtos.add(dto);
        }

        return dtos;
    }

    private boolean checkIsWeekend() {
        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        return weekend.contains(today.getDayOfWeek());
    }

    private Map<String, TypicalJourneyTime> getTypicalJourneyTimeMap(List<TypicalJourneyTime> typicalJourneyTimes) {
        Map<String, TypicalJourneyTime> map = new HashMap<>();
        typicalJourneyTimes.forEach(e -> map.put(e.getSystemCodeNumber(), e));
        return map;
    }
}
