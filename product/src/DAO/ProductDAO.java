package DAO;

import java.sql.SQLException;
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

            rs = psmt.executeQuery();

            if( rs.next() ) {
                product.setNo( rs.getInt("no") );
                product.setTitle(rs.getString("title"));
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
	 * 데이터 등록
	 * @param product
	 * @return
	 */

	public int insert(Product product) {
		int result = 0;			// 결과 : 적용된 데이터 개수
		
		String sql = " INSERT INTO product (title, writer, content) "
				   + " VALUES( ?, ?, ? ) ";
		
		try {
			psmt = con.prepareStatement(sql);			// 쿼리 실행 객체 생성
			psmt.setString( 1, product.getTitle() );		// 1번 ? 에 title(제목) 매핑
			psmt.setString( 2, product.getWriter() );		// 2번 ? 에 writer(작성자) 매핑
			psmt.setString( 3, product.getContent() );	// 3번 ? 에 content(내용) 매핑
			result = psmt.executeUpdate();				// SQL 실행 요청
			// * executeUpdate() 
			// SQL(INSERT, UPDATE, DELETE) 실행 시 적용된 데이터 개수를 int 타입으로 받아온다.
			// ex) 게시글 1개 적용 성공 시, result : 1 
			//				    실패 시, result : 0
		} catch (Exception e) {
			System.err.println("게시글 등록 시, 예외 발생");
			e.printStackTrace();
		}
		return result;
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
