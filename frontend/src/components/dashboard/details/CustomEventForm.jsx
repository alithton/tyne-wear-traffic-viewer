import styles from "./CustomEventForm.module.css";
import detailsStyles from './DetailsPanel.module.css'
import {COORDINATE_PRECISION} from "./DetailsPanel.jsx";
import {useState} from "react";
import SelectFieldModal from "./SelectFieldModal.jsx";
import {formElementLookup} from './CustomFormFields.jsx'

function CustomEventForm(props) {

    const [showModal, setShowModal] = useState(false);
    const [selectedFields, setSelectedFields] = useState([]);

    const lat = props.position.lat.toFixed(COORDINATE_PRECISION);
    const long = props.position.long.toFixed(COORDINATE_PRECISION);

    const handleAddType = () => {
        setShowModal(true);
    }

    const handleCloseModal = () => setShowModal(false);

    const handleSelectField = (e) => {
        e.preventDefault();
        const selected = e.target['0'].value;
        setSelectedFields([...selectedFields, selected]);
        setShowModal(false);
    }

    return (
        <>
            <div onClick={handleCloseModal} className={`${showModal ? styles['modal-backdrop'] : ''}`}></div>
            <div className={detailsStyles['details--selection']}>
                <h3 className={detailsStyles.details__header}>Custom Event</h3>
                <p className={detailsStyles['info--minor']}>Location: [{lat}, {long}]</p>
                <form onSubmit={e => e.preventDefault()} className={styles['custom-form']}>
                    <div className={styles['form-input']}>
                        <label className={styles['form-label']} htmlFor='event-type-select'>Event Type</label>
                        <select id='event-type-select' name='event-types'>
                            <option value="">Please choose a type</option>
                            <option value='incident'>Incident</option>
                            <option value='event'>Event</option>
                        </select>
                        <label className={styles['form-label']} htmlFor='event-name'>Name</label>
                        <input id='event-name' name='name' type='text' />
                        <label className={styles['form-label']} htmlFor='event-start'>Started</label>
                        <input id='event-start' name='start' type='datetime-local' />
                        <label className={styles['form-label']} htmlFor='event-description'>Description</label>
                        <textarea id='event-description' name='description'  />
                        {selectedFields.map(fieldName => formElementLookup[fieldName])}
                    </div>
                    <button onClick={handleAddType} className={styles['form-button']}>Add Field</button>
                    <button className={styles['form-button']} type='submit'>Create</button>
                </form>
            </div>
            <SelectFieldModal
                selectedFields={selectedFields}
                onChange={handleSelectField}
                showModal={showModal}
                onClose={handleCloseModal}
            />
        </>

    );
}

export default CustomEventForm;