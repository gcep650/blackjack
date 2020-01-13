public class Tester
{
    public static void main()
    {
        /*
        Deck d = new Deck();
        Hand h = new Hand();
        h.Hit(new Card(10, 1));
        h.Hit(new Card(3, 1));
        h.Hit(new Card(1, 2));
        */
        Game g = new Game(100, 100);
        while (true)
        {
            g.Start();
            if (g.getBalances()[0] <= 0)
            {
                System.out.println("Player is out of cash!\nThanks for playing!");
                break;
            }
            else if (g.getBalances()[1] <= 0)
            {
                System.out.println("Dealer is out of cash!\nThanks for playing!");
                break;
            }
            System.out.println("--START OF NEW GAME--");
            double[] bal = g.getBalances();
            g = new Game(bal);
        }
        
        
       
    }

}