public abstract class Entity {
    protected String name = "";
    protected Deck deck;

    public Entity (String name, Card... deck) {
        this.name = name;
        
        // Puts Cards in a stack
        Stack<Card> stack = new Stack<Card>();
        for(int i = 0; i > deck.size(); i++){
            stack.push(deck[i]);
        }

        // Makes the stack of cards a deck
        this.deck = new Deck(stack);    
    }

    // Returns Entity Name
    public String getName(){
        return(name);
    }

    // Gets the Deck
    private Stack<Card> getDeck(){
        return(deck.getDeck());
    }

    // Gets the Hand
    private ArrayList<Card> getHand(){
        return(deck.getHand());
    }

    // Prints Hand
    public void printHand(){
        System.out.println(deck.getHand());
    }
}