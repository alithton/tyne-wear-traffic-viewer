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
    tagTypes: ['Comment'],
    endpoints: builder => ({

        /*
         * Get traffic data from the REST backend. Expects to receive a single
         * object. The `datatype` property defines the types of traffic data
         * requested while the `speedType` property specifies the type requested
         * for the speed data type specifically.
         */
        getIncidents: builder.query({
            query: params => prepareFetchDataUrl(params.dataType, params.speedType)
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
            invalidatesTags: ['Comment']
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
                useAddCommentMutation
                } = apiSlice;