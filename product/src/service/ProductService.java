package service;

import java.util.List;

import DTO.Product;

public interface ProductService {
// 목록
List<Product>list();
// 조회
Product select(int no);
// 등록
int insert(Product product);
// 수정
int update(Product product);
//삭제
int delete(int no);
    
}
