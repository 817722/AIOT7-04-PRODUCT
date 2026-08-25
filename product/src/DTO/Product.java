package DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Product {

    private int no;
    private String title;
    private String writer;
    private String content;
    private Date createdAt;
    private Date updateAT;
    
    public Product(){
        this("(제목없음","(작성자없음","");

    }
    public Product(String title, String writer, String content){
        this.title = title;
        this.writer = writer;
        this.content = content;
    }
}
