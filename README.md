# SEGUNDO PROJECTO SDIS - 'Naval Battle'

Jogo de Batalha Naval multiplayer.

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
$ java Main server 8080
```

To start the Player, use the following command as an example:
```
$ java Main player 192.168.1.78 8080
```
