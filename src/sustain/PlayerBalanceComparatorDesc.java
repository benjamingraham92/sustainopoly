package sustain;

import java.util.Comparator;

/**
 * Comparator to order players based on their balance in descending order in
 * order to determine the results at the end of the game. If balance is the same
 * and the game is tied, the comparator then orders players based on how many
 * areas they own. If they own the same number of areas, then the comparator
 * orders the players based on how many regions the players own. If they own the
 * same number of regions, the comparator then flips a coin to decide the
 * winner.
 */
public class PlayerBalanceComparatorDesc implements Comparator<Player> {

	/**
	 * Overridden method from Comparator interface to compare players' balances,
	 * owned areas, owned regions and then simulates coin flip if equal.
	 */
	@Override
	public int compare(Player o1, Player o2) {

		if (o1.getBalance() > o2.getBalance()) {
			return -1;
		} else if (o1.getBalance() < o2.getBalance()) {
			return 1;
		}

		// count areas
		if (o1.getOwnedAreas() > o2.getOwnedAreas()) {
			return -1;
		} else if (o1.getOwnedAreas() < o2.getOwnedAreas()) {
			return 1;
		}

		// count regions
		if (o1.getOwnedRegions() > o2.getOwnedRegions()) {
			return -1;
		} else if (o1.getOwnedRegions() < o2.getOwnedRegions()) {
			return 1;
		}

		return 0;
	}

}
