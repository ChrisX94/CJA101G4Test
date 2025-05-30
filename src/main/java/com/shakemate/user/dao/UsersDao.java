package com.shakemate.user.dao;


import java.util.List;
import com.shakemate.user.model.Users;

public interface UsersDao {

    public Users findByEmail(String email);
    public void updateByUser(Users user);
    public void updateByAdm(Users user);
    public List<Users> getAllUsers();
    public Users getUserById(Integer id);
    public void addUser(Users user);

}
