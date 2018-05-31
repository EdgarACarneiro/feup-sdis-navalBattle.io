[![BCH compliance](https://bettercodehub.com/edge/badge/EdgarACarneiro/feup-sdis-navalBattle.io?branch=master&token=cc268dedf0e5bd6570ae8c2935ad72a14cdeff32)](https://bettercodehub.com/)

# Second Project Distriuted Systems - 'NavalBattle.io'

A naval battle game implemented with a client-server tipology that follows the idea of the modern .io games: the game has no beggining nor end, it is always running. To know more about the game clone the repository and see the [documentation](https://github.com/EdgarACarneiro/feup-sdis-navalBattle.io/blob/master/navalBattle/docs/report%20(1).pdf). 

## Build Tips (Eclipse/IntelliJ)

During our development we used both Eclipse and IntelliJ for their easier use of run configurations, and other Full IDEs adavantages.

To build on these IDEs it is just needed to create a project from the folder and then defining Main.java as our main class.

## Build Tips (Terminal)
```
# Linux
$ find -name "*.java" > sources.txt
$ javac @sources.txt

:: Windows
> dir /s /B *.java > sources.txt
> javac @sources.txt
```

## Running

To start a Server, use the following command as an example:
```
$ java Main game 8080
OR
In and IDE add arguments: game 8080
```

To start the Player, use the following command as an example:
```
$ java Main player 192.168.1.78 8080
OR
In and IDE add arguments: player 192.168.1.78 8080
```
