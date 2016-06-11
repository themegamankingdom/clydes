/**
 * Editor.java		May 16, 2016, 10:03:58 PM
 *
 * -
 */
package komorebi.clyde.states;

import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;
import komorebi.clyde.editor.Palette;
import komorebi.clyde.map.Map;

import org.lwjgl.opengl.Display;

/**
 * Represents the level editor
 * 
 * @author Aaron Roy
 * @version 
 */
public class Editor extends State{
    
    private Map map;
    private static Palette pal;
    public static float aspect;
    public static float xSpan = 1;
    public static float ySpan = 1;

    
    public Editor(){
        pal = new Palette();
        map = new Map(20, 20);
    }
    
    
    /* (non-Javadoc)
     * @see komorebi.clyde.states.State#getInput()
     */
    @Override
    public void getInput() {
        if(Display.wasResized())resize();
        pal.getInput();
        map.getInput();
    }

    /* (non-Javadoc)
     * @see komorebi.clyde.states.State#update()
     */
    @Override
    public void update() {
        // TODO Auto-generated method stub
        pal.update();
        map.update();
    }

    /* (non-Javadoc)
     * @see komorebi.clyde.states.State#render()
     */
    @Override
    public void render() {
        map.render();
        pal.render();
    }


    /**
     * Returns the palette so it can be used by the map
     * 
     * @return pal
     */
    public static Palette getPalette() {
        // TODO Auto-generated method stub
        return pal;
    }
    
    /**
     * Resizes the window
     */
    private static void resize() {
        final int height = Display.getHeight();
        final int width = Display.getWidth();
        aspect = (float)width/height;
        xSpan = 1;
        ySpan = 1;
        
        if(aspect > 1){
            xSpan *= aspect;
        }else{
            ySpan = xSpan/aspect;
        }
        glViewport(0, 0, width, height);

        glLoadIdentity();
        glOrtho(0,width,0,height,-1,1);     //Updates 3D space
//        glOrtho(-xSpan,xSpan,-ySpan,ySpan,-1,1);     //Updates 3D space
        pal.reload();
    }


}
