
package game;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game extends JFrame {


    public static void main(String[] args) throws IOException, URISyntaxException {

        JFrame game = new JFrame();
        Game_Play game_play = new Game_Play(game);
        game.setTitle ("Score: " + game_play.score + " ||  Lives: " + game_play.totalLife);
        game.add(game_play);
        game.setBounds(10, 10,640, 480);
        game.setResizable(false);
        game.setVisible(true);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
    
}
