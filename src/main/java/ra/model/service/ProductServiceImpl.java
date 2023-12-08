package ra.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.Product;
import ra.model.repository.IProductReporitory;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductReporitory productReporitory;

    @Override
    public List<Product> findAll() {
        return productReporitory.findAll();
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return productReporitory.saveOrUpdate(product);
    }

    @Override
    public Product findById(Long id) {
        return productReporitory.findById(id);
    }

    @Override
    public void delete(Product product) {
        productReporitory.delete(product);
    }
}
