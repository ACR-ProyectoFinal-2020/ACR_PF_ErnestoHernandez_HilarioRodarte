/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author mijum
 */
public class Pelota extends Objetos{
    private Image pelota;
    private ArrayList<Rectangle> Hitboxes = new ArrayList<>();
    
    public Pelota(int x, int y){
      this.alto=25;
      this.ancho=25;
      
      this.x=x;
      this.y=y;
      
      this.dirX=1; 
      this.dirY=-1;
      
      pelota = new ImageIcon("Bolita.png").getImage();
      
      Hitboxes.add(new Rectangle((int)x+3,(int)y+3,19,19));
      Hitboxes.add(new Rectangle((int)x+1,(int)y+5,23,15));
      Hitboxes.add(new Rectangle((int)x+5,(int)y+1,15,23));
      Hitboxes.add(new Rectangle((int)x+12,(int)y,1,25));
      Hitboxes.add(new Rectangle((int)x,(int)y+12,25,1));
      
    }
        public boolean colision(Rectangle A) {
        for (int i = 0; i < Hitboxes.size(); i++) {
            if(A.intersects(Hitboxes.get(i))) return true;
        }
        return false;
    }
    public void updateHitBox(){
        Hitboxes.clear();
        Hitboxes.add(new Rectangle((int)x+6,(int)y,3,15));
        Hitboxes.add(new Rectangle((int)x,(int)y+6,15,3));
        Hitboxes.add(new Rectangle((int)x+2,(int)y+2,11,11));
        Hitboxes.add(new Rectangle((int)x+4,(int)y+1,7,13));
        Hitboxes.add(new Rectangle((int)x+1,(int)y+4,13,7));
    }
    
    @Override
    public void mover() {
        this.x+=dirX;
        this.y+=dirY;
        updateHitBox();
    }

    @Override
    public void dibujar(Graphics2D obj) {
        obj.drawImage(pelota, this.x, this.y, this.alto, this.ancho,null);
    }

    @Override
    public Rectangle hitBox() {
        return new Rectangle(x,y,alto,ancho);
    }
        
}
