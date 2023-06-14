import {Marker, Popup, Tooltip} from "react-leaflet";

function IncidentMarker(props) {

    const handleClick = () => {
        console.log(props.incidentData.shortDescription);
    }

    return (
        <Marker eventHandlers={{ click: handleClick }} position={props.incidentData.incidentPosition}>
            <Tooltip >{props.incidentData.shortDescription}</Tooltip>
            <Popup >{props.incidentData.longDescription}</Popup>
        </Marker>
    );
}

export default IncidentMarker;