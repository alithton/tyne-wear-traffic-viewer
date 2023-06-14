import styles from './TimeOptions.module.css';
import SectionHeading from "./SectionHeading.jsx";
import {useState} from "react";

function TimeOptions() {
    const [enableTimeFilter, setEnableTimeFilter] = useState(false);

    const currentDate = new Date();

    const handleChange = () => {
        setEnableTimeFilter(!enableTimeFilter);
    }

    return (
        <>
            <SectionHeading title='Date' />
            <div className={styles['time-options']}>
                <label htmlFor='date-filter'>Filter by date?</label>
                <input className={styles['date-filter']} type='checkbox' id='date-filter' name='date-filter' onChange={handleChange}/>

                <label htmlFor='start'>From</label>
                <input type='date' id='start' name='start-date' value={currentDate.toDateString()} disabled={!enableTimeFilter}/>

                <label htmlFor='end'>To</label>
                <input type='date' id='end' name='end-date' value={currentDate.toDateString()} disabled={!enableTimeFilter} />
            </div>
        </>
    );
}

export default TimeOptions;