package org.example.warehouse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.warehouse.dto.Order;
import org.example.warehouse.dto.Warehouse;
import org.example.warehouse.repository.WarehouseRepository;
import org.example.warehouse.service.producer.ProducerDeliveryService;
import org.example.warehouse.service.producer.ProducerPayService;
import org.example.warehouse.service.saga.ProducerSagaService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseService {

  private final WarehouseRepository repository;
  private final ProducerDeliveryService producerDeliveryService;
  private final ProducerPayService producerPayService;
  private final ProducerSagaService producerSagaService;

  @Transactional
  @Async
  public void checkOrder(Order order) {
    var warehouse = repository.findById(order.getId())
      .orElse(Warehouse.builder().build());

    if (warehouse.getIsExistInWarehouse().equals(Boolean.TRUE)) {
      order.setStatus("WAREHOUSE");
      warehouse.setIsExistInWarehouse(false);
      repository.save(warehouse);
      producerDeliveryService.sendOrder(order);
      producerPayService.sendOrder(order);
    } else {
      order.setStatus("CANCELED");
      producerSagaService.sendOrder(order);
    }
  }

  @Transactional
  public void save(Warehouse warehouse) {
    repository.save(warehouse);
  }

  @Transactional
  public Warehouse findById(String id) {
    return repository.findById(id)
      .orElse(Warehouse.builder().build());
  }

  @Transactional
  @Async
  public void checkOrderStatus(Order order) {
    var orderStatus = order.getStatus();

    if (orderStatus.equals("CANCELED")) {
      repository.findById(order.getId())
        .ifPresent(warehouse -> {
          warehouse.setIsExistInWarehouse(true);
          repository.save(warehouse);
        });
    }
  }
}
