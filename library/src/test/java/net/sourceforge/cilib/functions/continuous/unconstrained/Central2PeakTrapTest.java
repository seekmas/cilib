/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */

package net.sourceforge.cilib.functions.continuous.unconstrained;

import static org.junit.Assert.assertEquals;
import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.type.types.container.Vector;

import org.junit.Before;
import org.junit.Test;


/**
 *
 */
public class Central2PeakTrapTest {
    
    private ContinuousFunction function;

    @Before
    public void instantiate() {
        this.function = new Central2PeakTrap();
    }

    /** Test for Central2PeakTrapTest */
    @Test
    public void testEvaluate() {
        Vector x = Vector.of(20.0);
        
        assertEquals(-200.0, function.apply(x), 0.000000009);

        x.setReal(0, 0.0);
        assertEquals(0.0, function.apply(x), 0.000000009);
        
        x.setReal(0, 10.0);
        assertEquals(-160.0, function.apply(x), 0.000000009);
    }
}
