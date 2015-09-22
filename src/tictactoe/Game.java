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

import java.util.Objects;

/**
 *
 * @author Sam W. <wngsam@gmail.com>
 */
public class Game {

    private Boolean[] gameState;
    private AI ai;
    private int prevMove;
    private boolean userWin;
    private boolean playerFirst;
    private int moves;

    public Game() {
        gameState = new Boolean[9];
        ai = new AI();
    }

    public void startGame(boolean playerFirst) {
        gameState = new Boolean[9];
        prevMove = -1;
        moves = 0;
        if (!playerFirst) {
            cpuMove();
        }
    }

    public boolean checkWin() {
        switch (prevMove) {
            case 0:
                if (Objects.equals(gameState[0], gameState[1]) && Objects.equals(gameState[0], gameState[2])) {
                    return true;
                } else if (Objects.equals(gameState[0], gameState[3]) && Objects.equals(gameState[0], gameState[6])) {
                    return true;
                } else if (Objects.equals(gameState[0], gameState[4]) && Objects.equals(gameState[0], gameState[8])) {
                    return true;
                }
                break;
            case 1:
                if (Objects.equals(gameState[1], gameState[0]) && Objects.equals(gameState[1], gameState[2])) {
                    return true;
                } else if (Objects.equals(gameState[1], gameState[4]) && Objects.equals(gameState[1], gameState[7])) {
                    return true;
                }
                break;
            case 2:
                if (Objects.equals(gameState[2], gameState[0]) && Objects.equals(gameState[2], gameState[1])) {
                    return true;
                } else if (Objects.equals(gameState[2], gameState[4]) && Objects.equals(gameState[2], gameState[6])) {
                    return true;
                } else if (Objects.equals(gameState[2], gameState[5]) && Objects.equals(gameState[2], gameState[8])) {
                    return true;
                }
                break;
            case 3:
                if (Objects.equals(gameState[3], gameState[0]) && Objects.equals(gameState[3], gameState[6])) {
                    return true;
                } else if (Objects.equals(gameState[3], gameState[4]) && Objects.equals(gameState[3], gameState[5])) {
                    return true;
                }
                break;
            case 4:
                if (Objects.equals(gameState[4], gameState[0]) && Objects.equals(gameState[4], gameState[8])) {
                    return true;
                } else if (Objects.equals(gameState[4], gameState[1]) && Objects.equals(gameState[4], gameState[7])) {
                    return true;
                } else if (Objects.equals(gameState[4], gameState[2]) && Objects.equals(gameState[4], gameState[6])) {
                    return true;
                } else if (Objects.equals(gameState[4], gameState[3]) && Objects.equals(gameState[4], gameState[5])) {
                    return true;
                }
                break;
            case 5:
                if (Objects.equals(gameState[5], gameState[2]) && Objects.equals(gameState[5], gameState[8])) {
                    return true;
                } else if (Objects.equals(gameState[5], gameState[3]) && Objects.equals(gameState[5], gameState[4])) {
                    return true;
                }
                break;
            case 6:
                if (Objects.equals(gameState[6], gameState[0]) && Objects.equals(gameState[6], gameState[3])) {
                    return true;
                } else if (Objects.equals(gameState[6], gameState[2]) && Objects.equals(gameState[6], gameState[4])) {
                    return true;
                } else if (Objects.equals(gameState[6], gameState[7]) && Objects.equals(gameState[6], gameState[8])) {
                    return true;
                }
                break;
            case 7:
                if (Objects.equals(gameState[7], gameState[1]) && Objects.equals(gameState[7], gameState[4])) {
                    return true;
                } else if (Objects.equals(gameState[7], gameState[6]) && Objects.equals(gameState[7], gameState[8])) {
                    return true;
                }
                break;
            case 8:
                if (Objects.equals(gameState[8], gameState[0]) && Objects.equals(gameState[8], gameState[4])) {
                    return true;
                } else if (Objects.equals(gameState[8], gameState[2]) && Objects.equals(gameState[8], gameState[5])) {
                    return true;
                } else if (Objects.equals(gameState[8], gameState[6]) && Objects.equals(gameState[8], gameState[7])) {
                    return true;
                }
                break;
        }
        return false;
    }

    public void cpuMove() {
        int x = ai.move(gameState, prevMove);
        prevMove = x;
        gameState[x] = false;
        moves++;
    }

    public void playerMove(int x) {
        prevMove = x;
        gameState[x] = true;
        moves++;
    }

    public Boolean[] getBoard() {
        return gameState;
    }

    public int getMoves() {
        return moves;
    }

    public boolean getUserWin() {
        return userWin;
    }

    public void setUserWin(boolean userWon) {
        this.userWin = userWon;
    }

}
