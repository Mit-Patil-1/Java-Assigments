package ejb;

import entity.CurrencyRate;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Stateless
public class CurrencyConverterBean {

    @PersistenceContext(unitName = "ass4p02PU")
    private EntityManager em;

    public double convert(String from, String to, double amount) {
        CurrencyRate rate = em.createQuery(
                "SELECT r FROM CurrencyRate r WHERE r.fromCode = :from AND r.toCode = :to",
                CurrencyRate.class)
                .setParameter("from", from.toUpperCase())
                .setParameter("to", to.toUpperCase())
                .getSingleResult();
        return amount * rate.getFactor();
    }

    public boolean hasRate(String from, String to) {
        try {
            em.createQuery(
                    "SELECT r FROM CurrencyRate r WHERE r.fromCode = :from AND r.toCode = :to",
                    CurrencyRate.class)
                    .setParameter("from", from.toUpperCase())
                    .setParameter("to", to.toUpperCase())
                    .getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }
}

