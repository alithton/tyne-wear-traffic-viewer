package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.JourneyTimeDTO;
import com.afsmith.tyneweartrafficviewer.business.services.filter.SpeedType;
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

/**
 * Provides services for working with typical journey time data. This service can provide
 * the typical speed data directly or as a comparison to current journey times.
 */
@RequiredArgsConstructor
@Service
public class TypicalJourneyTimeService {
    private final TrafficDataPersistence dataPersistence;
    private final JourneyTimeMapper mapper;

    /**
     * Get all available typical journey time data.
     * @return A list of typical journey time data.
     */
    public <T extends JourneyTimeDTO> List<T> listAll(SpeedType speedType) {
        if (speedType == SpeedType.CURRENT) throw new IllegalArgumentException("Retrieving current journey time data is not supported.");

        List<JourneyTime> journeyTimes = dataPersistence.listAll(TrafficDataTypes.SPEED);

        LocalTime currentTime = LocalTime.now();
        boolean isWeekend = checkIsWeekend();

        List<TypicalJourneyTime> typicalJourneyTimes = dataPersistence.findTypicalJourneyTimesByTime(currentTime, isWeekend);
        Map<String, TypicalJourneyTime> typicalJourneyTimeMap = getTypicalJourneyTimeMap(typicalJourneyTimes);

        List<T> dtos = new ArrayList<>();

        for (var journeyTime : journeyTimes) {
            var typicalJourneyTime = typicalJourneyTimeMap.get(journeyTime.getSystemCodeNumber());
            if (typicalJourneyTime == null) continue;
            T dto = mapToDTO(speedType, journeyTime, typicalJourneyTime);
            dtos.add(dto);
        }

        return dtos;
    }

    /*
     * Map the current and typical journey time data to the appropriate data transfer object based on the
     * requested speed type. The unchecked casts are safe in the context as consistency between the
     * type T and the return type of the mapper function is ensured externally.
     */
    @SuppressWarnings("unchecked")
    private <T extends JourneyTimeDTO> T mapToDTO(SpeedType speedType, JourneyTime journeyTime, TypicalJourneyTime typicalJourneyTime) {
        T dto;
        if (speedType == SpeedType.TYPICAL) {
            dto = (T) mapper.entityToDto(journeyTime, typicalJourneyTime);
        } else {
            dto = (T) mapper.comparison(journeyTime, typicalJourneyTime);
        }
        return dto;
    }

    // Is the current day a weekend day?
    private boolean checkIsWeekend() {
        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        return weekend.contains(today.getDayOfWeek());
    }

    // Convert a list of typical journey times into a Map, keyed by the system code number.
    private Map<String, TypicalJourneyTime> getTypicalJourneyTimeMap(List<TypicalJourneyTime> typicalJourneyTimes) {
        Map<String, TypicalJourneyTime> map = new HashMap<>();
        typicalJourneyTimes.forEach(e -> map.put(e.getSystemCodeNumber(), e));
        return map;
    }
}
