import { describe, it, expect } from 'vitest';
import { render } from '@testing-library/react';
import App from "../src/App.jsx";
import {Provider} from "react-redux";
import store from "../src/store/store.js";

describe('App component', () => {
    it('Renders a leaflet container', () => {
        const { container } = render(
            <Provider store={store}>
                <App />
            </Provider>
        );
        const mapElement = container.querySelector('.leaflet-container');
        expect(mapElement).toBeInTheDocument();
    })
});