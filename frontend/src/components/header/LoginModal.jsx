import styles from './LoginModal.module.css';
import Card from "../ui/Card.jsx";
import {useDispatch} from "react-redux";
import {logIn} from "../../store/slices/authenticationSlice.js";
import {useLoginMutation, useSignUpMutation} from "../../store/slices/apiSlice.js";
import {useState} from "react";
import FormInputElement from "../ui/FormInputElement.jsx";

function LoginModal(props) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [isSignUp, setIsSignUp] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');
    const dispatch = useDispatch();

    const [triggerLogin, {}] = useLoginMutation();
    const [triggerSignUp, {}] = useSignUpMutation();

    const actionText = isSignUp ? 'Sign Up' : 'Log In';

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const credentials = {username: username, password: password};
            if (isSignUp) {
                await triggerSignUp(credentials).unwrap();
            } else {
                await triggerLogin(credentials).unwrap();
            }
            props.handleCloseModal();
            dispatch(logIn(credentials));

        } catch (err) {
            console.log(err);
            const message = isSignUp ? 'A user with that username already exists.'
                                            : 'Username or password did not match.'
            setErrorMessage(message);
        }

    };

    return (
        <Card className={props.display}>
            <form onSubmit={handleSubmit} className={styles['login-form']}>
                <h3 className={styles.heading}>{actionText}</h3>
                <div className={styles.inputs}>
                    <FormInputElement
                        label='Username'
                        name='username'
                        type='text'
                        value={username}
                        setValue={setUsername}
                        error={errorMessage}
                    />
                    <FormInputElement
                        label='Password'
                        name='password'
                        type='password'
                        value={password}
                        setValue={setPassword}
                        error={errorMessage}
                    />
                </div>
                <button type='submit'>{actionText}</button>
                <p className={styles.text}>
                    {`${isSignUp ? "Already have an account? " : "Don't have an account? "}` }
                    <a className={styles.link} onClick={() => setIsSignUp(!isSignUp)}>Click here</a>
                </p>
            </form>
        </Card>
    );
}

export default LoginModal;