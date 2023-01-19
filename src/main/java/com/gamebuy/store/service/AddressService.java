package com.gamebuy.store.service;

import com.gamebuy.store.dao.AddressDAO;
import com.gamebuy.store.domain.Address;

public class AddressService {

    private static AddressService instance;

    private final AddressDAO addressDAO;

    private AddressService(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public static AddressService getInstance() {
        AddressDAO addressDAO = new AddressDAO();
        if (instance == null) {
            instance = new AddressService(addressDAO);
        }
        return instance;
    }

    /**
     * Returns the address of a customer specified by customerId.
     * Returns new empty address if address does not exist in database.
     *
     * @param customerId the id of customer who's address is required.
     * @return Address of customerId supplied.
     */
    public Address getCustomerAddress(int customerId) {
        Address address = addressDAO.getAddress(customerId);
        if (address == null) {
            return new Address();
        }
        return address;
    }

    /**
     * Accesses addressDAO update address method.
     *
     * @param address
     */
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
