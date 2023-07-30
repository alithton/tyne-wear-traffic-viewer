import styles from './FormElement.module.css';
import FormElement from "./FormElement.jsx";

function FormInputElement({name, label, type, value, setValue, error, ...props}) {

    const handleChange = (e) => {
        setValue(e.target.value);
    }

    return (
        <FormElement label={label} error={error}>
                <input
                    name={name}
                    type={type}
                    value={value}
                    onChange={handleChange}
                    className={`${error ? styles.error : ''} ${props.className}`}
                    {...props}
                />
        </FormElement>
    );
}

export default FormInputElement;