# poker-hands
Find how many winning hands of poker are dealt to Player 1

### [Background](https://projecteuler.net/problem=54)
In poker, a `Hand` consists of five `Cards`.  
The following is a list of `Hand` ranks, from lowest to highest:  
* High Card: Highest value card.
* One Pair: Two cards of the same value.
* Two Pairs: Two different pairs.
* Three of a Kind: Three cards of the same value.
* Straight: All cards are consecutive values.
* Flush: All cards of the same suit.
* Full House: Three of a kind and a pair.
* Four of a Kind: Four cards of the same value.
* Straight Flush: All cards are consecutive values of same suit.
* Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.

If two `Hands` have the same rank, then the highest value unique to that rank determines which `Hand` is better.  
Ex.
* One Pair: A pair of 3s beats a pair of 2s.
* Two Pairs: A pair of 9s and a pair of 2s beats a pair of 7s and a pair of 8s.
* Straight: (5 6 7 8 9) of Hearts beats (2 3 4 5 6) of Diamonds.
* Full House: (A A A 2 2) beats (K K K Q Q).

### Problem Statement
Given [a list](https://projecteuler.net/project/resources/p054_poker.txt) of 1,000 `Hands` dealt to two players (Player 1 and Player 2), how many winning `Hands` was Player 1 dealt?

Below are a few lines from the above list.  
```
...
5C AD 5D AC 9C 7C 5H 8D TD KS
3H 7H 6S KC JS QH TD JC 2D 8S
TH 8H 5C QS TC 9H 4D JC KS JS
...
```
Note that the first 5 `Cards` per line belong to Player 1, while the remaining 5 `Cards` belong to Player 2.

### Motivation
This project was completed for [Project Euler - Problem 54](https://projecteuler.net/problem=54).

I recommend that this code should only be viewed _after_ you've completed your own implementation.  
If you're super stuck, take a break, take a walk, and it will come to you; good luck.
