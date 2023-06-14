import Card from "../../ui/Card.jsx";
import styles from './DetailsPanel.module.css';

function DetailsPanel(props) {
    return (
        <Card className={props.className}>
            <div className={styles['details--no-selection']}>
                <h3 className={styles['no-selection-text']}>Click an icon for further information.</h3>
            </div>
        </Card>
    );
}

export default DetailsPanel;