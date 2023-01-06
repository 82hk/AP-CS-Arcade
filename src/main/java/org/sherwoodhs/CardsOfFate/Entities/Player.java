public class Player extends Entity {
    private static Player player = new Player("Player",null);
    private Player (String name, Deck deck){
        super (name,deck);

    }
    public static getInstance () {
        return (player);
    }
}