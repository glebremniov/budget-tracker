package com.glebremniov.budget.tracker.common;

import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Base repository interface for all entities.
 *
 * @param <T> the type of the entity
 */
@NoRepositoryBean
public interface BaseRepository<T> extends PagingAndSortingRepository<T, UUID>, ListCrudRepository<T, UUID> {

}
