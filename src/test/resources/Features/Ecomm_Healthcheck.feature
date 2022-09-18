@healthCheck
Feature: E-Commerce website healthcheck

Background: Navigation to the URL
Given User navigate to URL and open the landing page

@URLRedirection
Scenario: User naviaget to URL, User redirect to landing page with expected current URL
When User is on landing page
Then Validate current URL of landing page with expected URL

@LandingPageTitle
Scenario: User naviaget to URL, User redirect to landing page with expected page title
When User is on landing page
Then Validate title of landing page with expected title as "My Store"

@DisplayLogo
Scenario: User able to see logo of application on landing page
When User is on landing page
Then User see the logo of application

@LogoHeight
Scenario: Logo present on landing page with specific height dimension
When fetch the height of logo
Then Height of logo should be "99"

@LogoWidth
Scenario: Logo present on landing page with specific width dimension
When fetch the width of logo
Then Width of logo should be "350"

@SignInPage @test
Scenario: User click on SignIn button and navigate to respective page
Given User see SignIn button
When User click on SignIn button
Then User is on signIn page which have expected page title as "Login - My Store"

@TwitterHandle @test
Scenario: User click on twitter link and navigate to respective page
          and see the twitter account name
Given User click on twitter link
When navigate to twitter account page
Then User see the twitter account name "Selenium Framework"

@ProductCategory
Scenario: User able to see product category on landing page
When User see the product category
Then Validate product category as per expected product category listed below
|     WOMEN    |
|    DRESSES   |
|    T-SHIRTS  |
And Size of product category should be 3 

  @loginPositive @smoke
  Scenario Outline: User is able to login into the application
    Given User click on signin button from home page
    When User redirected to login page of the application where title us "Login - My Store"
    And User enters "<username>" and "<password>" and click on signin button
    Then User successfully redirected to "My account - My Store" page with user name displayed of the "<firstname>" and "<lastname>"
    Examples: 
      | username               | password | firstname | lastname |
      | dsfsfsdfds11@gmail.com |   123456 | Tom       | Peter    |
      | dsfsfsdfds12@gmail.com |   123456 | John      | Tyson    |
      | dsfsfsdfds13@gmail.com |   123456 | Steve     | Johnson  |
      
  @loginNegative @smoke
  Scenario Outline: User is unable to login into the application
    When User click on signin button from home page
    And User redirected to login page of the application where title us "Login - My Store"
    And User enters "<username>" and "<password>" and click on signin button
    Then User is unable to login with an error message "Authentication failed."

    Examples: 
      | username            | password |
      | testuser1@gmail.com |   123456 |
      | testuser2@gmail.com |   123456 |
      





















