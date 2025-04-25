package com.glebremniov.budget.tracker.api.transaction;

import com.glebremniov.budget.tracker.api.category.CategoryEntity;
import com.glebremniov.budget.tracker.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a transaction.
 *
 * <p>This class is used to store transaction information in the database.
 */
@Entity
@Table(name = TransactionEntity.RESOURCE_NAME)
@Getter
@Setter
@NoArgsConstructor
public class TransactionEntity extends BaseEntity {

  static final String RESOURCE_NAME = "transaction";

  @Column(name = "date", nullable = false)
  private Instant date;

  @Column(name = "amount", nullable = false, precision = 19, scale = 2)
  private BigDecimal amount;

  @ManyToOne(optional = false)
  @JoinColumn(name = "category_id", nullable = false)
  private CategoryEntity category;

  @Column(name = "description", length = 100)
  private String description;

}
