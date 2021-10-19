package com.tul.inventory.configuration;

import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tul.inventory.infraestructure.dataprovider.core.KafkaConsumer;
import com.tul.inventory.infraestructure.dataprovider.core.entities.KafkaReportedProduct;
import com.tul.inventory.infraestructure.dataprovider.core.mapper.KafkaMapper;
import com.tul.inventory.infraestructure.dataprovider.jpa.InventoryProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {

  @Value(value = "${kafka.bootstrapAddress}")
  private String bootstrapAddress;

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(GROUP_ID_CONFIG, "json");
    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
        new StringDeserializer());
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String>
  kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

  public Deserializer<List<KafkaReportedProduct>> kafkaDeserializer() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.getTypeFactory().constructParametricType(List.class, KafkaReportedProduct.class);
    return new JsonDeserializer<>(objectMapper);
  }

  @Bean
  public KafkaConsumer kafkaConsumer(InventoryProvider inventoryProvider, KafkaMapper kafkaMapper) {
    return new KafkaConsumer(inventoryProvider, kafkaMapper);
  }
}
