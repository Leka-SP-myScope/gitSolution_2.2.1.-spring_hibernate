package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

    @Override
    public User getUserWithCar(String model, int series) {
       TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
                "Select u from User u " +
                "where u.car.model = :model and u.car.series = :series", User.class)
                .setParameter("model", model)
                .setParameter("series", series);



//        List<User> users = query.getResultList();
//        for (User user : users) {
//            System.out.println("Id = " + user.getId());
//            System.out.println("First Name = " + user.getFirstName());
//            System.out.println("Last Name = " + user.getLastName());
//            System.out.println("Email = " + user.getEmail());
//            System.out.println("Car = " + user.getCar());
//            System.out.println();
//        }
//        for (User value : query.getResultList()) {
//            System.out.println(value.getCar().getModel());
//            System.out.println(value.getCar().getSeries());
//        }


        //User user = query.getSingleResult();
        //System.out.println(query.getSingleResult());

        return query.getSingleResult();
    }
}
