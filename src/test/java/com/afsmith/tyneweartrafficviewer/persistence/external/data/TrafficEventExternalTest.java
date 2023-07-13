package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.entities.TrafficEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TrafficEventExternalTest {
    List<TrafficEventExternal> externalEvents;

    @BeforeEach
    void setUp() {
        externalEvents = List.of(
                buildEvent("event1"),
                buildEvent("event2")
        );
    }

    @Test
    void convertSingleEventToEntity() {
        TrafficEventExternal externalEvent = externalEvents.get(0);
        TrafficEvent eventEntity = externalEvent.toEntity();
        assertThat(eventEntity.getSystemCodeNumber()).isEqualTo("event1");
    }

    @Test
    void convertListOfEventsToEntities() {
        List<TrafficEvent> events = externalEvents.stream()
                .map(TrafficEventExternal::toEntity)
                                           .toList();
        assertThat(events.size()).isEqualTo(2);
    }

    private TrafficEventExternal buildEvent(String systemCodeNumber) {
        return TrafficEventExternal.builder()
                .systemCodeNumber(systemCodeNumber)
                                   .build();
    }

}