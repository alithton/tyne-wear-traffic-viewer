import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/query/react";

const API_BASE_URL = "http://localhost:8080";

/*
 * Defines the queries passed to REST API endpoints using RTK Query from Redux
 * Toolkit.
 */
export const apiSlice = createApi({
    reducerPath: "api",
    baseQuery: fetchBaseQuery({baseUrl: API_BASE_URL}),
    endpoints: builder => ({

        /*
         * Get traffic data from the REST backend. Expects to receive a single
         * object. The `datatype` property defines the types of traffic data
         * requested while the `speedType` property specifies the type requested
         * for the speed data type specifically.
         */
        getIncidents: builder.query({
            query: params => prepareFetchDataUrl(params.dataType, params.speedType)
        })
    })
});

/*
 * Prepare URL to fetch traffic data from the backend. Takes data type and speed
 * type parameters that are used as URL query parameters to specify the type(s)
 * of data to be returned.
 * dataTypes - a list of traffic data types to retrieve.
 * speedType - the speed type data to return.
 */
function prepareFetchDataUrl(dataTypes, speedType) {
    const url = new URL(API_BASE_URL + "/incidents");
    dataTypes.forEach(param => url.searchParams.append("type", param));
    url.searchParams.append("speedType", speedType);
    console.log(url);
    return url.toString();
}

export const { useGetIncidentsQuery } = apiSlice;