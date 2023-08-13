# Tyne and Wear Traffic Viewer

View traffic incidents and conditions for the Tyne and Wear region.

## Set-up

PostgreSQL must be running on the machine in order to start the backend server process.

### Environment Variables

To start up the application, ensure that the following environment variables are set in order to correctly
configure access to the database:

* POSTGRES_DB_USERNAME - the username for the database
* POSTGRES_DB_PASSWORD - the password for the database
* POSTGRES_DB_DATABASE_NAME - the name of the database
* POSTGRES_DB_PORT - the port number for the database

Live data is accessed from the Tyne and Wear open data service API. To access this
service, create an account at https://www.netraveldata.co.uk/ then store the username
and password for that account as environment variables:

* UTMCODS_USERNAME - the username for the account
* UTMCODS_PASSWORD - the password for the account

### Open-Source Routing Machine

The Open-Source routing machine (OSRM) is used to obtain route data in order to ensure
that traffic speed data is correctly overlaid on roads. It is also used to determine the distance
over which travel time data is measured, which is needed to accurately calculate the speed.

A connection to a functioning OSRM server is required to start up the backend server application.
The application assumes that the OSRM server is running on the local machine
on port 5000. Instructions for setting up a local server instance using a Docker image
are provided on the project's Github page at https://github.com/Project-OSRM/osrm-backend.

## Running the application

After ensuring that the environment is configured correctly, open the project in the
Intellij IDEA IDE and run the main Spring Boot application class, which is TyneWearTrafficViewerApplication.
The application accepts several command-line arguments:

* --local - Use locally installed test data (in /src/main/resources/data) rather than accessing the
          Open Data Service API.
* --local-routes - First attempt to use route data that is stored in a local file before loading data from the OSRM server.
* --update-routes - Update locally stored route data.

## Starting the frontend

In order to start the frontend server, Node must be installed (tested with V18.15.0). 
From the command-line, navigate to the root frontend directory (./frontend) and type:

```shell
npm install 
```

This will install the dependencies for the project from the package.json. With the
dependencies successfully installed, start the development server:

```shell
npm run dev
```

This will start a development server on the local machine. By default, the application
can be accessed on port 5173 using the URL http://localhost:5173/.
