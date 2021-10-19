package com.tul.inventory.configuration;


import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tul.inventory.domain.entities.Product;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfiguration {

  @Value(value = "${kafka.bootstrapAddress}")
  private String bootstrapAddress;

  @Bean
  public Map<String, Object> kafkaProperties() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    configProps.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return configProps;
  }

  @Bean
  public ProducerFactory<String, Product> producerProcessProductFactory() {
    DefaultKafkaProducerFactory<String, Product> producerFactory =
        new DefaultKafkaProducerFactory<>(kafkaProperties());
    producerFactory.setValueSerializer(new JsonSerializer<>(objectMapper()));
    return producerFactory;
  }

  @Bean
  public ProducerFactory<String, String> producerDeleteProductFactory() {
    DefaultKafkaProducerFactory<String, String> producerFactory =
        new DefaultKafkaProducerFactory<>(kafkaProperties());
    producerFactory.setValueSerializer(new JsonSerializer<>(objectMapper()));
    return producerFactory;
  }

  private ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public KafkaTemplate<String, Product> processProductKafkaTemplate() {
    return new KafkaTemplate<>(producerProcessProductFactory());
  }

  @Bean
  public KafkaTemplate<String, String> deleteKafkaTemplate() {
    return new KafkaTemplate<>(producerDeleteProductFactory());
  }
}
