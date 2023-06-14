import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import Map from "../../../src/components/dashboard/map/Map.jsx";

describe('Map component', () => {
    test('Has an attribution', () => {
        render(<Map />);
        const attribution = screen.getByRole('link', {name: 'OpenStreetMap'});
        expect(attribution).toHaveAttribute('href', 'https://www.openstreetmap.org/copyright');
    });

    test('Markers have rendered', async () => {
        render(<Map />);
        const renderedMarkers = await screen.findAllByAltText('Marker');
        expect(renderedMarkers.length).toBeGreaterThan(0);
    });
});