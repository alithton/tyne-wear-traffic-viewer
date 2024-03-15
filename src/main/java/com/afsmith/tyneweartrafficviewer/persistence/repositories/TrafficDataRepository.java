package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.entities.TrafficPointData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrafficDataRepository extends JpaRepository<TrafficData, Long> {
    @Query("select d from TrafficData d where type(d) = :dataType")
    <T extends TrafficData> List<T> findByDataType(@Param("dataType") Class<T> dataType);

    @Query("select d from TrafficData d where d.systemCodeNumber = :codeNumber")
    <T extends TrafficData> T findByCodeNumber(@Param("codeNumber") String codeNumber);

    @Query("select p from TrafficPointData p where p.systemCodeNumber = :codeNumber")
    <T extends TrafficPointData> T findPointDataByCodeNumber(@Param("codeNumber") String codeNumber);


}
