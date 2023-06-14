import styles from './TypeOptions.module.css';
function TypeOptions() {
    return (
        <div className={styles.typeOptions}>
            <div>
                <input type='checkbox' id='incidents' name='Incidents' checked/>
                <label htmlFor='incidents'>Incidents</label>
            </div>
            <div>
                <input type='checkbox' id='events' name='Events' />
                <label htmlFor='events'>Events</label>
            </div>
            <div>
                <input type='checkbox' id='speed' name='Speed' />
                <label htmlFor='speed'>Speed</label>
            </div>
        </div>
    )
}

export default TypeOptions;