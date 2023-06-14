import {Marker, Popup, Tooltip} from "react-leaflet";
import {useDispatch} from 'react-redux';
function IncidentMarker(props) {

    const dispatch = useDispatch();

    const selectEvent = () => {
        dispatch(update(props.incidentData));
    }

    const popupContent = (
        <>
            <h3>{props.incidentData.incidentTypeDescription}</h3>
            <p>{props.incidentData.longDescription}</p>
        </>
);

    return (
        <Marker eventHandlers={{ click: selectEvent }} position={props.incidentData.incidentPosition}>
            <Tooltip >{props.incidentData.shortDescription}</Tooltip>
            <Popup >{popupContent}</Popup>
        </Marker>
    );
}

import {update} from '../../../features/details/detailsSlice';

export default IncidentMarker;