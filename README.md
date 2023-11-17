# :file_folder: PROJECT: API Clickup - E2E scenario - Java / Rest Assured
Verification of REST API in Clickup / E2E scenario using JAVA/Rest Assured

**Project scope:**

- find, read and analyse REST API documentation for Clickup
- E2E scenario:
    - create spaces
    - create list in the space
    - create a task in the list
    - update task name and description
    - change task's status to "completed"
- create requests and test scripts in JAVA using Rest Assured library
- apply good practises: clean up after testing activities
- run tests from Maven and Jenkins
- create Test Report in Allure

**Approach:**
In 'test' folder there are two classes with tests. 
Class: 'UpdateTaskE2EAllinOneTest.java' has one test with several methods.
Class: 'UpdateTaskE2EOrderTest.java' has 6 tests run in given order.
Both approaches are correct.

**Tools:**
- Intellij IDEA
- Maven
- Rest Assured library
- Jenkins
- Allure Report

**Jenkins configuration:**
- Repository URL: https://github.com/AleksandraPujanek/PROJECT-API-Clickup-E2E-scenario
- Build steps: Invoke top-level Maven targets
- Goals: clean test -Dtoken=TYPE_YOUR_TOKEN -DteamId=TYPE_YOUR_TEAM_ID

**Documentation:** [REST API Clickup - documentation](https://clickup.com/api/)

**Summary:** [Clickup - Test Report](https://drive.google.com/file/d/1LAeVc9l94KoXb7WgQBTqxLiGl-U3RQUB/view?usp=drive_link)
