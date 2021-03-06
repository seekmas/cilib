/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.functions.continuous.unconstrained;

import static org.junit.Assert.assertEquals;
import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.type.types.Real;
import net.sourceforge.cilib.type.types.container.Vector;

import org.junit.Before;
import org.junit.Test;

/**
*/
public class MichalewiczTest {

    private ContinuousFunction function;

    @Before
    public void instantiate() {
        this.function = new Michalewicz();
    }

    /** Test of evaluate method, of class cilib.functions.unconstrained.Michalewicz. */
    @Test
    public void testEvaluate() {
//        function.setDomain("R(0, 3.141592653589793)^2");

        Vector x = Vector.of(1.5, 1.3);
        assertEquals(-0.07497735029244701, function.apply(x), 0.00000000000000001);

        x.setReal(0, 0.0);
        x.setReal(1, 0.0);
        assertEquals(0.0, function.apply(x), 0.0);
    }
}
