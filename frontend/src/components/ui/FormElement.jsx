import styles from './FormElement.module.css';

function FormElement({name, label, type, value, setValue, error, ...props}) {

    const handleChange = (e) => {
        setValue(e.target.value);
    }

    return (
        <>
            <label className={styles.label} htmlFor={name}>{label}</label>
            <div className={styles['input-container']}>
                <input
                    name={name}
                    type={type}
                    value={value}
                    onChange={handleChange}
                    className={`${error ? styles.error : ''} ${props.className}`}
                    {...props}
                />
                {error && <p className={styles['error-message']}>{error}</p>}
            </div>

        </>
    );
}

export default FormElement;