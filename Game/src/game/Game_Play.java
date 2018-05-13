package game;


import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


public class Game_Play extends JPanel implements KeyListener, ActionListener {

    private boolean start = false;
    private int score = 0;
    private int bricks = 21;
    private Timer timer;
    private int delay =0;
    private int playerX = 300;
    private int ballX = 320;
    private int ballY = 390;
    private int ballXdir = -1;
    private int ballYdir = -2;
     private BufferedImage backGround;
    private Image pop;
    private Image katch;

    public Game_Play() throws URISyntaxException,IOException {
        File file = new File(getClass().getResource("assets/Background1.bmp").toURI());

        this.backGround = ImageIO.read(file);



       this.katch = ImageIO.read(new File(getClass().getResource("assets/Katch.gif").toURI()));
       this.pop =ImageIO.read(new File(getClass().getResource("assets/Pop.gif").toURI()));
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(10, this);
        timer.start();

    }

    public void paint(Graphics g){


           g.drawImage(this.backGround, 0, 0, null);
           g.drawImage(this.katch, playerX, 430, null);
           g.drawImage(this.pop, ballX, ballY, null);
           g.dispose();


    }



    public void actionPerformed( ActionEvent e){
        timer.start();
        if(start){
           if(new Rectangle(ballX,ballY, this.pop.getWidth(null), this.pop.getHeight(null)).intersects(new Rectangle(playerX, 430,this.katch.getWidth(null), this.katch.getHeight(null)))){
               ballYdir = -ballYdir;
           }
            ballX +=ballXdir;
            ballY +=ballYdir;
            if(ballX < 0){
                ballXdir = -ballXdir;

            }
            if(ballY < 0){
                ballYdir = -ballYdir;

            }
            if(ballX > 560){
                ballXdir = -ballXdir;

            }

        }
        repaint();
    }

    public void keyTyped(KeyEvent e){

    }


    public void keyPressed (KeyEvent e){

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 560){
                playerX = 560;
            }
            else {
                moveright();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 10){
                playerX = 10;
            }
            else {
                moveleft();
            }
        }

    }

    public void keyReleased (KeyEvent e){

    }

    public void moveright(){
        this.start = true;
        playerX += 20;
    }

    public void moveleft(){
        this.start = true;
        playerX -=20;
    }
}

