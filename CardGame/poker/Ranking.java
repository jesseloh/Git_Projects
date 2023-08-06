package cardGame.poker;

public enum Ranking {

    NONE, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND;
    /*Current ranking is simplified. For future development:
    NONE, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH, ROYAL FLUSH;
     */

    @Override
    public String toString() {
        return this.name().replace('_', ' ');
    }
}