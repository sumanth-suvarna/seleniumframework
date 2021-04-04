Feature: Launch Amazon Webpage

Scenario: Test webpage launch
Given The web drivers are initialized
When The user enters Amazon URL "https://www.amazon.in/" in the browser
Then Verify Amazon webpage is launched successfully
