import Card from "../../ui/Card.jsx";
import TypeOptions from "./TypeOptions.jsx";

import styles from './OptionsPanel.module.css';
import SeverityOptions from "./SeverityOptions.jsx";
import TimeOptions from "./TimeOptions.jsx";

function OptionsPanel(props) {
    return (
    <Card className={`${props.className} ${styles.options__card}`}>
        <div className={styles.options}>
            <h2 className={styles.options__header}>Options</h2>
            <TypeOptions />
            <SeverityOptions />
            <TimeOptions />
        </div>
    </Card>
    );
}

export default OptionsPanel;