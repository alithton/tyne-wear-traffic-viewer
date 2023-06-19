import styles from './TimeOptions.module.css';
import SectionHeading from "./SectionHeading.jsx";
import {useState} from "react";
import DateInput from "./DateInput.jsx";

function TimeOptions() {
    const [enableTimeFilter, setEnableTimeFilter] = useState(false);

    const handleChange = () => {
        setEnableTimeFilter(!enableTimeFilter);
    }

    return (
        <>
            <SectionHeading title='Date' />
            <div className={styles['time-options']}>
                <label htmlFor='date-filter'>Filter by date?</label>
                <input className={styles['date-filter']} type='checkbox' id='date-filter' name='date-filter' onChange={handleChange}/>

                <DateInput id='start' label='From' name='start-date' disabled={!enableTimeFilter} />
                <DateInput id='end' label='To' name='end-date' disabled={!enableTimeFilter} />

            </div>
        </>
    );
}

export default TimeOptions;