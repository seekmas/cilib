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
 *
 */
public class SphericalTest {

    private Spherical function = new Spherical();

    @Before
    public void instantiate() {
        this.function = new Spherical();
    }

    /** Test of evaluate method, of class za.ac.up.cs.ailib.Functions.Spherical. */
    @Test
    public void testEvaluate() {
        Vector x = Vector.of(1.0, 2.0, 3.0);

        assertEquals(14.0, function.apply(x), 0.0);
    }

    @Test
    public void testGradient() {
        Vector x = Vector.of(1.0, 2.0, 3.0);

        assertEquals(Vector.of(2.0, 4.0, 6.0), function.getGradient(x));
    }
}
