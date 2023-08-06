import {Marker, Popup, Tooltip} from "react-leaflet";
import L from 'leaflet';
import {useDispatch} from 'react-redux';

import lowAccidentImageUrl from '../../../assets/marker-accident-low.png';
import moderateAccidentImageUrl from '../../../assets/marker-accident-moderate.png';
import severeAccidentImageUrl from '../../../assets/marker-accident-severe.png';
import unknownAccidentImageUrl from '../../../assets/marker-accident-unknown.png';
import markerShadowImageUrl from '../../../assets/marker-shadow-5opacity.png';
function IncidentMarker({incidentData}) {

    const dispatch = useDispatch();

    const selectEvent = () => {
        dispatch(update(incidentData));
    }

    const EventIcon = L.Icon.extend({
        options: {
            iconSize: [30, 30],
            iconAnchor: [0, 30],
            popupAnchor: [15, -30],
            tooltipAnchor: [0, -15],
            shadowUrl: markerShadowImageUrl,
            shadowSize: [30, 30],
            shadowAnchor: [-5, 35]
        }
    });

    let iconUrl;
    switch(incidentData.severityTypeRefDescription) {
        case 'Low':
            iconUrl = lowAccidentImageUrl;
            break;
        case 'Medium':
            iconUrl = moderateAccidentImageUrl;
            break;
        case 'High':
            iconUrl = severeAccidentImageUrl;
            break;
        default:
            iconUrl = unknownAccidentImageUrl;
    }

    const markerIcon = new EventIcon({iconUrl: iconUrl});

    const popupContent = (
        <>
            <h3>{incidentData.typeDescription}</h3>
            <p>{incidentData.longDescription || incidentData.shortDescription}</p>
        </>
    );

    const incidentPosition = [incidentData.point.latitude, incidentData.point.longitude];

    return (
        <Marker icon={markerIcon} eventHandlers={{ click: selectEvent }} position={incidentPosition}>
            <Tooltip >{incidentData.shortDescription}</Tooltip>
            <Popup >{popupContent}</Popup>
        </Marker>
    );
}

import {update} from '../../../store/slices/detailsSlice.js';

export default IncidentMarker;