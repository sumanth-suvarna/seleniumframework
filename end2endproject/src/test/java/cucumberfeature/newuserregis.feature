Feature: Register New User in Amazon

Scenario: Register existing user
Given The Amazon webpage "https://www.amazon.in/" is launched
And Navigate to new user signin page
When The user tries to register already existing user "Sumanth" with mobno "9972355711" email id "sumanth.suvarna@gmail.com" and password "pass@1234"
Then Validate Email Id "sumanth.suvarna@gmail.com" is already registered message in the browser