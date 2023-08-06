import styles from './TypeOptions.module.css';
import {useDispatch, useSelector} from "react-redux";
import {addDataType, removeDataType} from "../../../store/slices/filterSlice.js";
function TypeOptions() {

    const dispatch = useDispatch();
    const {dataType} = useSelector(state => state.filters.value);

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
                <input type='checkbox'
                       id='incidents'
                       name='Incidents'
                       value='INCIDENT'
                       checked={dataType.includes('INCIDENT')}
                       onChange={handleTypeChange}/>
                <label htmlFor='incidents'>Incidents</label>
            </div>
            <div>
                <input type='checkbox'
                       id='events'
                       name='Events'
                       value='EVENT'
                       checked={dataType.includes('EVENT')}
                       onChange={handleTypeChange} />
                <label htmlFor='events'>Events</label>
            </div>
            <div>
                <input type='checkbox'
                       id='accidents'
                       name='Accidents'
                       value='ACCIDENT'
                       checked={dataType.includes('ACCIDENT')}
                       onChange={handleTypeChange} />
                <label htmlFor='accidents'>Accidents</label>
            </div>
            <div>
                <input type='checkbox'
                       id='roadworks'
                       name='Roadworks'
                       value='ROADWORKS'
                       checked={dataType.includes('ROADWORKS')}
                       onChange={handleTypeChange} />
                <label htmlFor='roadworks'>Roadworks</label>
            </div>
            <div>
                <input type='checkbox'
                       id='speed'
                       name='Speed'
                       value='SPEED'
                       checked={dataType.includes('SPEED')}
                       onChange={handleTypeChange} />
                <label htmlFor='speed'>Traffic Speed</label>
            </div>
            <div>
                <input type='checkbox'
                       id='cameras'
                       name='Cameras'
                       value='CAMERA'
                       checked={dataType.includes('CAMERA')}
                       onChange={handleTypeChange} />
                <label htmlFor='cameras'>Cameras</label>
            </div>
        </div>
    )
}

export default TypeOptions;