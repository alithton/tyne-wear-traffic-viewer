import styles from "./CustomEventForm.module.css";
import detailsStyles from './DetailsPanel.module.css'
import {COORDINATE_PRECISION} from "./DetailsPanel.jsx";
import {useState} from "react";
import SelectFieldModal from "./SelectFieldModal.jsx";
import {formElementLookup} from './CustomFormFields.jsx'
import {formDataToObject} from "../../../util/formHelpers.js";
import {useAddIncidentMutation} from "../../../store/slices/apiSlice.js";
import {formDateToIsoString} from "../../../util/dateHelpers.js";
import {useDispatch, useSelector} from "react-redux";
import PanelMessage from "./PanelMessage.jsx";
import {ErrorContext} from "./ErrorContext.jsx";
import {logOut} from "../../../store/slices/authenticationSlice.js";
import {setSuccess} from "../../../store/slices/detailsSlice.js";

const requiredFields = ['type', 'shortDescription', 'severityTypeRefDescription', 'start'];
const defaultErrorMessages = {};
requiredFields.forEach(field => defaultErrorMessages[field] = '');

function CustomEventForm(props) {

    const [showModal, setShowModal] = useState(false);
    const [selectedFields, setSelectedFields] = useState(requiredFields);
    const [errorMessages, setErrorMessages] = useState(defaultErrorMessages);

    const {isLoggedIn, sessionExpired} = useSelector(state => state.authentication.value);
    const {submissionSuccess} = useSelector(state => state.details.value);
    const dispatch = useDispatch();

    const [triggerAddIncident, {isSuccess, error}] = useAddIncidentMutation();


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

    const updateErrorMessage = (field, message) => {
        const newErrorMessages = errorMessages;
        newErrorMessages[field] = message;
        setErrorMessages(newErrorMessages);
    }

    /*
     * Check whether the new incident data has all the required fields.
     */
    const hasRequiredFields = (data) => {
        let requiredFieldsPresent = true;
        requiredFields.forEach(field => {
            if (!data[field]) {
                updateErrorMessage(field, 'This field is required.');
                requiredFieldsPresent = false;
                console.log('Required field ' + field + ' is missing.');
            } else if (errorMessages[field]) {
                updateErrorMessage(field, '');
            }
        })
        return requiredFieldsPresent;
    }

    const isValid = (data) => {
        return hasRequiredFields(data);
    }

    const handleCreate = async (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        const data = formDataToObject(formData);
        data['latitude'] = props.position.lat;
        data['longitude'] = props.position.long;
        data.start = data.start ? formDateToIsoString(data.start) : '';
        if (data.end) data.end = formDateToIsoString(data.end);

        console.log(data);

        if (isValid(data)) {
            await triggerAddIncident(data);
            if(isSuccess) {
                console.log('Successfully created');
                dispatch(setSuccess(true));
            }
        }
    }

    let submissionErrorText = '';

    const handleUnauthorised = () => {
        submissionErrorText = 'Session expired. Please log in again to proceed.';
        dispatch(logOut(true));
    }

    const handleBadRequest = () => {
        submissionErrorText = 'Unable to process request.';
    }


    if (error && error.status === 401) {
        handleUnauthorised();
    } else if (error && error.status === 400) {
        handleBadRequest();
    }

    const message = submissionSuccess ? 'Event created.' : 'Log in to add custom events.';

    let contents = <PanelMessage message={message} />;
    if ((isLoggedIn || sessionExpired) && !submissionSuccess) {
        contents = (
            <div className={detailsStyles['details--selection']}>
                <h3 className={detailsStyles.details__header}>Custom Event</h3>
                <p className={detailsStyles['info--minor']}>Location: [{lat}, {long}]</p>
                <form onSubmit={handleCreate} className={styles['custom-form']}>
                    <div className={styles['form-input']}>
                        {selectedFields.map(fieldName => formElementLookup[fieldName])}
                    </div>
                    <button onClick={handleAddType} className={styles['form-button']}>Add Field</button>
                    <button className={styles['form-button']} type='submit'>Create</button>
                    <p className={styles['error-message']}>{submissionErrorText}</p>
                </form>
            </div>
        );
    }

    return (
        <>
            <div onClick={handleCloseModal} className={`${showModal ? styles['modal-backdrop'] : ''}`}></div>
            <ErrorContext.Provider value={errorMessages}>
                {contents}
            </ErrorContext.Provider>
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