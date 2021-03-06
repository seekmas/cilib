/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.entity.initialization;

import net.sourceforge.cilib.ec.Individual;
import net.sourceforge.cilib.entity.EntityType;
import net.sourceforge.cilib.math.Maths;
import net.sourceforge.cilib.type.types.Real;
import net.sourceforge.cilib.type.types.container.Vector;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

/**
 *
 */
public class ConstantInitializationStrategyTest {

    @Test
    public void testGetClone() {
        ConstantInitializationStrategy strategy = new ConstantInitializationStrategy(1.0);
        ConstantInitializationStrategy clone = strategy.getClone();

        Assert.assertNotSame(strategy, clone);
        Assert.assertEquals(strategy.getConstant(), clone.getConstant(), Maths.EPSILON);
    }

    @Test
    public void initialize() {
        Vector vector = Vector.of(1.0, 1.0, 1.0);
        Individual individual = new Individual();
        individual.getProperties().put(EntityType.CANDIDATE_SOLUTION, Vector.copyOf(vector));

        ConstantInitializationStrategy<Individual> initializationStrategy = new ConstantInitializationStrategy<Individual>();
        initializationStrategy.initialize(EntityType.CANDIDATE_SOLUTION, individual);

        Vector chromosome = (Vector) individual.getCandidateSolution();

        for (int i = 0; i < vector.size(); i++) {
            Assert.assertThat(vector.doubleValueOf(i), is(not(chromosome.doubleValueOf(i))));
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void invalidInitialize() {
        Individual individual = new Individual();
        individual.getProperties().put(EntityType.CANDIDATE_SOLUTION, Real.valueOf(0.0));

        ConstantInitializationStrategy<Individual> initializationStrategy = new ConstantInitializationStrategy<Individual>();

        initializationStrategy.initialize(EntityType.CANDIDATE_SOLUTION, individual);
    }
}
