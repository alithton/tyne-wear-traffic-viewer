import styles from './FormElement.module.css';
import FormElement from "./FormElement.jsx";
import {useContext, useEffect, useState} from "react";
import {ErrorContext} from "../dashboard/details/ErrorContext.jsx";

const MISSING_REQUIRED_VALUE_MESSAGE = 'This value is required.'

function CustomEventInputField({name, label, type, isRequired, labelClassName, ...props}) {
    const [isTouched, setIsTouched] = useState(false);
    const [error, setError] = useState('');
    const [value, setValue] = useState('');

    const errorMessages = useContext(ErrorContext);
    const errorMessage = errorMessages[name];
    console.log('Error message for ' + name + ': ' + errorMessage);

    const handleChange = (e) => {
        const newValue = e.target.value;
        setValue(newValue);
    }

    const handleBlur = () => {
        if (!isTouched) setIsTouched(true);
    }

    const requiredFieldEmptyOnSubmit = isRequired && errorMessage && !value && !isTouched;
    const requiredFieldEmptyAfterTouch = isRequired && !value && isTouched

    useEffect(() => {
        if (requiredFieldEmptyOnSubmit) {
            setError(errorMessage)
        } else if (requiredFieldEmptyAfterTouch) {
            setError(MISSING_REQUIRED_VALUE_MESSAGE);
        } else {
            setError('');
        }
    }, [value, isTouched, errorMessage])


    return (
        <FormElement className={labelClassName} label={label} error={error}>
            <input
                name={name}
                type={type}
                value={value}
                onChange={handleChange}
                onBlur={handleBlur}
                className={`${error ? styles.error : ''} ${props.className}`}
                {...props}
            />
        </FormElement>
    );
}

export default CustomEventInputField;