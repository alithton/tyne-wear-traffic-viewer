import styles from './Footer.module.css';

function Footer() {
    return (
        <footer className={styles.footer}>
            <a className={styles.footer__links} href='#'>Contact</a>
            <a className={styles.footer__links} href='#'>Privacy</a>
        </footer>
    );
}

export default Footer;