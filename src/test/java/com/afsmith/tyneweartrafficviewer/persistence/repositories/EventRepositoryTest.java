package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.Point;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class EventRepositoryTest {
    @Autowired
    EventRepository eventRepository;

    /**
     * Test that traffic events can be successfully saved to the repository.
     */
    @Test
    public void save() {
        TrafficEvent event = TrafficEvent.builder()
                                           .systemCodeNumber("code1")
                                           .point(new Point(1L, 1L, 0.0, 0.0))
                                           .build();

        TrafficEvent savedEvent = eventRepository.save(event);
        assertNotNull(savedEvent);
        assertNotNull(savedEvent.getSystemCodeNumber());
        assertNotEquals(0, eventRepository.count());
    }
}
