import styles from './App.module.css'
import Header from "./components/header/Header.jsx";
import Dashboard from "./components/dashboard/Dashboard.jsx";
import Footer from "./components/footer/Footer.jsx";

function App() {

    return (
        <>
            <div className={styles.background}></div>
            <div className={styles['app-container']}>
                <Header className={styles.header} />
                <Dashboard />
                <Footer />
            </div>
        </>
    );
}

export default App
