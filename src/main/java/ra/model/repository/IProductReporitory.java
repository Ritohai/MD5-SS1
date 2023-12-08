package ra.model.repository;

import ra.model.entity.Product;

import java.util.List;

public interface IProductReporitory {
    List<Product> findAll();

    Product saveOrUpdate(Product product);

    Product findById(Long id);

    void delete(Product product);
}
