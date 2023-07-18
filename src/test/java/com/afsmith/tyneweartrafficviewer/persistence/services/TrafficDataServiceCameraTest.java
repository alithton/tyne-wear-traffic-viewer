package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.Camera;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.CameraRepository;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TrafficDataServiceCameraTest {
    TrafficDataServiceCamera dataService;

    @Autowired
    CameraRepository repository;
    List<Camera> cameras;

    @BeforeEach
    void setUp() {
        dataService = new TrafficDataServiceCamera(repository);
        cameras = List.of(MockData.getCamera("code1"),
                                       MockData.getCamera("code2"));

        repository.saveAll(cameras);
    }

    @Test
    void findCameraWhenPresent() {
        Camera found = dataService.findById("code1");
        assertThat(found).isNotNull();
        assertThat(found.getImage()).isEqualTo(cameras.get(0).getImage());
    }

    @Test
    void throwErrorWhenNotPresent() {
        assertThrows(EntityNotFoundException.class, () -> {
            dataService.findById("code3");
        });
    }

}