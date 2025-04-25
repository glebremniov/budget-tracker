package com.glebremniov.budget.tracker.api.category;

import com.glebremniov.budget.tracker.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a category.
 *
 * <p>This class is used to store category information in the database.
 */
@Entity
@Table(name = CategoryEntity.RESOURCE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends BaseEntity {

  final static String RESOURCE_NAME = "category";

  @Column(name = "name", nullable = false, unique = true, length = 100)
  private String name;

}
