package com.afsmith.tyneweartrafficviewer.entities;

import com.afsmith.tyneweartrafficviewer.business.services.filter.FilterService;

public interface TrafficEntity {
    String getSystemCodeNumber();
    boolean isIncluded(FilterService filterService);
}
