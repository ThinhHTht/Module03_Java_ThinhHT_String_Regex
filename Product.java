package String_Regex_Excercise;

import javax.sound.midi.MidiFileFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Product {
    private String  productId;
    private String productName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private String descriptions;
    private boolean status;

    public Product() {
    };

    public Product(String productId, String productName, float importPrice, float exportPrice, float profit, int quantity, String descriptions, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.descriptions = descriptions;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // other method
    public void inputData(Scanner scanner, Product[] arrProduct, int indexProduct){
         this.productId = inputProductId(scanner, arrProduct, indexProduct);
         this.productName = inputProductName(scanner, arrProduct, indexProduct);
         this.importPrice = inputImportPrice(scanner);
         this.exportPrice = inputExportPrice(scanner);
         this.quantity = inputQuantity(scanner);
         this.descriptions = inputDescription(scanner);
         this.status = inputStatus(scanner);

    }
    public String inputProductId(Scanner scanner, Product[] arrProduct, int indexProduct){
        System.out.println("Hãy nhập id của sản phẩm:");
        do {
            String productId = scanner.nextLine();
           if(Pattern.matches("P...", productId)){
               boolean isExist = false;
               for (int i = 0; i < indexProduct; i++) {
                    if(arrProduct[i].getProductId().equals(productId)){
                        isExist = true;
                        break;
                    }
               }
               if(isExist){
                   System.err.println("Id vừa nhaapj đã tồn tại, vui lòng nhập Id khác");
               }else {
                   return  productId;
               }
           } else {
               System.err.println("Id sản phẩm phải bắt đầu từ chữ P và có 4 ký tự");
           }

        } while(true);
    }

    public String inputProductName(Scanner scanner, Product[] arrProduct, int indexProduct){
        System.out.println("Hãy nhập tên sản phẩm:");
        do {
            String productName = scanner.nextLine();
            if(productName.length()>= 6 && productName.length()<= 50){
                boolean isExist = false;
                for (int i = 0; i < indexProduct; i++) {
                   if(arrProduct[i].getProductName().equals(productName)){
                       isExist = true;
                       break;
                   }
                }
                if(isExist){
                    System.err.println("Tên sản phẩm này đã tồn tại, vui lòng nhập lại tên sản phẩm");
                } else {
                    return productName;
                }
            } else {
                System.err.println("Vui lòng nhập tên sản phẩm trong khoảng từ 6-50 ký tự");
            }
        }while(true);
    }

    public float inputImportPrice(Scanner scanner){
        System.out.println("Hãy nhập giá nhập của sản phẩm:");
        do {
            float importPrice = Float.parseFloat(scanner.nextLine());
            if(importPrice <=0){
                System.err.println("Giá sản phẩm phải > 0");
            }else {
                return importPrice;
            }

        }while(true);
    }

    public float inputExportPrice(Scanner scanner){
        System.out.println("Hãy nhập giá xuất của sản phẩm:");
        do {
            float exportPrice = Float.parseFloat(scanner.nextLine());
            if(exportPrice >= 120%this.importPrice){
                return exportPrice;
            } else {
                System.err.println("Giá xuất phải >= 20% lần giá nhập");
            }
        }while(true);
    }
    public int inputQuantity(Scanner scanner){
        System.out.println("Hãy nhập số lượng sản phẩm:");
        do {
            int quantity = Integer.parseInt(scanner.nextLine());
            if(quantity<= 0){
                System.err.println("Số lượng sản phẩm phải > 0");
            } else {
                return quantity;
            }
        }while(true);
    }

    public String inputDescription(Scanner scanner){
        System.out.println("Hãy nhập mô tả sản phẩm");
        return scanner.nextLine();
    }

    public Boolean inputStatus(Scanner scanner){
        System.out.println("Hãy nhập trạng thái của sản phẩm:");
        do {
            String statusInput = scanner.nextLine();
            if(Pattern.matches("(true|false)", statusInput)){
                return Boolean.parseBoolean(statusInput);
            } else {
                System.err.println("Vui lòng nhập true hoặc false");
            }
        }while(true);
    }

    public void displayData(){
        System.out.printf("\nProductId:%s - ProductName: %s - ImportPrice: %.2f - ExportPrice: %.2f - Profit: %.2f\n", this.productId, this.productName, this.importPrice, this.exportPrice, calProfit());
        System.out.printf("Quantity: %d - Description: %s - Stautus: %s\n", this.quantity, this.descriptions, this.status? "Đang bán" : "Không bán");
    }

    public float calProfit(){
       this.profit = this.exportPrice - this.importPrice;
       return profit;
    }
}
