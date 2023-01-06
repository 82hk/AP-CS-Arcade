public class Player extends Entity {
    private static Player player = new Player("Player", new Test(1), new Test(2), new Test(3), new Test(4), new Test(5), new Test (6), new Test(7),new Test(8));
    private Player (String name, Card... deck){
        super (name,deck);

    }
    public Player getInstance () {
        return (player);
    }
}