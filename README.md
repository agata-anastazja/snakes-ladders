# snake-and-ladders

This is a simulator for the game snake and ladders (also known as chutes and  ladders). The simulator can be run multiple times to find out various stats about the game. The  below sections provide details regarding the rules of the game, implementation constraints, and  the stats to be captured. 
Game rules: 
● The game starts at position 0 which is outside the board and it finishes when a player  reaches the last square that is 100. 
● The player rolls a single 6 sided dice (die) numbered from 1 to 6 to move their token by  the number of squares indicated by the die rolled. 
● If, on completion of a roll, a player's token lands on the lower-numbered end of a  "ladder", the player moves the token up to the ladder's higher-numbered square.  ● If the player lands on the higher-numbered square of a "snake" (or chute), the token  must be moved down to the snake's lower-numbered square 
● If a 6 is rolled by the player, the player gets an additional roll after the token is moved.  This additional roll is considered as a part of the same turn of the current player (1 turn  can have multiple rolls if 6 is rolled); otherwise, play passes to the next player in turn.  
● The player who is first to bring their token to the last square of the board is the winner. ● The player must roll the exact number to reach the final square. For example, if a player  needs a 3 to reach square 100 and rolls a 5, the player stays at the current location.  ○ If the player rolls 6 when they are stuck, they get additional rolls, but the token  does not move ahead. For example, 3 is needed to win and the player rolls a 6.  Then they get the additional roll. If they roll 6 again, they get another roll, and so  on. 
Implementation Constraints: 
● The number of times the simulation will be run should be configurable ● The positions of snakes & ladders should be configurable and not hardcoded on the  board 
● The role of snakes & ladders is set once before the simulation and will stay the same  during the entire simulation. 
● For the purpose of the simulation, a single-player implementation is acceptable. In case  multiple players are implemented, the simulation result should still be across all the  players and all the runs. 
● Reading of file/console input for the input to the program is not necessary

Stats to be captured: 
The following stats are to be captured across all the simulations: 
● Minimum/Average/Maximum number of rolls needed to win. 
● Minimum/Average/Maximum amount of climbs during the game 
○ A climb is the amount of distance covered by climbing up a ladder. For example,  if the token goes up a ladder from 21 to 51, the distance climbed is 30. 
● Minimum/Average/Maximum amount of slides during the game 
○ A slide is the amount of distance covered by sliding down a snake. For example,  if the token goes down a snake from 88 to 48, the slide distance is 40. 
● The biggest climb in a single turn. 
● The biggest slide in a single turn. 
● Longest turn. The longest turn is the highest streak of consecutive rolls due to rolling 6s. ○ Examples: 
■ Roll x is 5 and roll y is 3, then the longest turn so far is 5 as it is the  highest roll. 
■ Rolls in turn x are [6,4] and rolls in turn y are [6,3]. The longest turn is  [6,4]. 
■ Rolls in turn x are [6,6,6,5] and rolls in turn y are [6,6,6,6,1]. The longest  turn is [6,6,6,6,1]. 
● Minimum/Average/Maximum unlucky rolls during the game 
○ An unlucky roll is considered when any of the following is true 
■ A player lands on a snake  
● Minimum/Average/Maximum lucky rolls during the game 
○ A lucky roll is considered when any of the following is true 
■ A player lands on a ladder 
■ Misses a snake by 1 or 2 steps  
■ When they roll the exact number needed to win after 94 in a single roll.


Throughout the game fields on the board are reffered through their indices from 0 - 99
## Assumptions
You cannot chain slides and climbs
When the player lands on the same snake more then once we keep track of each slide
Landing field from a snake or ladder will not be the beginning of snake or ladder
A roll can be lucky and unlucky at the same time
A roll is lucky if we land next to snake without stepping on it when we get off the ladder if there is a ladder, but not if we get on a ladder which is close to "entrypoint" for snake
A roll is lucky and unlucky if we land on a snake that is just next to another snake 

## Given more time
I would 
- do rolls and board input validation
- wrote a bit more tests around edge cases for game stats



## License

Copyright © 2022 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.
