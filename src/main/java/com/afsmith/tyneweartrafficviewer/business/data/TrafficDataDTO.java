package com.afsmith.tyneweartrafficviewer.business.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * A superclass for all traffic data transfer objects, which expresses the fact
 * they all shared a system code number.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrafficDataDTO implements TrafficDTO {
    String systemCodeNumber;

}
