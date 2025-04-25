package com.glebremniov.budget.tracker.common;

import java.util.UUID;

/**
 * Interface for entities that have a unique identifier.
 */
@FunctionalInterface
public interface Identifiable {

  /**
   * Returns the unique identifier of the entity.
   *
   * @return the unique identifier
   */
  UUID getId();

}
