import {useState} from "react";

function DateInput({id, label, disabled, ...others}) {
    const [date, setDate] = useState('');

    const handleChange = (e) => {
        setDate(e.target.value);
    }

    return (
        <>
            <label htmlFor={id}>{label}</label>
            <input type='date' id={id} {...others} value={date} onChange={handleChange} disabled={disabled}/>
        </>
    );
}

export default DateInput;