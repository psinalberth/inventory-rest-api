# Inventory REST API

## Overview

The Inventory REST API is a robust and scalable backend service designed for managing inventory systems. This project is implemented in **Java** and leverages modern frameworks and libraries to ensure high performance and reliability. It also includes functionality for interacting with inventory items, products, and batch types.

## Features

- **Inventory Management**: Create, update, and fetch inventory details.
- **Product Management**: Manage products and their associated data.
- **Batch Type Operations**: Handle batch types for inventory items.
- **CSV Import/Export**: Supports CSV data import/export for seamless data operations.
- **Database Integration**: Features MongoDB repositories for efficient data storage and retrieval.
- **Error Handling**: Implements custom error mapping and response generation for enhanced user experience.

## Project Structure

```plaintext
src/main/java/com/github/psinalberth/
├── api
│   ├── providers
│   │   ├── reactor
│   │   ├── opencsv
│   │   ├── config
│   │   └── springdoc
├── domain
│   ├── inventory
│   │   ├── core
│   │   ├── application
│   │   └── infrastructure
│   └── product
│       ├── core
│       ├── application
│       └── infrastructure
└── shared
    ├── exception
    └── port
```

### Key Modules

1. **API Providers**:
   - Provides various configurations (`CorsConfig`, `OpenApiConfig`) and utility classes for handling server responses and CSV operations.

2. **Domain Layer**:
   - Core logic for inventory and product management.
   - Models and ports for domain-driven design.

3. **Infrastructure**:
   - Database repositories for MongoDB integrations.
   - Includes mappers and data access objects.

4. **Shared Utilities**:
   - Common utilities like error handling (`ResourceNotFoundException`) and data import/export interfaces.

## Getting Started

### Prerequisites

- **Java 17** or higher.
- **Maven** for dependency management.
- A running instance of **MongoDB**.

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/psinalberth/inventory-rest-api.git
   cd inventory-rest-api
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   java -jar target/inventory-rest-api.jar
   ```

### Running the Tests

Run the unit tests using Maven:
```bash
mvn test
```

## API Endpoints

- **Inventory**:
  - `POST /inventories`: Create a new inventory.
  - `GET /inventories/{id}`: Retrieve inventory by ID.
  - `PUT /inventories/{id}`: Update an existing inventory.

- **Products**:
  - `POST /products`: Add new products.
  - `GET /products/{id}`: Fetch product details.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
