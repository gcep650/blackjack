import java.util.ArrayList;
public class Hand
{
    private ArrayList<Card> m_hand = new ArrayList<Card>();
    private double m_cash;
    public void Hit(Card c)
    {
        m_hand.add(c);
    }

    public Hand(double cash) { m_cash = cash; }

    public int Score()
    {
        int numAces = 0;
        int s = 0;
        for (Card c: m_hand)
        {
            if (c.Rank() > 9) { s += 10; }
            else
            {
                s += c.Rank();  
            }
            if (c.Rank() == 1) { numAces++; }
        }
        while (s < 12 && numAces > 0)
        {
            numAces--;
            s += 10;
        }
        return s;
    }

    public String toString()
    {
        String retval = "Hand:\n";
        for (Card c: m_hand)
        {
            retval += "- " + c + "\n";
        }
        retval += "Score: " + Score();
        retval += "\nBusted? " + isBusted();
        retval += "\nhas BlackJack? " + isBlackJack();
        retval += "\nFive Card Charlie? " + isFiveCardCharlie();
        retval += "\nBalance: $" + m_cash;
        return retval;
    }

    public boolean isBlackJack()
    {
        return m_hand.size() == 2 && Score() == 21;
    }

    public boolean isFiveCardCharlie()
    {
        return m_hand.size() == 5;
    }

    public String viewCards()
    {
        String retval = "";
        for (Card c: m_hand)
        {
            retval += "- " + c + "\n";
        }
        return retval;
    }

    public String viewCard(int card)
    {
        return m_hand.get(card).toString();
    }

    public boolean withdrawel(double amount)
    {
        if (amount > m_cash) { return false; }
        m_cash -= amount;
        return true;
    }
    
    public double balance() { return m_cash; }

    public void deposit(double amount)
    {
        if (amount > 0)
        {
            m_cash += amount;  
        }
    }

    public boolean isBusted() { return Score() > 21; }
}