import {MapContainer, TileLayer} from "react-leaflet";
import IncidentMarker from "./IncidentMarker.jsx";
import 'leaflet/dist/leaflet.css'
import {useEffect, useState} from "react";

function Map() {

    const [incidents, setIncidents] = useState([]);

    const API_URL = "http://localhost:8080/";
    const API_INCIDENT_URL = API_URL + "incidents";

    const defaultPosition = [54.97, -1.61];

    useEffect(() => {
        const fetchData = async (url) => {
            const response = await fetch(url);
            const data = await response.json();
            const fetchedIncidents = data.map(incident => ({
                systemCodeNumber: incident.systemCodeNumber,
                incidentPosition: [incident.point.latitude, incident.point.longitude],
                shortDescription: incident.shortDescription,
                longDescription: incident.longDescription
            }));
            setIncidents(fetchedIncidents);
        }
        fetchData(API_INCIDENT_URL);
    }, [API_INCIDENT_URL]);

    return (
        <MapContainer center={defaultPosition} zoom={13} scrollWheelZoom={false}>
            <TileLayer
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            {incidents.map(incident => {
                return <IncidentMarker key={incident.systemCodeNumber} incidentData={incident} />;
            })}

        </MapContainer>
    );
}

export default Map;