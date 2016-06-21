/**
 * Palette.java		May 22, 2016, 2:23:43 PM
 *
 * -
 */
package komorebi.clyde.editor;

import komorebi.clyde.engine.Animation;
import komorebi.clyde.engine.MainE;
import komorebi.clyde.engine.Playable;
import komorebi.clyde.map.Tile;
import komorebi.clyde.map.TileList;
import komorebi.clyde.states.Editor;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 * The current palette to choose from
 * 
 * @author Aaron Roy
 * @version 0.0.1.0
 */
public class Palette implements Playable{

  private static final int HEIGHT = 16;
  private static final int WIDTH = 4;

  //Holds current palette tiles
  private Tile[][] tiles = new Tile[HEIGHT][WIDTH]; 
                                            
  //Offset of palette in tiles
  public static int xOffset = Display.getWidth()/(MainE.scale*16) - WIDTH;
  public static int yOffset = Display.getHeight()/(MainE.scale*16) - HEIGHT;
                                  
  //Selector X and Y, in tiles
  private int selX = xOffset, selY = yOffset + HEIGHT-1;  

  //The Selector itself
  private Animation selection;              

  //Removes repeated input
  private boolean lButtonWasDown, lButtonIsDown;//Left Click
  private boolean rButtonIsDown, rButtonWasDown;//Right Button Clicked



  /**
   * Creates a blank palette
   */
  public Palette(){
    for (int i = tiles.length-1, k=0; i >= 0; i--) {
      for (int j = 0; j < tiles[0].length; j++, k++) {
        tiles[i][j] = new Tile(j+xOffset, i + yOffset, TileList.getTile(k));
      }
    }
    
    System.out.println("SelX: "+selX + ", SelY: "+selY);

    selection = new Animation(8, 8, 16, 16, 2);
    for(int i=3; i>=0; i--){
      selection.add(0 , 0 , i);
      selection.add(16, 0 , i);
    }
  }

  /**
   * Gets clicks and tells what tile was clicked
   */
  @Override
  public void getInput() {
    lButtonWasDown = lButtonIsDown;
    lButtonIsDown = Mouse.isButtonDown(0);
  }

  /* (non-Javadoc)
   * @see komorebi.clyde.engine.Renderable#update()
   */
  @Override
  public void update() {
    if (checkBounds() && lButtonIsDown && !lButtonWasDown) {
      selX = (Mouse.getX() / (16 * MainE.getScale()));
      selY = (Mouse.getY() / (16 * MainE.getScale()));
    }

  }
  
  /**
   * Renders everything in tiles and selection box
   */
  @Override
  public void render(){
    for(Tile[] t : tiles){
      for (Tile tile : t) {
        tile.render();
      }
    }

    selection.play(selX*16, selY*16);
  }

  public Tile getSelected(){
    return tiles[selY-yOffset][selX-xOffset];
  }

  public void setLoc(TileList tl) {
    for (int i = 0; i < tiles.length; i++) {
      for (int j = 0; j < tiles[0].length; j++) {
        if (tl == tiles[i][j].getType()) {
          selX = j+xOffset;
          selY = i+yOffset;
        }
      }
    }
  }
  
  /**
   * Reloads the Palette
   */
  public void reload(){
      xOffset = Display.getWidth()/(MainE.scale*16) - 4;    
      yOffset = Display.getHeight()/(MainE.scale*16) - 14;  
      selX = (int)(xOffset*Editor.xSpan);
      selY = (int)(yOffset*Editor.ySpan);
      
      System.out.println("SelX: "+selX + ", SelY: "+selY);
      
      for (int i = tiles.length-1, k=0; i >= 0; i--) {
          for (int j = 0; j < tiles[0].length; j++, k++) {
              tiles[i][j].move(j+xOffset, i + yOffset);
          }
      }

  }
  
  /**
   * Checks if the Mouse is in bounds of the map
   * @return Mouse is in map
   */
  private boolean checkBounds() {
      return (Mouse.getX()/MainE.getScale()>=Palette.xOffset*16 &&
              Mouse.getY()/MainE.getScale()>=Palette.yOffset*16);
  }
  
  /**
   * Converts Mouse X into a tile index, adjusting for map position
   * @return adjusted mouse x
   */
  private int getMouseX(){
      return (Mouse.getX()/MainE.getScale())/(16);
  }

  /**
   * Converts Mouse Y into a tile index, adjusting for map position
   * @return adjusted mouse y
   */
  private int getMouseY() {
      return (Mouse.getY()/MainE.getScale())/(16);
  }



}
