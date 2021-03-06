/*
 * GameHandler.java     Apr 27, 2016, 8:28:43 PM
 */

package komorebi.clyde.engine;

import komorebi.clyde.states.Game;
import komorebi.clyde.states.Menu;
import komorebi.clyde.states.Pause;
import komorebi.clyde.states.State.States;

/**
 * Updates, renders and gets input depending on the current state
 * 
 * @author Aaron Roy
 * @version 0.0.1.0
 * 
 */
public class GameHandler implements Playable{

  public static States state;

  public static Game game;
  private static Menu menu;
  private static Pause pause;

  /**
   * Creates the GameHandler
   */
  public GameHandler(){
    state = States.GAME;
    game = new Game();
    menu = new Menu();
    pause = new Pause();
  }

  /**
   * @see komorebi.clyde.engine.Playable#getInput()
   */
  public void getInput() {
    switch(state){
      case GAME:
        game.getInput();
        break;
      case MENU:
        menu.getInput();
        break;
      case PAUSE:
        pause.getInput();
      default:
        break;
    }
  }

  /**
   * @see komorebi.clyde.engine.Playable#update()
   */
  public void update() {
    switch(state){
      case GAME:
        game.update();
        break;
      case MENU:
        menu.update();
        break;
      case PAUSE:
        game.update();
        pause.update();
      default:
        break;
    }
  }

  /**
   * @see komorebi.clyde.engine.Playable#render()
   */
  public void render() {
    switch (state) {
      case GAME:
        game.render();
        break;
      case MENU:
        menu.render();
        break;
      case PAUSE:
        game.render();
        pause.render();
      default:
        break;
    }
  }

  /**
   * Switches to a new state
   * 
   * @param nstate The state to switch to
   */
  public static void switchState(States nstate){
    if (nstate == States.PAUSE)
    {
      pause.reload();
    }
    state = nstate;
  }

}
