Technical Test: Java Program for a Banking Application with Savings
Objective:
The objective of this test is to evaluate your Java programming skills, particularly your ability to design and implement functionalities for a banking application, including handling savings accounts with a 5% interest rate.
Statement:
You are required to write a Java program to simulate basic operations of a bank account, as well as operations specific to a savings account. The program should allow creating either a checking account or a savings account, depositing money, withdrawing money, and displaying the account balance.
Specifications:
1. Account Creation:
 The program should allow the user to create an account by entering a name, initial balance, and the type of account (checking or savings).
 Each account should have a unique identifier.
2. Deposit:
 The program should allow the user to deposit money into an account using its identifier.
 The deposited amount should be added to the account balance.
3. Withdrawal:
 The program should allow the user to withdraw money from an account using its identifier.
 The withdrawal amount should be deducted from the account balance.
 For a checking account, ensure that the balance does not become negative.
 For a savings account, check if the withdrawal does not exceed the maximum allowed amount per period (e.g., 1000 euros per month).
4. Display Balance:
 The program should allow the user to display the balance of an account using its identifier.
5. Calculation of Interest for a Savings Account:
 The program should allow calculating and adding interest to a savings account.
 Interest should be calculated monthly at a fixed rate of 5% and added to the account balance.
6. Unit Tests:
 Write unit tests to verify the proper functioning of your program's methods.
Example :
Constraints:
1. The program must be written in Java.
2. Use concepts of classes and objects to model bank accounts.
1. Create an account
2. Deposit money
3. Withdraw money
4. Display balance
5. Calculate interest for a savings account
6. Quit
Enter your choice: 1
Enter your name: Alice Smith
Enter the initial balance: 5000
Choose the account type (1 for checking, 2 for savings): 2
Savings account created successfully. Account identifier: 456
Enter your choice: 2
Enter your account identifier: 456
Enter the amount to deposit: 1000
1000 euros have been deposited into your account.
Enter your choice: 3
Enter your account identifier: 456
Enter the amount to withdraw: 500
500 euros have been withdrawn from your account.
Enter your choice: 5
Enter your account identifier: 456
Interest for this month is 25 euros.
Enter your choice: 4
Enter your account identifier: 456
Your balance is 5525 euros.
Enter your choice: 6
Goodbye!
3. Handle error cases (non-existent account, insufficient balance, withdrawal exceeding the maximum allowed amount, etc.) by displaying appropriate messages.
4. The code should be clear, well-commented, and follow good programming practices.
5. Write unit tests to verify the proper functioning of your program's methods.
Evaluation Criteria:
Your solution will be evaluated based on the following criteria:
 Accuracy of results.
 Efficiency of operations (deposit, withdrawal, balance display, interest calculation).
 Clarity and readability of the code.
 Proper error handling.
 Coverage of unit tests.
Good luck!
