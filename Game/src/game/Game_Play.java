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
    public int score = 0;
    private int bricks = 21;
    private Timer timer;
    private int delay =0;
    private int playerX = 300;
    private int ballX = 320;
    private int ballY = 390;
    private int ballXdir = -1;
    private int ballYdir = -2;
    public int totalLife =3;
     private BufferedImage backGround;
    private Image pop;
    private Image katch;
    private Image bigLeg;
    private Game_Bricks map;
    private Image wall;
    private Image win;
    private JFrame frame;

    public Game_Play(JFrame frame) throws URISyntaxException,IOException {

        this.frame = frame;

        map = new Game_Bricks(9, 15);
        File file = new File(getClass().getResource("assets/Background1.bmp").toURI());

        this.backGround = ImageIO.read(file);

        this.win = ImageIO.read(new File(getClass().getResource("assets/Congratulation.gif").toURI()));


        this.wall = ImageIO.read(new File(getClass().getResource("assets/Wall.gif").toURI()));
        this.bigLeg = ImageIO.read(new File(getClass().getResource("assets/Bigleg_small.gif").toURI()));

        this.katch = ImageIO.read(new File(getClass().getResource("assets/Katch.gif").toURI()));
       this.pop =ImageIO.read(new File(getClass().getResource("assets/Pop.gif").toURI()));
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(10, this);
        timer.start();

    }

    public void paint(Graphics g) {






            g.drawImage(this.backGround, 0, 0, null);

            try {
                wall(g);
                this.map.draw(g);
                g.drawImage(this.katch, playerX, 430, null);
                g.drawImage(this.pop, ballX, ballY, null);
                g.drawImage(this.bigLeg, 290, 20, 60, 60, null, null);


                g.dispose();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        if (ballY > 580 && start ){
            totalLife--;

                start = false;

            ballXdir = 0;
                ballYdir = 0;
            this.frame.setTitle("Score: " + score + " ||  " + "Lives: " + totalLife);

        }




    }



    public void actionPerformed( ActionEvent e){
        timer.start();
        if(start){
           if(new Rectangle(ballX,ballY, this.pop.getWidth(null), this.pop.getHeight(null)).intersects(new Rectangle(playerX, 430,this.katch.getWidth(null), this.katch.getHeight(null)))){
               ballYdir = -ballYdir;
           }
            if(new Rectangle(new Rectangle(ballX,ballY, this.pop.getWidth(null), this.pop.getHeight(null))).intersects(
                    new Rectangle (280, 20, 60, 60))){


            }


           A: for(int i =0; i < map.map.length; i ++){

               for(int j =0; j < map.map[0].length; j++){

                   if(map.map[i][j] > 0){

                       int brickX = j * map.brickWidth + 20;
                       int brickY = i * map.brickHeight + 20;
                       int brickWidth = map.brickWidth;
                       int brickHeight = map.brickHeight;
                       Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                       Rectangle ballRect = new Rectangle(ballX, ballY, this.pop.getWidth(null), this.pop.getHeight(null));
                       Rectangle lifeRect1 = new Rectangle(180,180, 40, 20);
                       Rectangle lifeRect2 = new Rectangle(340, 100, 40, 20);
                       Rectangle lifeRect3 = new Rectangle(500, 20, 40, 20);
                       if(rect.intersects(ballRect)){
                           if(lifeRect1.intersects(ballRect) && map.map[8][4]!= 0) {
                               // map.setValue(0, 8, 4);
                               totalLife++;
                           }
                           if(!(detectCollision(i,j))) {
                               this.map.setValue(0, i, j);
                               bricks--;
                               score += 5;



                               this.frame.setTitle("Score: " + score + " ||  " + "Lives: " + totalLife);
                           }

                           if(ballX + 19 <= rect.x || ballX + 1 >= rect.x  + rect.width){
                                ballXdir = -ballXdir;
                           }
                           else{
                               ballYdir = -ballYdir;
                           }
                           break A;

                       }
                   }
               }

           }
            ballX +=ballXdir;
            ballY +=ballYdir;

            if(ballX < 20){
                ballXdir = -ballXdir;

            }
            if(ballY < 20){
                ballYdir = -ballYdir;

            }


            if(ballX > 580){
                ballXdir = -ballXdir;

            }




            repaint();

        }
    }

    public void keyTyped(KeyEvent e){

    }


    public void keyPressed (KeyEvent e){

        if(e.getKeyCode() == KeyEvent.VK_RIGHT && ballY <=580){
            if(playerX >= 540){
                playerX = 540;
            }
            else {
                moveright();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT && ballY <= 580){
            if(playerX <= 20){
                playerX = 20;
            }
            else {
                moveleft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!start && totalLife > 0){
                start = true;
                ballX= 320;
                ballY = 390;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 300;




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

    public void wall(Graphics g){
        int x =0;
        int y =0;
        int height = this.wall.getHeight(null);
        int width = this.wall.getWidth(null);

   while(y < 640){


           g.drawImage(this.wall, 0, y, null);
           y = y + height;


      }

      while(x < 620){
          g.drawImage(this.wall, x, 0, null);
          x = x + width;
      }
      y =0;

   while(y < 640){
       g.drawImage(this.wall, x, y, null );
       y = y +20;
   }


  }
    public boolean detectCollision(int i , int j) {

        if(i==8 && j ==3 || i==7 && j ==3 || i==6 && j==3 || i==5 && j ==3 || i==4 && j ==3 || i==3 && j==3 || i==2 && j ==3 || i==1 && j==3 ||
                i==0 && j ==3 || i==0 && j==10 || i==1 && j ==10 || i==2 && j==10 || i==3 && j ==10 || i==4 && j==10
                || i==5 && j ==10 || i==6 && j==10 || i ==7 && j==10 || i==8 && j ==10) {

            return true;
        }

        return false;
    }

}

