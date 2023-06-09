import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import App from "../src/App.jsx";

describe('App component', () => {
    test('Renders a header containing "Map"', () => {
        render(<App />);
        const headerElement = screen.getByText('Map');
        expect(headerElement).toBeInTheDocument();
    })
});