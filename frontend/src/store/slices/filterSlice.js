import {createSlice} from "@reduxjs/toolkit";

/*
 * State slice holding information on the filters that have been applied.
 */
export const filterSlice = createSlice({
        name: 'filters',
        initialState: {
            value: {
                dataType: ['INCIDENT'],
                includeCustomIncidents: false,
                severity: ['Low', 'Medium', 'High'],
                speedType: 'CURRENT',
                currentOnly: true,
                startDate: '',
                endDate: ''
            }
        },
        reducers: {
            // Add a traffic data type, if it is not already present.
            addDataType: (state, action) => {
                const current = state.value.dataType;
                if (!current.includes(action.payload)) {
                    current.push(action.payload);
                }
            },
            removeDataType: (state, action) => {
                state.value.dataType = state.value.dataType.filter(dataType => dataType !== action.payload);
            },
            setCustomIncidents: (state, action) => {
                state.value.includeCustomIncidents = action.payload;
            },
            addSeverity: (state, action) => {
                state.value.severity.push(action.payload);
            },
            removeSeverity: (state, action) => {
                state.value.severity = state.value.severity.filter(severity => severity !== action.payload);
            },
            changeSpeedType: (state, action) => {
                state.value.speedType = action.payload;
            },
            setCurrentOnly: (state, action) => {
                state.value.currentOnly = action.payload;
            },
            setStartDate: (state, action) => {
                state.value.startDate = action.payload;
            },
            setEndDate: (state, action) => {
                state.value.endDate = action.payload;
            }
        }
    }
);

export const {
    addDataType,
    removeDataType,
    setCustomIncidents,
    addSeverity,
    removeSeverity,
    changeSpeedType,
    setCurrentOnly,
    setStartDate,
    setEndDate
} = filterSlice.actions;
export default filterSlice.reducer;