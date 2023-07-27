import styles from './Comments.module.css';
import Comment from "./Comment.jsx";
import {useState} from "react";
import {useSelector} from "react-redux";
import {useAddCommentMutation} from "../../../store/slices/apiSlice.js";

function Comments(props) {
    const [text, setText] = useState('');
    const userDetails = useSelector(state => state.authentication.value);
    const [triggerAddComment, {}] = useAddCommentMutation();

    const handleComment = (e) => {
        e.preventDefault();
        const newComment = {
            codeNumber: props.codeNumber,
            comment: {
                userName: userDetails.credentials.username,
                created: new Date(),
                content: e.target[0].value
            }
        };
        triggerAddComment(newComment);
        setText('');
    }

    const handleChange = (e) => setText(e.target.value);

    const commentForm = (
        <form className={styles.comment__form} onSubmit={handleComment}>
            <label htmlFor='comment'>Leave a comment</label>
            <textarea className={styles.comment__text} id='comment' name='comment' value={text} onChange={handleChange} />
            <button type='submit'  className={styles.comment__button}>Submit</button>
        </form>
    );

    return (
        <>
            <h2>Comments</h2>
            <div data-testid='comments'>
                {props.comments.map(comment => (
                    <Comment key={comment.id} comment={comment} />
                ))}
            </div>

            {userDetails.isLoggedIn && commentForm}
        </>
    );
}

export default Comments;