export function utcToLocal(utcDate) {
    const localDate = new Date(utcDate);
    return localDate.toLocaleString(undefined, {dateStyle: 'medium', timeStyle: 'short'});
}

/*
 * Convert the default date format that results from extract dates from form data
 * into an ISO formatted date string.
 */
export function formDateToIsoString(formDate) {
    const date = new Date(formDate);
    return date.toISOString();
}

export function isValidDate(date) {
    try {
        new Date(date).toISOString();
        return true;
    } catch (error) {
        if (error instanceof RangeError) {
            return false;
        }
        throw error;
    }
}

export function hasOccurred(utcDate) {
    const eventDate = new Date(utcDate);
    const currentDate = new Date();
    return eventDate > currentDate;
}