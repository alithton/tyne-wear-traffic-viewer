package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.entities.TrafficPointData;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.TrafficDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TrafficDataAccessServiceImpl implements TrafficDataAccessService {
    private final TrafficDataRepository repository;

    /**
     * Get a list of all the data managed by the service of the specified type.
     *
     * @param dataType The type of traffic data to be retrieved.
     * @return A list of traffic data.
     */
    @Override
    public <T extends TrafficData> List<T> listAll(Class<T> dataType) {
        return repository.findByDataType(dataType);
    }

    /**
     * Store the provided traffic data in the database.
     *
     * @param trafficData A list of traffic data.
     */
    @Override
    public void saveAll(List<? extends TrafficData> trafficData) {
        repository.saveAll(trafficData);
    }

    /**
     * Store the provided traffic data in the database.
     *
     * @param trafficData A list of traffic data.
     */
    @Override
    public void save(TrafficData trafficData) {
        repository.save(trafficData);
    }

    /**
     * Attempt to find traffic data using the system code number.
     *
     * @param systemCodeNumber The code number of the data.
     * @return The traffic data matching the code number.
     */
    @Override
    public <T extends TrafficData> T findByCodeNumber(String systemCodeNumber) {
        return repository.findByCodeNumber(systemCodeNumber);
    }

    /**
     * Attempt to find point traffic data using the system code number.
     *
     * @param systemCodeNumber The code number of the data.
     * @return The traffic data matching the code number.
     */
    @Override
    public <T extends TrafficPointData> T findPointDataByCodeNumber(String systemCodeNumber) {
        return repository.findPointDataByCodeNumber(systemCodeNumber);
    }
}
