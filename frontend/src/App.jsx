import styles from './App.module.css'
import Header from "./components/header/Header.jsx";
import Dashboard from "./components/dashboard/Dashboard.jsx";

function App() {

    return (
        <>
            <div className={styles.background}></div>
            <div className={styles['app-container']}>
                <Header className={styles.header} />
                <Dashboard />
            </div>
        </>
    );
}

export default App
