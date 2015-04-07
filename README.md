# Iniswap-for-SMACX
Java application for modifying "Alpha Centauri.ini" file for SMACX

1. What is Iniswap
2. Requirements
3. Installation
4. How to use

1. What is Iniswap.
=
Iniswap, starting from version 2.4, is a Java application for solving problem of faction graphics bug in Sid Meier's Alpha Centauri game. After creation of the expansion, Alien Crossfire, more than original 7 factions could be played (without special modifications), however, the set of faction names was not recorded into game save files. Although factional parameters are recorded in the save file, for the purpose of faction graphics, the game uses what is presently declared in 'Alpha Centauri.ini' file, what does not need to be matching player's current factional set. This can be noticed when playing two or more games with different sets of factions.
Iniswap application allows for a player to set appropriate factional set in required order, and launch the expansion game.

2. Requirements
=
Iniswap, from version 2.4, requires installed Java Runtime Environment (JRE). Version 8.

3. Installation
=
Place the jar file .... in the game folder, where your 'terranx.exe' and 'Alpha Centauri.ini' files are located.
Start the application by double click on the jar file.
You can also create a shortcut to the jar file and place it in a chosen location for easier access, for example your 'Desktop.'

4. How to use Iniswap.
=
Start the application by double click on the jar file, or by clicking a shortcut, that you previously created.
Iniswap is using only one frame (window), the elements, starting from top left corner, are:
- Labels of 'present factions in ini file' and 'factions to be inserted'
- List of factions, on the left, currently present, and on the right, to be inserted. The right list can be empty in case you did not declare any factions for substitution.
- ...
