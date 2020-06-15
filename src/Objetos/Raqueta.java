package Objetos;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author mijum
 */
public class Raqueta extends Objetos {
    
    private Image paddle;
    public int vida;
    private Rectangle hitbox;
    
    
    public Raqueta(int x, int y, Image img){
       vida = 3;
       this.x = x;
       this.y = y;
       this.paddle = img;
       this.ancho = 80;
       this.alto = 20;
       hitbox = new Rectangle(this.x, this.y, ancho, alto);
    }
    public Raqueta(int x, int y, Image img, int v){
       this.vida = v;
       this.x = x;
       this.y = y;
       this.paddle = img;
       this.ancho = 80;
       this.alto = 20;
       hitbox = new Rectangle(this.x, this.y, ancho, alto);
    }

    @Override
    public void mover() {
        if(x + dirX > 15 && x + dirX < 792-ancho){
            x+=dirX;
        }
        this.hitbox = new Rectangle(this.x,this.y, alto, ancho);
    }
    
    public void mover(int i){
        if(i + dirX > 15 && i + dirX < 792-ancho){
            x=i;   
        }
        this.hitbox = new Rectangle(i,this.y, alto, ancho);
    }

    @Override
    public void dibujar(Graphics2D obj) {
        obj.drawImage(this.paddle,x,y,ancho,alto,null);
    }

    @Override
    public Rectangle hitBox() {
        return new Rectangle(this.x, this.y, ancho, alto);
    }
}
