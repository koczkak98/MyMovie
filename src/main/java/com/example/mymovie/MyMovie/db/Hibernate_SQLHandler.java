package com.example.mymovie.MyMovie.db;

import com.example.mymovie.MyMovie.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Hibernate_SQLHandler {

    private SessionFactory sessionFactory;

    public void open ()
    {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory =
                new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public User getUserById(int userId) {
        User user = new User();

        Session session = sessionFactory.openSession();
        session.beginTransaction();


        user = session.get(User.class, userId);


        session.getTransaction().commit(); // needed for Create, Update, Delete transactions
        session.close();


        return user;
    }

    public User getUserByIdAndName (int userId, String name)
    {
        User user = null;

        /** Open Session */
        Session session = sessionFactory.openSession();
        session.beginTransaction();


        /** Prepare Criteria */
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        /** Prepare HQL Statement */
        Root<User> fromTable = criteria.from(User.class);
        Predicate condition1 = builder.equal( fromTable.get("userID"), userId );
        Predicate condition2 = builder.like( fromTable.get("userName"), name );
        CriteriaQuery<User> hqlStatement = criteria.select(fromTable).where(condition2);
        TypedQuery<User> query = session.createQuery(hqlStatement);


        /** Execute HQL Statement */
        try {
            user = query.getSingleResult();
        }
        catch (NoResultException e) {
            user = new User(0);
        }
        catch (NonUniqueResultException e) {
            user = new User(0);
        }


        /** Close Session */
        session.getTransaction().commit(); // needed for Create, Update, Delete transactions
        session.close();

        return user;
    }

    public String checkUserByName (int userId)
    {
        User user = getUserById(userId);

        return user.getUserName();
    }

    public void close ()
    {
        sessionFactory.close();
    }

}
