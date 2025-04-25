package com.glebremniov.budget.tracker.api.category;

import com.glebremniov.budget.tracker.common.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository for managing categories.
 *
 * <p>Provides CRUD operations and RESTful endpoints for categories.
 */
@RepositoryRestResource(path = CategoryEntity.RESOURCE_NAME)
public interface CategoryRepository extends BaseRepository<CategoryEntity> {

}
