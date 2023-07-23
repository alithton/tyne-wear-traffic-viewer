import {createSlice} from "@reduxjs/toolkit";

export const authenticationSlice = createSlice({
    name: 'authentication',
    initialState: {
        value: {
            isLoggedIn: false,
            credentials: {username: '', password: ''}
        }
    },
    reducers: {
        logIn: (state, action) => {
            state.value.isLoggedIn = true;
            state.value.credentials = action.payload;
        },
        logOut: state => {
            state.value.isLoggedIn = false;
        },
        updateCredentials: (state, action) => {
            state.value.credentials = action.payload;
        }
    }
});

export const {logIn, logOut, updateCredentials} = authenticationSlice.actions;

export default authenticationSlice.reducer;