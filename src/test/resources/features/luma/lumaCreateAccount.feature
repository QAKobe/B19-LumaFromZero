Feature: Create an Account

  Scenario: Create an account on luma web page
    Given user is main page and clicks on create account link
    Then user scrolls down the page
    And user enters firstname and lastname as
      | firstname | Vasya   |
      | lastname  | Vasiliy |
    Then user enters email and password as
      | email    | rf@trew.com     |
      | password | YTYTyr4534$%$%4 |
    And user confirms password as previously entered
      | confirmPassword | YTYTyr4534$%$%4 |
    Then user clicks on create account link
    And user validates success message
      | successMessage | Thank you for registering with Main Website Store. |


    #//input[@id='name' and @name='name'] or, and condition in xpath

    # //input[@id='name']//parent::div when we talk to parent

   # //input[@id='name']//child::div not working but this an example of parent/child relationship