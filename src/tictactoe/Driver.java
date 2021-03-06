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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Main class where the GUI is made and displayed.
 * @author Sam W. <wngsam@gmail.com>
 */
public class Driver extends Application {

    private Game game; //Contains most of the game's functionalities.
    private Stage primaryStage; //JavaFX
    
    private ImageView logoView; //Top image.
    private Text author; 
    private Text choiceText; //Text saying "New Game".
    private HBox choices; //Contains the buttons for going first or second.
    
    private BorderPane gameBoard; //(9 Game buttons on this pane).

    /**
     * The application's starting point used by JavaFX.
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("T^3");
        game = new Game();
        createStaticContent();
        refreshBoard(drawBoard(true), "Select to go first or second!");
    }
    
    /**
     * Static GUI content are made here. Called once. This includes the buttons
     * that determine if the user will go first or second.
     */
    public void createStaticContent() {
        Image img = new Image("file:img//logo.png");
        logoView = new ImageView(img);
        author = new Text("By Sam W. 2015");
        author.setFont(Font.font("Arial", 10));
        author.setFill(Color.BLUE);
        choiceText = new Text(" New Game:");
        gameBoard = new BorderPane();
        gameBoard.setPadding(new Insets(10, 10, 10, 10));
        choices = new HBox();
        choices.setSpacing(5);
        choices.setPadding(new Insets(5, 10, 5, 10));
        Button goFirst = new Button("Go First");
        goFirst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newGame(true);
            }
        });

        Button goSecond = new Button("Go Second");
        goSecond.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newGame(false);
            }
        });

        choices.getChildren().addAll(goFirst, goSecond);
    }

    /**
     * This method is called when the individual tic tac toe squares (buttons here)
     * are made. This method will put an EventHandler on them to drive the game's logic.
     * @param btn
     * @param x 
     */
    public void makeGameButtons(Button btn, int x) {

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.playerMove(x);
                if (game.checkWin()) {
                    game.setUserWin(1);
                    gameEnd();
                } else {
                    if(game.getMoves() != 9){
                        game.cpuMove();
                        if (game.checkWin()) {
                            game.setUserWin(2);
                            gameEnd();
                        } else {
                            if (game.getMoves() == 9) {
                                game.setUserWin(0);
                                gameEnd();
                            } else {
                                refreshBoard(drawBoard(false), "Playing...");
                            }
                        }
                    }else{
                        game.setUserWin(0);
                        gameEnd();
                    }
                }
            }
        });
    }

    /**
     * 
     * @return 
     */
    public HBox createDifficultyBtn() {
        Button easy = new Button("Easy");
        Button hard = new Button("Hard");

        if (game.isChallenge()) {
            hard.setDisable(true);
        } else {
            easy.setDisable(true);
        }

        easy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                easy.setDisable(true);
                hard.setDisable(false);
                game.setChallenge(false);
            }
        });

        hard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hard.setDisable(true);
                easy.setDisable(false);
                game.setChallenge(true);
            }
        });

        HBox hbox = new HBox();
        hbox.getChildren().addAll(easy, hard);

        return hbox;
    }
    
    /**
     * Cleans the board and initiates a new game.
     * @param playerFirst 
     */
    public void newGame(boolean playerFirst) {
        game.startGame(playerFirst);
        refreshBoard(drawBoard(false), "Begin New Game!");
    }

    /**
     * The tictactoe board is drawn here.
     * @param over
     * @return 
     */
    public GridPane drawBoard(boolean over) {
        GridPane root = new GridPane();
        Boolean[] gameState = game.getBoard();

        for (int i = 0; i < gameState.length; i++) {
            Button btn = new Button();
            btn.setMinSize(50, 50);

            if (gameState[i] == null) {
                btn.setText(" ");
                if (!over) {
                    makeGameButtons(btn, i);
                } else {
                    btn.setDisable(true);
                }
            } else if (gameState[i]) {
                btn.setText("X");
                btn.setDisable(true);
            } else {
                btn.setText("O");
                btn.setDisable(true);
            }

            if (i < 3) {
                root.add(btn, i, 0);
            } else if (i < 6) {
                root.add(btn, i - 3, 1);
            } else {
                root.add(btn, i - 6, 2);
            }
        }
        return root;
    }
    
    /**
     * The board's content is remade here to update the game's state.
     * @param board
     * @param s 
     */
    public void refreshBoard(GridPane board, String s) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(5);

        Text msg = new Text(s);
        msg.setFont(Font.font("Verdana", 12));
        msg.setFill(Color.GREEN);
        gameBoard.setCenter(board);
        vbox.getChildren().addAll(logoView, author, createDifficultyBtn(), gameBoard, msg, choiceText, choices);

        primaryStage.setScene(new Scene(vbox, 250, 375));
        primaryStage.show();
    }

    /**
     * Ends the game and figures out the outcome then writes it down.
     */
    public void gameEnd() {
        String s = "";
        if(game.getUserWin()==0){
            s = "DRAW!";
        }else if (game.getUserWin()==1) {
            if(game.isChallenge()){
                s = "YOU WON, BUT THAT'S IMPOSSIBLE!";
            }else{
                s = "You won, try again on hard now!";
            }
        } else if (game.getUserWin()==2) {
            s = "YOU LOST!";
        }

        refreshBoard(drawBoard(true), s);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
