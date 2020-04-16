package navalbattle.battlefield;
import navalbattle.battleships.Deck;

public class UserMove //ход пользователя
{
    private Deck userMove = new Deck(0,0);
    private boolean isUserIsPlayer = true;

    public UserMove(Deck userMove, boolean isUserIsPlayer)
    {
        this.userMove = userMove;
        this.isUserIsPlayer = isUserIsPlayer;
    }

    public boolean isUserIsPlayer() {
        return isUserIsPlayer;
    }

    public void setUserIsPlayer(boolean userIsPlayer) {
        isUserIsPlayer = userIsPlayer;
    }

    public Deck getUserMove() {
        return userMove;
    }

    public void setUserMove(Deck userMove) {
        this.userMove = userMove;
    }
}
