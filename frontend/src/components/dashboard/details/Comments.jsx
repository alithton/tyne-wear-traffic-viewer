import styles from './Comments.module.css';
import Comment from "./Comment.jsx";
import {useState} from "react";

const dummy_comments = [
    {id: 1, name: 'Alice', text: 'So much disruption!'},
    {id: 2, name: 'Bob', text: 'Good to now about this.'}
];

function Comments() {
    const [comments, setComments] = useState(dummy_comments);

    const handleComment = (e) => {
        e.preventDefault();
        const index = comments.at(-1).id + 1;
        const newComment = {id: index, name: 'Anon', text: e.target[0].value};
        setComments([...comments, newComment]);
    }

    return (
        <>
            <h2>Comments</h2>
            <div>
                {comments.map(comment => (
                    <Comment key={comment.id} name={comment.name} text={comment.text} />
                ))}
            </div>

            <form className={styles.comment__form} onSubmit={handleComment}>
                <label htmlFor='comment'>Leave a comment</label>
                <textarea className={styles.comment__text} id='comment' name='comment' />
                <button type='submit'  className={styles.comment__button}>Submit</button>
            </form>
        </>
    );
}

export default Comments;