import styles from './SeverityOptions.module.css';
import SectionHeading from "./SectionHeading.jsx";

function SeverityOptions() {
    return (
        <>
            <SectionHeading title='Severity' />
            <div className={styles['severity-options']}>
                <div>
                    <input type='checkbox' id='low-severity' name='low-severity'/>
                    <label htmlFor='low-severity'>Low</label>
                </div>
                <div>
                    <input type='checkbox' id='medium-severity' name='medium-severity'/>
                    <label htmlFor='medium-severity'>Medium</label>
                </div>
                <div>
                    <input type='checkbox' id='high-severity' name='high-severity'/>
                    <label htmlFor='high-severity'>High</label>
                </div>
            </div>
        </>
    );
}

export default SeverityOptions;