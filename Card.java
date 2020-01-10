public class Card
{
    private static final int HEARTS = 1;
    private static final int CLUBS = 2;
    private static final int SPADES = 3;
    private static final int DIAMONDS = 4;
    
    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 1;
    
    private int m_rank;
    private int m_suit;
    
    public Card(int rank, int suit)
    {
        m_rank = rank;
        m_suit = suit;
    }
    
    public int Rank() { return m_rank; }
    public int Suit() { return m_suit; }
    
    public String toString()
    {
        String retval = "";
        switch(m_rank)
        {
            case JACK:
                retval += "Jack";
                break;
            case QUEEN:
                retval += "Queen";
                break;
            case KING:
                retval += "King";
                break;
            case ACE:
                retval += "Ace";
                break;
            default:
                retval += m_rank;
                break;
        }
        retval += " of ";
        switch(m_suit)
        {
            case HEARTS:
                retval += "Hearts";
                break;
            case CLUBS:
                retval += "Clubs";
                break;
            case SPADES:
                retval += "Spades";
                break;
            case DIAMONDS:
                retval += "Diamonds";
                break;
        }
        return retval;
    }
    
    
}