package ra.model.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.model.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductReporitoryImpl implements IProductReporitory {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            list = session.createQuery("from Product ", Product.class).list();
        } catch ( Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return  list;
    }

    @Override
    public Product saveOrUpdate(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.saveOrUpdate(product);
                transaction.commit();
                return product;
            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException("Failed to save product: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to open session: " + e.getMessage());
        }
    }

    @Override
    public Product findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve product" + " id: " + id +" : " + e.getMessage());
        }
    }

    @Override
    public void delete(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(product);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException("Failed to delete product: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to open session: " + e.getMessage());
        }
    }
}
