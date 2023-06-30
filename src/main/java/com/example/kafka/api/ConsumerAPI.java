package com.example.kafka.api;

import com.example.kafka.model.avro.User;
import io.confluent.kafka.serializers.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.*;

import java.time.Duration;
import java.util.*;

public class ConsumerAPI {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        properties.put("schema.registry.url", "http://localhost:8081");

        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "my-avro-group");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<Integer, User> consumer = new KafkaConsumer<Integer, User>(properties);

        consumer.subscribe(Collections.singletonList("kafka-avro-topic"));

        try {
            while (true) {
                ConsumerRecords<Integer, User> records = consumer.poll(Duration.ofSeconds(0));
                for (ConsumerRecord record : records) {
                    String message = record.value().toString();
                    String topic = record.topic();
                    int partition = record.partition();
                    System.out.println("DATA: " + message + " TOPIC: " + topic + " PARTITION: " + partition);
                }
                consumer.commitAsync();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
