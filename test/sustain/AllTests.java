package sustain;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ BoardTest.class, DiceTest.class, NeutralAreaTest.class, PlayerTest.class, AreaTest.class,
		RegionTest.class, PlayerInputValidatorTest.class, PlayerBalanceComparatorDescTest.class })
public class AllTests {

}
