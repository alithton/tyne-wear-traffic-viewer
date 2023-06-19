import styles from './Dashboard.module.css';
import MapPanel from "./map/MapPanel.jsx";
import OptionsPanel from "./options-panel/OptionsPanel.jsx";
import DetailsPanel from "./details/DetailsPanel.jsx";

function Dashboard() {
    return (
        <main className={styles.dashboard}>
            <MapPanel className={styles['map-panel']} />
            <OptionsPanel className={styles['options-panel']} />
            <DetailsPanel className={styles.details}/>
        </main>
    );
}

export default Dashboard;