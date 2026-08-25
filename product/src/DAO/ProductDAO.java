package DAO;

import java.util.ArrayList;
import java.util.List;

import DTO.Product;

public class ProductDAO extends JDBConnection {
    
     public List<Product> list() {

     List<Product> productList = new ArrayList<>();

        String sql = " SELECT * "
                   + " FROM product ";
        try {
            stmt = con.createStatement(); 

            rs = stmt.executeQuery(sql);

             while ( rs.next() ) {
            
                Product product = new Product();

                product.setNo( rs.getInt("no") );
                product.setTitle( rs.getString("title") );
                product.setWriter( rs.getString("writer") );
                product.setContent( rs.getString("content") );
                product.setCreatedAt( rs.getTimestamp("created_at") );
                product.setUpdatedAt( rs.getTimestamp("updated_at") );

                productList.add(product);
            }
         }  catch (Exception e) {
            System.err.println("게시글 목록 조회 시, 예외 발생");
            e.printStackTrace();
         }

         return productList;
}
        public Product select(int no) {

            Product product = new Product();

            String sql = " SELECT * "
                       + " FROM product "
                       + " WHERE no = ? ";  

         try {
            psmt = con.prepareStatement(sql);

            psmt.setInt(1, no);

            if( rs.next() ) {
                product.setNo( rs.getInt("no") );
                product.setWriter( rs.getString("writer") );
                product.setContent( rs.getString("content") );
                product.setCreatedAt( rs.getTimestamp("created_at") );
                product.setUpdatedAt( rs.getTimestamp("updated_at") );
            }

         } catch (Exception e) {
              System.err.println("게시글 조회 시, 예외 발생");
              e.printStackTrace();
        }
            return product;
    }   
    /**
     * 
     * @param product
     * @return
     */
        public int update(Product product) {
        int result = 0;   

          String sql = " UPDATE product "
                   + "    SET title = ? "
                   + "      , writer = ? "
                   + "      , content = ? "
                   + "      , updated_at = sysdate "
                   + " WHERE no = ? ";
        try {
            psmt = con.prepareStatement(sql);           
            psmt.setString(1, product.getTitle());      
            psmt.setString(2, product.getWriter());    
            psmt.setString(3, product.getContent());     
            psmt.setInt(4, product.getNo());         
            result = psmt.executeUpdate();             

        }  catch (Exception e) {
            System.err.println("게시글 수정 시, 예외 발생");
            e.printStackTrace();
        }
        return result;  
}
    /**
     * 
     * @param no
     * @return
     */
    public int delete(int no) {
           int result = 0;        
           String sql = " DELETE FROM product "
                      + " WHERE no = ? ";
        try {
            psmt = con.prepareStatement(sql);
            psmt.setInt(1, no);
            result = psmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("게시글 삭제 시, 예외 발생");
            e.printStackTrace();
        }
        return result;
    }

}
