# Point of Sale (POS) System

The Point of Sale (POS) System is a Java-based application designed to facilitate sales transactions and manage inventory for a retail store. 
It provides a user-friendly interface for cashiers to process sales, handle payments, and generate receipts.

## Features

- Process sales transactions with multiple line items
- Support various payment methods, including cash, credit, and checks
- Calculate subtotals, taxes, and total amounts for each sale
- Manage inventory by tracking item quantities and prices
- Generate detailed sales reports for analysis and accounting purposes

## Project Structure

The project is organized into the following packages:

- `POSPD`: Contains the main business logic and data models
  - `Item`: Represents an item in the store's inventory
  - `Sale`: Represents a sales transaction
  - `SaleLineItem`: Represents a line item within a sale
  - `Payment`: Abstract base class for different payment methods
  - `Cash`, `Check`, `Credit`: Concrete payment method classes
- `POSUI`: Contains the user interface components
  - `MainFrame`: The main application window
  - `SalePanel`: Displays the current sale and allows adding line items
  - `PaymentPanel`: Handles the payment process for a sale
  - `InventoryPanel`: Manages the store's inventory

## Usage

1. Clone the repository: `git clone https://github.com/your-username/pos-system.git`
2. Open the project in your preferred Java IDE.
3. Build and run the `POSUI.MainFrame` class to start the application.
4. Use the user interface to process sales, add items, and handle payments.
5. Access the inventory management features through the appropriate panels.

## Contributing

Contributions to the project are welcome! If you find any issues or have suggestions for improvements, 
please open an issue or submit a pull request. Make sure to follow the existing code style and provide appropriate tests for any new features.

## License

This project is licensed under the Oklahoma Christian University. 
