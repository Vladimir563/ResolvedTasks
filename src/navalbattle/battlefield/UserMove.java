package navalbattle.battlefield;
import navalbattle.battleships.Deck;

public class UserMove //ход пользователя
{
    private Deck[] moves = new Deck[50];
    private boolean isUserIsPlayer = true;

    public Deck[] getMoves() {
        return moves;
    }

    public void setMoves(Deck[] moves) {
        this.moves = moves;
    }

    public boolean isUserIsPlayer() {
        return isUserIsPlayer;
    }

    public void setUserIsPlayer(boolean userIsPlayer) {
        isUserIsPlayer = userIsPlayer;
    }
}
