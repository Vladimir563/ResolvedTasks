package navalbattle;

import navalbattle.battlefield.BattleField;

public class Game
{
    public void start()
    {
        BattleField battleField = new BattleField();
        battleField.fieldGenerate(10);
    }
}
