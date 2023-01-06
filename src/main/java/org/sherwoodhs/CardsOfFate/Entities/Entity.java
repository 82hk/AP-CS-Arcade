public abstract class Entity {
    protected String name = "";
    protected Deck deck;

    public Entity (String name, Deck deck) {
        this.name = name;
        this.deck = deck;
    }
    public String toString(){
        return(name);
    }
}