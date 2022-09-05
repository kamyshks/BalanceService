package kamyshks.dao;

import kamyshks.entity.BalanceEntity;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BalanceDao {
    private final SessionFactory sessionFactory;

    @CachePut(value = "balance", key = "#balance.id")
    @Transactional
    public Optional<BalanceEntity> save(final BalanceEntity balance) {
        try (Session session = sessionFactory.openSession()) {
            if (findById(balance.getId()).isPresent()) {
                session.merge(balance);
            } else {
                session.persist(balance);
            }
            return Optional.of(balance);
        }
    }

    @Cacheable(value = "balance", key = "#id")
    public Optional<BalanceEntity> findById(final Integer id) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<BalanceEntity> criteriaQuery = cb.createQuery(BalanceEntity.class);
            Root<BalanceEntity> root = criteriaQuery.from(BalanceEntity.class);
            criteriaQuery.select(root);
            criteriaQuery.where(cb.equal(root.get("id"), id));
            Query query = session.createQuery(criteriaQuery);
            query.setMaxResults(1);
            try {
                return Optional.ofNullable((BalanceEntity) query.getSingleResult());
            } catch (Exception exception) {
                return Optional.empty();
            }
        }
    }

}
