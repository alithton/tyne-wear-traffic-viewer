import {describe, it, expect} from 'vitest';
import {fireEvent, render, screen} from "@testing-library/react";
import {Provider} from "react-redux";
import store from "../../../src/store/store.js";
import Dashboard from "../../../src/components/dashboard/Dashboard.jsx";
import userEvent from "@testing-library/user-event";
import {markers as incidentData} from "../../mocks/handlers.js"

/**
 * Integration tests for the dashboard components.
 */
describe('Dashboard component', () => {
    beforeEach(() => {
        render(
            <Provider store={store}>
                <Dashboard />
            </Provider>
        );
    });

    it('Renders a map with three markers before any options are changed', async () => {
       const markers = await screen.findAllByAltText('Marker');
       expect(markers.length).toBe(3);
    });

    it('Removes markers when the corresponding severity option is unchecked', async () => {
        const lowSeverityCheckBox = screen.getByLabelText('Low');
        expect(lowSeverityCheckBox).toBeInTheDocument();

        // Uncheck 'Low'
        await userEvent.click(lowSeverityCheckBox);
        let markers = screen.getAllByAltText('Marker');
        expect(markers.length).toBe(2);

        // Uncheck 'Medium'
        await userEvent.click(screen.getByLabelText('Medium'));
        markers = screen.getAllByAltText('Marker');
        expect(markers.length).toBe(1);

        // Uncheck 'High'
        await userEvent.click(screen.getByLabelText('High'));
        markers = screen.queryAllByAltText('Marker');
        expect(markers.length).toBe(0);

        // Recheck 'Low'
        await userEvent.click(lowSeverityCheckBox);
        markers = screen.getAllByAltText('Marker');
        expect(markers.length).toBe(1);
    });

    it('Details panel displays a default message when no markers are selected', () => {
        const defaultMessage = screen.getByText('Click an icon for further information.');
        expect(defaultMessage).toBeInTheDocument();
    });

    it('Details panel removes default message and displays information about a selected event when a marker is selected', async () => {
        const markers = await screen.findAllByAltText('Marker');
        fireEvent.click(markers[0]);

        // Check that default message has been removed
        const defaultMessage = screen.queryByText('Click an icon for further information.');
        expect(defaultMessage).toBeNull();

        // Check that event information is correctly displayed

        const systemCodeText = screen.getByText('Tyneandwear0000792812');
        expect(systemCodeText).toBeInTheDocument();

    });

    it('Details panel displays a correctly formatted location string', async () => {
        const markers = await screen.findAllByAltText('Marker');
        fireEvent.click(markers[0]);
        const selectedIncident = incidentData[0];

        const locationText = screen.getByText('Location:', {exact: false});
        expect(locationText).toBeInTheDocument();

        const selectedIncidentLatitude = selectedIncident.point.latitude.toFixed(4);
        const selectedIncidentLongitude = selectedIncident.point.longitude.toFixed(4);

        expect(locationText.textContent.includes(selectedIncident.locationDescription)).toBeTruthy();
        expect(locationText.textContent.includes(selectedIncidentLatitude)).toBeTruthy();
        expect(locationText.textContent.includes(selectedIncidentLongitude)).toBeTruthy();
    });

    it('Details panel displays correctly formatted data- and time stamps for the start and end dates', async () => {
        const markers = await screen.findAllByAltText('Marker');
        fireEvent.click(markers[0]);
        const selectedIncident = incidentData[0];

        const startDate = new Date(selectedIncident.incidentTime)
            .toLocaleString(undefined, {dateStyle: 'medium', timeStyle: 'short'});
        const endDate = new Date(selectedIncident.endTime)
            .toLocaleString(undefined, {dateStyle: 'medium', timeStyle: 'short'});

        const startDateElement = screen.getByText('Started', {exact: false});
        const endDateElement = screen.getByText('Finishing', {exact: false});

        expect(startDateElement).toBeInTheDocument();
        expect(endDateElement).toBeInTheDocument();

        expect(startDateElement.textContent.includes(startDate)).toBeTruthy();
        expect(endDateElement.textContent.includes(endDate)).toBeTruthy();
    });

    it('Comments can be added to the comment section', async () => {
        const markers = await screen.findAllByAltText('Marker');
        fireEvent.click(markers[0]);

        const commentBox = screen.getByLabelText('Leave a comment');
        const submitButton = screen.getByRole('button', {name: 'Submit'});

        expect(commentBox).toBeInTheDocument();
        expect(submitButton).toBeInTheDocument();

        await userEvent.type(commentBox, 'Test comment');
        await userEvent.click(submitButton);

        // TODO - fix these failing tests
        const comments = screen.getByTestId('comments');
        expect(comments).toBeInTheDocument();
    });
})