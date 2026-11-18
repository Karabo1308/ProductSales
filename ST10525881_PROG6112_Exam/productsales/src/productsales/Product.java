package productsales;

public class Product implements IProduct {

    String[] products;
    int[][] sales;

    public Product(String[] products, int[][] sales) {
        this.products = products; // keeps the products
        this.sales = sales;
    }

    @Override
    public int GetTotalProductsSold() {
        int totalSales = 0;
        for (int i = 0; i < 3; i++) { // loops through products
            for (int k = 0; k < 3; k++) { // loops through months
                totalSales = totalSales + sales[i][k]; // adds the sales
            }
        }
        return totalSales;
    }

    @Override
    public double GetAverageProductSold() {
        int total = GetTotalProductsSold(); // gets the total first
        double average = total / 9;
        return average;
    }

    @Override
    public String GetMostPopularProduct() {
        int highestTotal = 0;
        String highestProduct = "";

        for (int i = 0; i < 3; i++) { // loops through the products
            int total = 0;
            for (int k = 0; k < 3; k++) {
                total = total + sales[i][k]; // adds up one product
            }
            if (total > highestTotal) { // checks if this one is bigger
                highestTotal = total;
                highestProduct = products[i]; // keeps this product name
            }
        }
        return highestProduct;
    }
}