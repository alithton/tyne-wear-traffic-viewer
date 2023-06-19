import {createSlice} from "@reduxjs/toolkit";

/*
 * State slice holding information on the filters that have been applied.
 */
export const filterSlice = createSlice({
        name: 'filters',
        initialState: {
            value: {
                dataType: [],
                severity: ['Low', 'Medium', 'High'],
                startDate: null,
                endDate: null
            }
        },
        reducers: {
            addDataType: (state, action) => {
                const current = state.value.dataType;
                if (!current.includes(action.payload)) {
                    current.push(action.payload);
                }
            },
            removeDataType: (state, action) => {
                state.value.dataType = state.value.dataType.filter(dataType => dataType !== action.payload);
            },
            addSeverity: (state, action) => {
                state.value.severity.push(action.payload);
            },
            removeSeverity: (state, action) => {
                state.value.severity = state.value.severity.filter(severity => severity !== action.payload);
            }
        }
    }
);

export const { addDataType, removeDataType, addSeverity, removeSeverity } = filterSlice.actions;
export default filterSlice.reducer;