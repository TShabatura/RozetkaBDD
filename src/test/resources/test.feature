Feature: Automated Tests
  As a user
  I want to test all main site functional
  So that I can be sure that site works correctly

  Scenario Outline: Customer place an order by purchasing an item from a search and check total amount of cart
    Given user is on Home Page
    When user search for '<groupOfThings>'
    And user filter items by '<producer>'
    And user add item to cart
    And user open cart
    Then user check that total amount of cart is lower than <totalAmount>

    Examples:
      | groupOfThings | producer | totalAmount |
      | Ноутбук       | HP       | 100000      |
      | Ноутбук       | Acer     | 50000       |