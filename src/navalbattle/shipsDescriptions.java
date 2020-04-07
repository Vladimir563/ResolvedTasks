package navalbattle;

public enum shipsDescriptions
{
    ONEDECKSHIP("Однопалубный","OneDeckShip"),TWODECKSSHIP("Двухпалубный","TwoDecksShip"),THREEDECKSSHIP("Трехпалубный","ThreeDecksShip"),FOURDECKSSHIP("Четырехпалубный","FourDecksShip");
    private String shipNameRUS;
    private String shipNameENG;

    shipsDescriptions(String shipNameRUS, String shipNameENG)
    {
        this.shipNameRUS = shipNameRUS;
        this.shipNameENG = shipNameENG;
    }

    public String getShipNameRUS() {
        return shipNameRUS;
    }

    public String getShipNameENG() {
        return shipNameENG;
    }
}

