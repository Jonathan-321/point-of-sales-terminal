# Point of Sale (POS) System

The Point of Sale (POS) System is a Java-based application designed to facilitate sales transactions and manage inventory for a retail store. 
It provides a user-friendly interface for cashiers to process sales, handle payments, and generate receipts.

## Features

- Process sales transactions with multiple line items
- Support various payment methods, including cash, credit, and checks
- Calculate subtotals, taxes, and total amounts for each sale
- Manage inventory by tracking item quantities and prices
- Generate detailed sales reports for analysis and accounting purposes
- Read data from files to populate the application with initial data


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
- `POSDM`: Contains the data management functionality
  - `DataManager`: Handles reading data from files and populating the application
  - `FileReader`: Utility class for reading data from files
 
## Data Management

The POS System relies on data stored in files to populate the application with initial data. The `POSDM` package provides the necessary functionality to read data from files and load it into the application.

The `DataManager` class is responsible for coordinating the data loading process. It uses the `FileReader` utility class to read data from specific files and populate the corresponding objects in the application.

The data files are located in the `data` directory and follow a specific format. Each file contains data related to a specific entity, such as items, taxes, or store information. The `FileReader` class reads these files, parses the data, and creates the appropriate objects based on the file content.

To add or modify the initial data, you can update the corresponding files in the `data` directory. The application will load the updated data when it starts.


## Usage

1. Clone the repository: `https://github.com/Jonathan-321/point-of-sales-terminal.git`
2. Open the project in your preferred Java IDE.
3. Build and run the `POSUI.MainFrame` class to start the application.
4. Use the user interface to process sales, add items, and handle payments.
5. Access the inventory management features through the appropriate panels.

## Contributing

Contributions to the project are welcome! If you find any issues or have suggestions for improvements, 
please open an issue or submit a pull request. Make sure to follow the existing code style and provide appropriate tests for any new features.

## License

This project is licensed under the Oklahoma Christian University. 
