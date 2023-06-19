import { configureStore } from '@reduxjs/toolkit'
import detailsReducer from "../features/details/detailsSlice.js";
import filterReducer from '../features/filters/filterSlice.js';

export default configureStore({
    reducer: {
        details: detailsReducer,
        filters: filterReducer
    },
})