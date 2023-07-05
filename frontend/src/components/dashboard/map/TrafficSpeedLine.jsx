import {Polyline, Tooltip} from "react-leaflet";
import Gradient from "javascript-color-gradient";

const NUM_COLOURS = 20;

const gradientArray = new Gradient()
    .setColorGradient("#47d234", "#ecc52b", "#f31c1c")
    .setMidpoint(NUM_COLOURS)
    .getColors();

function calculateColour(min, max, actual) {
    const percent = actual / (max - min);
    const index = Math.floor(percent * NUM_COLOURS);
    console.log(index);
    return gradientArray[index];
}

function TrafficSpeedLine({positions, data, ...props}) {

    const colour = calculateColour(props.min, props.max, data.linkTravelTime);

    return (
        <Polyline pathOptions={ {color: colour} } positions={positions} >
            <Tooltip>{data.shortDescription}</Tooltip>
        </Polyline>
    );
}

export default TrafficSpeedLine;