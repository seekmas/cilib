/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.functions.continuous.unconstrained;

import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.type.types.container.Vector;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class BealeTest {

    private ContinuousFunction function;

    @Before
    public void instantiate() {
        this.function = new Beale();
    }

    /** Test of evaluate method, of class za.ac.up.cs.ailib.Functions.Beale. */
    @Test
    public void testEvaluate() {
        function = new Beale();

        Vector x = Vector.of(1.0, 2.0);
        assertEquals(126.4531250, function.apply(x), 0.0);

        x.setReal(0, 3.0);
        x.setReal(1, 0.5);
        assertEquals(0.0, function.apply(x), 0.0);
    }
}
