
/*
 * Calculate the maximum and minimum of the provided data.
 * data - An array of numbers.
 * return - An object of the form {max: Number, min: Number}.
 */
export function maxMin(data) {
    const stats = {};
    if (data) {
        stats.max = maximumOf(data);
        stats.min = minimumOf(data);
    }
    return stats;
}

// Calculate the maximum of the provided array of numbers.
function maximumOf(data) {
    return data.reduce((currentMax, value) => {
        return Math.max(value.speed, currentMax);
    }, 0);
}

// Calculate the minimum of the provided array of numbers.
function minimumOf(data) {
    return data.reduce((currentMin, value) => {
        return Math.min(value.speed, currentMin);
    }, Number.MAX_SAFE_INTEGER);
}