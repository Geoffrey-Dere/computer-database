package com.excilys.cdb.persistence;

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

import com.excilys.cdb.model.Company;

@Repository
public class CompanyDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);

    @PersistenceContext(name = "entityManagerFactory")
    EntityManager em;

    /**
     * @param id id of the company
     * @return the company
     */
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
    public boolean delete(Company obj) {

        LOGGER.debug("delete company {}", obj.toString());
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete<Company> delete = builder.createCriteriaDelete(Company.class);
        Root<Company> r = delete.from(Company.class);
        delete.where(r.get("id").in(obj.getId()));

        return em.createQuery(delete).executeUpdate() == 1;
    }
}
