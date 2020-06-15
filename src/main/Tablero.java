
package main;

import Conexion.Conexion;
import Objetos.Pelota;
import Objetos.Raqueta;
import Objetos.Bloque;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Yo mero
 */
public class Tablero extends JPanel{
    private Image fondo = new ImageIcon("assets/Fondos/fondo.png").getImage(); 
    private Image pausa = new ImageIcon("assets/Fondos/pausa.png").getImage();
    private Image red = new ImageIcon("assets/Fondos/winred.png").getImage();
    private Image blue = new ImageIcon("assets/Fondos/winblue.png").getImage();
    private Color x=Color.yellow;
    private Pelota pelota;
    private Raqueta raqueta_blue,raqueta_red;
    private ArrayList<Bloque> bloques = new ArrayList<>();
    private Rectangle Top, Bottom, Left, Right;
    public boolean isPaused,winred,winblue;
    private int tam;
    private Conexion con;
    
    public int getTam() {
        return tam;
    }
    
    public Tablero(){
        
        isPaused = false;
        pelota = new Pelota(425,735);
        //Sapwn sobre raqueta roja 425,735
        //Sapwn sobre raqueta azul 425,60
        winred = false;
        winblue = false;
        raqueta_blue = new Raqueta(400,40, new ImageIcon("assets/paddle_blue.png").getImage());
        raqueta_red = new Raqueta(400,760, new ImageIcon("assets/paddle_red.png").getImage());
        
        this.tam = 800;
        
        Top = new Rectangle(0, 0,tam,5);
        Bottom = new Rectangle(0, 800,tam,5);
        Left = new Rectangle(0, 0,5,tam);
        Right = new Rectangle(795, 0,5,tam);
        generarBloques();
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                lkeyPressed(ke);
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                lkeyReleased(ke);
            }
		});
		setFocusable(true);
            con = new Conexion();
           if (!con.connect()) con.initializeServer();     
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(fondo, 0, 0, tam, tam, null);
        for(Bloque x: bloques){
            x.dibujar(g2d);
        }
        for (int i = 0,x=10; i < raqueta_red.vida; i++) {
            
            g.drawImage(new ImageIcon("assets/kokororojo.png").getImage(),x,770,22,22,null);
            x+=22;
        }
         for (int i = 0,x=716; i < raqueta_blue.vida; i++) {
            
            g.drawImage(new ImageIcon("assets/kokoroazul.png").getImage(),x,15,22,22,null);
            x+=22;
        }
        pelota.dibujar(g2d);
        raqueta_red.dibujar(g2d);
        raqueta_blue.dibujar(g2d);
        if(isPaused&&!winblue&&!winred)g.drawImage(pausa, 0, 0, tam, tam, null);
        else if(winblue)g.drawImage(blue, 0, 0, tam, tam, null);
        else if(winred)g.drawImage(red, 0, 0, tam, tam, null);
    }
    
    public void generarBloques(){
        int bidadeUva = 0;
       
        
        for (int i = 0, y=310; i < 6; i++,y+=30) {
            
            for (int j = 0,x=80; j < 8; j++,x+=80) {
                if (i==0 || i==5) {
                    bidadeUva=0;
                }
                else if (i==1 || i == 4) {
                    bidadeUva=1;
                }
                else if(i==2 || i==3){
                    bidadeUva=2;
                }
                Bloque bloque = new Bloque(bidadeUva,x,y);
                bloques.add(bloque);
            }
       }
        
    }
    public  void lkeyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT){ 
                    if (con.getEsRojo()) {
                        raqueta_red.dirX=-2;
                    }else{
                        raqueta_blue.dirX=-2;
                    }
                    
                }
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                    if (con.getEsRojo()) {
                        raqueta_red.dirX=2;
                    }else{
                        raqueta_blue.dirX=2;
                    } 
                }
                if (e.getKeyCode() == KeyEvent.VK_P){
                    isPaused ^= true;
                }
    }
    public void lkeyReleased(KeyEvent e) {
            if (con.getEsRojo()) {
                        raqueta_red.dirX=0;
                    }else{
                        raqueta_blue.dirX=0;
                    } 
                 
	}
    private void colisionBloques(){
        Iterator i = bloques.iterator();
        while(i.hasNext()){
            Bloque x = (Bloque)i.next();
            if (pelota.colision(x.getHitboxs().get(0))) {
                if (x.rompemepues(x)==true) {
                    i.remove();
                    pelota.dirY=-1;
                }
            }
            else if(pelota.colision(x.getHitboxs().get(1))){
                if (x.rompemepues(x)==true) {
                    i.remove();
                    pelota.dirY=1;
                }
            }
            else if(pelota.colision(x.getHitboxs().get(2))){
                if (x.rompemepues(x)==true) {
                    i.remove();
                    pelota.dirX=-1;
                }
            }
            else if(pelota.colision(x.getHitboxs().get(3))){
                if (x.rompemepues(x)==true) {
                    i.remove();
                    pelota.dirX=1;
                }
            }
            
            
        }          
            
    }
    private void colisionRaquetas(){
        if (pelota.colision(raqueta_blue.hitBox())) {
            pelota.dirY = 1;
        }
        else if(pelota.colision(raqueta_red.hitBox())){
            pelota.dirY = -1;
        }
    }
    private void colisionTablero(){
        if (pelota.colision(Top)) {
            
            if(raqueta_blue.vida==1){
                //GG
                winred = true;
            }else{
                System.out.println("Vida azul: "+raqueta_blue.vida);
                reset(false,raqueta_red.vida,raqueta_blue.vida-1);
            }
        }
        else if (pelota.colision(Bottom)) {
            
            if(raqueta_red.vida==1){
                //GG
                winblue = true;
            }else{
                System.out.println("Vida roja: "+raqueta_red.vida);
                reset(true,raqueta_red.vida-1,raqueta_blue.vida);
            }
        }
        else if (pelota.colision(Left)) {
            pelota.dirX = 1;
        }
        else if (pelota.colision(Right)) {
            pelota.dirX = -1;
        }
    }
    
    private synchronized void  gameStateRefresher(){
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
                            sendInfo();
                            recieveInfo();
			}
		},0, 1);
	}
    
    public void sendInfo(){
        //System.out.println("send");
        try {
            if (con.getEsRojo()) {
                con.getDos().writeInt(raqueta_red.x);
            }else{
                con.getDos().writeInt(raqueta_blue.x);
            }
            con.getDos().flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void recieveInfo(){
        //System.out.println("recive");
        try {
            if (con.getEsRojo()) {
                int i = con.getDis().readInt();
                raqueta_blue.mover(i);
                //System.out.println("recive");
            }else{
                raqueta_red.mover(con.getDis().readInt());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
                            
			
    }
    public void ejecucion(){
        sendInfo();
        recieveInfo();
        colisionBloques();
        colisionTablero();
        colisionRaquetas();
        pelota.mover();
        raqueta_blue.mover();
        raqueta_red.mover();
    }
    
    public void reset(boolean red,int r,int b){
        //Sapwn sobre raqueta roja 425,735
        //Sapwn sobre raqueta azul 425,60
        if(red){
            this.pelota = new Pelota(425,735);
            
        }else
        {
            this.pelota = new Pelota(425,60);
        }
        System.out.println("aber: "+x);
        raqueta_blue = new Raqueta(400,40, new ImageIcon("assets/paddle_blue.png").getImage(),b);
        raqueta_red = new Raqueta(400,760, new ImageIcon("assets/paddle_red.png").getImage(),r);
        isPaused=true;
    }
    
    public static void main(String[] args) throws InterruptedException {
        JFrame xd = new JFrame();
        Tablero alola = new Tablero();
        xd.add(alola);
        xd.setSize(alola.tam+6,alola.tam+35);
        xd.setVisible(true);
        xd.setResizable(false);
        xd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while(true){
            if (!alola.con.getAccepted()) {
                alola.con.listenForServerRequest();
                //alola.gameStateRefresher();
            }
            /*if(alola.con.getIsClient()){
                alola.gameStateRefresher();
                alola.con.setIsClient(Boolean.FALSE);
            }*/
            if(!alola.isPaused)alola.ejecucion();
            alola.repaint();
            Thread.sleep(5);
        }
        
    }
}
