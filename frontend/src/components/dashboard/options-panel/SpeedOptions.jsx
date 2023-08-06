import styles from './SpeedOptions.module.css'
import SectionHeading from "./SectionHeading.jsx";
import {useDispatch, useSelector} from "react-redux";
import {changeSpeedType} from "../../../store/slices/filterSlice.js";

function SpeedOptions() {

    const dispatch = useDispatch();
    const {speedType} = useSelector(state => state.filters.value);

    const handleSelection = e => {
        const selected = e.target.value;
        dispatch(changeSpeedType(selected));
    }

    return (
        <>
            <SectionHeading title='Traffic Speed' />
            <form className={styles['speed-options']}>
                <div>
                    <input type='radio'
                           id='current-speed'
                           name='speed-options'
                           value='CURRENT'
                           onChange={handleSelection}
                           checked={speedType === 'CURRENT'} />
                    <label htmlFor='current-speed'>Current</label>
                </div>
                <div>
                    <input
                        type='radio'
                        id='typical-speed'
                        name='speed-options'
                        value='TYPICAL'
                        checked={speedType === 'TYPICAL'}
                        onChange={handleSelection}
                    />
                    <label htmlFor='typical-speed'>Typical</label>
                </div>
                <div>
                    <input
                        type='radio'
                        id='speed-comparison'
                        name='speed-options'
                        value='COMPARISON'
                        checked={speedType === 'COMPARISON'}
                        onChange={handleSelection}
                    />
                    <label htmlFor='speed-comparison'>Comparison</label>
                </div>
            </form>
        </>
    );
}

export default SpeedOptions;