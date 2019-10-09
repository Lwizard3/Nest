<pre>
  ___   _   _   ______    _____   _______   ___ 
 |  _| | \ | | |  ____|  / ____| |__   __| |_  |
 | |   |  \| | | |__    | (___      | |      | |
 | |   | . ` | |  __|    \___ \     | |      | |
 | |   | |\  | | |____   ____) |    | |      | |
 | |_  |_| \_| |______| |_____/     |_|     _| |
 |___|                                     |___|
</pre>
------------------------------------------------

Welcome to the 589 utility software!

check out the Source folder for the code and the Final folder for the compiled jar

## Changes:

10/09/19: (Pathfinding!!)

 - All the algorithms utilized in pathfinding have been implemented
 
 - Socket was created, allows Pathfinding window to do pathfinding
 
 - Open a field to see the preprogrammed pathfinding run
 
 - Converting images to a map still needs to be done
 
 - Smothing algorithm needs to be created, currently the path has points on a line which cubic splines does not like, which is why lines are not being drawn properly

09/22/19: (hopefully stable)

 - Small update here, just a stable build before I begin new work

09/15/19: (The pretty color update)

 - Flux Class added!
 
 - This is a class that lets you cycle through the colors of the rainbow.  Create a new Flux and call Flux() to get the next color in the cycle, or use Flux(int) to get that many colors down the cycle
  
 - Path drawing added, the FieldCanvas now holds a list of paths which they will draw