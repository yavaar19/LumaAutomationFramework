# Luma Test Automation Framework

This project is a test automation of an e-commerce website called Luma. It is a online store strictly for test automation scripts. Tools that were used to implement the framework are: Maven, Selenium, TestNG, Extent Reports and Apache POI. The test can be run on any IDE. The test should preferably be run using maven commands from the CLI. Please make sure to have Java and Maven installed on your system as well as have set their home path variables.

## Installation

Download the project from the github repository or at the following [link](https://github.com/yavaar19/LumaAutomationFramework/archive/refs/heads/main.zip)


## How to run the project

- Open the repository in your favorite IDE.
- Go to the IDE CLI.
- Enter the desired command listed in the Command section below.
- Please ensure that you have the browser installed on your system for which you are running the test on.
- Alternatively, you may also run the commands directly from your system's terminal. Just navigate to the project folder before doing so.

## Commands

The commands below are case sensitive. Please enter them exactly as shown below.

```java
# Run the smoke profile test in chrome browser
mvn test -P 'Smoke' -D browser='chrome'

# Run the smoke profile test in chrome headless mode
mvn test -P 'Smoke' -D browser='chromeheadless'

# Run the Regression profile test in chrome browser
mvn test -P 'Regression' -D browser='chrome'

# Run the Regression profile test in chrome headless mode
mvn test -P 'Regression' -D browser='chromeheadless'

# Run the smoke profile test in firefox browser
mvn test -P 'Smoke' -D browser='firefox'

# Run the smoke profile test in firefox headless mode
mvn test -P 'Smoke' -D browser='firefoxheadless'

# Run the Regression profile test in firefox browser
mvn test -P 'Regression' -D browser='firefox'

# Run the Regression profile test in firefox headless mode
mvn test -P 'Regression' -D browser='firefoxheadless'

```

## Results

The HTML report result will be placed in the 'Report' folder of the project. The screenshot failures will be placed in the 'Screenshots' folder of the project.
## Note
**Please  note that some test will fail as they should.** For example the e-commerce application should not allow  a user to register with a number in the first or last name. But currently the application allows for such scenario which is a bug that the developers of the e-commerce application should fix.

When running in Firefox, there will also be errors as when the website is used on the Firefox browser, the concerning alerts are shown in different locations randomly which is another bug that the developers of the e-commerce application should fix.

## Project status
I have so far implemented test automation for the 'Log In' page as well as the 'User Sign Up' page. I am currently on creating test cases for the 'My Account' page.
