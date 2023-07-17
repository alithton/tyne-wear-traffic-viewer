import {Polyline, Tooltip} from "react-leaflet";
import Gradient from "javascript-color-gradient";



const gradientArray = new Gradient()
    .setColorGradient( "#f31c1c", "#ecc52b", "#47d234")
    .setMidpoint(5)
    .getColors();

const comparisonResults = {
    "MUCH_SLOWER": {colour: gradientArray[0], message: "Much slower than usual."},
    "SLOWER": {colour: gradientArray[1], message: "Slower than usual"},
    "SIMILAR": {colour: gradientArray[2], message: "Similar to usual"},
    "FASTER": {colour: gradientArray[3], message: "Faster than usual"},
    "MUCH_FASTER": {colour: gradientArray[4], message: "Much faster than usual"}
}

function SpeedComparisonLine({positions, data}) {
    if (!data.comparison) return;
    const result = comparisonResults[data.comparison];

    return (
        <Polyline pathOptions={ {color: result.colour} } positions={positions}>
            <Tooltip>
                <p>{result.message}</p>
                <p>{data.systemCodeNumber}</p>
                <p>{data.shortDescription}</p>
            </Tooltip>
        </Polyline>
    );
}

export default SpeedComparisonLine;