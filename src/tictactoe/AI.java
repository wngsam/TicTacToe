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
    
    public int move(Boolean[] gameState, int prevMove, int moves){
        
        if(!challenge){
            return easyMove(gameState, moves);
        }else{
            return hardMove(gameState, prevMove, moves);
        }
    }
    
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
        
        if(moves>4){
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
        
        //FORK +
        //BLOCK +
        
        if(available.contains(4)){
            return 4;
        }
        
        //OPPOSITE +
        
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
                return sides.get((rand.nextInt(corners.size())));
            }else{
                return sides.get(0);
            }
        }
        
        return easyMove(gameState, moves);
    }

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
    
    public boolean isChallenge() {
        return challenge;
    }

    public void setChallenge(boolean challenge) {
        this.challenge = challenge;
    }
    
}
