package iuh.fit.se.bai1_jsp.dao;

import iuh.fit.se.bai1_jsp.model.User;

import java.util.List;

public interface UserDAO {
    public User save(User user);
    public List<User> findAllUsers();
    public User findById(int id);
    public boolean update(User user);
    public boolean delete(int id);
}
