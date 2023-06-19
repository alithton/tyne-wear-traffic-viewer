import styles from './TypeOptions.module.css';
function TypeOptions() {
    return (
        <div className={styles.typeOptions}>
            <div>
                <input type='checkbox' id='incidents' name='Incidents'/>
                <label htmlFor='incidents'>Incidents</label>
            </div>
            <div>
                <input type='checkbox' id='events' name='Events' />
                <label htmlFor='events'>Events</label>
            </div>
            <div>
                <input type='checkbox' id='accidents' name='Accidents' />
                <label htmlFor='accidents'>Accidents</label>
            </div>
            <div>
                <input type='checkbox' id='roadworks' name='Roadworks' />
                <label htmlFor='roadworks'>Roadworks</label>
            </div>
            <div>
                <input type='checkbox' id='speed' name='Speed' />
                <label htmlFor='speed'>Speed</label>
            </div>
            <div>
                <input type='checkbox' id='cameras' name='Cameras' />
                <label htmlFor='cameras'>Cameras</label>
            </div>
        </div>
    )
}

export default TypeOptions;