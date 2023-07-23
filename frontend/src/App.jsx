import styles from './App.module.css'
import Header from "./components/header/Header.jsx";
import Dashboard from "./components/dashboard/Dashboard.jsx";
import Footer from "./components/footer/Footer.jsx";
import {useState} from "react";

function App() {
    const [showModalBackdrop, setShowModalBackdrop] = useState(false);

    const handleShowModal = () => setShowModalBackdrop(true);
    const handleHideModal = () => setShowModalBackdrop(false);

    return (
        <>
            <div onClick={handleHideModal} className={`${showModalBackdrop ? styles['modal-backdrop'] : ''}`}></div>
            <div className={styles.background}></div>
            <div className={styles['app-container']}>
                <Header className={styles.header} handleShowModal={handleShowModal} handleHideModal={handleHideModal} showModal={showModalBackdrop} />
                <Dashboard handleShowModal={handleShowModal} />
                <Footer />
            </div>
        </>
    );
}

export default App
