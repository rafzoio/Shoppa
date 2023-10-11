# Shoppa 
##### An online store built in Java. Raphael Zoio January 2023
Project available on [GitHub]("https://github.com/rafzoio/GameBuy")

### Basic Functionality

- Interaction with SQLite Database.
- Console application (see end of Main) giving basic CRUD functionality for customer and product classes.
- Front-end Web GUI giving CRUD access to customers and products.

### Advanced Functionality

- Product list can be filtered by product category.
- Products can be added to shopping basket in any quantity up to the total available for that product.
- Basket displays total price of its containing products.
- Option for user to clear basket, remove individual items from basket and adjust quantities.
- Admin and Customer user roles. Admins can add, edit and remove products, customers and users but cannot access the basket functionality.

### Techniques Used

- MD5 password hashing to encode passwords in database.
- Singleton design pattern used for service classes.
- JavaDocs documentation at '/JavaDocs/index.html'.
- Some JUnit testing for DAO classes.
