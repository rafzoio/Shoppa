package com.gamebuy.store.utils;

import com.gamebuy.store.domain.Address;
import com.gamebuy.store.domain.Customer;
import com.gamebuy.store.domain.Product;
import com.gamebuy.store.service.AddressService;

import java.util.ArrayList;

public class DisplayTable {
	public static void displayProductTable(ArrayList<Product> products) {
		String leftAlignFormat = "| %-4d | %-8s | %-16s | %-10s | %-9s | %-5s |%n";

		System.out.format("+------+----------+------------------+------------+-----------+-------+%n");
		System.out.format("| ID   | SKU      | Description      | Category   | Available | Price |%n");
		System.out.format("+------+----------+------------------+------------+-----------+-------+%n");
		for (Product product : products) {
		    System.out.format(leftAlignFormat,
					product.getId(),
					product.getSKU(),
					product.getDescription(),
					product.getCategory(),
					product.getAvailable(),
					product.getPrice()
			);
		}
		System.out.format("+------+----------+------------------+------------+-----------+-------+%n");
		System.out.println();
	}

	public static void displayCustomerTable(ArrayList<Customer> customers) {

		AddressService addressService = AddressService.getInstance();

		Address address;

		String leftAlignFormat = "| %-4d | %-11s | %-11s | %-13s | %-59s |%n";

		System.out.format("+------+-------------+-------------+---------------+-------------------------------------------------------------+%n");
		System.out.format("| ID   | First name  | Second name | Telephone no. |  Address                                                    |%n");
		System.out.format("+------+-------------+-------------+---------------+-------------------------------------------------------------+%n");

		for (Customer customer : customers) {

			address = addressService.getCustomerAddress(customer.getId());

			System.out.format(leftAlignFormat,
					customer.getId(),
					customer.getFirstName(),
					customer.getSecondName(),
					customer.getTelephoneNumber(),
					(address.getHouse() + ", " +
					address.getAddressLine1() + ", " +
					address.getAddressLine2() + ", " +
					address.getCountry() + ", " +
					address.getPostcode())
			);
		}
		System.out.format("+------+-------------+-------------+---------------+-------------------------------------------------------------+%n");
		System.out.println();
	}
}
