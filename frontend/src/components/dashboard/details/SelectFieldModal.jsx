import styles from "./SelectFieldModal.module.css";
import {formElementNames} from './CustomFormFields.jsx';

function SelectFieldModal(props) {

    const modalClasses = props.showModal ? `${styles.dialog}` : `${styles['dialog-hidden']}`;

    return (
        <dialog className={modalClasses}>
            <h3 className={styles.header}>Select a field to add</h3>
            <form className={styles['modal-form']} onSubmit={props.onChange}>
                <select >
                    <option value=''>Please select a field</option>
                    {
                        Object.keys(formElementNames)
                            .filter(name => !props.selectedFields.includes(name))
                            .map(name => <option value={name}>{formElementNames[name]}</option>)
                    }
                </select>
                <div className={styles['dialog-actions']}>
                    <button type='button' onClick={props.onClose}>Cancel</button>
                    <button type='submit'>Add</button>
                </div>
            </form>

        </dialog>
    );
}

export default SelectFieldModal;