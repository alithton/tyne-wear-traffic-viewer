package com.afsmith.tyneweartrafficviewer.entities;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DATA_TYPE")
@Entity
public class TrafficData extends AbstractTrafficData {
    public TrafficData(String systemCodeNumber) {
        this.setSystemCodeNumber(systemCodeNumber);
    }
}
