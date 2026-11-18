package productsales;

import java.util.Scanner;

public class ProductSales {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // gets what the user types

        String[] products = {"Cozy Nights Monarch mattress", "Restonic Barbados mattress", "Sealy Phuket mattress"};
        int[][] sales = new int[3][3]; // makes a table for the sales

        int i = 0;
        int j = 0;

        for (i = 0; i < 3; i++) { // loops through the products
            for (j = 0; j < 3; j++) { // loops through the months
                System.out.print("Enter number of sales for month " + (j + 1) + " for " + products[i] + " : ");
                sales[i][j] = input.nextInt(); // saves the sales in the table
            }
            System.out.println(); // goes to the next line
        }

        Product product = new Product(products, sales);
        PrintProduct printer = new PrintProduct(product);

        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println();
        System.out.println("************************************");
        printer.PrintTotalProductsSold(); // prints the total sold
        printer.PrintAverageProductSold();
        printer.PrintMostPopularProduct(); // prints the most popular one
    }
}