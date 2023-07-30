import styles from "./CustomEventForm.module.css";
import CustomEventInputField from "../../ui/CustomEventInputField.jsx";

const typeField = (
    <>
        <label className={styles['form-label']} htmlFor='event-type-select'>Incident Type</label>
        <select id='event-type-select' name='type'>
            <option value='INCIDENT'>Incident</option>
            <option value='EVENT'>Event</option>
            <option value='ACCIDENT'>Accident</option>
            <option value='ROADWORKS'>Roadworks</option>
        </select>
    </>
);

// const shortDescriptionField = (
//     <>
//         <label className={styles['form-label']} htmlFor='event-name'>Name</label>
//         <input id='event-name' name='shortDescription' type='text' />
//     </>
// );

const shortDescriptionField = (
    <CustomEventInputField
        labelClassName={styles['form-label']}
        isRequired={true}
        name='shortDescription'
        type='text'
        label='Name'
    />
);

const longDescriptionField = (
    <>
        <label className={styles['form-label']} htmlFor='event-description'>Description</label>
        <textarea id='event-description' name='longDescription'  />
    </>
);


// const locationDescriptionField = (
//     <>
//         <label className={styles['form-label']} htmlFor='location'>Location</label>
//         <input id='location' name='locationDescription' type='text' />
//     </>
// );

const locationDescriptionField = (
    <CustomEventInputField
        labelClassName={styles['form-label']}
        name='locationDescription'
        type='text'
        label='Location'
    />
);

const severityField = (
    <>
        <label className={styles['form-label']} htmlFor='event-severity'>Severity</label>
        <select id='event-severity' name='severityTypeRefDescription' >
            <option value='Low'>Low</option>
            <option value='Medium'>Medium</option>
            <option value='High'>High</option>
        </select>
    </>
);

const lanesAffectedField = (
    <>
        <label className={styles['form-label']} htmlFor='lanesAffected'>Lanes Affected</label>
        <select id='lanesAffected' name='lanesAffectedTypeRefDescription' >
            <option value='T'>T</option>
            <option value='|'>|</option>
            <option value='|T'>| T</option>
            <option value='T|'>T |</option>
            <option value='TT'>T T</option>
            <option value='||'>| |</option>
        </select>
    </>
);

const diversionField = (
    <>
        <label className={styles['form-label']} htmlFor='diversion'>Diversion</label>
        <select id='diversion' name='diversionInForce' >
            <option value='N'>No</option>
            <option value='Y'>Yes</option>
        </select>
    </>
);

const phaseField = (
    <>
        <label className={styles['form-label']} htmlFor='phaseType'>Phase</label>
        <select id='phaseType' name='phaseTypeRef' >
            <option value='Current'>Current</option>
            <option value='Future'>Future</option>
            <option value='Past'>Past</option>
        </select>
    </>
);

// const startDateField = (
//     <>
//         <label className={styles['form-label']} htmlFor='event-start'>Started</label>
//         <input id='event-start' name='start' type='datetime-local' />
//     </>
// );

const startDateField = (
    <CustomEventInputField
        labelClassName={styles['form-label']}
        isRequired={true}
        name='start'
        type='datetime-local'
        label='Started'
    />
);

const endDateField = (
    <>
        <label className={styles['form-label']} htmlFor='event-end'>Ending</label>
        <input id='event-end' name='end' type='datetime-local' />
    </>
);

export const formElementLookup = {
    'type': typeField,
    'shortDescription': shortDescriptionField,
    'longDescription': longDescriptionField,
    'locationDescription': locationDescriptionField,
    'severityTypeRefDescription': severityField,
    'lanesAffectedTypeRefDescription': lanesAffectedField,
    'diversionInForce': diversionField,
    'phaseTypeRef': phaseField,
    'start': startDateField,
    'end': endDateField
}

export const formElementNames = {
    'type': 'Incident Type',
    'shortDescription': 'Short Description',
    'longDescription': 'Long Description',
    'locationDescription': 'Location',
    'severityTypeRefDescription': 'Severity',
    'lanesAffectedTypeRefDescription': 'Lanes Affected',
    'diversionInForce': 'Diversion in force',
    'phaseTypeRef': 'Phase Type',
    'start': 'Start Date',
    'end': 'End Date'
}