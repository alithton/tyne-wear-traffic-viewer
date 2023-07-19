import styles from "./ProfileItem.module.css";
import {useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {useEditDetailsMutation} from "../../store/slices/apiSlice.js";
import {updateCredentials} from "../../store/slices/authenticationSlice.js";

function ProfileItem(props) {
    const [isEditing, setIsEditing] = useState(false);
    const [value, setValue] = useState(props.value);
    const [storedValue, setStoredValue] = useState('');

    const credentials = useSelector(state => state.authentication.value.credentials);
    const dispatch = useDispatch();
    const [triggerEdit, {}] = useEditDetailsMutation();

    const handleEdit = () => {
        setIsEditing(true);
        setStoredValue(value);
    }

    const handleCancel = () => {
        setIsEditing(false);
        setValue(storedValue);
    }

    const handleInputChange = (e) => setValue(e.target.value);

    const handleConfirmChange = async (e) => {
        e.preventDefault();
        const updatedCredentials = {...credentials};
        updatedCredentials[props.name.toLowerCase()] = value;
        await triggerEdit({current: credentials, updated: updatedCredentials}).unwrap();
        dispatch(updateCredentials(updatedCredentials));
        setIsEditing(false);
    }

    const notEditing = (
        <div className={styles['profile-item']}>
            <p className={styles['profile-item-label']} >{props.name + ':'}</p>
            <p>{value}</p>
            <button onClick={handleEdit} className={styles['profile-item-button']}>Edit</button>
        </div>
    );

    const editing = (
        <form onSubmit={handleConfirmChange} className={styles['profile-item']} >
            <label className={styles['profile-item-label']} htmlFor={props.name} >{props.name + ':'}</label>
            <input name={props.name} value={value} onChange={handleInputChange} />
            <button type='submit' className={styles['profile-item-button']}>Confirm</button>
            <button type='button' className={styles['profile-item-button']} onClick={handleCancel}>Cancel</button>
        </form>
    );

    return (
        <>
            {isEditing ? editing : notEditing}
        </>
    );
}

export default ProfileItem;