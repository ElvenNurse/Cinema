package mate.academy.cinema.dao.impl;

import mate.academy.cinema.dao.TicketDao;
import mate.academy.cinema.exception.DataProcessingException;
import mate.academy.cinema.lib.Dao;
import mate.academy.cinema.model.Ticket;
import mate.academy.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(ticket);
            transaction.commit();
            ticket.setId(itemId);
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Ticket entity", e);
        }
    }
}
