/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.math.random.generator;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;


/**
 *
 */
public class NumericalRecipesRan4Test {

    @Test
    public void testNextDouble() {
        RandomTester tester = new SimpleRandomTester();
        Random r = new Random();
 //       Random r = new NumericalRecipesRan4();  // Not GPL
        for (int i = 0; i < 100000; ++i) {
            double d = r.nextDouble();
            assertTrue("Random value out of range", 0 <= d && d < 1);
            tester.addSample(d);
        }
        assertTrue("Samples are not random", tester.hasRandomSamples());
    }


}
