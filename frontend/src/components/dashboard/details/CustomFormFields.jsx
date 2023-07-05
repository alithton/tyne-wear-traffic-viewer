import styles from "./CustomEventForm.module.css";

const severityField = (
    <>
        <label className={styles['form-label']} htmlFor='event-severity'>Name</label>
        <select id='event-severity' name='severity' >
            <option value='low'>Low</option>
            <option value='medium'>Medium</option>
            <option value='high'>High</option>
        </select>
    </>
);

const endDateField = (
    <>
        <label className={styles['form-label']} htmlFor='event-end'>Ending</label>
        <input id='event-end' name='end' type='datetime-local' />
    </>
);

export const formElementLookup = {
    'severity': severityField,
    'endDate': endDateField
}

export const formElementNames = {
    'severity': 'Severity',
    'endDate': 'End Date'
}