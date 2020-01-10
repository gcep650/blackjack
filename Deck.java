import java.util.ArrayList;
import java.util.Random;
public class Deck
{
    private ArrayList<Card> m_cards;
    
    public Deck()
    {
        m_cards = new ArrayList<Card>();
        Shuffle();
        
    }
    
    public void Shuffle()
    {   
        m_cards.clear();
        for (int i = 1; i < 5; i++)
        {
            for (int j = 1; j < 14; j++)
            {
                m_cards.add(new Card(j, i));
            }
        }
    }
    
    public Card Deal()
    {
        if (m_cards.size() <= 0) { return null; }
        Random rand = new Random();
        int c = rand.nextInt(m_cards.size());
        return m_cards.remove(c);
    }
    
    public String toString()
    {
        String retval = "";
        for (Card c: m_cards)
        {
            retval += c + "\n";
        }
        return retval;
    }
}