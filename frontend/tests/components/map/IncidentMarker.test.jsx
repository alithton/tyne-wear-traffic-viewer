import {describe, test, expect} from 'vitest';
import {render, screen} from '@testing-library/react';
import {MapContainer} from "react-leaflet";

import IncidentMarker from "../../../src/components/dashboard/map/IncidentMarker";
import {incidentProps} from "../../mocks/handlers.js";

describe('IncidentMarker component', () => {
    test('is rendered', () => {
        render(
            <MapContainer center={[54.97, -1.61]} zoom={13}>
                <IncidentMarker key={incidentProps.systemCodeNumber} incidentData={incidentProps} />
            </MapContainer>
        );
        const marker = screen.getByAltText('Marker');
        expect(marker).toBeInTheDocument();
    });
});