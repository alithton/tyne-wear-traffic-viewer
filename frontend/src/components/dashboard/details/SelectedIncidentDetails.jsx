import styles from "./DetailsPanel.module.css";
import {hasOccurred, utcToLocal} from "../../../util/dateHelpers.js";
import {COORDINATE_PRECISION} from "./DetailsPanel.jsx";
import Comments from "./Comments.jsx";
import {useGetIncidentQuery} from "../../../store/slices/apiSlice.js";

function SelectedIncidentDetails({codeNumber}) {

    // Load incident data from the server.
    const {data: eventDetails, isLoading, isSuccess} = useGetIncidentQuery(codeNumber);

    let content;

    if (isLoading) {
        content = <h3>Loading...</h3>;
    }

    // On successfully loading the data
    else if (isSuccess) {
        const incidentPosition = [eventDetails.point.latitude, eventDetails.point.longitude];
        const lat = incidentPosition[0].toFixed(COORDINATE_PRECISION);
        const long = incidentPosition[1].toFixed(COORDINATE_PRECISION);
        const startTime = eventDetails.incidentTime;
        const endTime = eventDetails.endTime;
        const diversion = eventDetails.diversionInForce;
        const lanes = eventDetails.lanesAffectedTypeRefDescription;
        const incidentType = eventDetails.incidentTypeDescription
                             || eventDetails.eventTypeDescription
                             || eventDetails.roadworkTypeDescription
                             || eventDetails.accidentTypeDescription;

        const startTimeDisplay = (
            <p className={styles['info--date']}>{hasOccurred(startTime) ? 'Starting' : 'Started'}: <b>{utcToLocal(startTime)} </b></p>
        );

        const endTimeDisplay = (
            <p className={styles['info--date']}>{hasOccurred(endTime) ? 'Finishing' : 'Ended'}: <b>{utcToLocal(endTime)}</b></p>
        );

        content = (
            <div className={styles['details--selection']}>
                <h3 className={styles.details__header}>{incidentType}</h3>
                <p className={styles['info--minor']}>{eventDetails.systemCodeNumber}</p>
                <p className={styles['info--minor']}>Location: {eventDetails.locationDescription} [{lat}, {long}]</p>
                <p className={styles['info--important']}>Impact: {eventDetails.severityTypeRefDescription}</p>
                <p className={styles['info--main']}>{eventDetails.longDescription || eventDetails.shortDescription}</p>
                {lanes && lanes.trim() && <p className={styles['info--main']}>Lanes affected: {lanes}</p>}
                {diversion && diversion.trim() && <p className={styles['info--main']}>Diversion: {diversion}</p>}
                {startTimeDisplay}
                {endTimeDisplay}
                <Comments codeNumber={eventDetails.systemCodeNumber} comments={eventDetails.comments} />
            </div>
        );
    }


    return (
        <>
            {content}
        </>
    );

}

export default SelectedIncidentDetails;