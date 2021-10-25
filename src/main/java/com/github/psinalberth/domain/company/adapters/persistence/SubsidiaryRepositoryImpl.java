package com.github.psinalberth.domain.company.adapters.persistence;

import com.github.psinalberth.domain.company.application.domain.model.Subsidiary;
import com.github.psinalberth.domain.shared.application.port.RetrieveSubsidiaryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class SubsidiaryRepositoryImpl implements RetrieveSubsidiaryPort {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Subsidiary retrieve(String name) {

        String sql = "select s from Subsidiary s where s.name = :name";
        return entityManager.createQuery(sql, Subsidiary.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst()
                .orElseGet(() -> {
                    Subsidiary subsidiary = new Subsidiary();
                    subsidiary.setName(name);
                    return entityManager.merge(subsidiary);
                });
    }
}
