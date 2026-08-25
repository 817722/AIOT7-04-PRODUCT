package service;

import java.util.List;

import DTO.Product;

public class ProductServicelmpl implements ProductService {
   
    private ProductDAO productDAO = new ProductDAO();

    @Override
    public List<Product> list() {
        List<Product> productList = productDAO.list();
        return productList;
    }

    @Override
    public Product select(int no) {
        Product product = productDAO.select(no);
        return produt;
    }

    @Override
    public int insert(Product product) {
        int result = productDAO.insert(product);
        if (result > 0 ) System.out.println(" 데이터 등록 성공!")
        else System.err.println("데이터 등록 실패!");
        return result;
        
    }
    @Override
    public int update(Product product) {
        int result = productDAO.update(product);
        if (result > 0 ) System.out.println(" 데이터  수정 성공!")
        else System.err.println("데이터 수정 실패!");
        return result;
        
    }
    @Override
    public int delete(int no) {
        int result = productDAO.delete(no);
        if (result > 0 ) System.out.println(" 데이터 삭제 성공!")
        else System.err.println("데이터 삭제 실패!");
        return result;
        
    }

}
