package com.afsmith.tyneweartrafficviewer.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TrafficIncident {
    @Id
    private String systemCodeNumber;
    private String type;
    private String incidentTypeDescription;
    private String shortDescription;
    private String locationDescription;
}
