package com.example.kafka.api;


import io.confluent.kafka.serializers.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.*;

import java.util.*;

public class ProducerApi {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        properties.put("schema.registry.url", "http://localhost:8081");

//        KafkaProducer<Integer, User> producer = new KafkaProducer<Integer, User>(properties);
//
//        User user = User.newBuilder().setName("John").setFavoriteColor("Red").setFavoriteNumber(1).build();
//
//        ProducerRecord<Integer, User> record = new ProducerRecord<>("kafka-avro-topic", 1, user);
//
//        producer.send(record);
//
//        producer.close();

    }
}
