import {MapContainer, TileLayer} from "react-leaflet";
import {useDispatch, useSelector} from "react-redux";
import IncidentMarker from "./IncidentMarker.jsx";
import 'leaflet/dist/leaflet.css'
import 'leaflet-contextmenu';
import {addNew} from "../../../store/slices/detailsSlice.js";
import {useGetIncidentsQuery} from "../../../store/slices/apiSlice.js";
import TrafficSpeedLine from "./TrafficSpeedLine.jsx";
import SpeedComparisonLine from "./SpeedComparisonLine.jsx";
import {maxMin} from "../../../util/summaryStats.js";

function Map() {

    const defaultPosition = [54.97, -1.61];
    const dispatch = useDispatch();

    const filters = useSelector(state => state.filters.value);

    // Fetch incident data from back end or from store
    const {data: trafficData, isSuccess} = useGetIncidentsQuery(filters);

    // Create a new custom event.
    function createNewEvent(e) {
        const payload = {lat: e.latlng.lat, long: e.latlng.lng};
        dispatch(addNew(payload));
    }

    const showSpeedComparison = trafficData && trafficData.SPEED && filters.speedType === 'COMPARISON';
    const showCurrentOrAverageSpeed = trafficData && trafficData.SPEED && filters.speedType !== 'COMPARISON';

    // An array of all data that is displayed as incident markers
    let pointData = [];
    let speedStats = {};
    if (isSuccess) {
        pointData = Object.keys(trafficData).filter(key => key !== "SPEED")
                                .map(key => trafficData[key])
                                .flat();
        // Calculate maximum and minimum speeds (used for determining display colour).
        if (showCurrentOrAverageSpeed) speedStats = maxMin(trafficData.SPEED);
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

            {showCurrentOrAverageSpeed && trafficData.SPEED.map(value => {
                return <TrafficSpeedLine key={value.systemCodeNumber} positions={value.route.coordinates} data={value} max={speedStats.max} min={speedStats.min} />
            })}
            {showSpeedComparison && trafficData.SPEED.map(value => {
                return <SpeedComparisonLine key={value.systemCodeNumber} positions={value.route.coordinates} data={value} />
            })}


        </MapContainer>
    );
}

export default Map;