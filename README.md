
# Realtime Group Chat App 

Created using Apache Kafka and Websocket.

Kafka is used to stream messages in real-time. When a user or system generates a message, a Kafka Producer sends it to a specific Kafka topic. The topic serves as a stream where messages are stored temporarily. A Kafka Consumer subscribes to this topic and reads the messages, often as part of a consumer group for load balancing. Kafka efficiently handles large volumes of messages through partitioning and offset management.

After consuming messages from Kafka, the system passes them to a WebSocket server. The WebSocket server then forwards these messages to connected clients based on specific WebSocket topics. On the frontend, a WebSocket client listens for messages and instantly displays them to users in real-time, ensuring low-latency updates.


## Run Locally

#### Need to run zookeeper and kafka locally and specify url in kafkaConfig file.
```bash
to run zookeeper:
bin\windows\zookeeper-server-start.bat config\zookeeper.properties

to run kafka:
bin\windows\kafka-server-start.bat config\server.properties
```
