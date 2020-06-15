package Objetos;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javafx.scene.shape.Circle;


public abstract class Objetos {
    //Atributos
    protected Graphics2D objeto;
    protected int alto;
    protected  int ancho;
    public int x,y; // Variables encargadas de determinar el lugar en que estará pintado el 'objeto'
    public double dirX, dirY; // Variables encargadas de determinar la velocidad en que se mueve el objeto
   
    public Objetos(){
        
    }
    public abstract  void mover();
    public abstract void dibujar(Graphics2D obj);
    //public abstract boolean colision();
    public abstract Rectangle hitBox();
    

    
}
