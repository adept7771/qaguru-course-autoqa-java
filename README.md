# qaguru-course

Repository contains lessons from QA Guru course ga.guru. Each branch contains finished homework for lessons. A final 
branch contains completed project which you can use for your automation purposes. 

Technologies involved:

- java
- gradle
- Selenide (Automation framework based on Selenium)
- Selenoid (Virtualization system based on the docker)
- Junit5 + multi-threading runs
- Allure (+Allure test ops) test report system.
- Jira integration / Jenkins integration
- Telegram piechart reporting library integration

How-To:

You can run tests from a local machine (or your IDE) or by Selenoid. Just choose it in Props file. All Props file 
variables can be also passed in system variables and have the highest priority to use (if we have defined option from system 
variables and in a file, system vars have a higher priority). To pass variables to system (or to CI) just write 
-DoptionName=optionValue (for Example: -DrunViaSelenoid=true) in gradle execution command line.

Define how much threads to run you can in build-gradle file via junit.jupiter.execution.parallel.config.fixed.parallelism
 option.

All steps are decomposed to steps-files. Locators for elements defined also in step-files. For future work you can
move all locators in page-object files.

Selenoid run:

![selenoid screenshot](src/test/resources/readmeImages/Screenshot 2021-02-26 at 13.48.49.png)
![selenoid screenshot](src/test/resources/readmeImages/Screenshot 2021-02-26 at 13.43.10.png)

Allure free version:

![allure free](src/test/resources/readmeImages/Screenshot 2021-02-26 at 13.43.28.png)
![allure free](src/test/resources/readmeImages/Screenshot 2021-02-26 at 13.43.46.png)
![allure free](src/test/resources/readmeImages/Screenshot 2021-02-26 at 13.44.21.png)

Jenkins run:

![jenkins](src/test/resources/readmeImages/Screenshot 2021-02-26 at 13.45.08.png)
![jenkins](src/test/resources/readmeImages/Screenshot 2021-02-26 at 13.45.17.png)

Allure enterprise edition (Allure test ops):

![allure test ops](src/test/resources/readmeImages/Screenshot 2021-02-26 at 13.45.39.png)
![allure test ops](src/test/resources/readmeImages/Screenshot 2021-02-26 at 13.45.55.png)
![allure test ops](src/test/resources/readmeImages/Screenshot 2021-02-26 at 13.46.06.png)
![allure test ops](src/test/resources/readmeImages/Screenshot 2021-02-26 at 13.46.11.png)
![allure test ops](src/test/resources/readmeImages/Screenshot 2021-02-26 at 13.45.08.png)

Telegram piechart reporting:

![telegram](src/test/resources/readmeImages/Screenshot 2021-02-26 at 13.46.41.png)

Jira integration:

![jira](src/test/resources/readmeImages/Screenshot 2021-02-26 at 13.47.35.png)