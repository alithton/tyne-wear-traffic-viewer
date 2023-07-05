import styles from './Dashboard.module.css';
import MapPanel from "./map/MapPanel.jsx";
import OptionsPanel from "./options-panel/OptionsPanel.jsx";
import DetailsPanel from "./details/DetailsPanel.jsx";
import {useState} from "react";

function Dashboard(props) {
    const [showOptions, setShowOptions] = useState(true);
    const toggleOptions = () => setShowOptions(!showOptions);

    return (
        <main className={styles.dashboard}>
            <MapPanel className={styles['map-panel']} />
            <OptionsPanel onToggle={toggleOptions} showOptions={showOptions} className={styles['options-panel']} />
            <DetailsPanel
                className={`${styles.details} ${showOptions ? styles['details-with-options'] : styles['details-no-options']}`}
                handleShowModal={props.handleShowModal}
            />
        </main>
    );
}

export default Dashboard;