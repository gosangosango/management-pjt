package com.management.domain.management;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagementRepository extends JpaRepository<Management, Long> {
    Management findByDateTime(String dateTime);
}
