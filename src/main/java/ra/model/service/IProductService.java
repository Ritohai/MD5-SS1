package ra.model.service;

import ra.model.entity.Product;

import java.util.List;


public interface IProductService {
    List<Product> findAll();

    Product saveOrUpdate(Product product);

    Product findById(Long id);

    void delete(Product product);

}
