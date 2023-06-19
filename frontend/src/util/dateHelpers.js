export function utcToLocal(utcDate) {
    const localDate = new Date(utcDate);
    return localDate.toLocaleString(undefined, {dateStyle: 'medium', timeStyle: 'short'});
}

export function hasOccurred(utcDate) {
    const eventDate = new Date(utcDate);
    const currentDate = new Date();
    return eventDate > currentDate;
}