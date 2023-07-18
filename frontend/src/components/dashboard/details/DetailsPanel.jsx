import {useSelector} from 'react-redux';

import Card from "../../ui/Card.jsx";
import styles from './DetailsPanel.module.css';
import CustomEventForm from "./CustomEventForm.jsx";
import SelectedIncidentDetails from "./SelectedIncidentDetails.jsx";
import CctvDetails from "./CctvDetails.jsx";

export const COORDINATE_PRECISION = 4;


function DetailsPanel(props) {

    const {markerSelected, newMarker, data: eventDetails} = useSelector((state) => state.details.value);

    const cctvSelected = markerSelected && eventDetails.image;

    // Default display if no events are selected by the user
    let  detailsDisplay = (
        <div className={styles['details--no-selection']}>
            <h3 className={styles['no-selection-text']}>Click an icon for further information.</h3>
        </div>
    );

    if (cctvSelected) {
        detailsDisplay = (
            <CctvDetails details={eventDetails} />
        );
    } else if (markerSelected) {
        detailsDisplay = (
            <SelectedIncidentDetails eventDetails={eventDetails} />
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