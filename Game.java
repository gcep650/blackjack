import java.util.*;
public class Game
{
    private Hand m_dealer;
    private Hand m_player;
    private Deck m_deck;
    private Scanner in = new Scanner(System.in);
    private boolean playerWon = false;
    private boolean gameOver = false;
    private double m_bet;
    public Game(double playerMoney, double dealerMoney)
    {
        m_dealer = new Hand(dealerMoney);
        m_player = new Hand(playerMoney);
        m_deck = new Deck();
        m_bet = 0;

        for (int i = 0; i < 2; i++)
        {
            m_dealer.Hit(m_deck.Deal());
            m_player.Hit(m_deck.Deal());
        }
    }

    public Game(double[] balances)
    {
        this(balances[0], balances[1]);
    }

    public void Start()
    {
        while (true)
        {
            Player();
            hasWon(m_player, true);
            if (gameOver) { break; }
            Dealer();
            hasWon(m_dealer, false);
            if (gameOver) { break; }
        }
        System.out.println("\nGame over!\n");
        if (playerWon)
        {
            double finAmount = 0;
            if (m_dealer.balance() < m_bet)
            {
                finAmount += m_dealer.balance();
                m_dealer.withdrawel(m_dealer.balance());
            }
            else
            {
                finAmount += m_bet;
                m_dealer.withdrawel(m_bet);
            }
            finAmount += m_bet;
            System.out.println("-->Player wins!<--\n");
            System.out.println("You won: $" + finAmount);
            m_player.deposit(finAmount);
        }
        else
        {
            System.out.println("-->Dealer wins!<--\n");
            m_player.withdrawel(m_bet);
            m_dealer.deposit(m_bet);
        }
        System.out.println("Results:\nPlayer stats:\n" + m_player
            + "\nDealer stats:\n" + m_dealer);
    }

        private void hasWon(Hand h, boolean isPlayer)
        {
        if (checkWin(h))
        {
            playerWon = isPlayer;
            gameOver = true;
        }
        else if(h.isBusted())
        {
            playerWon = !isPlayer;
            gameOver = true;
        }
    }

    private void Player()
    {
        boolean hasHit = false;
        while (!hasHit)
        {
            System.out.println("\nWhat do you want to do?"
                + "\nh - Hit" + "\ns - Stand" + "\nv - View Cards"
                + "\nq - Quit\nb - Set a bet\nType in the letter of the option and press enter." +
                "\nYour balance is $" + m_player.balance());
            String resp = in.nextLine().trim().toLowerCase();
            if (resp.length() > 0)
            {
                char c = resp.charAt(0);
                switch (c)
                {
                    case 'h':
                    Card cd = m_deck.Deal();
                    m_player.Hit(cd);
                    System.out.println("You recieved: " + cd);
                    hasHit = true;
                    break;

                    case 's':
                    System.out.println("You decided to stand.");
                    hasHit = true;
                    break;

                    case 'v':
                    System.out.print("These are your cards: \n" + m_player.viewCards()
                        + "Score: " + m_player.Score() + "\n");
                    System.out.println("House's visible card: " + m_dealer.viewCard(0));
                    break;

                    case 'q':
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                    break;

                    case 'b':
                    System.out.println("Enter the amount to bet:\nThe current bet is $" + m_bet);
                    double ans = in.nextDouble();
                    if (m_player.withdrawel(ans))
                    {
                        m_player.deposit(m_bet);
                        m_bet = ans;
                        System.out.println("Bet set to $" + m_bet);
                    }
                    else
                    {
                        System.out.println("Insufficient funds or invalid answer.");
                    }
                    in.nextLine();
                    break;

                    default:
                    System.out.println("Invalid choice.");
                    break;
                }
            }
            else { System.out.println("Invalid choice."); }
            if (hasHit) { break;}

        }
    }

    private void Dealer()
    {
        while (m_dealer.Score() < 16)
        {
            m_dealer.Hit(m_deck.Deal());
        }
    }

    public double[] getBalances()
    {
        double[] balances = new double[2];
        balances[0] = m_dealer.balance();
        balances[1] = m_player.balance();
        return balances;
    }

    private boolean checkWin(Hand h)
    {
        return h.Score() == 21 || h.isBlackJack() || h.isFiveCardCharlie();   
    }

    public String toString()
    {
        return "Dealer:\n" + m_dealer + "\nPlayer: \n" + m_player;
    }
}