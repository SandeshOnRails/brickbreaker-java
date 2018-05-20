package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;


public class Game_Bricks {
 public int map[][];
 public int brickWidth;
 public int brickHeight;

 Game_Bricks(int row, int col) throws URISyntaxException, IOException{
     map = new int[row][col];
     for(int i =0; i < map.length; i ++){
         for(int j =0; j < map[0].length; j++){
             map[i][j] =1;
         }
     }
     brickWidth = 40;
     brickHeight = 20;


 }

 public void draw (Graphics g) throws URISyntaxException, IOException{

     Random rand = new Random();

     int imageValue = rand.nextInt(7)+1;
     Image brick = ImageIO.read(new File(getClass().getResource("assets/Block1.gif").toURI()));
     Image brick2 = ImageIO.read(new File(getClass().getResource("assets/Block2.gif").toURI()));
     Image brick3 =  ImageIO.read(new File(getClass().getResource("assets/Block3.gif").toURI()));
     Image brick4 =  ImageIO.read(new File(getClass().getResource("assets/Block4.gif").toURI()));
     Image brick5 =  ImageIO.read(new File(getClass().getResource("assets/Block5.gif").toURI()));
     Image brick6 =  ImageIO.read(new File(getClass().getResource("assets/Block6.gif").toURI()));
     Image brick7 =  ImageIO.read(new File(getClass().getResource("assets/Block7.gif").toURI()));
     Image life =    ImageIO.read(new File(getClass().getResource("assets/Block_life.gif").toURI()));
     Image solid =  ImageIO.read(new File(getClass().getResource("assets/Block_solid.gif").toURI()));




     int x = 20;
     int y = 20;
     for(int i =0; i < map.length; i ++){
         for(int j =0; j < map[0].length; j++){
             if(map[i][j] > 0){
                 if(detectCollision(i, j)){

                     g.drawImage(solid, j * brickWidth + 20, i * brickHeight + 20, brickWidth, brickHeight, null);

                 }
                 else if (bigLegRectangle(i, j)){

                 }
                 else if(j== 2){
                     g.drawImage(brick2, j * brickWidth + 20, i * brickHeight + 20, brickWidth, brickHeight, null);

                 }
                 else if (j ==4){

                     if(i == 8 && j ==4){

                         g.drawImage(life, j * brickWidth + 20, i * brickHeight + 20, brickWidth, brickHeight, null);


                     }
                     else {
                         g.drawImage(brick3, j * brickWidth + 20, i * brickHeight + 20, brickWidth, brickHeight, null);
                     }
                 }

                 else if ( j == 6){

                     g.drawImage(brick4, j * brickWidth + 20, i * brickHeight + 20, brickWidth, brickHeight, null);

                 }
                 else if (j == 8){

                     if(i ==4 && j ==8){
                         g.drawImage(life, j * brickWidth + 20, i * brickHeight + 20, brickWidth, brickHeight, null);

                     }
                     else {
                         g.drawImage(brick5, j * brickWidth + 20, i * brickHeight + 20, brickWidth, brickHeight, null);
                     }
                 }
                 else if (j == 10){
                     g.drawImage(brick6, j * brickWidth + 20, i * brickHeight + 20, brickWidth, brickHeight, null);

                 }
                 else if (j == 12){

                     if (i ==0 && j ==12){
                         g.drawImage(life, j * brickWidth + 20, i * brickHeight + 20, brickWidth, brickHeight, null);


                     }
                     else {


                         g.drawImage(brick7, j * brickWidth + 20, i * brickHeight + 20, brickWidth, brickHeight, null);
                     }
                 }
                 else {
                     g.drawImage(brick, j * brickWidth + 20, i * brickHeight + 20, brickWidth, brickHeight, null);
                 }

             }
         }
     }

     }


    public void setValue(int value, int row, int col){
        this.map[row][col]= value;
    }

    public boolean detectCollision(int i , int j){

        if(i==8 && j ==3 || i==7 && j ==3 || i==6 && j==3 || i==5 && j ==3 || i==4 && j ==3 || i==3 && j==3 || i==2 && j ==3 || i==1 && j==3 ||
                i==0 && j ==3 || i==0 && j==10 || i==1 && j ==10 || i==2 && j==10 || i==3 && j ==10 || i==4 && j==10
                || i==5 && j ==10 || i==6 && j==10 || i ==7 && j==10 || i==8 && j ==10) {

            return true;
        }

     return false;
    }

    public boolean bigLegRectangle(int i, int j ){

     if(i==0 && j ==6 || i==0 && j==7 || i ==0 && j==8 || i==1 && j ==6 || i==1 && j==7 || i ==1 && j==8 ||
             i==2 && j ==6 || i==2 && j==7 || i ==2 && j==8) {
         map[i][j]= 0;
         return true;
     }
     return false;
    }
}
