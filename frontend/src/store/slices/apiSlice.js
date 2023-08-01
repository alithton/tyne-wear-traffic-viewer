import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/query/react";

const API_BASE_URL = "http://localhost:8080";

/*
 * Defines the queries passed to REST API endpoints using RTK Query from Redux
 * Toolkit.
 */
export const apiSlice = createApi({
    reducerPath: "api",
    baseQuery: fetchBaseQuery({
        baseUrl: API_BASE_URL,
        credentials: "include"
    }),
    tagTypes: ['Comment', 'Data'],
    endpoints: builder => ({

        /*
         * Get traffic data from the REST backend. Expects to receive a single
         * object. The `datatype` property defines the types of traffic data
         * requested while the `speedType` property specifies the type requested
         * for the speed data type specifically.
         */
        getIncidents: builder.query({
            query: params => prepareFetchDataUrl(params),
            providesTags: ['Data']
        }),

        // Get data for a single traffic incident specified by a code number.
        getIncident: builder.query({
           query: codeNumber => "/incidents/" + codeNumber,
            providesTags: ['Comment']
        }),

        // Get the latest CCTV image from the specified camera.
        getCctvImage: builder.query({
            query: codeNumber => ({
                url: prepareFetchImageUrl(codeNumber),
                responseHandler: response => response.blob()
            })
        }),

        // Allow a new user to create an account.
        signUp: builder.mutation({
            query: credentials => ({
                url: '/users/signup',
                method: 'POST',
                // Username and password being used to sign up.
                body: credentials
            })
        }),

        // Log in with existing user credentials.
        login: builder.mutation({
            query: credentials => ({
                url: '/users/login',
                method: 'POST',
                body: credentials
            })
        }),

        // Make changes to user details or credentials.
        editDetails: builder.mutation({
            query: details => ({
                url: '/users/edit',
                method: 'PUT',
                body: details
            })
        }),

        addComment: builder.mutation({
            query: commentDetails => ({
                url: '/incidents/' + commentDetails.codeNumber,
                method: 'POST',
                body: commentDetails.comment
            }),
            // Causes incident data to be reloaded when a comment is added.
            invalidatesTags: ['Comment']
        }),

        // Add a new user-created incident
        addIncident: builder.mutation({
            query: incident => ({
                url: '/incidents',
                method: 'POST',
                body: incident
            }),
            invalidatesTags: ['Data']
        })
    })
});

/*
 * Prepare URL to fetch traffic data from the backend. Takes data type and speed
 * type parameters that are used as URL query parameters to specify the type(s)
 * of data to be returned.
 * options - An object containing the filter parameter options. This is expected
 * to be in the same format as the filter state slice value.
 */
function prepareFetchDataUrl(options) {
    const url = new URL(API_BASE_URL + "/incidents");
    appendParamList(url, options.dataType, "type");
    url.searchParams.append("includeCustomIncidents", options.includeCustomIncidents);
    appendParamList(url, options.severity, "severity");
    url.searchParams.append("speedType", options.speedType);
    url.searchParams.append("currentOnly", options.currentOnly);
    if (options.startDate) appendDateParam(url, options.startDate, "startDate");
    if (options.endDate) appendDateParam(url, options.endDate, "endDate");
    console.log(url);
    return url.toString();
}

/*
 * Append a list of URL search parameters of the same type to a provided URL.
 * url - The URL to which the parameters are to be appended.
 * paramList - The list of parameter values.
 * paramName - The name of the parameter.
 */
function appendParamList(url, paramList, paramName) {
    paramList.forEach(param => url.searchParams.append(paramName, param));
}

/*
 * Append an ISO-formatted date parameter to a provided URL.
 * url - The URL to which the parameter is to be appended.
 * date - The date value.
 * paramName - The name of the parameter.
 */
function appendDateParam(url, date, paramName) {
    url.searchParams.append(paramName, date);
}

/*
 * Prepare URL to fetch the latest CCTV image from the camera referred to by the
 * provided code number.
 */
function prepareFetchImageUrl(codeNumber) {
    console.log('Provided code: ' + codeNumber);
    const url = new URL(API_BASE_URL + "/image/" + codeNumber);
    console.log(url)
    return url.toString();
}

export const { useGetIncidentsQuery,
                useGetIncidentQuery,
                useGetCctvImageQuery,
                useSignUpMutation,
                useLoginMutation,
                useEditDetailsMutation,
                useAddCommentMutation,
                useAddIncidentMutation
                } = apiSlice;