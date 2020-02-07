package mate.academy.cinema.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import mate.academy.cinema.dao.ShoppingCartDao;
import mate.academy.cinema.exception.DataProcessingException;
import mate.academy.cinema.lib.Dao;
import mate.academy.cinema.model.ShoppingCart;
import mate.academy.cinema.model.Ticket;
import mate.academy.cinema.model.User;
import mate.academy.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(shoppingCart);
            transaction.commit();
            shoppingCart.setId(itemId);
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Shopping Cart entity", e);
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ShoppingCart> cq = cb.createQuery(ShoppingCart.class);
            Root<ShoppingCart> root = cq.from(ShoppingCart.class);
            cq.select(root).where(cb.equal(root.get("user"), user));
            ShoppingCart shoppingCart = session.createQuery(cq).getSingleResult();

            List<Ticket> tickets = session
                    .createQuery("FROM Ticket WHERE user = :user", Ticket.class)
                    .setParameter("user", user)
                    .getResultList();

            shoppingCart.setTickets(tickets);
            return shoppingCart;
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Shopping Cart by user", e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update Shopping Cart", e);
        }
    }
}
