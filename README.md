# _HOW TO RUN ON TERMINAL_
*// mvn -Dtest=DentsplyTest test
*// mvn -Dtest=DentsplyTest#testLogin test
*// allure: mvn -Dtest=tests.DentsplyTest test

//mvn test -Dtest=DentsplyTest#testAssertCompanyInformationLabels
//mvn test -Dtest=DentsplyTest#testB_Organizational

//RUN ON TERMINAL
//Use Maven to run the TestNG suite:
// mvn test -DsuiteXmlFile=testng.xml

//OR, if using JUnit Runner for TestNG, you can also run:
//mvn surefire:test

//6️⃣ View TestNG Reports
//open target/surefire-reports/emailable-report.html
//start target\surefire-reports\index.html
