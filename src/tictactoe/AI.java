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

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Sam W. <wngsam@gmail.com>
 */
public class AI {
    
    private boolean challenge;
    
    public AI(boolean hard){
        challenge = hard;
    }
    
    /**
     * Given the game's board and difficulty, choose to play easy or hard.
     * @param gameState
     * @param prevMove
     * @param moves
     * @return 
     */
    public int move(Boolean[] gameState, int prevMove, int moves){
        
        if(!challenge){
            return easyMove(gameState, moves);
        }else{
            return hardMove(gameState, prevMove, moves);
        }
    }
    
    /**
     * An easy move is just a random move on an empty position.
     * @param gameState
     * @param moves
     * @return 
     */
    public int easyMove(Boolean[] gameState, int moves){
        if(moves<=6){
            Random rand = new Random();
            int n = rand.nextInt(8);
            while(gameState[n]!=null){
                n = rand.nextInt(8);
            }
            return n;
        }else{
            for(int i=0;i<gameState.length;i++){
                if(gameState[i]==null){
                    return i;
                }
            }
        }
        return -1;
    }
    /**
     * The hard mode implements Newell and Simon's 1972 Strategy.
     * https://en.wikipedia.org/wiki/Tic-tac-toe#Strategy
     * @param gameState
     * @param prevMove
     * @param moves
     * @return 
     */
    public int hardMove(Boolean[] gameState, int prevMove, int moves){
        if(moves==0){
            int[] corners = {0,2,6,8};
            Random rand = new Random();
            return corners[(rand.nextInt(4))];
        }
        
        //Need to make this part more efficient and add opening moves if going 2nd.
        ArrayList<Integer> available = new ArrayList();
        ArrayList<Integer> myMoves = new ArrayList();
        ArrayList<Integer> enemyMoves = new ArrayList();
        
        for(int i=0 ;i<gameState.length;i++){
            if(gameState[i]==null){
                available.add(i);
            }else if(gameState[i]==false){
                myMoves.add(i);
            }else if(gameState[i]==true){
                enemyMoves.add(i);
            }
        }
        
        if(moves>2){
            for (Integer num : available) {
                if (canWinOrBlock(num, myMoves)) {
                    return num;
                }
            }

            for (Integer num : available) {
                if (canWinOrBlock(num, enemyMoves)) {
                    return num;
                }
            }
        }
        
        if(moves>4){
            for (Integer num : available) {
                if (canForkOrBlock(num, gameState, false)) {
                    return num;
                }
            }

            for (Integer num : available) {
                if (canForkOrBlock(num, gameState, true)) {
                    return num;
                }
            }
        }
        
        if(available.contains(4)){
            return 4;
        }
        
        if(enemyMoves.contains(0)){
            if(available.contains(8)){
                return 8;
            }
        }else if(enemyMoves.contains(2)){
            if(available.contains(6)){
                return 6;
            }
        }else if(enemyMoves.contains(6)){
            if(available.contains(2)){
                return 2;
            }
        }else if(enemyMoves.contains(8)){
            if(available.contains(0)){
                return 0;
            }
        }
        
        ArrayList<Integer> corners = new ArrayList();
        if(available.contains(0)){
            corners.add(0);
        }
        if(available.contains(2)){
            corners.add(2);
        }
        if(available.contains(6)){
            corners.add(6);
        }
        if(available.contains(8)){
            corners.add(8);
        }
        
        if(corners.size()>0){
            if(corners.size()>1){
                Random rand = new Random();
                return corners.get((rand.nextInt(corners.size())));
            }else{
                return corners.get(0);
            }
        }
        
        ArrayList<Integer> sides = new ArrayList();
        if(available.contains(1)){
            sides.add(1);
        }
        if(available.contains(3)){
            sides.add(3);
        }
        if(available.contains(5)){
            sides.add(5);
        }
        if(available.contains(7)){
            sides.add(7);
        }
        
        if(sides.size()>0){
            if(sides.size()>1){
                Random rand = new Random();
                return sides.get((rand.nextInt(sides.size())));
            }else{
                return sides.get(0);
            }
        }
        
        return easyMove(gameState, moves);
    }

    /**
     * Checks if a move at position i with moves that you already possess would make 3 in a row.
     * @param i
     * @param moves
     * @return 
     */
    public boolean canWinOrBlock(int i, ArrayList<Integer> moves){
        switch(i){
            case 0: 
                    if(moves.contains(1) && moves.contains(2)){
                        return true;
                    }else if(moves.contains(3) && moves.contains(6)){
                        return true;
                    }else if(moves.contains(4) && moves.contains(8)){
                        return true;
                    }
                    break;
            case 1: 
                    if(moves.contains(0) && moves.contains(2)){
                        return true;
                    }else if(moves.contains(4) && moves.contains(7)){
                        return true;
                    }
                    break;
            case 2: 
                    if(moves.contains(1) && moves.contains(0)){
                        return true;
                    }else if(moves.contains(4) && moves.contains(6)){
                        return true;
                    }else if(moves.contains(5) && moves.contains(8)){
                        return true;
                    }
                    break;
            case 3: 
                    if(moves.contains(0) && moves.contains(6)){
                        return true;
                    }else if(moves.contains(4) && moves.contains(5)){
                        return true;
                    }
                    break;
            case 4: 
                    if(moves.contains(0) && moves.contains(8)){
                        return true;
                    }else if(moves.contains(2) && moves.contains(6)){
                        return true;
                    }else if(moves.contains(3) && moves.contains(5)){
                        return true;
                    }else if(moves.contains(1) && moves.contains(7)){
                        return true;
                    }
                    break;
            case 5: 
                    if(moves.contains(8) && moves.contains(2)){
                        return true;
                    }else if(moves.contains(4) && moves.contains(3)){
                        return true;
                    }
                    break;
            case 6: 
                    if(moves.contains(4) && moves.contains(2)){
                        return true;
                    }else if(moves.contains(3) && moves.contains(0)){
                        return true;
                    }else if(moves.contains(7) && moves.contains(8)){
                        return true;
                    }
                    break;
            case 7: 
                    if(moves.contains(6) && moves.contains(8)){
                        return true;
                    }else if(moves.contains(4) && moves.contains(1)){
                        return true;
                    }
                    break;
            case 8: 
                    if(moves.contains(5) && moves.contains(2)){
                        return true;
                    }else if(moves.contains(7) && moves.contains(6)){
                        return true;
                    }else if(moves.contains(4) && moves.contains(0)){
                        return true;
                    }
                    break;
        }
        return false;
    }
    
    /**
     * Checks if a move at i with the game's board and 
     * the target you want (true for user, false for AI) would satisfy a fork.
     * A fork is satisfied when you have 2 rows at position i that
     * contains 1 empty spot and 1 spot with target.
     * @param i
     * @param gameState
     * @param target
     * @return 
     */
    public boolean canForkOrBlock(int i, Boolean[] gameState, boolean target){
        int satisfied = 0;
        switch(i){
            case 0: if((gameState[1]==null && gameState[2]==target)
                            || (gameState[2]==null && gameState[1]==target)){
                        satisfied++;
                    }
                    if((gameState[4]==null && gameState[8]==target)
                            || (gameState[8]==null && gameState[4]==target)){
                        satisfied++;
                    }
                    if((gameState[3]==null && gameState[6]==target)
                            || (gameState[6]==null && gameState[3]==target)){
                        satisfied++;
                    }
                    if(satisfied>=2){
                        return true;
                    }
                    break;
            case 1: if((gameState[0]==null && gameState[2]==target)
                            || (gameState[2]==null && gameState[0]==target)){
                        satisfied++;
                    }
                    if((gameState[4]==null && gameState[7]==target)
                            || (gameState[7]==null && gameState[4]==target)){
                        satisfied++;
                    }
                    if(satisfied>=2){
                        return true;
                    }
                    break;
            case 2: if((gameState[1]==null && gameState[0]==target)
                            || (gameState[0]==null && gameState[1]==target)){
                        satisfied++;
                    }
                    if((gameState[5]==null && gameState[8]==target)
                            || (gameState[8]==null && gameState[5]==target)){
                        satisfied++;
                    }
                    if((gameState[4]==null && gameState[6]==target)
                            || (gameState[6]==null && gameState[4]==target)){
                        satisfied++;
                    }
                    if(satisfied>=2){
                        return true;
                    }
                    break;
            case 3: if((gameState[0]==null && gameState[6]==target)
                            || (gameState[6]==null && gameState[0]==target)){
                        satisfied++;
                    }
                    if((gameState[4]==null && gameState[5]==target)
                            || (gameState[5]==null && gameState[4]==target)){
                        satisfied++;
                    }
                    if(satisfied>=2){
                        return true;
                    }
                    break;
            case 4: 
                    break;
            case 5: if((gameState[8]==null && gameState[2]==target)
                            || (gameState[2]==null && gameState[8]==target)){
                        satisfied++;
                    }
                    if((gameState[4]==null && gameState[3]==target)
                            || (gameState[3]==null && gameState[4]==target)){
                        satisfied++;
                    }
                    if(satisfied>=2){
                        return true;
                    }
                    break;
            case 6: if((gameState[0]==null && gameState[3]==target)
                            || (gameState[3]==null && gameState[0]==target)){
                        satisfied++;
                    }
                    if((gameState[7]==null && gameState[8]==target)
                            || (gameState[8]==null && gameState[7]==target)){
                        satisfied++;
                    }
                    if((gameState[2]==null && gameState[4]==target)
                            || (gameState[4]==null && gameState[2]==target)){
                        satisfied++;
                    }
                    if(satisfied>=2){
                        return true;
                    }
                    break;
            case 7: if((gameState[4]==null && gameState[1]==target)
                            || (gameState[1]==null && gameState[4]==target)){
                        satisfied++;
                    }
                    if((gameState[6]==null && gameState[8]==target)
                            || (gameState[8]==null && gameState[6]==target)){
                        satisfied++;
                    }
                    if(satisfied>=2){
                        return true;
                    }
                    break;
            case 8: if((gameState[5]==null && gameState[2]==target)
                            || (gameState[2]==null && gameState[5]==target)){
                        satisfied++;
                    }
                    if((gameState[4]==null && gameState[0]==target)
                            || (gameState[0]==null && gameState[4]==target)){
                        satisfied++;
                    }
                    if((gameState[7]==null && gameState[6]==target)
                            || (gameState[6]==null && gameState[7]==target)){
                        satisfied++;
                    }
                    if(satisfied>=2){
                        return true;
                    }
                    break;
        }
        return false;
    }
    
    public boolean isChallenge() {
        return challenge;
    }

    public void setChallenge(boolean challenge) {
        this.challenge = challenge;
    }
    
}
