package io.sippbot.assignment.jobs.repositories;

import io.sippbot.assignment.jobs.models.entities.JobRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface JobRepository extends JpaRepository<JobRecord, String>,
        JpaSpecificationExecutor<JobRecord> {
}
