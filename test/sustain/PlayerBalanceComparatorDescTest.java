package sustain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerBalanceComparatorDescTest {

    @Test
    public void testComparator() {
       
        Player player1 = new Player("Player 1", "red", 1000, 1);
        Player player2 = new Player("Player 2", "red",1500, 2);
        Player player3 = new Player("Player 3", "red",800, 3);

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

      
        Collections.sort(players, new PlayerBalanceComparatorDesc());

   
        assertEquals(player2, players.get(0)); 
        assertEquals(player1, players.get(1)); 
        assertEquals(player3, players.get(2)); 
    }

    @Test
    public void testEqualBalances() {
       
        Player player1 = new Player("Player 1", "red",1000, 1);
        Player player2 = new Player("Player 2","red", 1000, 2);
        player1.incrementOwnedAreas(); 
        player2.incrementOwnedRegions(); 

   
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

       
        Collections.sort(players, new PlayerBalanceComparatorDesc());

        
        assertEquals(player1, players.get(0)); 
        assertEquals(player2, players.get(1)); 
    }

    @Test
    public void testEqualBalancesAndAreas() {
        
        Player player1 = new Player("Player 1","red", 1000, 1);
        Player player2 = new Player("Player 2", "red",1000, 2);
        player1.incrementOwnedAreas();
        player2.incrementOwnedAreas();
        player2.incrementOwnedRegions();

       
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        
        Collections.sort(players, new PlayerBalanceComparatorDesc());

        
        assertEquals(player2, players.get(0)); 
        assertEquals(player1, players.get(1)); 
    }
    
    @Test
    public void testEqualBalancesAreasAndRegions() {
        
        Player player1 = new Player("Player 1", "red",1000, 1);
        Player player2 = new Player("Player 2","red",1000, 2);
        player1.incrementOwnedAreas();
        player2.incrementOwnedAreas();
        player1.incrementOwnedRegions();
        player2.incrementOwnedRegions();

        
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        
        Collections.sort(players, new PlayerBalanceComparatorDesc());

       
        assertTrue(players.indexOf(player1) != -1 && players.indexOf(player2) != -1); 
        assertNotEquals(0, players.indexOf(player1) - players.indexOf(player2)); 
    }
    
    @Test
    public void testEqualBalanceUnequalAreas() {
        Player player1 = new Player("Player 1", "red",1000, 1);
        Player player2 = new Player("Player 2","red", 1000, 2);
        player2.incrementOwnedAreas(); // Player 2 has more areas
        
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        Collections.sort(players, new PlayerBalanceComparatorDesc());

        assertEquals(player2, players.get(0)); 
        assertEquals(player1, players.get(1)); 
    }

    
    @Test
    public void testEqualBalanceAreasUnequalRegions() {
        Player player1 = new Player("Player 1","red", 1000, 1);
        Player player2 = new Player("Player 2","red", 1000, 2);
        player1.incrementOwnedRegions(); // Player 1 has more regions
        
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        Collections.sort(players, new PlayerBalanceComparatorDesc());

        assertEquals(player1, players.get(0)); 
        assertEquals(player2, players.get(1)); 
    }


}


