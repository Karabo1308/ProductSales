package productsalesgui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ProductSalesGUI extends JFrame {

    public static void main(String[] args) {
        new ProductSalesGUI();
    }

    JTextField txtCustomer;
    JComboBox<String> cmbProduct;
    JTextField txtPrice;
    JButton btnProcess;

    public ProductSalesGUI() {
        setTitle("Product App");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // puts the window in the middle

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu toolsMenu = new JMenu("Tools");

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0)); // closes the app
        fileMenu.add(exitItem);

        JMenuItem processItem = new JMenuItem("Process");
        processItem.addActionListener(e -> btnProcess.doClick()); // clicks the process button for us
        toolsMenu.add(processItem);

        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(e -> {
            txtCustomer.setText(""); // clears the customer box
            txtPrice.setText("");
            cmbProduct.setSelectedIndex(0); // puts the product back to the first one
        });
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // makes the table form

        panel.add(new JLabel("Customer Name:"));
        txtCustomer = new JTextField();
        panel.add(txtCustomer);

        panel.add(new JLabel("Select Product:"));
        String[] products = {"Cozy Night Monarch", "Restonic Barbados", "Sealy Phuket"};
        cmbProduct = new JComboBox<>(products); // shows the products in the list
        panel.add(cmbProduct);

        panel.add(new JLabel("Enter Price:"));
        txtPrice = new JTextField();
        panel.add(txtPrice);

        btnProcess = new JButton("PROCESS");
        panel.add(btnProcess);
        panel.add(new JLabel(""));

        add(panel, BorderLayout.CENTER);

        btnProcess.addActionListener(a -> {
            String customer = txtCustomer.getText(); // gets what the user typed
            String product = (String) cmbProduct.getSelectedItem();
            double price = Double.parseDouble(txtPrice.getText()); // changes the price to a number

            ProductCalculations calc = new ProductCalculations(price);
            double vat = calc.GetVat(); // gets the vat
            double commission = calc.GetCommission();
            double total = calc.GetTotal();

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt")); // opens the file to write
                writer.write("DATA LOG");
                writer.newLine(); // goes to the next line
                writer.write("************************************************");
                writer.newLine();
                writer.write("Customer Name: " + customer);
                writer.newLine();
                writer.write("Product: " + product);
                writer.newLine();
                writer.write("VAT: R " + vat);
                writer.newLine();
                writer.write("COMMISSION: R " + commission);
                writer.newLine();
                writer.write("TOTAL: R " + total);
                writer.close(); // closes the file
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving file");
            }

            JFrame resultFrame = new JFrame("Sale Details"); // makes the second window
            resultFrame.setSize(350, 300);
            resultFrame.setLocationRelativeTo(null);
            resultFrame.setLayout(new GridLayout(6, 1, 10, 10));

            resultFrame.add(new JLabel("Customer Name: " + customer));
            resultFrame.add(new JLabel("Product : " + product));
            resultFrame.add(new JLabel("VAT : R" + vat));
            resultFrame.add(new JLabel("COMMISSION: R" + commission));
            resultFrame.add(new JLabel("TOTAL: R" + total));

            JButton btnOK = new JButton("OK");
            btnOK.addActionListener(b -> resultFrame.dispose()); // closes the second window
            resultFrame.add(btnOK);

            resultFrame.setVisible(true); // shows the second window
        });

        setVisible(true);
    }
}