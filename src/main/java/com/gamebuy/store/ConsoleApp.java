package com.gamebuy.store;

import com.gamebuy.store.dao.AddressDAO;
import com.gamebuy.store.dao.CustomerDAO;
import com.gamebuy.store.dao.ProductDAO;
import com.gamebuy.store.domain.Address;
import com.gamebuy.store.domain.Customer;
import com.gamebuy.store.domain.Product;
import com.gamebuy.store.utils.DisplayTable;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleApp {

    /**
     * Console App.
     * Provides CRUD access to customers and products.
     */
    public static void consoleApp() {

        ProductDAO productDAO = new ProductDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        AddressDAO addressDAO = new AddressDAO();

        Scanner in = new Scanner(System.in);

        String selection;
        String newSKU;
        String newDescription;
        String newCategory;
        int newAvailable;
        int newPrice;

        do {
            System.out.println("--------------------");
            System.out.println("The Everything Store");
            System.out.println("Choose from these options:");
            System.out.println("--------------------");
            System.out.println("[1] List all products");
            System.out.println("[2] Add a new product");
            System.out.println("[3] Search for a product by ID");
            System.out.println("[4] Update a product by ID");
            System.out.println("[5] Delete a product by ID");
            System.out.println("[6] List all customers");
            System.out.println("[7] Add a new customer");
            System.out.println("[8] Search for a customer by ID");
            System.out.println("[9] Update a customer by ID");
            System.out.println("[10] Delete a customer by ID");
            System.out.println("[99] Exit");
            System.out.println();

            selection = in.nextLine();

            switch (selection) {
                case "1":
                    System.out.println("All Products:");
                    ArrayList<Product> allProducts = productDAO.getAllProducts();
                    DisplayTable.displayProductTable(allProducts);
                    System.out.println();
                    break;

                case "2":
                    System.out.println("Add a new product:");

                    System.out.println("Please enter a SKU:");
                    newSKU = in.nextLine();

                    System.out.println("Please enter a description:");
                    newDescription = in.nextLine();

                    System.out.println("Please enter a category:");
                    newCategory = in.nextLine();

                    System.out.println("Please enter the available quantity of this product:");
                    newAvailable = Integer.parseInt(in.nextLine());

                    System.out.println("Please enter a price:");
                    newPrice = Integer.parseInt(in.nextLine());

                    Product newProduct = new Product(newSKU, newDescription, newCategory, newAvailable, newPrice);

                    productDAO.addProduct(newProduct);

                    break;

                case "3":
                    System.out.println("Search for a product by ID:");

                    int id = Integer.parseInt(in.nextLine());

                    ArrayList<Product> products = new ArrayList<>();

                    products.add(productDAO.getProduct(id));

                    DisplayTable.displayProductTable(products);

                    break;

                case "4":
                    System.out.println("Update a product by ID:");
                    id = Integer.parseInt(in.nextLine());

                    System.out.println("Please enter a SKU:");
                    newSKU = in.nextLine();

                    System.out.println("Please enter a description:");
                    newDescription = in.nextLine();

                    System.out.println("Please enter a category:");
                    newCategory = in.nextLine();

                    System.out.println("Please enter the available quantity of this product:");
                    String strAvailable = in.nextLine();

                    if (strAvailable.equals("")) {
                        newAvailable = Integer.parseInt(strAvailable);
                    } else {
                        newAvailable = 0;
                    }

                    System.out.println("Please enter a price:");
                    String strPrice = in.nextLine();
                    if (!strAvailable.equals("")) {
                        newPrice = Integer.parseInt(strPrice);
                    } else {
                        newPrice = 0;
                    }

                    productDAO.updateProduct(id, newSKU, newDescription, newCategory, newAvailable, newPrice);

                    System.out.println("Product has been updated.");

                    break;

                case "5":
                    System.out.println("Delete a product by ID:");
                    System.out.println("Please enter an id:");

                    id = Integer.parseInt(in.nextLine());

                    productDAO.deleteProduct(id);

                    break;

                case "6":
                    System.out.println("All Customers:");

                    ArrayList<Customer> allCustomers = customerDAO.getAllCustomers();

                    DisplayTable.displayCustomerTable(allCustomers);

                    System.out.println();

                    break;

                case "7":
                    System.out.println("Add a new customer:");

                    System.out.println("Please enter a first name:");
                    String firstName = in.nextLine();

                    System.out.println("Please enter a second name:");
                    String secondName = in.nextLine();

                    System.out.println("Please enter a telephone number:");
                    String telephoneNumber = in.nextLine();

                    System.out.println("Please enter a house name:");
                    String house = in.nextLine();

                    System.out.println("Please enter the first address line:");
                    String addressLine1 = in.nextLine();

                    System.out.println("Please enter the second address line:");
                    String addressLine2 = in.nextLine();

                    System.out.println("Please enter a country:");
                    String country = in.nextLine();

                    System.out.println("Please enter a postcode:");
                    String postcode = in.nextLine();

                    Customer customer = new Customer(firstName, secondName, telephoneNumber);

                    int customerId = customerDAO.addCustomer(customer);

                    Address address = new Address(customerId, house, addressLine1, addressLine2, country, postcode);

                    addressDAO.addAddress(address);

                    break;

                case "8":
                    System.out.println("Search for a customer by ID:");

                    id = Integer.parseInt(in.nextLine());

                    ArrayList<Customer> customers = new ArrayList<>();

                    customers.add(customerDAO.getCustomer(id));

                    DisplayTable.displayCustomerTable(customers);

                    break;

                case "9":
                    System.out.println("Update a customer by ID:");
                    id = Integer.parseInt(in.nextLine());

                    System.out.println("Please enter a first name:");
                    firstName = in.nextLine();

                    System.out.println("Please enter a second name:");
                    secondName = in.nextLine();

                    System.out.println("Please enter a telephone number:");
                    telephoneNumber = in.nextLine();

                    System.out.println("Please enter a house name:");
                    house = in.nextLine();

                    System.out.println("Please enter the first address line:");
                    addressLine1 = in.nextLine();

                    System.out.println("Please enter the second address line:");
                    addressLine2 = in.nextLine();

                    System.out.println("Please enter a country:");
                    country = in.nextLine();

                    System.out.println("Please enter a postcode:");
                    postcode = in.nextLine();

                    customerDAO.updateCustomer(id, firstName, secondName, telephoneNumber);
                    addressDAO.updateAddress(id, house, addressLine1, addressLine2, country, postcode);

                    System.out.println("Product has been updated.");

                    break;
                case "10":
                    System.out.println("Delete a customer by ID:");
                    System.out.println("Please enter an id:");

                    id = Integer.parseInt(in.nextLine());
                    customerDAO.deleteCustomer(id);
                    addressDAO.deleteAddress(id);
                    break;

            }
        } while (!selection.equals("99"));
        in.close();
    }
}
