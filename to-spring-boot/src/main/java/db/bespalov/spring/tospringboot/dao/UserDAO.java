package db.bespalov.spring.tospringboot.dao;


import db.bespalov.spring.tospringboot.model.User;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    User showUserByID(int id);
    void add(User user);

    User update(User user, int id);

    void delete(int id);
}
