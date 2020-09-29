###  Please run this script only from inside config folder, otherwise it will not work   ###

param=$1

if [ $# -eq 0 ] || [ $param = 'db' ]; then
  echo "Installing database service..."
  docker build -q -t db-service:0.0.1 ../db-service
fi

if [ $# -eq 0 ] || [ $param = 'users' ]; then
  #Install users-service
  echo "Installing users service..."
  mvn -q -f ../users-service clean install -DskipTests
  docker build -q -t users-service:0.0.1 ../users-service
fi

if [ $# -eq 0 ] || [ $param = 'trips' ]; then
  #Install trips-service
  echo "Installing trips service..."
  mvn -q -f ../trips-service clean install -DskipTests
  docker build -q -t trips-service:0.0.1 ../trips-service
fi

if [ $# -eq 0 ] || [ $param = 'payments' ]; then
  #Install payments-service
  echo "Installing payments service..."
  mvn -q -f ../payments-service clean install -DskipTests
  docker build -q -t payments-service:0.0.1 ../payments-service
fi

if [ $# -eq 0 ] || [ $param = 'cars' ]; then
  #Install cars-service
  echo "Installing cars service..."
  mvn -q -f ../cars-service clean install -DskipTests
  docker build -q -t cars-service:0.0.1 ../cars-service
fi

#Deploying all services...
echo "Deploying services..."
docker-compose -f docker/docker-compose.yml up -d
