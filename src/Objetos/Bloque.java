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
public class Bloque extends Objetos {
    
    private Image textura;
    private int vida;
    private ArrayList<Rectangle> hitboxs;
    
    public Bloque(){
        changeColor(this.vida);
    }

    public synchronized ArrayList<Rectangle> getHitboxs() {
        return hitboxs;
    }
    
    public Bloque(int l, int x, int y){
        hitboxs = new ArrayList<>();
        this.vida = l;
        this.x = x;
        this.y = y;
        this.alto = 30;
        this.ancho = 80;
        changeColor(this.vida);    
        hitboxs.add(new Rectangle(x+2,y,78,2));
        hitboxs.add(new Rectangle(x+2,y+28,78,2));
        hitboxs.add(new Rectangle(x,y+2,2,28));
        hitboxs.add(new Rectangle(x+78,y+2,2,28));
    }
    
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
        changeColor(vida);
    }
    
    @Override
    public void mover() {
        System.out.println("Yo no me muevo, compa xd");
    }

    @Override
    public void dibujar(Graphics2D obj) {
        obj.drawImage(textura,this.x,this.y,ancho,alto, null);
    }

    @Override
    public Rectangle hitBox() {
        return new Rectangle(x,y,ancho-10,alto);
    }
        public boolean rompemepues(Bloque x){
        if(x.vida==-1){ return true; }
        else{
            System.out.println("algo");
            x.vida--; changeColor(x.vida);
            return false;
        }
    }

    private void changeColor(int vida) {
        switch(vida){
            case 0:
                textura = new ImageIcon("assets/Bloques/rojo.png").getImage();
                break;
            case 1:
                textura = new ImageIcon("assets/Bloques/naranja.png").getImage();
                break;
            case 2:
                textura = new ImageIcon("assets/Bloques/verde.png").getImage();
               // System.out.println("Velde");
                break;
        }

    }

   
    
}
