package bigproject.demo.web;

import bigproject.demo.model.entities.ProductEntity;
import bigproject.demo.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestProductController {
    private final ProductService productService;

    public RestProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product/all")
    private List<ProductEntity> allProducts(){

        return productService.getAllProducts();
    }



}
