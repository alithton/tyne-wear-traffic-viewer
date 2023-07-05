import {useSelector} from 'react-redux';

import Card from "../../ui/Card.jsx";
import styles from './DetailsPanel.module.css';
import {utcToLocal, hasOccurred} from "../../../util/dateHelpers.js";
import Comments from "./Comments.jsx";
import CustomEventForm from "./CustomEventForm.jsx";

export const COORDINATE_PRECISION = 4;


function DetailsPanel(props) {

    const {markerSelected, newMarker, data: eventDetails} = useSelector((state) => state.details.value);
    console.log(eventDetails);
    console.log("Selected: " + markerSelected);
    console.log("New Marker: " + newMarker);

    // Default display if no events are selected by the user
    let  detailsDisplay = (
        <div className={styles['details--no-selection']}>
            <h3 className={styles['no-selection-text']}>Click an icon for further information.</h3>
        </div>
    );

    if (markerSelected) {
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
    } else if (newMarker) {
        detailsDisplay = (
            <CustomEventForm handleShowModal={props.handleShowModal} position={eventDetails} />
        );
    }



    return (
        <Card className={`${props.className} ${eventDetails ? styles['details__card--selection'] : ''}`}>
            {detailsDisplay}
        </Card>
    );
}

export default DetailsPanel;