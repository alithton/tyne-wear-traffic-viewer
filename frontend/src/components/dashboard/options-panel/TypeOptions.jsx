import styles from './TypeOptions.module.css';
import {useDispatch} from "react-redux";
import {addDataType, removeDataType} from "../../../store/slices/filterSlice.js";
function TypeOptions() {

    const dispatch = useDispatch();

    const handleTypeChange = e => {
        const selectedType = e.target.value;
        console.log(selectedType);
        if (e.target.checked) {
            dispatch(addDataType(selectedType));
        } else {
            dispatch(removeDataType(selectedType));
        }
    }

    return (
        <div className={styles.typeOptions}>
            <div>
                <input type='checkbox' id='incidents' name='Incidents' value='INCIDENT' defaultChecked={true} onChange={handleTypeChange}/>
                <label htmlFor='incidents'>Incidents</label>
            </div>
            <div>
                <input type='checkbox' id='events' name='Events' value='EVENT' onChange={handleTypeChange} />
                <label htmlFor='events'>Events</label>
            </div>
            <div>
                <input type='checkbox' id='accidents' name='Accidents' value='ACCIDENT' onChange={handleTypeChange} />
                <label htmlFor='accidents'>Accidents</label>
            </div>
            <div>
                <input type='checkbox' id='roadworks' name='Roadworks' value='ROADWORKS' onChange={handleTypeChange} />
                <label htmlFor='roadworks'>Roadworks</label>
            </div>
            <div>
                <input type='checkbox' id='speed' name='Speed' value='SPEED' onChange={handleTypeChange} />
                <label htmlFor='speed'>Traffic Speed</label>
            </div>
            <div>
                <input type='checkbox' id='cameras' name='Cameras' value='CAMERA' onChange={handleTypeChange} />
                <label htmlFor='cameras'>Cameras</label>
            </div>
        </div>
    )
}

export default TypeOptions;