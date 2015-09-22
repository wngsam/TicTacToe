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

/**
 *
 * @author Sam W. <wngsam@gmail.com>
 */
public class AI {
    
    private boolean challenge;
    
    public AI(boolean hard){
        challenge = hard;
    }
    
    public int move(Boolean[] gameState, int prevMove){
        for(int i=0;i<gameState.length;i++){
            if(gameState[i]==null){
                return i;
            }
        }
        return -1;
    }
    
    public int stub(Boolean[] gameState, int prevMove){
        
        return -1;
    }

    public boolean isChallenge() {
        return challenge;
    }

    public void setChallenge(boolean challenge) {
        this.challenge = challenge;
    }
    
}
