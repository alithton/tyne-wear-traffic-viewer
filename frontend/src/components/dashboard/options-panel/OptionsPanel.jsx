import Card from "../../ui/Card.jsx";
import TypeOptions from "./TypeOptions.jsx";

import styles from './OptionsPanel.module.css';
import SeverityOptions from "./SeverityOptions.jsx";
import TimeOptions from "./TimeOptions.jsx";
import SpeedOptions from "./SpeedOptions.jsx";
import {useDispatch, useSelector} from "react-redux";
import {setCustomIncidents} from "../../../store/slices/filterSlice.js";

function OptionsPanel(props) {

    const toggleText = props.showOptions ? 'Hide' : 'Show'

    const dispatch = useDispatch();
    const {includeCustomIncidents} = useSelector(state => state.filters.value);

    // Update the filter state when the option to show user-created incidents is checked or unchecked.
    const handleShowCustomIncidentsChange = (e) => {
        dispatch(setCustomIncidents(e.target.checked));
    }

    const options = (
        <>
            <TypeOptions />
            <div className={styles['shared-content-option']}>
                <input type='checkbox'
                       id='other-users'
                       name='other-users'
                       checked={includeCustomIncidents}
                       onChange={handleShowCustomIncidentsChange}
                />
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