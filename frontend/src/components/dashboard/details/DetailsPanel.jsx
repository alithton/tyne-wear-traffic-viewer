import {useEffect, useState} from 'react';
import {useSelector} from 'react-redux';

import Card from "../../ui/Card.jsx";
import styles from './DetailsPanel.module.css';
import {utcToLocal, hasOccurred} from "../../../util/dateHelpers.js";
import Comments from "./Comments.jsx";

const COORDINATE_PRECISION = 4;


function DetailsPanel(props) {
    const [eventSelected, setEventSelected] = useState(false);

    const eventDetails = useSelector((state) => state.details.value);
    console.log(eventDetails);

    useEffect(() => {
        if (eventDetails.systemCodeNumber) {
            setEventSelected(true);
        }
    }, [eventDetails]);

    // Default display if no events are selected by the user
    let  detailsDisplay = (
        <div className={styles['details--no-selection']}>
            <h3 className={styles['no-selection-text']}>Click an icon for further information.</h3>
        </div>
    );

    if (eventSelected) {
        const lat = eventDetails.incidentPosition[0].toFixed(COORDINATE_PRECISION);
        const long = eventDetails.incidentPosition[1].toFixed(COORDINATE_PRECISION);
        const startTime = eventDetails.incidentTime;
        const endTime = eventDetails.endTime;
        const diversion = eventDetails.diversionInForce;
        const lanes = eventDetails.lanesAffectedTypeRefDescription;

        const startTimeDisplay = (
            <p className={styles['info--date']}>{hasOccurred(startTime) ? 'Starting' : 'Started'}: <b>{utcToLocal(startTime)} </b></p>
        );

        const endTimeDisplay = (
            <p className={styles['info--date']}>{hasOccurred(endTime) ? 'Finishing' : 'Ended'}: <b>{utcToLocal(endTime)}</b></p>
        );

        detailsDisplay = (
            <div className={styles['details--selection']}>
                <h3 className={styles.details__header}>{eventDetails.incidentTypeDescription}</h3>
                <p className={styles['info--minor']}>{eventDetails.systemCodeNumber}</p>
                <p className={styles['info--minor']}>Location: {eventDetails.locationDescription} [{lat}, {long}]</p>
                <p className={styles['info--important']}>Impact: {eventDetails.severityTypeRefDescription}</p>
                <p className={styles['info--main']}>{eventDetails.longDescription}</p>
                {lanes && lanes.trim() && <p className={styles['info--main']}>Lanes affected: {lanes}</p>}
                {diversion && diversion.trim() && <p className={styles['info--main']}>Diversion: {diversion}</p>}
                {startTimeDisplay}
                {endTimeDisplay}
                <Comments />
            </div>
        );
    }

    return (
        <Card className={`${props.className} ${eventDetails ? styles['details__card--selection'] : ''}`}>
            {detailsDisplay}
        </Card>
    );
}

export default DetailsPanel;