import {createSlice} from "@reduxjs/toolkit";

export const authenticationSlice = createSlice({
    name: 'authentication',
    initialState: {
        value: {
            isLoggedIn: false,
            sessionExpired: false,
            credentials: {username: '', password: ''}
        }
    },
    reducers: {
        logIn: (state, action) => {
            state.value.isLoggedIn = true;
            state.value.sessionExpired = false;
            state.value.credentials = action.payload;
        },
        logOut: (state, action) => {
            state.value.isLoggedIn = false;
            state.value.sessionExpired = !!action.payload;
        },
        updateCredentials: (state, action) => {
            state.value.credentials = action.payload;
        }
    }
});

export const {logIn, logOut, updateCredentials} = authenticationSlice.actions;

export default authenticationSlice.reducer;