package org.example.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.example.warehouse.dto.Warehouse;
import org.example.warehouse.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/warehouse")
public class WarehouseController {

  private final WarehouseService warehouseService;

  @PostMapping("/save")
  public ResponseEntity createWarehouse(@RequestBody Warehouse warehouse) {
    warehouseService.save(warehouse);
    return ResponseEntity.ok("Warehouse with name " + warehouse.getName() + " was created");
  }

  @GetMapping("/status/{warehouseId}")
  public ResponseEntity getCurrentStatus(@PathVariable("warehouseId") String warehouseId) {
    var warehouse = warehouseService.findById(warehouseId);
    return ResponseEntity.ok("Warehouse status is " + warehouse.getIsExistInWarehouse());
  }
}
