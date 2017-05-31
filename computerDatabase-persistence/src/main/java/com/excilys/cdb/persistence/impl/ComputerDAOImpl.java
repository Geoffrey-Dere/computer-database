package com.excilys.cdb.persistence.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Pager;
import com.excilys.cdb.persistence.ComputerDAO;
import com.excilys.cdb.persistence.DAO;

@Repository
public class ComputerDAOImpl implements ComputerDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAOImpl.class);

    @PersistenceContext(name = "entityManagerFactory")
    EntityManager em;

    @Transactional(propagation = Propagation.MANDATORY)
    public boolean create(Computer obj) {

        LOGGER.debug("inserting new computer {}", obj);
        em.persist(obj);
        em.flush();
        return true;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public boolean delete(Computer obj) {

        LOGGER.debug("delete computer {}", obj.toString());
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete<Computer> delete = builder.createCriteriaDelete(Computer.class);
        Root<Computer> r = delete.from(Computer.class);
        delete.where(r.get("id").in(obj.getId()));

        return em.createQuery(delete).executeUpdate() == 1;
    }

    /**
     * @param id id of the computer
     * @return true if success
     */
    public boolean deleteById(long id) {

        Computer computer = new Computer();
        computer.setId(id);
        return delete(computer);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean update(Computer obj) {

        LOGGER.debug("update computer {}", obj.toString());

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate<Computer> update = builder.createCriteriaUpdate(Computer.class);
        Root<Computer> r = update.from(Computer.class);
        update.set("name", obj.getName());
        update.set("introduced", obj.getIntroduced().isPresent() ? obj.getIntroduced().get() : null);
        update.set("discontinued", obj.getDiscontinued().isPresent() ? obj.getDiscontinued().get() : null);
        update.where(r.get("id").in(obj.getId()));

        return this.em.createQuery(update).executeUpdate() == 1;

    }

    @Transactional(propagation = Propagation.MANDATORY, readOnly = true)
    public Optional<Computer> find(long id) {

        LOGGER.debug("find computer with id = {}", id);
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Computer> cq = builder.createQuery(Computer.class);
        Root<Computer> r = cq.from(Computer.class);
        r.fetch("company", JoinType.LEFT);
        cq.where(r.get("id").in(id));

        return Optional.ofNullable(em.createQuery(cq).getSingleResult());
    }

    /**
     * @param companyId company id
     * @return list of computer
     */
    @Transactional(propagation = Propagation.MANDATORY, readOnly = true)
    public List<Long> getIdByCompany(long companyId) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
        Root<Computer> r = cq.from(Computer.class);
        cq.select(r.get("id")).where(r.get("company_id").in(companyId));

        return em.createQuery(cq).getResultList();

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY, readOnly = true)
    public void findAll(Pager<Computer> page) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Computer> cq = builder.createQuery(Computer.class);
        Root<Computer> r = cq.from(Computer.class);
        r.fetch("company", JoinType.LEFT);
        cq.select(r);

        String order = page.getOrder();
        String column = page.getColumn();
        int limit = page.getLimit();
        int offset = page.getOffset();

        if (order.equals("DESC")) {
            cq.orderBy(builder.desc(r.get(column)));
        } else {
            cq.orderBy(builder.asc(r.get(column)));
        }

        List<Computer> res = em.createQuery(cq).setFirstResult((int) offset).setMaxResults((int) limit).getResultList();
        page.setListEntity(res);

    }

    /**
     * @return count of computer
     */
    @Transactional(propagation = Propagation.MANDATORY, readOnly = true)
    public int count() {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
        Root<Computer> r = cq.from(Computer.class);
        cq.select(builder.count(r));
        return em.createQuery(cq).getSingleResult().intValue();
    }

    /**
     * @param name name
     * @return count of computer
     */
    @Transactional(propagation = Propagation.MANDATORY, readOnly = true)
    public int count(String name) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
        Root<Computer> r = cq.from(Computer.class);
        cq.select(builder.count(r)).where(builder.like(r.get("name"), name + "%"));
        return em.createQuery(cq).getSingleResult().intValue();
    }

    /**
     * @param companyId companyiD
     * @return bool success
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean deleteByCompany(long companyId) {

        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaDelete<Computer> delete = builder.createCriteriaDelete(Computer.class);
        Root<Computer> r = delete.from(Computer.class);
        delete.where(r.get("company_id").in(companyId));

        return em.createQuery(delete).executeUpdate() >= 1;

    }

    /**
     * @param listId list
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void remove(List<Integer> listId) {

        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaDelete<Computer> delete = builder.createCriteriaDelete(Computer.class);
        Root<Computer> r = delete.from(Computer.class);
        delete.where(r.get("id").in(listId));

        em.createQuery(delete).executeUpdate();
    }
}
