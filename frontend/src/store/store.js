import { configureStore } from '@reduxjs/toolkit'
import detailsReducer from "./slices/detailsSlice.js";
import filterReducer from './slices/filterSlice.js';
import {apiSlice} from "./slices/apiSlice.js";

export default configureStore({
    reducer: {
        details: detailsReducer,
        filters: filterReducer,
        [apiSlice.reducerPath]: apiSlice.reducer
    },
    middleware: getDefaultMiddleware =>
        getDefaultMiddleware().concat(apiSlice.middleware),
    devTools: true
})