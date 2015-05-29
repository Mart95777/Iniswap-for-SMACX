# Iniswap-for-SMACX
Java application for modifying "Alpha Centauri.ini" file for SMACX

1. What is Iniswap
2. Requirements
3. Installation
4. How to use

1. What is Iniswap.
=
Iniswapj, starting from version 1.0.0, is a Java application for solving problem of faction graphics bug in Sid Meier's Alpha Centauri game. After creation of the expansion, Alien Crossfire, more than original 7 factions could be played (without special modifications), however, the set of faction names was not used in a proper manner. When playing multiplayer game, game loading disregards faction names for the purpose of graphics and uses what is presently declared in 'Alpha Centauri.ini' file. This can be noticed when playing two or more games with different sets of factions.
IniswapJ application allows a player to set appropriate factional set in required order in Alpha Centauri.ini file, and launch the expansion game.
Iniswap was previously written in C++ and the last released version was 2.2.

2. Requirements
=
IniswapJ, requires installed Java Runtime Environment (JRE). Version 8.

3. Installation
=
Place the IniswapJ.1.0.0.jar file in the game folder, where your 'terranx.exe' and 'Alpha Centauri.ini' files are located.
Start the application by double click on the jar file.
You can also create a shortcut to the jar file and place it in a chosen location for easier access, for example your 'Desktop.'

4. How to use IniswapJ.
=
Start the application by double click on the jar file, or by clicking a shortcut, that you previously created.
IniswapJ is using a single frame (window). The elements, starting from top are:
- Labels of 'present factions in ini file' and 'factions to be inserted'
- List of factions, on the left, currently present in ini file, and on the right, to be inserted when launching the game. The right list can be empty in case you did not select any factions for substitution.
- Label "Give name for the new set:" Below is the text box where you can type a name for a new set for storing in a separate file in Iniswap3 folder. See button "Add existing set..."
- To the left is scrollable list box, where stored faction sets names are listed. You can select one and the factions from this set will be displayed in the top right corner list box, these are "Factions to insert into 'ini' file"
- Below list of factions to be inserted, on the right, is the list of executables. They need to start from "terranx" name to be there. The game has more executables, therefore, name your optional game executables (patched ones) by extending "terranx" name to have them on that list. In case you have only one such file, the file will be automatically selected on the list when launching IniswapJ.
- Button "Add existing set to the list" This adds faction set currently in the Alpha Centauri.ini file to the sets of factions. They will be stored in the ini_factions_sets.txt file for future use. To have a distinct name for this set, enter the name in the text box above.
- Button "Remove selected set from the list" This will remove selected set from the list and the set will not be further stored in ini_factions_sets.txt file.
- (Not implemented yet!!!) Button "Save changes only and exit" This will save ini_factions_sets.txt file with changes, like added or removed sets and also will replace faction set in Alpha Centauri.ini file, if selected for substitution.
- Button "PLAY selected set and exe" This will save files ini_factions_sets.txt and Alpha Centauri.ini files with appropriate changes and will launch the game with selected executable file.
