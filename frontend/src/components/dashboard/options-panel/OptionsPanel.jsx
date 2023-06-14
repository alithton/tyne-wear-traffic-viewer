import Card from "../../ui/Card.jsx";
import TypeOptions from "./TypeOptions.jsx";

import styles from './OptionsPanel.module.css';

function OptionsPanel(props) {
    return (
    <Card className={`${props.className} ${styles.options__card}`}>
        <div className={styles.options}>
            <h3 className={styles.options__header}>Options</h3>
            <TypeOptions />
        </div>
    </Card>
    );
}

export default OptionsPanel;