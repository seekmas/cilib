/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.functions.continuous.unconstrained;

import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.type.types.container.Vector;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

/**
 */
public class ModifiedSchaffer3Test {
    private static final double EPSILON = 1.0E-6;
    private ContinuousFunction function;

    @Before
    public void instantiate() {
        this.function = new ModifiedSchaffer3();
    }

    /** Test of the apply method of  the {@link ModifiedSchaffer3} class */
    @Test
    public void testApply() {
        Vector x = Vector.of(0.0, 1.253115);

        //test global minimum
        Assert.assertEquals(0.00156685, function.apply(x), EPSILON);

        //test another point
        x.setReal(0, 2.0);
        x.setReal(1, 3.0);
        Assert.assertEquals(0.0890824, function.apply(x), EPSILON);
    }
}
