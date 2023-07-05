package com.afsmith.tyneweartrafficviewer.business.data;

import com.afsmith.tyneweartrafficviewer.persistence.mappers.MappableDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrafficDataDTO implements MappableDTO {
    String systemCodeNumber;

}
