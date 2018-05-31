# SEGUNDO PROJECTO SDIS - 'Naval Battle'

Jogo de Batalha Naval multiplayer.

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
