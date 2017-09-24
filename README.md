# maze-explorer
Finds the way through a given maze and tracks the path

# how to run
<code>
  
  Maze maze = new Maze(char[][] maze);
  
  Explorer explorer = new Explorer(maze);
  
  explorer.exploreMaze();
  
  String routeString = explorer.getMovementString();

</code>
