import styles from './SpeedOptions.module.css'
import SectionHeading from "./SectionHeading.jsx";

function SpeedOptions() {
    return (
        <>
            <SectionHeading title='Traffic Speed' />
            <form className={styles['speed-options']}>
                <div>
                    <input type='radio' id='current-speed' name='speed-options' defaultChecked={true} />
                    <label htmlFor='current-speed'>Current</label>
                </div>
                <div>
                    <input type='radio' id='typical-speed' name='speed-options' />
                    <label htmlFor='typical-speed'>Typical</label>
                </div>
                <div>
                    <input type='radio' id='speed-comparison' name='speed-options' />
                    <label htmlFor='speed-comparison'>Comparison</label>
                </div>
            </form>
        </>
    );
}

export default SpeedOptions;