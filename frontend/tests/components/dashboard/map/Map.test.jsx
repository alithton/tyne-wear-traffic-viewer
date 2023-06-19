import { describe, it, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import Map from "../../../../src/components/dashboard/map/Map.jsx";
import {Provider} from "react-redux";
import store from "../../../../src/store/store.js";

describe('Map component', () => {
    beforeEach(() => {
        render(
            <Provider store={store}>
                <Map />
            </Provider>
        );
    });

    it('Has an attribution', () => {
        const attribution = screen.getByRole('link', {name: 'OpenStreetMap'});
        expect(attribution).toHaveAttribute('href', 'https://www.openstreetmap.org/copyright');
    });

    it('Markers have rendered', async () => {
        const renderedMarkers = await screen.findAllByAltText('Marker');
        expect(renderedMarkers.length).toBeGreaterThan(0);
    });
});