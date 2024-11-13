package org.example.warehouse.service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.warehouse.dto.Order;
import org.example.warehouse.service.WarehouseService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerPayService {

  private static final String topicCreateOrder = "${topic.consumer.pay}";
  private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";
  private final WarehouseService warehouseService;

  @Transactional
  @KafkaListener(topics = topicCreateOrder, groupId = kafkaConsumerGroupId, properties = {"spring.json.value.default.type=org.example.warehouse.dto.Order"})
  public Order checkOrder(Order orderEvent) {
    log.info("Message consumed {}", orderEvent);
    warehouseService.checkOrder(orderEvent);
    return orderEvent;
  }
}
