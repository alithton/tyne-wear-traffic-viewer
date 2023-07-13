package com.afsmith.tyneweartrafficviewer.entities;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

/**
 * The base traffic data class that expresses the shared relationship between
 * all traffic data entities.
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class TrafficData implements TrafficEntity {
    @Id
    private String systemCodeNumber;
}
