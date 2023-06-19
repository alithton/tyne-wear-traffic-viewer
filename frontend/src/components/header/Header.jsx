import styles from './Header.module.css';

function Header(props) {
    return (
        <header className={`${styles.header} ${props.className}`}>
            <a href='#' className={styles.logo}>
                <h1 className={styles.logo__main}>Traffic Viewer</h1>
                <h2 className={styles.logo__sub}>Tyne and Wear</h2>
            </a>
            <p className={styles['user-profile']}>Profile</p>
        </header>
    );
}
export default Header;