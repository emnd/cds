package com.opengroup.res.jpa;

import com.opengroup.res.jpa.entities.HistoryLog;
import com.opengroup.res.jpa.entities.HistoryLogPK;
import org.springframework.data.repository.CrudRepository;

/**
 * The repository interface to manage HistoryLog entity
 *
 * @author Open groupe
 * @since 1.0.0
 */
public interface HistoryLogRepository extends CrudRepository<HistoryLog, HistoryLogPK> {
}
