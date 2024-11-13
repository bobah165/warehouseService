package org.example.warehouse.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "general", name = "warehouse")
public class Warehouse {
  @Id
  @Column(name = "id")
  private String orderId;
  @Column(name = "name")
  private String name;
  @Column(name = "is_exist_in_warehouse")
  @Builder.Default
  private Boolean isExistInWarehouse = false;
}
