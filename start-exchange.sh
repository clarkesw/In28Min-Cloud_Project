#!/bin/bash

# Change directory to the project root
cd /cygdrive/c/Users/clark/OneDrive/Desktop/In28Min-Cloud_Project/Currency-Exchange

# Run the first Spring Boot application in the background on port 8100
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8100" &
process_1_pid=$!  # Capture the PID of the first process

# Run the second Spring Boot application in the background on port 8101
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8101" &
process_2_pid=$!  # Capture the PID of the second process

echo "Spring Boot applications started on ports 8100 (PID: $process_1_pid) and 8101 (PID: $process_2_pid)"

# Keep the script running so the background processes don't terminate immediately.
# You can modify this section based on how you want to manage the processes.

# Option 1: Keep the script running indefinitely until explicitly stopped (Ctrl+C)
while true; do
  sleep 1
done

# Option 2: Keep the script running for a specific duration (e.g., 1 hour)
# sleep 3600  # Sleep for 3600 seconds (1 hour)

# Option 3:  Wait for the processes to finish (less common for Spring Boot dev)
# wait $process_1_pid
# wait $process_2_pid

# If you choose Option 1 or 2 and want to add a clean exit:
# Trap SIGINT (Ctrl+C) to kill the background processes before exiting
trap "kill $process_1_pid; kill $process_2_pid; echo 'Shutting down...'; exit" INT

echo "Script finished." # This will only be reached if you use Option 3 or comment out the while loop/sleep.