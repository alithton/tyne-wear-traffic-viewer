# Pull the latest osrm-backend image, then use the following commands. Adapted from
# the guide at https://github.com/Project-OSRM/osrm-backend
#
# Also download OpenStreetMap data that covers the relevant area. I used the data for
# England, downloaded from Geofabrik (note this is around 1.2GB):

# wget https://download.geofabrik.de/europe/great-britain/england-latest.osm.pbf

# Navigate to the directory in which the data file is stored, then run these commands.
# To get this to run successfully, I had to increase the memory allocated to the docker container above the default
# 4GB. Peak memory usage was just under 6.4GB.
docker run -t -v "${PWD}:/data" ghcr.io/project-osrm/osrm-backend osrm-extract -p /opt/car.lua /data/england-latest.osm.pbf || echo "osrm-extract failed"

docker run -t -v "${PWD}:/data" ghcr.io/project-osrm/osrm-backend osrm-partition /data/england-latest.osrm || echo "osrm-partition failed"
docker run -t -v "${PWD}:/data" ghcr.io/project-osrm/osrm-backend osrm-customize /data/england-latest.osrm || echo "osrm-customize failed"

docker run -t -i -p 5678:5678 -v "${PWD}:/data" ghcr.io/project-osrm/osrm-backend osrm-routed --algorithm mld /data/england-latest.osrm

# Send http requests to the server. E.g.

curl "http://localhost:5000/route/v1/driving/-1.58476070926927,54.9101791759364;-1.58847047258996,54.9150785396271?geometries=geojson"

