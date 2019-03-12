import java.util.List;
import java.util.ArrayList;

/**
 * The ThirteensBoard class represents the board in a game of Thirteens.
 */
public class ThirteensBoard extends Board {

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 9;

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS =
            {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS =
            {"spades", "hearts", "diamonds", "clubs"};

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES =
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    /**
     * Flag used to control debugging print statements.
     */
    private static final boolean I_AM_DEBUGGING = false;


    /**
     * Creates a new <code>ThirteensBoard</code> instance.
     */
    public ThirteensBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
    }

    /**
     * Determines if the selected cards form a valid group for removal.
     * In Thirteens, the legal groups are (1) a pair of non-face cards
     * whose values add to 13, and (2) a group of three cards consisting of
     * a jack, a queen, and a king in some order.
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid group for removal;
     *         false otherwise.
     */
    @Override
    public boolean isLegal(List<Integer> selectedCards) {
        return (containsPairSum13(selectedCards) || containsK(selectedCards));
    }

    /**
     * Determine if there are any legal plays left on the board.
     * In Thirteens, there is a legal play if the board contains
     * (1) a pair of non-face cards whose values add to 13, or (2) a group
     * of three cards consisting of a jack, a queen, and a king in some order.
     * @return true if there is a legal play left on the board;
     *         false otherwise.
     */
    @Override
    public boolean anotherPlayIsPossible() {
        ArrayList<Integer> values = new ArrayList<Integer>();
        boolean k = false;
        for (int i = 0; i < size(); i++)
        {
            if (cardAt(i) != null)
            {
                values.add(cardAt(i).pointValue());
                if (cardAt(i).pointValue() == 13)
                {
                    return true;
                }
            }
        }
        for (int a = 0; a < values.size(); a++)
        {
            for (int b = 0; b < values.size(); b++)
            {
                if (values.get(a) + values.get(b) == 13)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check for an 13-pair in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 13-pair.
     * @return true if the board entries in selectedCards
     *              contain an 13-pair; false otherwise.
     */
    private boolean containsPairSum13(List<Integer> selectedCards) {
        if (selectedCards.size() != 2)
        {
            return false;
        }
        if (cardAt(selectedCards.get(0)).pointValue() + cardAt(selectedCards.get(1)).pointValue() == 13)
        {
            return true;
        }
        return false;
    }

    /**
     * Check for a JQK in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a JQK group.
     * @return true if the board entries in selectedCards
     *              include a jack, a queen, and a king; false otherwise.
     */
    private boolean containsK(List<Integer> selectedCards) {
        if (selectedCards.size() != 1)
        {
            return false;
        }
        if (cardAt(selectedCards.get(0)).pointValue() == 13)
        {
            return true;
        }
        return false;
    }
}
