import { configureStore } from '@reduxjs/toolkit'
import detailsReducer from "../features/details/detailsSlice.js";

export default configureStore({
    reducer: {
        details: detailsReducer
    },
})