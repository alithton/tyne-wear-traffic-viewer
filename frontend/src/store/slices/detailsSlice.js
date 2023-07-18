import { createSlice } from "@reduxjs/toolkit";

/*
 * Access the full details of the traffic event currently selected by the user.
 */
export const detailsSlice = createSlice({
    name: 'details',
    initialState: {
        value: {
            // Has a marker been selected?
            markerSelected: false,
            // Did the user opt to create a new custom marker?
            newMarker: false,
            // The data associated with the selected marker.
            data: {}
        }
    },
    reducers: {
        update: (state, action) => {
            state.value = {markerSelected: true, newMarker: false, data: action.payload};
        },
        clear: state => {
            state.value = {markerSelected: false, newMarker: false, data: {}}
        },
        addNew: (state, action) => {
            state.value = {markerSelected: false, newMarker: true, data: action.payload};
        }
    }
});

export const { update, clear, addNew } = detailsSlice.actions;
export default detailsSlice.reducer;