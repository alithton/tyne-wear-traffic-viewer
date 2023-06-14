import Map from "./Map.jsx";
import Card from "../../ui/Card.jsx";

function MapPanel(props) {
    return (
    <Card className={props.className}>
        <Map />
    </Card>
    );
}

export default MapPanel;