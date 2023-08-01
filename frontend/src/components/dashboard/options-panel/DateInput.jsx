import {useState} from "react";
import {useDispatch} from "react-redux";
import {isValidDate} from "../../../util/dateHelpers.js";

function DateInput({id, label, dispatchFn, disabled, ...others}) {
    const [date, setDate] = useState('');

    const dispatch = useDispatch();

    const handleChange = (e) => {
        const value = e.target.value;
        console.log(value);
        const date = isValidDate(value) ? new Date(value).toISOString() : '';
        setDate(value);
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