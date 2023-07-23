import Modal from "../ui/Modal.jsx";
import styles from './ProfileModal.module.css';
import {useDispatch, useSelector} from "react-redux";
import {logOut} from "../../store/slices/authenticationSlice.js";
import ProfileItem from "./ProfileItem.jsx";
import ChangePassword from "./ChangePassword.jsx";

function ProfileModal(props) {

    const dispatch = useDispatch();
    const credentials = useSelector(state => state.authentication.value.credentials);

    const handleLogout = () => {
        props.handleCloseModal()
        dispatch(logOut())
    };

    return (
        <Modal className={styles['profile-modal']}>
            <h3 className={styles['profile-header']}>User Profile</h3>
            <ProfileItem name='Username' value={credentials.username} />
            <ChangePassword />
            <button onClick={handleLogout}>Log out</button>
        </Modal>
    );
}

export default ProfileModal;