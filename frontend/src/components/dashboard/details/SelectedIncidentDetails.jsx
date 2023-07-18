import styles from "./DetailsPanel.module.css";
import {hasOccurred, utcToLocal} from "../../../util/dateHelpers.js";
import {COORDINATE_PRECISION} from "./DetailsPanel.jsx";
import Comments from "./Comments.jsx";

function SelectedIncidentDetails({eventDetails}) {

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

    return (
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

export default SelectedIncidentDetails;