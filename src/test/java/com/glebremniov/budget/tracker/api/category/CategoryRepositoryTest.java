package com.glebremniov.budget.tracker.api.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CategoryRepositoryTest {

  @Autowired
  private EntityManager entityManager;

  @Autowired
  private CategoryRepository categoryRepository;

  @Test
  void findAll_Should_ReturnAllCategories_When_CategoriesExist() {
    // Given
    final var category1 = new CategoryEntity("Food");
    final var category2 = new CategoryEntity("Transport");
    categoryRepository.save(category1);
    categoryRepository.save(category2);

    // When
    final var categories = categoryRepository.findAll();

    // Then
    assertThat(categories).hasSize(2).contains(category1, category2);
  }

  @Test
  void findAll_Should_ReturnEmptyList_When_NoCategoriesExist() {
    // When
    final var categories = categoryRepository.findAll();

    // Then
    assertThat(categories).isEmpty();
  }

  @Test
  void findById_Should_ReturnCategory_When_CategoryExists() {
    // Given
    var category = new CategoryEntity("Health");
    category = categoryRepository.save(category);

    // When
    final var foundCategory = categoryRepository.findById(category.getId());

    // Then
    assertThat(foundCategory).isPresent();
    assertThat(foundCategory.get().getName()).isEqualTo("Health");
  }

  @Test
  void findById_Should_ReturnEmpty_When_CategoryDoesNotExist() {
    // When
    final var foundCategory = categoryRepository.findById(UUID.randomUUID());

    // Then
    assertThat(foundCategory).isEmpty();
  }

  @Test
  void save_Should_PersistCategory_When_CategoryIsValid() {
    // Given
    final var category = new CategoryEntity("Utilities");

    // When
    final var savedCategory = categoryRepository.save(category);

    // Then
    assertThat(savedCategory.getId()).isNotNull();
    assertThat(savedCategory.getName()).isEqualTo("Utilities");
  }

  @Test
  void save_Should_Fail_When_CategoryNameIsNull() {
    // Given
    final var category = new CategoryEntity(null);

    // When // Then
    assertThatThrownBy(() -> categoryRepository.save(category))
        .isInstanceOf(Exception.class);
  }

  @Test
  void save_Should_Fail_When_CategoryNameExceedsMaxLength() {
    // Given
    final var longName = "A".repeat(101);
    final var category = new CategoryEntity(longName);

    // When // Then
    assertThatThrownBy(() -> {
      categoryRepository.save(category);
      entityManager.flush();
    }).isInstanceOf(DataException.class);
  }

  @Test
  void save_Should_Fail_When_CategoryNameIsNotUnique() {
    // Given
    final var category1 = new CategoryEntity("Duplicate");
    final var category2 = new CategoryEntity("Duplicate");
    categoryRepository.save(category1);

    // When // Then
    assertThatThrownBy(() -> {
      categoryRepository.save(category2);
      entityManager.flush();
    }).isInstanceOf(ConstraintViolationException.class);
  }

  @Test
  void delete_Should_RemoveCategory_When_CategoryExists() {
    // Given
    var category = new CategoryEntity("Entertainment");
    category = categoryRepository.save(category);

    // When
    categoryRepository.delete(category);

    // Then
    final var deletedCategory = categoryRepository.findById(category.getId());
    assertThat(deletedCategory).isEmpty();
  }
}
