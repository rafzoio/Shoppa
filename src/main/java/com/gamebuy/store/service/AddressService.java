package com.gamebuy.store.service;

import com.gamebuy.store.dao.AddressDAO;
import com.gamebuy.store.dao.CustomerDAO;
import com.gamebuy.store.domain.Address;

public class AddressService {

    private static AddressService instance;

    private final AddressDAO addressDAO;
    private final CustomerDAO customerDAO;

    private AddressService(AddressDAO addressDAO, CustomerDAO customerDAO) {
        this.addressDAO = addressDAO;
        this.customerDAO = customerDAO;
    }

    public static AddressService getInstance() {
        AddressDAO addressDAO = new AddressDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        if (instance == null) {
            instance = new AddressService(addressDAO, customerDAO);
        }
        return instance;
    }

    public Address getCustomerAddress(int customerId) {
        Address address = addressDAO.getAddress(customerId);
        if (address == null) {
            return new Address();
        }
        return address;
    }

    public void updateCustomerAddress(Address address) {
        addressDAO.updateAddress(
                address.getCustomerId(),
                address.getHouse(),
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCountry(),
                address.getPostcode()
        );
    }
}
