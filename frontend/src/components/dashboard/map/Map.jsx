import {MapContainer, TileLayer} from "react-leaflet";
import {useDispatch, useSelector} from "react-redux";
import IncidentMarker from "./IncidentMarker.jsx";
import 'leaflet/dist/leaflet.css'
import {useEffect, useState} from "react";
import 'leaflet-contextmenu';
import {addNew} from "../../../features/details/detailsSlice.js";

const API_URL = "http://localhost:8080/";
const API_INCIDENT_URL = API_URL + "incidents";

function Map() {

    const [incidents, setIncidents] = useState([]);
    const [filteredIncidents, setFilteredIncidents] = useState([]);

    const defaultPosition = [54.97, -1.61];
    const filters = useSelector(state => state.filters.value);

    const dispatch = useDispatch();

    // Load data from backend
    useEffect(() => {
        const fetchData = async (url) => {
            const response = await fetch(url);
            const data = await response.json();
            const fetchedIncidents = data.map(incident => ({
                systemCodeNumber: incident.systemCodeNumber,
                incidentPosition: [incident.point.latitude, incident.point.longitude],
                shortDescription: incident.shortDescription,
                longDescription: incident.longDescription,
                ...incident
            }));
            setIncidents(fetchedIncidents);
            setFilteredIncidents(fetchedIncidents);
        }
        fetchData(API_INCIDENT_URL);
    }, [API_INCIDENT_URL]);

    // Apply any filters
    useEffect(() => {
        console.log('Filtering...');
        const filteredIncidentsTemp = incidents.filter(incident => {
            const isSelectedSeverity = filters.severity.includes(incident.severityTypeRefDescription);
            return isSelectedSeverity;
        });
        setFilteredIncidents(filteredIncidentsTemp);
    }, [filters.severity]);

    function createNewEvent(e) {
        const payload = {lat: e.latlng.lat, long: e.latlng.lng};
        dispatch(addNew(payload));
    }

    return (
        <MapContainer
            center={defaultPosition}
            zoom={13}
            scrollWheelZoom={false}
            contextmenu={true}
            contextmenuwidth={140}
            contextmenuItems={[
                {
                    text: 'Add custom event',
                    callback: createNewEvent
                }
            ]}
        >
            <TileLayer
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            {filteredIncidents.map(incident => {
                return <IncidentMarker key={incident.systemCodeNumber} incidentData={incident} />;
            })}

        </MapContainer>
    );
}

export default Map;