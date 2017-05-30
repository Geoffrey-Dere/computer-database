package com.excilys.cdb.persistence.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDAO;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);

    @PersistenceContext(name = "entityManagerFactory")
    EntityManager em;

    /**
     * @param id id of the company
     * @return the company
     */
    @Transactional(propagation = Propagation.MANDATORY, readOnly = true)
    public Optional<Company> find(long id) {

        LOGGER.debug("find company with id = {}", id);
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Company> cq = builder.createQuery(Company.class);
        Root<Company> r = cq.from(Company.class);
        cq.where(r.get("id").in(id));

        return Optional.ofNullable(em.createQuery(cq).getSingleResult());
    }

    /**
     * @return all companies
     */
    @Transactional(propagation = Propagation.MANDATORY, readOnly = true)
    public List<Company> findAll() {

        LOGGER.debug("find all companies");
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Company> cq = builder.createQuery(Company.class);
        Root<Company> r = cq.from(Company.class);

        return em.createQuery(cq).getResultList();
    }

    /**
     * @param obj the company
     * @return true if the company has been deleted
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean delete(Company obj) {

        LOGGER.debug("delete company {}", obj.toString());
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete<Company> delete = builder.createCriteriaDelete(Company.class);
        Root<Company> r = delete.from(Company.class);
        delete.where(r.get("id").in(obj.getId()));

        return em.createQuery(delete).executeUpdate() == 1;
    }

    @Override
    public boolean create(Company obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Company obj) {
        throw new UnsupportedOperationException();
    }

}
