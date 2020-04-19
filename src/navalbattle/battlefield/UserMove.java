package navalbattle.battlefield;
import navalbattle.battleships.Cell;

public class UserMove //ход пользователя
{
    private Cell userMove;
    private boolean isUserIsPlayer;

    public UserMove(Cell userMove, boolean isUserIsPlayer)
    {
        this.userMove = userMove;
        this.isUserIsPlayer = isUserIsPlayer;
    }


    public boolean isUserIsPlayer()
    {
        return isUserIsPlayer;
    }

    public void setUserIsPlayer(boolean userIsPlayer) {
        isUserIsPlayer = userIsPlayer;
    }

    public Cell getUserMove() {
        return userMove;
    }

    public void setUserMove(Cell userMove) {
        this.userMove = userMove;
    }

    @Override
    public String toString() {
        return "UserMove{" +
                "userMove=" + userMove +
                ", isUserIsPlayer=" + isUserIsPlayer +
                '}';
    }
}
