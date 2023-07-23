import styles from './Header.module.css';
import loginStyles from './LoginModal.module.css';
import {useSelector} from "react-redux";
import {useEffect, useState} from "react";
import LoginModal from "./LoginModal.jsx";
import ProfileModal from "./ProfileModal.jsx";

function Header(props) {
    const [showModal, setShowModal] = useState(false);

    const {isLoggedIn} = useSelector(state => state.authentication.value);
    console.log("Logged In? " + isLoggedIn);

    const handleShowModal = () => {
        setShowModal(true);
        props.handleShowModal();
    }

    const handleCloseModal = () => {
        setShowModal(false);
        props.handleHideModal();
    }

    // hide modal when background is clicked.
    useEffect(() => {
        if (!props.showModal) setShowModal(false);
    }, [props.showModal]);

    return (
        <>
            <header className={`${styles.header} ${props.className}`}>
                <a href='#' className={styles.logo}>
                    <h1 className={styles.logo__main}>Traffic Viewer</h1>
                    <h2 className={styles.logo__sub}>Tyne and Wear</h2>
                </a>
                <p className={styles['user-profile']} onClick={handleShowModal}>
                    {isLoggedIn ? 'Profile' : 'Log in'}
                </p>
            </header>
            {showModal && !isLoggedIn && <LoginModal handleCloseModal={handleCloseModal} display={loginStyles['login-card']} />}
            {showModal && isLoggedIn && <ProfileModal handleCloseModal={handleCloseModal} username={'user'} password={'pass'} />}
        </>

    );
}
export default Header;