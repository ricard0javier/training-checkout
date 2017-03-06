Feature: Supermarket checkout

  Supermarket checkout that calculates the total price of a number of items

  Rules:
  - Goods prices can be set individually
  - Basket accepts items in any order
  - Checkout receives a list of rules
  - Checkout calculates the total price of a number of items

  Background:
    Given a new shop



  Scenario: Goods prices can be set individually
    When there is an inventory of goods
      | sku | price | unitsForOffer | offerPrice |
      | A   | 50    | 3             | 130        |
      | B   | 30    | 2             | 45         |
      | C   | 20    |               |            |
      | D   | 15    |               |            |
    Then the inventory should contains 4 items available
    And the inventory contains the item A with price 50
    And the inventory contains the item B with price 30
    And the inventory contains the item C with price 20
    And the inventory contains the item D with price 15



  Scenario: Basket accepts items in any order
    When there is an inventory of goods
      | sku | price | unitsForOffer | offerPrice |
      | A   | 50    | 3             | 130        |
      | B   | 30    | 2             | 45         |
    And I get a basked
    And I add the item B to the basked
    And I add the item A to the basked
    And I add the item B to the basked
    Then the basket contains 3 items
    Then the basket contains 1 A's
    And the basket contains 2 B's



  Scenario: Calculates the total price of a number of items
    When there is an inventory of goods
      | sku | price | unitsForOffer | offerPrice |
      | A   | 50    | 3             | 130        |
      | B   | 30    | 2             | 45         |
      | C   | 20    |               |            |
      | D   | 15    |               |            |
    And I get a basked
    And I add the item B to the basked
    And I add the item A to the basked
    And I add the item B to the basked
    And I add the item A to the basked
    And I add the item B to the basked
    And I add the item A to the basked
    And I add the item C to the basked
    Then the checkout total price is 225
