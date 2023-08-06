import styles from './TimeOptions.module.css';
import SectionHeading from "./SectionHeading.jsx";
import DateInput from "./DateInput.jsx";
import {useDispatch, useSelector} from "react-redux";
import {setCurrentOnly, setEndDate, setStartDate} from "../../../store/slices/filterSlice.js";

function TimeOptions() {

    const dispatch = useDispatch();
    const {currentOnly, startDate, endDate} = useSelector(state => state.filters.value);

    const handleChange = () => {
        dispatch(setCurrentOnly(false));
    }

    const handleCurrentOnlyChange = (e) => {
        const isChecked = e.target.checked;
        dispatch(setCurrentOnly(isChecked));
    }

    return (
        <>
            <SectionHeading title='Date' />
            <div className={styles['time-options']}>
                <label htmlFor='date-currentOnly'>Current only</label>
                <input className={styles['date-filter']}
                       type='checkbox'
                       checked={currentOnly}
                       onChange={handleCurrentOnlyChange}
                       id='date-currentOnly'
                       name='date-currentOnly'/>
                <label htmlFor='date-filter'>Filter by date?</label>
                <input className={styles['date-filter']}
                       type='checkbox'
                       checked={!currentOnly}
                       id='date-filter'
                       name='date-filter'
                       onChange={handleChange}/>

                <DateInput id='start'
                           label='From'
                           name='start-date'
                           dispatchFn={setStartDate}
                           value={startDate}
                           disabled={currentOnly} />
                <DateInput id='end'
                           label='To'
                           name='end-date'
                           dispatchFn={setEndDate}
                           value={endDate}
                           disabled={currentOnly} />

            </div>
        </>
    );
}

export default TimeOptions;