# Purchase Order Web Service

## Overview

This Java-based web service implements a comprehensive Purchase Order management system with XML-based communication. The service handles purchase order validation, persistence, and integration with various business entities including suppliers, transporters, and products.

## Architecture

### Core Components

- **Web Service Interface**: SOAP/XML-based service for receiving and processing purchase orders
- **Validation Layer**: Comprehensive validation of purchase order data and business rules
- **Persistence Layer**: Hibernate-based data persistence with JPA annotations
- **Integration Layer**: Handles relationships between various business entities

### Project Structure

```
web-service/
├── constants/          # System constants and configuration values
├── dao/               # Data Access Object layer
│   └── impl/          # DAO implementations
├── entity/            # JPA entity classes
├── facade/            # Business logic facade layer
├── factory/           # Factory pattern implementations
├── impl/              # Service implementations
├── intf/              # Service interfaces
├── purchase/          # Purchase order object models (OM)
├── util/              # Utility classes and exception handling
└── xml/               # XML result object models
```

## Key Features

### Purchase Order Management

- **Complete Order Validation**: Validates all purchase order fields including:
  - Order number, dates, and currency
  - Supplier information and payment terms
  - Product details (codes, quantities, prices)
  - Cost center and accounting information
  - Transporter and redispatch details

- **Product Item Processing**:
  - Multi-item support with automatic value calculations
  - Unit of measure validation
  - Price and quantity verification
  - Total value reconciliation

- **Accounting Integration**:
  - Cost center validation and assignment
  - Sub-cost center support
  - Multiple accounting entries per product item
  - Cost type categorization

### Business Entity Relationships

- **Suppliers** (`Clientes_Principal`): Manages supplier/vendor information
- **Products** (`Produtos`): Product catalog with concatenated codes for EFD integration
- **Payment Terms** (`Condicoes_Pagto`): Payment condition management
- **Transporters** (`Transportadoras`): Freight carrier information
- **Units of Measure** (`RetUnidades`): Product unit validation

## Technical Details

### Technologies Used

- **Java**: Core programming language
- **JPA/Hibernate**: Object-relational mapping and persistence
- **Apache Axiom**: XML object model processing
- **SOAP**: Web service protocol

### Database Entities

#### Primary Tables
- `Ws_Int_Purchase`: Main purchase order table
- `Ws_Int_ItemPurchase`: Purchase order line items
- `Ws_Int_PurchaseProdAccounting`: Accounting information per item
- `Cmt_Pedido`: Integrated order management table

#### Integration Tables
- `Ws_Int_Relationship`: Cross-reference between external and internal codes
- `VwContSPEDExpDeptoCCusto`: Cost center department view
- `VwContSPEDExpCCusto`: Sub-cost center view

### Data Validation Rules

1. **Required Fields**:
   - Purchase number, date, delivery date
   - Currency and supplier code
   - Product code, quantity, price, and value

2. **Business Logic**:
   - Product value must equal quantity × price (with 4 decimal precision)
   - Total order value must equal sum of all item values
   - All referenced entities must exist in the database

3. **Relationship Validation**:
   - Supplier codes mapped through relationship tables
   - Product codes validated against concatenated EFD codes
   - Payment terms and transporters verified in master data

## API Usage

### Purchase Order Submission

The service exposes a `setPurchaseOrder` method that:

1. Receives XML purchase order data
2. Validates all fields and relationships
3. Persists data to multiple tables
4. Returns response with order ID or error details

### Response Codes

- `100`: Purchase successfully validated
- `101`: Purchase successfully saved
- `900`: Error occurred (details in error message)

## Exception Handling

### Custom Exception Types

- `ValidationException`: Field validation failures
- `FacadeException`: Business logic errors
- `DAOImplException`: Data access layer errors
- `PersistenceMicrodataException`: Persistence specific errors
- `MicrodataException`: General microdata processing errors

## Configuration

The system uses constants defined in `Constants.java` for:
- Company codes
- Default values
- System parameters

## Development Notes

### Key Classes

1. **PurchaseImpl**: Main service implementation entry point
2. **PurchaseFacade**: Business logic for purchase order processing
3. **CmtPedidoFacade**: Integration with order management system
4. **ValidationFacade**: Centralized validation logic
5. **HibernateUtil**: Database session management

### Extension Points

- Add new validation rules in `PurchaseFacade.validatePurchase()`
- Extend entity relationships through `WsIntRelationship`
- Implement additional facades for new business domains

## Error Handling

The service implements comprehensive error handling:
- Field-level validation with descriptive messages
- Transaction rollback on failures
- Detailed error codes and messages in responses
- Request logging for audit trails

## Security Considerations

- Input validation prevents SQL injection
- XML parsing configured to prevent XXE attacks
- Sensitive data handling through secure persistence layer

## Future Enhancements

Potential areas for improvement:
- RESTful API addition alongside SOAP
- Asynchronous processing for large orders
- Enhanced reporting and analytics
- Real-time inventory integration
- Multi-currency support expansion

## License

This project is proprietary software. All rights reserved.

## Support

For technical support and questions, please contact the development team.