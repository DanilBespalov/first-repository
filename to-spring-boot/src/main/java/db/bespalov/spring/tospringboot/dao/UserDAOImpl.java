package db.bespalov.spring.tospringboot.dao;


import db.bespalov.spring.tospringboot.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;


@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        TypedQuery<User> userTypedQuery = entityManager.createQuery("from User", User.class);
        return userTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public User showUserByID(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public User update(User user, int id) {
        User userToUpdate = showUserByID(id);
        userToUpdate.setId(user.getId());
        userToUpdate.setName(Objects.requireNonNull(user.getName(),"Cant be null"));
        userToUpdate.setSurname(Objects.requireNonNull(user.getSurname(), "Cant be null"));
        return entityManager.merge(userToUpdate);

    }

    @Override
    @Transactional
    public void delete(int id) {
        User userToDelete = showUserByID(id);
        entityManager.remove(userToDelete);
    }
}
