# Tyne and Wear Traffic Viewer

View traffic incidents and conditions for the Tyne and Wear region in North-East England.
Live travel data is obtained from the NE Travel Data (https://www.netraveldata.co.uk/) open
data service. Available data includes for roadworks, accidents and other traffic
incidents as well as traffic speed data and current traffic CCTV images.

![Landing page png](./media/images/landing-screen.png)

Users can browse traffic data on an interactive map and view additional information
about traffic incidents by mousing over or clicking on the icons.

![Incident information png](./media/images/incident-information.png)

The application is configurable, with options for users to select the kind of
data they want to view via the filter options.

![Filter option png](./media/images/filter-options.png)

Users can register for an account so that they may leave comments and also add 
custom traffic events.

# Running the application

Ensure that the prerequisites are fulfilled and then clone the repository.

## Prerequisites

Before starting, ensure that the following are installed on the local machine:

* JDK 17 or later
* PostgreSQL 15.3
* Node (tested with V18.15.0)

## Running the backend

The backend server requires a PostgreSQL database to be running. The following environment 
variables are required in order to configure access to the database:

* POSTGRES_DB_USERNAME - the username for the database
* POSTGRES_DB_PASSWORD - the password for the database
* POSTGRES_DB_DATABASE_NAME - the name of the database
* POSTGRES_DB_PORT - the port number for the database

Live data is accessed from the Tyne and Wear open data service API. To access this
service, create an account at https://www.netraveldata.co.uk/. The following 
environment variables reference the username and password for that account:

* UTMCODS_USERNAME - the username for the account
* UTMCODS_PASSWORD - the password for the account

The application accepts several command-line arguments, which can be used to
determine whether local, precomputed data should be used. This can be useful
for troubleshooting connection issues:

* --local - Use locally installed test data (in /src/main/resources/data) rather than accessing the
          Open Data Service API.
* --local-routes - Use route data that is stored in a local file instead of loading data from the OSRM server.
* --update-routes - Replace locally stored route data with routes obtained from the OSRM server.

To run the application, enter the following. Text between angle brackets should
be replaced with the relevant values for the environment values defined above.
This command includes the `--local-routes` flag to avoid the necessity of setting
up and running a routing machine server. If that has already been done, it can be
omitted.

```shell
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="\
-DPOSTGRES_DB_PORT=<port_number> \
-DPOSTGRES_DB_DATABASE_NAME=<database_name> \
-DPOSTGRES_DB_USERNAME=<db_username> \
-DPOSTGRES_DB_PASSWORD=<db_password> \
-DUTMCODS_USERNAME=<data_service_username> \
-DUTMCODS_PASSWORD=<data_service_password>" \
-Dspring-boot.run.arguments="--local-routes"
```

### Obtaining routing data

Routing data is used to accurately overlay the average traffic speed data onto the
road network. Route data is bundled with the project files, providing accurate route
data for all the traffic speed link segments as of August 2023. If newer route data
is required, it can be obtained using a local Open-Source routing machine (OSRM)
server.

The application assumes that the OSRM server is running on the local machine
on port 5000. Instructions for setting up a local server instance using a Docker image
are provided on the project's Github page at https://github.com/Project-OSRM/osrm-backend.

## Starting the frontend

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
