package com.afsmith.tyneweartrafficviewer.business.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleRouteDTO {
    private List<GeoJsonPointDTO> coordinates;
    private double distance;
    private double duration;
}
