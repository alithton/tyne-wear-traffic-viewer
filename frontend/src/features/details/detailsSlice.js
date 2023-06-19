import { createSlice } from "@reduxjs/toolkit";

/*
 * Access the full details of the traffic event currently selected by the user.
 */
export const detailsSlice = createSlice({
    name: 'details',
    initialState: {
        value: {}
    },
    reducers: {
        update: (state, action) => {
            state.value = action.payload
        },
        clear: state => {
            state.value = {}
        }
    }
});

export const { update, clear } = detailsSlice.actions;
export default detailsSlice.reducer;