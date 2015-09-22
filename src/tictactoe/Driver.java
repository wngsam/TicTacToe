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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Sam W. <wngsam@gmail.com>
 */

public class Driver extends Application {
    
    private Game game;
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Tic-Tac-Toe");
        newGame();
    }
    
    public void newGame(){
        game = new Game();
        refresh(createBoard(false),"Playing");
    }
    
    public void refresh(GridPane board, String s){
        VBox vbox= new VBox();
        
        Text t = new Text("TIC-TAC-TOE");
        
        BorderPane border = new BorderPane();
        
        border.setPadding(new Insets(10, 10, 10, 10)); //TOP,RIGHT,BOTTOM,LEFT
        border.setCenter(board);
        
        Text t2 = new Text(s);
        Button btn = new Button("New Game");
        
        
        vbox.getChildren().addAll(t,border,t2,btn);
        
        Scene scene = new Scene(vbox, 200, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void gameEnd(){
        String s ="";
            if (game.getUserWon()) {
                s+="YOU WON!";
            } else {
                s+="YOU LOST!";
            }
        
        refresh(createBoard(true),s);
    }
    
    public GridPane createBoard(boolean over){
        GridPane root = new GridPane();
        
        Boolean[] gameState = game.getBoard();
        
        for(int i=0; i<gameState.length; i++){
            
            Button btn = new Button();
            btn.setMinSize(50, 50);
            if(gameState[i]==null){
                btn.setText(" ");
                if(!over){
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
                                    refresh(createBoard(false),"Playing");
                                }
                            }
                        }
                    });
                }
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
        launch(args);
    }
    
}
