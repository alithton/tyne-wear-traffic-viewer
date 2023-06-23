import Card from "../../ui/Card.jsx";
import TypeOptions from "./TypeOptions.jsx";

import styles from './OptionsPanel.module.css';
import SeverityOptions from "./SeverityOptions.jsx";
import TimeOptions from "./TimeOptions.jsx";
import SpeedOptions from "./SpeedOptions.jsx";

function OptionsPanel(props) {

    const toggleText = props.showOptions ? 'Hide' : 'Show'

    const options = (
        <>
            <TypeOptions />
            <div className={styles['shared-content-option']}>
                <input type='checkbox' id='other-users' name='other-users' />
                <label htmlFor='other-users'>Show events added by other users?</label>
            </div>
            <SeverityOptions />
            <TimeOptions />
            <SpeedOptions />
        </>
    );

    return (
    <Card className={`${props.className} ${styles.options__card}`}>
        <div className={styles.options}>
            <div className={styles['header-line']}>
                <h2 className={styles.options__header}>Options</h2>
                <button className={styles['show-content-toggle']} onClick={props.onToggle}>{toggleText}</button>
            </div>
            {props.showOptions && options}
        </div>
    </Card>
    );
}

export default OptionsPanel;