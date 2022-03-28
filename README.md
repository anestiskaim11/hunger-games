# Hunger-games
A university assignment where we had to code the board game "hunger games" with graphics.

## Rules
* Two player game, both start from the bottom right corner of the table
* Players gather points from food and lose points from traps (ropes, wild animals)
* Players can gather weapons (bow, sword, pistol)
* If a player has picked up a bow they can disable wild animal traps
* If a player has picked up a bow they can disable rope traps
* if a player owns a pistol they can kill the other player if they are in a neighbour block
* The board shrinks every 3 rounds
* Game ends if the board reaches size 4x4 or if a player dies

## Players
We developped 3 kinds of players with different strategies.
* Random Player (completely random moves)
* Heuristic Player (evaluates the next move according to food, weapons and the position of the other player
* MinMax Player (evaluates the next move in the same way as Heuristic Player but MinMax Player also uses a MinMax algorithm in order to minimise opponent's outcome and maximise theirs



<img width="1096" alt="Screenshot 2022-03-28 at 12 45 56 PM" src="https://user-images.githubusercontent.com/81708900/160375596-b041ba21-3b18-4ce3-a782-0439e737070b.png">
