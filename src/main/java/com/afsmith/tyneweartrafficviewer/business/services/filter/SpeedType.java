package com.afsmith.tyneweartrafficviewer.business.services.filter;

/**
 * All types of traffic speed data that can be requested.
 */
public enum SpeedType {

    /**
     * Current speed data. This is derived from the most recent journey time data
     * that were downloaded from the Open Data Service API.
     */
    CURRENT,

    /**
     * Typical speed data. This is derived from historical journey time data that
     * is available from {@see https://www.netraveldata.co.uk/?page_id=44}. That
     * data presents average journey times over a selection of road sections, with
     * data broken down into 5 minute intervals for both weekdays and weekends. Note
     * that the data is quite old by this point, apparently dating back to 2014, so
     * it may not be relevant to current traffic conditions. Only a subset of roads
     * for which current journey time data is available also have typical journey
     * time data.
     */
    TYPICAL,

    /**
     * Compare the current and typical speed data for all stretches of road for
     * which there is data.
     */
    COMPARISON
}
