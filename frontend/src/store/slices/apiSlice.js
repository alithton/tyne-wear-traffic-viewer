import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/query/react";

const API_BASE_URL = "http://localhost:8080";

export const apiSlice = createApi({
    reducerPath: "api",
    baseQuery: fetchBaseQuery({baseUrl: API_BASE_URL}),
    endpoints: builder => ({
        getIncidents: builder.query({
            query: typeParams => {
                const url = new URL(API_BASE_URL + "/incidents");
                typeParams.forEach(param => url.searchParams.append("type", param));
                return url.toString();
            }
        })
    })
});

export const { useGetIncidentsQuery } = apiSlice;