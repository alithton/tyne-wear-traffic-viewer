import styles from './SectionHeading.module.css';

function SectionHeading(props) {
    return (
        <div className={styles['section-heading']}>
            <h3 className={styles.header}>{props.title}</h3>
            <hr className={styles.line} />
        </div>
    );
}

export default SectionHeading;