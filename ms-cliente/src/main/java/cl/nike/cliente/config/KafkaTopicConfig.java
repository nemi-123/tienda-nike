package cl.nike.cliente.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topicProductoCreated() {

        log.debug("Registrando topic Kafka → topic: {}", "catalogo.producto.created");

        return TopicBuilder.name("catalogo.producto.created")
                .partitions(1) // En desarrollo con 1 está bien
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicProductoUpdated() {

        log.debug("Registrando topic Kafka → topic: {}", "catalogo.producto.updated");

        return TopicBuilder.name("catalogo.producto.updated")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicProductoDeleted() {

        log.debug("Registrando topic Kafka → topic: {}", "catalogo.producto.deleted");

        return TopicBuilder.name("catalogo.producto.deleted")
                .partitions(1)
                .replicas(1)
                .build();
    }
}