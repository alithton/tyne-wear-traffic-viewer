import styles from './SpeedOptions.module.css'
import SectionHeading from "./SectionHeading.jsx";
import {useDispatch} from "react-redux";
import {changeSpeedType} from "../../../store/slices/filterSlice.js";

function SpeedOptions() {

    const dispatch = useDispatch();

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
                           defaultChecked={true} />
                    <label htmlFor='current-speed'>Current</label>
                </div>
                <div>
                    <input
                        type='radio'
                        id='typical-speed'
                        name='speed-options'
                        value='TYPICAL'
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
                        onChange={handleSelection}
                    />
                    <label htmlFor='speed-comparison'>Comparison</label>
                </div>
            </form>
        </>
    );
}

export default SpeedOptions;