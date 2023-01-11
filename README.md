# OOP-Middle

## Paterns description

### Strategy
We use a Strategy pattern in the implementations of scrapers. We have three different ways to scrap different data.

To make the code more flexible and comply with the basic principles of SOLID, We implemented a common interface for all scraping algorithms.

In the future, this will allow us to easily add new scrapers without interfering with the business logic code.

### Chain of Responsibility
We use a chain of responsibility pattern in the process of parsing information about company.

### Controller (GRASP)
We use a separate controller class that accepts and delegates requests from the UI.

This class receives the request and calls the appropriate business logic methods assigned to a separate service class.
