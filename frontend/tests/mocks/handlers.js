import { rest } from 'msw';

// Some dummy data
export const markers = [
    {
        "systemCodeNumber": "Tyneandwear0000792812",
        "type": "Incident",
        "incidentTypeDescription": "BRIDGE CLOSED",
        "shortDescription": "Salters bridge closed to motoris",
        "longDescription": "Salters bridge closed to motorised traffic under a Experimental Traffic Regulations Order. More information at https://safenewcastlebridges.commonplace.is/",
        "point": {
            "easting": 425479,
            "northing": 568542,
            "latitude": 55.0107869827092000,
            "longitude": -1.6031330330235400
        },
        "locationDescription": "Salters Brg",
        "creationDate": "2020-08-20T09:56:49.733+0100",
        "dataSourceTypeRef": "UTMC",
        "confirmedDate": "2020-08-13T00:00:00.000+0100",
        "modifiedDate": "2021-08-09T12:37:51.983+0100",
        "severityTypeRefDescription": "Low",
        "lanesAffectedTypeRefDescription": "T T",
        "diversionInForce": "N",
        "phaseTypeRef": "Current",
        "incidentTime": "2020-08-13T06:00:35.857+0100",
        "endTime": "2023-08-13T16:00:35.857+0100"
    },
    {
        "systemCodeNumber": "Tyneandwear0000792813",
        "type": "Incident",
        "incidentTypeDescription": "BRIDGE CLOSED",
        "shortDescription": "Haldane Terrace bridge closed to",
        "longDescription": "Haldane Terrace bridge closed to motorised traffic under a Experimental Traffic Regulations Order. More information at https://safenewcastlebridges.commonplace.is/",
        "point": {
            "easting": 425290,
            "northing": 565906,
            "latitude": 54.9871097390072000,
            "longitude": -1.6063215503412300
        },
        "locationDescription": "Haldane Tce Brg",
        "creationDate": "2020-08-20T10:00:57.537+0100",
        "dataSourceTypeRef": "UTMC",
        "confirmedDate": "2020-08-13T10:00:00.000+0100",
        "modifiedDate": "2021-08-09T12:35:53.567+0100",
        "severityTypeRefDescription": "Low",
        "lanesAffectedTypeRefDescription": "T T",
        "diversionInForce": "N",
        "phaseTypeRef": "Current",
        "incidentTime": "2020-08-13T06:00:16.033+0100",
        "endTime": "2023-08-13T16:00:16.033+0100"
    },
    {
        "systemCodeNumber": "Tyneandwear0000792814",
        "type": "Incident",
        "incidentTypeDescription": "BRIDGE CLOSED",
        "shortDescription": "Argyle Street bridge closed to m",
        "longDescription": "Argyle Street bridge closed to motorised traffic under a Experimental Traffic Regulations Order. More information at https://safenewcastlebridges.commonplace.is/",
        "point": {
            "easting": 425471,
            "northing": 564383,
            "latitude": 54.9734114023724000,
            "longitude": -1.6036212585441200
        },
        "locationDescription": "Argyle St Brg",
        "creationDate": "2020-08-20T10:06:19.263+0100",
        "dataSourceTypeRef": "UTMC",
        "confirmedDate": "2020-08-13T10:05:00.000+0100",
        "modifiedDate": "2021-08-09T12:37:11.853+0100",
        "severityTypeRefDescription": "Low",
        "lanesAffectedTypeRefDescription": "T T",
        "diversionInForce": "N",
        "phaseTypeRef": "Current",
        "incidentTime": "2020-08-13T06:00:54.840+0100",
        "endTime": "2023-08-13T16:00:54.840+0100"
    }
];

export const incidentProps = {
    systemCodeNumber: "Tyneandwear0000792812",
    shortDescription: "Salters bridge closed to motoris",
    longDescription: "Salters bridge closed to motorised traffic under a Experimental Traffic Regulations Order. More information at https://safenewcastlebridges.commonplace.is/",
    incidentPosition: [54.9871097390072000, -1.6031330330235400]
};

export const handler = rest.get('http://localhost:8080/incidents', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(markers));
});

