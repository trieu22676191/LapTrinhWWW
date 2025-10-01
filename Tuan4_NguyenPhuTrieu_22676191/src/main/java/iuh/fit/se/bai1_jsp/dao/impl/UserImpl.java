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


    public User findById(int id) {
        try {
            return em.find(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean update(User nv) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(nv);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) tr.rollback();
        }
        return false;
    }

    public boolean delete(int maNV) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            User nv = em.find(User.class, maNV);
            if (nv != null) {
                em.remove(nv);
                tr.commit();
                return true;
            }
            tr.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) tr.rollback();
        }
        return false;
    }
}
