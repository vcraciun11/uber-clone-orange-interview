#Stopping all services
echo "Stoping all serices..."
docker stop $(docker ps -qa)
