package String_Regex_Excercise;

import java.util.MissingResourceException;
import java.util.Scanner;

public class ProductImp {
    Product[] arrProduct = new Product[100];
    int indexProduct = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductImp productImp = new ProductImp();

        do {
            System.out.println("***********Menu************");
            System.out.println("1. Nhập thông tin n sản phẩm (n nhập từ bàn phím)");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Tính lợi nhuận các sản phẩm");
            System.out.println("4. Sắp xếp các sản phẩm theo lợi nhuận giảm dần");
            System.out.println("5. Thống kê các sản phẩm theo giá");
            System.out.println("6. Tìm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Nhập sản phẩm");
            System.out.println("8. Bán sản phẩm");
            System.out.println("9. Cập nhật trạng thái sản phẩm");
            System.out.println("10. Thoát");
            System.out.print("Lựa chọn của bạn là:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    productImp.inputProduct(scanner);
                    break;
                case 2:
                    productImp.displayProduct();
                    break;
                case 3:
                    productImp.displayProfit();
                    break;
                case 4:
                    productImp.sortByProfit();
                    break;
                case 5:
                    productImp.sortByPrice(scanner);
                    break;
                case 6:
                    productImp.searchByProductName(scanner);
                    break;
                case 7:
                    productImp.updateQuantity(scanner);
                    break;
                case 8:
                    productImp.sellProduct(scanner);
                    break;
                case 9:
                    productImp.updateStatus(scanner);
                    break;
                case 10:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-10");
            }
        }while(true);


    }
    public void inputProduct(Scanner scanner){
        System.out.println("Hãy nhập số sản phẩm muốn thêm:");
        int totalQuantity = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i <totalQuantity ; i++) {
            arrProduct[indexProduct] = new Product();
            arrProduct[indexProduct].inputData(scanner, arrProduct, indexProduct);
            indexProduct++;
        }


    }
    public void displayProduct(){
        System.out.println("Danh sách sản phẩm là:");
        for (int i = 0; i < indexProduct; i++) {
            arrProduct[i].displayData();

        }

    }
    public void displayProfit(){
        System.out.println("Lợi nhuận của các sản phẩm là:");
        for (int i = 0; i < indexProduct; i++) {
            System.out.printf("%s - %s: %f\n", arrProduct[i].getProductId(), arrProduct[i].getProductName(), arrProduct[i].calProfit());

        }
    }

    public void sortByProfit(){
        System.out.println("Danh sách sản phẩm sắp xếp theo thứ tự lợi nhuận giảm dần là:");
        for (int i = 0; i < indexProduct-1; i++) {
            for (int j = i+1; j < indexProduct; j++) {
                if(arrProduct[i].getProfit() < arrProduct[j].getProfit()){
                    Product[] temp = new Product[1];
                    temp[0] = arrProduct[i];
                    arrProduct[i] = arrProduct[j];
                    arrProduct[j] = temp[0];
                }

            }

        }
        for (int i = 0; i < indexProduct; i++) {
            arrProduct[i].displayData();

        }
    }

    public void sortByPrice(Scanner scanner){
        System.out.println("Hãy nhập mức giá from:");
        float fromPrice = Float.parseFloat(scanner.nextLine());
        System.out.println("Hãy nhập mức giá to:");
        float toPrice = Float.parseFloat(scanner.nextLine());
        int total = 0;
        System.out.println("Các sản phẩm thỏa mãn là:");
        for (int i = 0; i < indexProduct; i++) {
            if(arrProduct[i].getExportPrice() >= fromPrice && arrProduct[i].getExportPrice() <= toPrice){
                arrProduct[i].displayData();
                total += 1;
            }

        }
        System.out.printf("Số lượng sản phẩm có mức giá từ %f - %f là: %d", fromPrice, toPrice, total);

    }
    public void searchByProductName(Scanner scanner){
        boolean isExist = true;
do {
    System.out.println("Hãy nhập tên sản phẩm muốn tìm kiếm:");
    String inputName = scanner.nextLine();
    for (int i = 0; i < indexProduct; i++) {
        if(arrProduct[i].getProductName().equals(inputName)){
            isExist = false;
            System.out.println("Sản phẩm cần tìm là:");
            arrProduct[i].displayData();
        }

    }
    if(isExist){
        System.err.println("Tên sản phẩm đã nhập không tồn tại, vui lòng nhập lại!");
    }
}while(isExist);

    }
    public void updateQuantity(Scanner scanner){
        boolean isExist = true;
        do {
            System.out.println("Hãy nhập mã sản phẩm muốn update số lượng:");
            String inputId = scanner.nextLine();

            for (int i = 0; i < indexProduct; i++) {
                if(inputId.equals(arrProduct[i].getProductId())){
                    isExist = false;
                    System.out.println("Hãy nhập số lượng muốn thêm vào:");
                    int addQuantity = Integer.parseInt(scanner.nextLine());
                    arrProduct[i].setQuantity(arrProduct[i].getQuantity() + addQuantity);
                    break;
                }
            }
            if(isExist){
                System.err.println("ID đã nhập không tồn tại, vui lòng nhập lại.");
            }

        }while(isExist);
    }
    public void sellProduct(Scanner scanner){
        boolean isExist = true;
        do {
            System.out.println("Hãy nhập tên sản phẩm muốn bán: ");
            String inputProductName = scanner.nextLine();
            for (int i = 0; i < indexProduct; i++) {
                if(arrProduct[i].getProductName().equals(inputProductName)){
                    isExist = false;
                    System.out.println("Hãy nhập số lượng muốn bán");
                    int sellQuantity = Integer.parseInt(scanner.nextLine());
                    arrProduct[i].setQuantity(arrProduct[i].getQuantity() - sellQuantity);
                }

            }
            if(isExist){
                System.err.println("Sản phẩm đã nhập không tồn tại, vui lòng nhập lại.");
            }
        }while(isExist);
    }
    public void updateStatus(Scanner scanner){
        boolean isExist = true;
        do {
            System.out.println("Hãy nhập mã sản phẩm muốn update trạng thái:");
            String inputId = scanner.nextLine();
            for (int i = 0; i < indexProduct; i++) {
                if(arrProduct[i].getProductId().equals(inputId)){
                    isExist = false;
                    if(arrProduct[i].isStatus()){
                        arrProduct[i].setStatus(false);
                    } else{
                        arrProduct[i].setStatus(true);
                    }
                }
            }
            if(isExist){
                System.err.println("Mã sản phẩm đã nhập không tồn tại, vui lòng nhập lại");
            }

        }while(isExist);

    }


}
