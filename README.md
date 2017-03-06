Supermarket Checkout
====================

Code for a supermarket checkout that calculates the total price of a number of items.

In a normal supermarket, items are identified by ‘stock keeping units’ or ‘SKUs’. In our store, we will use
individual letters of the alphabet, A, B, C etc, as the SKUs. Our goods are priced individually. In addition,
some items are multipriced: buy n of them and which will cost you y. For example, item A might cost 50
pence individually but this week we have a special offer where you can buy 3 As for £1.30.
This week’s prices are the following:

| Item | Unit Price | Special Price |
|------|------------|---------------|
|    A |         50 |     3 for 130 |
|    B |         30 |      2 for 45 |
|    C |         20 |               |
|    D |         15 |               |

Our checkout accepts items in any order so if we scan a B, then an A, then another B, we will recognise
the two B’s and price them at 45 (for a total price so far of 95).
Extra points: Because the pricing changes frequently we will need to be able to pass in a set of pricing
rules each time we start handling a checkout transaction.

Dev Notes
---------
- find the Scenario Descriptions in `src/test/resources/checkout.feature`
- execute the following command on this directory to execute the tests
```
mvn verify
```
