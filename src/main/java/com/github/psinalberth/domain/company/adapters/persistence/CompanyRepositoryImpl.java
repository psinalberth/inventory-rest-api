package com.github.psinalberth.domain.company.adapters.persistence;

import com.github.psinalberth.domain.company.application.domain.model.Company;
import com.github.psinalberth.domain.shared.application.port.RetrieveCompanyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements RetrieveCompanyPort {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Company retrieve(String name) {

        String sql = "select c from Company c where c.name = :name";
        return entityManager.createQuery(sql, Company.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst()
                .orElseGet(() -> {
                    Company company = new Company();
                    company.setName(name);
                    return entityManager.merge(company);
                });
    }
}
