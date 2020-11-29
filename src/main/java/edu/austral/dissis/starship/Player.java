package edu.austral.dissis.starship;

public class Player {
    String nickname;
    Score score;
    Life life;


    public Player(String nickname,Score score,Life life) {
        this.nickname = nickname;
        this.score=score;
        this.life=life;


    }

    public Score getScore() {
        return score;
    }

    public Life getLife() {
        return life;
    }
}
