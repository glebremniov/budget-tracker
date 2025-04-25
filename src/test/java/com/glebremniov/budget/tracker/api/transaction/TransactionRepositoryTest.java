package com.glebremniov.budget.tracker.api.transaction;

import static org.assertj.core.api.Assertions.assertThat;

import com.glebremniov.budget.tracker.api.category.CategoryEntity;
import com.glebremniov.budget.tracker.api.category.CategoryRepository;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TransactionRepositoryTest {

  private static final Instant DATE = Instant.now();
  private static final BigDecimal AMOUNT = BigDecimal.TEN;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private TransactionRepository transactionRepository;

  @Test
  void findAll_Should_ReturnAllTransactions_When_TransactionsExist() {
    // Given
    final var category = createCategory();
    categoryRepository.save(category);
    final var transaction1 = createAndAssert(
        BigDecimal.ZERO,
        category,
        "Transaction 1"
    );

    final var transaction2 = createAndAssert(
        BigDecimal.TEN,
        category,
        "Transaction 2"
    );

    // When
    final var actual = transactionRepository.findAll();

    // Then
    assertThat(actual).hasSize(2).contains(transaction1, transaction2);
  }

  @Test
  void save_Should_PersistTransaction_When_TransactionIsValid() {
    // Given
    final var description = "Utilities";
    final var category = createCategory();
    final var amount = BigDecimal.valueOf(150);

    // When -> Then
    createAndAssert(
        amount,
        category,
        description
    );
  }

  @Test
  void delete_Should_RemoveTransaction_When_TransactionExists() {
    // Given
    final var transaction = createAndAssert(
        AMOUNT,
        createCategory(),
        "Test Transaction"
    );

    // When
    transactionRepository.delete(transaction);

    // Then
    var deletedTransaction = transactionRepository.findById(transaction.getId());
    assertThat(deletedTransaction).isEmpty();
  }

  @Test
  void findById_Should_ReturnTransaction_When_TransactionExists() {
    // Given
    final var transaction = createAndAssert(
        AMOUNT,
        createCategory(),
        "Test Transaction"
    );

    // When
    final var actual = transactionRepository.findById(transaction.getId());

    // Then
    assertThat(actual).contains(transaction);
  }

  @Test
  void findById_Should_ReturnEmpty_When_TransactionDoesNotExist() {
    // When
    final var actual = transactionRepository.findById(UUID.randomUUID());

    // Then
    assertThat(actual).isEmpty();
  }

  @Test
  void findAll_Should_ReturnEmptyList_When_NoTransactionsExist() {
    // When
    final var transactions = transactionRepository.findAll();

    // Then
    assertThat(transactions).isEmpty();
  }

  private CategoryEntity createCategory() {
    final var category = new CategoryEntity("Food");

    final var actual = categoryRepository.save(category);
    assertThat(actual)
        .withFailMessage("Category should not be null")
        .isNotNull();

    return actual;
  }

  private TransactionEntity createAndAssert(
      final BigDecimal amount,
      final CategoryEntity category,
      final String description
  ) {
    // Given
    final var transaction = new TransactionEntity();
    transaction.setDate(DATE);
    transaction.setAmount(amount);
    transaction.setCategory(category);
    transaction.setDescription(description);

    // When
    final var actual = transactionRepository.save(transaction);

    // Then
    assertTransactionSaved(actual);
    assertThat(actual.getDate()).isEqualTo(DATE);
    assertThat(actual.getAmount()).isEqualTo(amount);
    assertThat(actual.getCategory()).isEqualTo(category);
    assertThat(actual.getDescription()).isEqualTo(description);

    return actual;
  }

  private static void assertTransactionSaved(final TransactionEntity transaction) {
    assertThat(transaction)
        .withFailMessage("Transaction should not be null")
        .isNotNull();
    assertThat(transaction.getId())
        .withFailMessage("Transaction ID should not be null")
        .isNotNull();
  }
}
