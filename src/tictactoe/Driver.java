/*
 * Copyright 2015 Sam W. <wngsam@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Sam W. <wngsam@gmail.com>
 */

public class Driver extends Application {
    
    private static Game game;
    private static Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        Driver.primaryStage = primaryStage;
        primaryStage.setTitle("Tic-Tac-Toe");
        refresh();
    }
    
    public void refresh(){
        Scene scene = new Scene(createBoard(), 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void gameEnd(){
        GridPane root = new GridPane();
        Boolean[] gameState = game.getBoard();
        for(int i=0; i<gameState.length; i++){
            Button btn = new Button();
            if(gameState[i]==null){
                btn.setText(" ");
            }else if(gameState[i]){
                btn.setText("X");
            }else{
                btn.setText("O");
            }
            if(i<3){
                root.add(btn,i,0);
            }else if(i<6){
                root.add(btn,i-3,1);
            }else{
                root.add(btn,i-6,2);
            }
        }
        System.out.println("GAME OVER!"+" USER WON = "+game.getUserWon());
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public GridPane createBoard(){
        GridPane root = new GridPane();
        
        Boolean[] gameState = game.getBoard();
        
        for(int i=0; i<gameState.length; i++){
            
            Button btn = new Button();
            if(gameState[i]==null){
                btn.setText(" ");
                final int x = i;
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        game.playerMove(x);
                        if(game.checkWin()){
                            game.setUserWon(true);
                            gameEnd();
                        }else{
                            game.cpuMove();
                            if (game.checkWin()) {
                                game.setUserWon(false);
                                gameEnd();
                            }else{
                                refresh();
                            }
                        }
                    }

                });
            }else if(gameState[i]){
                btn.setText("X");
            }else{
                btn.setText("O");
            }
            if(i<3){
                root.add(btn,i,0);
            }else if(i<6){
                root.add(btn,i-3,1);
            }else{
                root.add(btn,i-6,2);
            }
            
        }
        
        return root;
    }
    
    public static void main(String[] args) {
        game = new Game();
        launch(args);
    }
    
}
