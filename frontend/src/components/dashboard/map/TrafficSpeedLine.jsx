import {Polyline, Tooltip} from "react-leaflet";
import Gradient from "javascript-color-gradient";

const NUM_COLOURS = 20;

const gradientArray = new Gradient()
    .setColorGradient( "#f31c1c", "#ecc52b", "#47d234")
    .setMidpoint(NUM_COLOURS)
    .getColors();

function calculateColour(min, max, actual) {
    const percent = (actual - min) / (max - min);
    const index = Math.floor(percent * NUM_COLOURS);
    return gradientArray[index];
}

function TrafficSpeedLine({positions, data, ...props}) {

    const colour = calculateColour(props.min, props.max, data.speed);

    return (
        <Polyline pathOptions={ {color: colour} } positions={positions} >
            <Tooltip>
                <p>{`${Math.ceil(data.speed)} mph`}</p>
                <p>{data.systemCodeNumber}</p>
                <p>{data.shortDescription}</p>
            </Tooltip>
        </Polyline>
    );
}

export default TrafficSpeedLine;