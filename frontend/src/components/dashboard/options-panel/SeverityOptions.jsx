import {useDispatch, useSelector} from "react-redux";
import {addSeverity, removeSeverity} from "../../../features/filters/filterSlice.js";

import styles from './SeverityOptions.module.css';
import SectionHeading from "./SectionHeading.jsx";

const severityMapping = {
    'low-severity': 'Low',
    'medium-severity': 'Medium',
    'high-severity': 'High'
}

function SeverityOptions() {

    const dispatch = useDispatch();
    const filters = useSelector((state) => state.filters.value);

    const handleSelection = e => {
        const severity = severityMapping[e.target.id];
        if (e.target.checked) {
            dispatch(addSeverity(severity));
        } else {
            dispatch(removeSeverity(severity));
        }

    }

    console.log(filters.severity);

    return (
        <>
            <SectionHeading title='Severity' />
            <div className={styles['severity-options']}>
                <div>
                    <input type='checkbox' id='low-severity' name='low-severity' defaultChecked={true} onChange={handleSelection}/>
                    <label htmlFor='low-severity'>Low</label>
                </div>
                <div>
                    <input type='checkbox' id='medium-severity' name='medium-severity' defaultChecked={true} onChange={handleSelection}/>
                    <label htmlFor='medium-severity'>Medium</label>
                </div>
                <div>
                    <input type='checkbox' id='high-severity' name='high-severity' defaultChecked={true} onChange={handleSelection}/>
                    <label htmlFor='high-severity'>High</label>
                </div>
            </div>
        </>
    );
}

export default SeverityOptions;