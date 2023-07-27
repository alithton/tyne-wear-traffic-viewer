import styles from './Comment.module.css';
import {utcToLocal} from "../../../util/dateHelpers.js";

function Comment({comment}) {
    return (
        <div className={styles.comment__box}>
            <p className={styles.comment__name}>{`${comment.userName} at ${utcToLocal(comment.created)}`}</p>
            <p className={styles.comment__text}>{comment.content}</p>
        </div>
    );
}

export default Comment;