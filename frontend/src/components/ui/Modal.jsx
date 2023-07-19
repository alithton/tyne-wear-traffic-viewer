import styles from './Modal.module.css';
import Card from "./Card.jsx";

function Modal(props) {
    return (
        <Card className={`${styles.modal} ${props.className}`}>
            {props.children}
        </Card>
    );
}

export default Modal;