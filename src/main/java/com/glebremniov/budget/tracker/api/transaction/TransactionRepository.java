package com.glebremniov.budget.tracker.api.transaction;

import com.glebremniov.budget.tracker.common.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository for managing transactions.
 *
 * <p>Provides CRUD operations and RESTful endpoints for transactions.
 */
@RepositoryRestResource(path = TransactionEntity.RESOURCE_NAME)
public interface TransactionRepository extends BaseRepository<TransactionEntity> {

}
