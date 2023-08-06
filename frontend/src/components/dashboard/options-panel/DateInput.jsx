import {useState} from "react";
import {useDispatch} from "react-redux";
import {isValidDate} from "../../../util/dateHelpers.js";

function DateInput({id, label, value, dispatchFn, disabled, ...others}) {

    const dateValue = value ? new Date(value).toISOString().split('T')[0] : '';

    const [date, setDate] = useState(dateValue);

    const dispatch = useDispatch();

    const handleChange = (e) => {
        const newValue = e.target.value;
        const date = isValidDate(newValue) ? new Date(newValue).toISOString() : '';
        setDate(newValue);
        dispatch(dispatchFn(date));
    }

    return (
        <>
            <label htmlFor={id}>{label}</label>
            <input type='date' id={id} {...others} value={date} onChange={handleChange} disabled={disabled}/>
        </>
    );
}

export default DateInput;