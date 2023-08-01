import styles from './TimeOptions.module.css';
import SectionHeading from "./SectionHeading.jsx";
import {useState} from "react";
import DateInput from "./DateInput.jsx";
import {useDispatch} from "react-redux";
import {setCurrentOnly, setEndDate, setStartDate} from "../../../store/slices/filterSlice.js";

function TimeOptions() {
    const [enableTimeFilter, setEnableTimeFilter] = useState(false);
    const [enableCurrentOnly, setEnableCurrentOnly] = useState(true);

    const dispatch = useDispatch();

    const handleChange = (e) => {
        const isChecked = e.target.checked;
        setEnableTimeFilter(isChecked);
        if (isChecked && enableCurrentOnly) uncheckCurrentOnly();
    }

    const uncheckCurrentOnly = () => {
        setEnableCurrentOnly(false);
        dispatch(setCurrentOnly(false));
    }

    const handleCurrentOnlyChange = (e) => {
        const isChecked = e.target.checked;
        setEnableCurrentOnly(isChecked);
        if (isChecked && enableTimeFilter) setEnableTimeFilter(false);
        dispatch(setCurrentOnly(isChecked));
    }

    return (
        <>
            <SectionHeading title='Date' />
            <div className={styles['time-options']}>
                <label htmlFor='date-currentOnly'>Current only</label>
                <input className={styles['date-filter']} type='checkbox'
                       checked={enableCurrentOnly} onChange={handleCurrentOnlyChange}
                       id='date-currentOnly' name='date-currentOnly'/>
                <label htmlFor='date-filter'>Filter by date?</label>
                <input className={styles['date-filter']} type='checkbox' checked={enableTimeFilter} id='date-filter' name='date-filter' onChange={handleChange}/>

                <DateInput id='start' label='From' name='start-date' dispatchFn={setStartDate} disabled={!enableTimeFilter} />
                <DateInput id='end' label='To' name='end-date' dispatchFn={setEndDate} disabled={!enableTimeFilter} />

            </div>
        </>
    );
}

export default TimeOptions;