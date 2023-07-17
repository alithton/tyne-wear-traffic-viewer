import {MapContainer, TileLayer} from "react-leaflet";
import {useDispatch, useSelector} from "react-redux";
import IncidentMarker from "./IncidentMarker.jsx";
import 'leaflet/dist/leaflet.css'
import {useEffect, useState} from "react";
import 'leaflet-contextmenu';
import {addNew} from "../../../store/slices/detailsSlice.js";
import {useGetIncidentsQuery} from "../../../store/slices/apiSlice.js";
import TrafficSpeedLine from "./TrafficSpeedLine.jsx";

const API_URL = "http://localhost:8080/";
const API_INCIDENT_URL = API_URL + "incidents";

const TRAFFIC_POINT_TYPES = ["INCIDENT", "ACCIDENT", "ROADWORKS", "EVENT"];

function Map() {

    // const [incidents, setIncidents] = useState([]);
    const [filteredData, setFilteredData] = useState([]);

    const defaultPosition = [54.97, -1.61];
    const filters = useSelector(state => state.filters.value);

    const dispatch = useDispatch();
    console.log(filters.speedType);

    // Fetch incident data from back end or from store
    const {data: trafficData, isSuccess} = useGetIncidentsQuery({dataType: filters.dataType, speedType: filters.speedType});

    // Filter the loaded data and reformat coordinates to the format expected by Leaflet
    useEffect(() => {
        console.log("Checking that data is loaded...");
        if (isSuccess) {
            const filteredDataTemp = {};
            console.log('Filtering...');
            Object.keys(trafficData).forEach(dataType => {
                const data = trafficData[dataType];
                if (TRAFFIC_POINT_TYPES.includes(dataType)) {
                    filteredDataTemp[dataType] = data
                        .filter(incident => filters.severity.includes(incident.severityTypeRefDescription))
                        .map(incident => ({
                            incidentPosition: [incident.point.latitude, incident.point.longitude],
                            ...incident
                        }));
                } else if (dataType === "SPEED") {
                    filteredDataTemp[dataType] = data.map(entry => ({
                        positions: [
                            [entry.point.latitude, entry.point.longitude],
                            [entry.endPoint.latitude, entry.endPoint.longitude]
                        ],
                        ...entry
                    }))
                } else {
                    filteredDataTemp[dataType] = data.map(entry => ({
                        incidentPosition: [entry.point.latitude, entry.point.longitude],
                        ...entry
                    }))
                }

            });
            console.log("Finished...");
            setFilteredData(filteredDataTemp);
        }
    }, [trafficData, isSuccess, filters.severity]);

    // Create a new custom event.
    function createNewEvent(e) {
        const payload = {lat: e.latlng.lat, long: e.latlng.lng};
        dispatch(addNew(payload));
    }

    // An array of all data that is displayed as incident markers
    const pointData = Object.keys(filteredData).filter(key => key !== "SPEED")
                            .map(key => filteredData[key])
                            .flat();
    console.log(pointData);

    // Calculate maximum and minimum speeds (used for determining colour).
    let speedStats = {};
    if (filteredData.SPEED) {
        speedStats.max = filteredData.SPEED.reduce((currentMax, value) => {
                                return Math.max(value.averageSpeed, currentMax);
                            }, 0);
        speedStats.min = filteredData.SPEED.reduce((currentMin, value) => {
            return Math.min(value.averageSpeed, currentMin);
        }, Number.MAX_SAFE_INTEGER);
        console.log(speedStats);
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

            {pointData.length > 0 && pointData.map(value => {
                return <IncidentMarker key={value.systemCodeNumber} incidentData={value}/>;
            })}
            {filteredData.SPEED && filteredData.SPEED.map(value => {
                console.log("Plotting speed data at ");
                console.log(value.route.coordinates);
                return <TrafficSpeedLine positions={value.route.coordinates} data={value} max={speedStats.max} min={speedStats.min} />
            })}

        </MapContainer>
    );
}

export default Map;