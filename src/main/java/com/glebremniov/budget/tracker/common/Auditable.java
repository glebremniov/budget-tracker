package com.glebremniov.budget.tracker.common;

import java.time.Instant;

/**
 * Interface for entities that have audit information.
 *
 * <p>Entities implementing this interface should provide methods to retrieve
 * the creation and last modification timestamps.
 */
public interface Auditable {

  /**
   * Returns the creation timestamp of the entity.
   *
   * @return the creation timestamp
   */
  Instant getCreatedAt();

  /**
   * Returns the last modification timestamp of the entity.
   *
   * @return the last modification timestamp
   */
  Instant getUpdatedAt();

}
