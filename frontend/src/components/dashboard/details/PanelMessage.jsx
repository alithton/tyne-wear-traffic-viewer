import styles from './PanelMessage.module.css';

function PanelMessage({message}) {
    return (
    <div className={styles.panel}>
        <h3>{message}</h3>
    </div>
    );
}

export default PanelMessage;