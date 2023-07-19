import {useState} from "react";
import FormElement from "../ui/FormElement.jsx";
import styles from './ChangePassword.module.css';
import {useDispatch, useSelector} from "react-redux";
import {useEditDetailsMutation} from "../../store/slices/apiSlice.js";
import {updateCredentials} from "../../store/slices/authenticationSlice.js";

function ChangePassword() {
    const [isChangingPassword, setIsChangingPassword] = useState(false);
    const [firstEntry, setFirstEntry] = useState('');
    const [secondEntry, setSecondEntry] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const credentials = useSelector(state => state.authentication.value.credentials);
    const dispatch = useDispatch();
    const [triggerEdit, {}] = useEditDetailsMutation();

    const resetState = () => {
        setFirstEntry('');
        setSecondEntry('');
        setErrorMessage('');
        setIsChangingPassword(false);
    }

    const handleConfirm = async () => {
        if (firstEntry === secondEntry) {
            const updatedCredentials = {username: credentials.username, password: firstEntry};
            try {
                await triggerEdit({
                    current: credentials,
                    updated: updatedCredentials}).unwrap();
                dispatch(updateCredentials(updatedCredentials));
                resetState();
            } catch (err) {
                setErrorMessage("Unable to change password.")
            }
        } else {
            setErrorMessage('Passwords must match');
        }
    }


    const notChanging = <button onClick={() => setIsChangingPassword(true)}>Change password</button>

    const changing = (
        <>
            <div className={styles['form-elements']}>
                <FormElement
                    name='passwordFirstEntry'
                    label='Enter new password'
                    type='password'
                    value={firstEntry}
                    setValue={setFirstEntry}
                    error={errorMessage}
                />
                <FormElement
                    name='passwordSecondEntry'
                    label='Re-enter password'
                    type='password'
                    value={secondEntry}
                    setValue={setSecondEntry}
                    error={errorMessage}
                />
            </div>
            <div className={styles.buttons}>
                <button onClick={handleConfirm}>Confirm</button>
                <button onClick={resetState}>Cancel</button>
            </div>
        </>
    );

    return (
        <>
            {isChangingPassword ? changing : notChanging}
        </>
    );
}

export default ChangePassword;