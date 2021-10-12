# Game of three
## Specifications
* Java 11
* Spring 5

## API documentation

### Create game
Create a player with name sent. 
Create a new game with a new UUID, status IDLE and set player 1. 
Create a new move with number sent and save in memory repository.

POST http://localhost:8080/game-of-three/game

<code>
{
"name": "Bob",
"number": 51
}
</code>

### Join Game
Find a game with UUID sent and status IDLE. 
Set name as player 2.
Change the status of the game as IN_PROGRESS
Update nextPlayer

POST http://localhost:8080/game-of-three/join-game

<code>
{
    "name": "Nick",
    "gameId": "e01d8a73-859f-458a-82af-5b6f4b37108c"
}
</code>

### Join Game randomly
Find any game inserted with status IDLE.
Set player 2 of game with name.
Change the status of the game as IN_PROGRESS.
Update nextPlayer

POST http://localhost:8080/game-of-three/join-random-game

<code>
{
"name": "Nick"
}
</code>

### Move
Create a new move with the information sent.
Process the operation to get the new number.
Save move in history of moves in game object
Update nextPlayer.
Validate if game is complete or not.
Save game in repository

POST http://localhost:8080/game-of-three/move

### List of games in memory
List all the games in repository

GET http://localhost:8080/game-of-three/list

## Instructions

1. Run $ ./mvnw spring-boot:run
2. Go to http://localhost:8080/ as window #1
3. Add name and insert the first number you want to use. Then click on "Create" button. The page will show "Connected to game #: [GAME ID]

4. Open a new window #2 and go to http://localhost:8080/
5. You have two options: You can join a game by provide the GAME_ID shown in step three and click on Join or You can click directly on join any IDLE game to connect to a random game with status IDLE.
6. After connecting to a game in windows #2, you will see the first number sent by the opponent player 1. Click on -1, 0 or 1 buttons to make your move.

7. Windows #1 will show the number sent by player two and will let make your move.
8. Game will finish and show the text "Game over. The winner is: [PLAYER]" when the result of compute the numbers is 1.

## Postman API collection
https://github.com/diegorl84/game-of-three/blob/master/TakeAway%20API%20-%20Game%20of%20three.postman_collection.json

## Localhost test
Please use this video that explain how use the web app:

https://github.com/diegorl84/game-of-three/blob/master/www_screencapture_com_2021-10-11_19_22.mp4

