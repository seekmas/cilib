/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.pso.positionprovider;

import net.sourceforge.cilib.entity.Particle;
import net.sourceforge.cilib.functions.activation.Sigmoid;
import net.sourceforge.cilib.type.types.container.Vector;
import net.sourceforge.cilib.math.random.generator.MersenneTwister;
import net.sourceforge.cilib.math.random.generator.RandomProvider;

/**
 * Binary position update strategy to enable the BinaryPSO.
 *
 */
public class BinaryPositionProvider implements PositionProvider {

    private static final long serialVersionUID = -2136786203855125909L;
    private Sigmoid sigmoid;
    private RandomProvider random;

    /**
     * Create an instance of {@linkplain BinaryPositionProvider}.
     */
    public BinaryPositionProvider() {
        this(new Sigmoid(), new MersenneTwister());
    }

    public BinaryPositionProvider(Sigmoid sigmoid, RandomProvider random) {
        this.sigmoid = sigmoid;
        this.random = random;
    }

    /**
     * Create a copy of the provided instance.
     * @param copy The instance to copy.
     */
    public BinaryPositionProvider(BinaryPositionProvider copy) {
        this.sigmoid = copy.sigmoid;
        this.random = copy.random;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BinaryPositionProvider getClone() {
        return new BinaryPositionProvider(this);
    }

    /**
     * BinaryPSO particle position update, as defined by Kennedy and Eberhart.
     */
    @Override
    public Vector get(Particle particle) {
        Vector velocity = (Vector) particle.getVelocity();
        Vector.Builder builder = Vector.newBuilder();
        for (int i = 0; i < particle.getDimension(); i++) {
            double result = this.sigmoid.apply(velocity.doubleValueOf(i));
            double rand = this.random.nextDouble();

            if (rand < result) {
                builder.add(true);
            } else {
                builder.add(false);
            }
        }
        return builder.build();
    }

    /**
     * Get the sigmoid function used within the update strategy.
     * @return The {@linkplain Sigmoid} function used.
     */
    public Sigmoid getSigmoid() {
        return this.sigmoid;
    }

    /**
     * Set the sigmoid function to use.
     * @param sigmoid The function to set.
     */
    public void setSigmoid(Sigmoid sigmoid) {
        this.sigmoid = sigmoid;
    }

    /**
     * Get the random function used within the update strategy.
     * @return The {@linkplain RandomProvider} function used.
     */
    public RandomProvider getRandom() {
        return this.random;
    }

    /**
     * Set the random function to use.
     * @param random The function to set.
     */
    public void setRandom(RandomProvider random) {
        this.random = random;
    }
}
