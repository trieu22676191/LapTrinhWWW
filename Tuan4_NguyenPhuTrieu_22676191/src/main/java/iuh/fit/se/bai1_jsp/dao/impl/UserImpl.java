package iuh.fit.se.bai1_jsp.dao.impl;

import iuh.fit.se.bai1_jsp.dao.UserDAO;
import iuh.fit.se.bai1_jsp.model.User;
import iuh.fit.se.bai1_jsp.until.EntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class UserImpl implements UserDAO {
    private EntityManager em;

    public UserImpl() {
        em = EntityManagerFactoryUtil.getEntityManager();
    }

    @Override
    public User save(User user) {
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.persist(user);
            tr.commit();
        } catch (Exception e){
            e.printStackTrace();
            if(tr !=null && tr.isActive()){
                tr.rollback();
            }
        }
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        EntityTransaction tr = em.getTransaction();
        try{
            return em.createQuery("select u from User u", User.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
