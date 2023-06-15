import styles from './Comment.module.css';

function Comment(props) {
    return (
        <div className={styles.comment__box}>
            <p className={styles.comment__name}>{props.name}</p>
            <p className={styles.comment__text}>{props.text}</p>
        </div>
    );
}

export default Comment;