import styles from './FormElement.module.css';
import {cloneElement} from "react";

function FormElement({label, error, ...props}) {

    return (
        <>
            <label className={`${styles.label} ${props.className}`} htmlFor={name}>{label}</label>
            <div className={styles['input-container']}>
                {props.children}
                {error && <p className={styles['error-message']}>{error}</p>}
            </div>

        </>
    );
}

export default FormElement;