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
