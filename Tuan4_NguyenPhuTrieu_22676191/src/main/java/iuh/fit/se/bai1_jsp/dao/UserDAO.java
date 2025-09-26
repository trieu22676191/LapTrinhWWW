package iuh.fit.se.bai1_jsp.dao;

import iuh.fit.se.bai1_jsp.model.User;

import java.util.List;

public interface UserDAO {
    public User save(User user);
    public List<User> findAllUsers();
}
