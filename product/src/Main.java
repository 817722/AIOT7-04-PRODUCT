import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import DTO.Product;
import service.ProductService;
import service.ProductServicelmpl;


public class Main {

    static Scanner sc = new Scanner(System.in);
    static List<Product> productList = null;
    static ProductService productService = new ProductServicelmpl();

    public static void menu() {
        System.out.println("::::::::: 메인 화면 :::::::::");
        System.out.println("1. 상품 목록");
        System.out.println("2. 상품 조회");
        System.out.println("3. 상품 등록");
        System.out.println("4. 상품 수정");
        System.out.println("5. 상품 삭제");
        System.out.println("0. 프로그램 종료");
        System.out.print("::::::::: 번호 입력 : ");
    }

    /**
     * 상품 목록
     */
    private static void list() {
        System.out.println();
        System.err.println("::::::::: 상품 목록 :::::::::");
        productList = productService.list();
        printAll();
    }


    /**
     * 상품 목록 전체 출력
     */
    private static void printAll() {
        if(productList == null || productList.isEmpty()) {
            System.err.println("조회된 상품이 없습니다");
            return;
        }
        // 상품 목록 출력
        for (Product product : productList) {
            print(product);
        }
    }

    /**
     * 상품 목록 전체 조회
     * @param product
     */
    private static void print(Product product) {
        if (product == null) {
            System.err.println("상품을 조회할 수 없습니다.");
            return;
        }

        int no = product.getNo();
        String title = product.getTitle();
        String writer = product.getWriter();
        String content = product.getContent();
        Date createdAt = product.getCreatedAt();
        Date updatedAt = product.getUpdatedAt();
        // 날짜 포맷
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String reg = sdf.format(createdAt);
        String upd = sdf.format(updatedAt);

        System.out.println("::::::::::::::::::::::::::::::::::::");
        System.out.println("★ 상품 번호: " + no);
        System.out.println("★ 상품명: " + title);
        System.out.println("★ 등록자명: " + writer);
        System.out.println("------------------------------------");
        System.out.println(" " + content);
        System.out.println();
        System.out.println("★ 등록 일자: " + reg);
        System.out.println("★ 수정 일자: " + upd);
        System.out.println("::::::::::::::::::::::::::::::::::::");
        System.out.println();
    }


    /**
     * 상품 목록 조회
     */
    public static void select() {
        System.out.println("::::::::: 상품 조회 :::::::::");
        System.out.print("상품 번호: ");
        int no = sc.nextInt();
        sc.nextLine();
        // 상품 번호 (no)를 전달하여 상품 정보 데이터 요청
        Product product = productService.select(no);
        print(product);     // 출력
        System.out.println();
    }


    /**
     * 상품 등록
     */
    public static void insert() {
        System.out.println("::::::::: 상품 등록 :::::::::");

        Product product = input();
        int result = productService.insert(product);
        if (result > 0) {
            System.out.println("★ 상품 등록이 완료 되었습니다.");
        } else {
            System.out.println("★ 상품 등록에 실패하였습니다.");
        }
    }


    /**
     * 상품 정보 입력
     * @return
     */
    private static Product input() {
        System.out.print("★ 상품명: ");
        String title = sc.nextLine();
        System.out.print("★ 등록자명: ");
        String writer = sc.nextLine();
        System.out.print("★ 상품 설명: ");
        String content = sc.nextLine();
        
        Product product = new Product(title, writer, content);
        return product;
    }


    /**
     * 상품 등록
     */
    public static void update() {
        System.out.println("::::::::: 상품 등록 :::::::::");
        System.out.println("상품 번호: ");
        int no = sc.nextInt();
        sc.nextLine();

        Product product = input();
        product.setNo(no);

        // 상품 수정 요청
        int result = productService.update(product);
        if (result > 0) {
            System.out.println("★ 상품이 수정 되었습니다.");
        } else {
            System.out.println("★ 상품 수정에 실패하였습니다.");
        }
        System.out.println();
    }

    /**
     * 상품 정보 삭제
     */
    public static void delete() {
        System.out.println();
        System.out.println("::::::::: 상품 삭제 :::::::::");

        System.out.println("상품 번호: ");
        int no = sc.nextInt();
        sc.nextLine();

        int result = productService.delete(no);
        if (result > 0) {
            System.out.println("★ 상품을 삭제하였습니다.");
        } else {
            System.out.println("★ 상품 삭제에 실패하였습니다.");
        }
    }


    
    public static void main(String[] args) throws Exception {
        int menuNo = 0;
        do {
            menu();
            menuNo = sc.nextInt();
            sc.nextLine();
            if(menuNo ==0) break;
            switch (menuNo) {
                case 1: list();
                        break;
                case 2: select();
                        break;
                case 3: insert();
                        break;
                case 4: update();
                        break;
                case 5: delete();
                        break;
                default:
                    break;
            }
        } while (menuNo != 0);

        System.out.println("프로그램을 종료합니다...");
    }
}
