package ua.edu.ucu.oopmiddle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.ucu.oopmiddle.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}