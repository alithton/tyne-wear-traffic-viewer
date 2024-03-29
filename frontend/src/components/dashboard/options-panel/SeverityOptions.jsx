import {useDispatch, useSelector} from "react-redux";
import {addSeverity, removeSeverity} from "../../../store/slices/filterSlice.js";

import styles from './SeverityOptions.module.css';
import SectionHeading from "./SectionHeading.jsx";

const severityMapping = {
    'low-severity': 'Low',
    'medium-severity': 'Medium',
    'high-severity': 'High'
}

function SeverityOptions() {

    const dispatch = useDispatch();
    const {severity: severityFilter} = useSelector((state) => state.filters.value);

    const handleSelection = e => {
        const severity = severityMapping[e.target.id];
        if (e.target.checked) {
            dispatch(addSeverity(severity));
        } else {
            dispatch(removeSeverity(severity));
        }

    }

    return (
        <>
            <SectionHeading title='Severity' />
            <div className={styles['severity-options']}>
                <div>
                    <input type='checkbox'
                           id='low-severity'
                           name='low-severity'
                           checked={severityFilter.includes('Low')}
                           onChange={handleSelection}/>
                    <label htmlFor='low-severity'>Low</label>
                </div>
                <div>
                    <input type='checkbox'
                           id='medium-severity'
                           name='medium-severity'
                           checked={severityFilter.includes('Medium')}
                           onChange={handleSelection}/>
                    <label htmlFor='medium-severity'>Medium</label>
                </div>
                <div>
                    <input type='checkbox'
                           id='high-severity'
                           name='high-severity'
                           defaultChecked={severityFilter.includes('High')}
                           onChange={handleSelection}/>
                    <label htmlFor='high-severity'>High</label>
                </div>
            </div>
        </>
    );
}

export default SeverityOptions;