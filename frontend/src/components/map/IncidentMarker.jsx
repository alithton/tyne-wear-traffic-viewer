import {Marker, Popup, Tooltip} from "react-leaflet";

function IncidentMarker(props) {

    return (
        <Marker position={props.incidentData.incidentPosition}>
            <Tooltip>{props.incidentData.shortDescription}</Tooltip>
            <Popup>{props.incidentData.longDescription}</Popup>
        </Marker>
    );
}

export default IncidentMarker;