# Equipment Lending Management System

### Overview

- The Equipment Lending Management System is a console-based application designed for administrators to manage equipment inventory, lending records, and user information in an organized manner. This system ensures streamlined equipment tracking, borrowing, and returning processes.
- The system provides features for viewing, adding, updating, and removing equipment and lending records, as well as generating detailed reports for overdue records and available equipment.

## Features
#### 1.Equipment Management
- View all equipment in the inventory.
- Add new equipment with relevant details like ID, name, status, purchase date, and condition.
- Update existing equipment status (e.g., Available/Borrowed) and condition (e.g., Brand New/Good/Needs Maintenance).
#### 2.Lending Record Management
- Add lending records, including borrower details, equipment list, borrowing dates, and purpose.
- Update lending records by modifying the borrower, borrowing/return dates, equipment list, and lending statuses.
- Delete lending records as needed.
#### 3.Reporting
- Generate reports for overdue lending records (equipment not returned on time).
- Generate reports for available equipment.
- Export reports to text files for future use.
#### 4.User Management
- View all registered students and staff.

## Folder Structure
```bash
src/
├── admin/
│     └── AdminConsole.java       # Main entry point for the application
├── model/
│     ├── Equipment.java          # Represents equipment in the inventory
│     ├── LendingRecord.java      # Tracks lending transactions
│     ├── Person.java             # Base class for all users
│     ├── Student.java            # Student class extending Person
│     ├── Staff.java              # Staff class extending Person
│     ├── Academic.java           # Subclass for academic staff
│     └── Professional.java       # Subclass for professional staff
├── service/
│     ├── InventoryService.java   # Handles inventory operations
│     ├── LendingService.java     # Handles lending operations
│     ├── LendingManager.java     # Interface for lending service
│     └── InventoryManager.java   # Interface for inventory service
```

## Setup and Installation
### Prerequisites
- Open Java Developer Kit (OpenJDK) 23 or higher.
- An IDE or text editor (e.g., IntelliJ IDEA, Eclipse, VS Code).
### Steps to run
#### 1. Clone the Repository
`$ git clone https://github.com/RMIT-Vietnam-Teaching/further-programming-assignment-1-build-a-console-app-hinatanguyen.git` <br>
`$ cd further-programming-assignment-1-build-a-console-app-hinatanguyen`
#### 2. Setup the Project in your IDE
- Open the project folder in your preferred IDE.
- Ensure the folder structure matches the layout provided above.
#### 3. Run the Application
- Locate the `$AdminConsole.java` file in the admin package.
- Run the `$main()` method to start the application.

## Usage Instruction
When the application runs, you will interact with the Admin Console menu displayed in the terminal:<br>
```bash
--- Admin Console ---
1. View all equipment
2. View all lending records
3. View all students
4. View all staff
5. Manage equipment
6. Manage lending records
7. Generate reports
8. Exit
```
Each option corresponds to a specific feature of the system:

### Option 1: View All Equipment
- Allows users to view a list of all equipment in the inventory.
- Displays details such as equipment ID, name, status (e.g., Available, Borrowed), purchase date, and condition.
- Provides a quick overview of the current inventory.

### Option 2: View All Lending Records
- Displays all lending transactions stored in the system.
- Includes comprehensive information, such as record ID, borrower details, equipment IDs, borrowing and return dates, status, and purpose.
- Helps users track lending activity.

### Option 3: View All Students
- Lists all registered students.
- Displays their personal details, including ID, name, contact information, and date of birth.
- Provides an overview of students eligible for borrowing equipment.

### Option 4: View All Staff
- Lists all registered staff, categorized into academic and professional staff.
- Displays their personal details, including ID, name, contact information, expertise (for academic staff), and date of birth.
- Allows users to view staff eligible for borrowing equipment.

### Option 5: Manage Equipment
- Provides tools to add, update, or remove equipment from the inventory.
- Includes functionality to update equipment status (e.g., Available, Borrowed) and condition (e.g., Brand New, Needs Maintenance).
- Helps administrators keep the inventory organized.

### Option 6: Manage Lending Records
- Allows users to add new lending records with details like borrower information, equipment IDs, borrowing dates, and purpose.
- Enables updating existing lending records or deleting outdated ones.
- Offers flexibility in tracking lending activity.

### Option 7: Generate Reports
- Generates detailed reports for various purposes:
- Overdue lending records (for equipment not returned on time).
- Available equipment in the inventory.
- Reports can be exported to text files for external use.

### Option 8: Exit
- Safely exits the program, ensuring all changes and data are saved.

### How to Navigate the Menu
1. Input Commands: Simply type the number corresponding to the menu option and press Enter. For example:
- Typing 1 and pressing Enter will display all equipment.
- Typing 8 and pressing Enter will exit the program.

2. Interaction Example:
```bash
--- Admin Console ---
Enter your choice: 1
--- Equipment List ---
ID: 101, Name: VR Headset, Status: Available, Condition: Brand New
ID: 102, Name: Laptop, Status: Borrowed, Condition: Good
Error Handling: If an invalid input is entered (e.g., 0 or abc), the system will display an error message and prompt the user to try again.
```

## Contributors
- **Hinata Nguyen**: Project Lead and Developer
