package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;

import java.util.List;

public interface TrafficDataService <T extends TrafficDataDTO> {
     List<T> listAll();
}
