package com.excilys.cdb.persistence;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.User;

@Repository
public class UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);

    @PersistenceContext(name = "entityManagerFactory")
    EntityManager em;

    /**
     * @param obj the user
     * @return true if the user has been added
     */
    public boolean create(User obj) {

        LOGGER.debug("inserting new user {}", obj);
        em.persist(obj);
        em.flush();
        return true;
    }

    /**
     * @param name name of the user
     * @return the user
     */
    public Optional<User> find(String name) {

        LOGGER.debug("find user with name = {}", name);

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = builder.createQuery(User.class);
        Root<User> r = cq.from(User.class);
        cq.where(r.get("name").in(name));

        try {
            return Optional.of(em.createQuery(cq).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * @param user user
     * @return true if the user exist in the database
     */
    public boolean exist(User user) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
        Root<Computer> r = cq.from(Computer.class);
        cq.select(builder.count(r)).where(builder.equal(r.get("name"), user.getName()),
                builder.equal(r.get("password"), user.getPassword()));
        return em.createQuery(cq).getSingleResult().intValue() == 1;
    }
}
