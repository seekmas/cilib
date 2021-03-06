/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.pso.positionprovider;

import net.sourceforge.cilib.entity.Particle;
import net.sourceforge.cilib.type.types.container.Vector;
import net.sourceforge.cilib.math.random.generator.MersenneTwister;
import net.sourceforge.cilib.math.random.generator.RandomProvider;

/**
 * Implementation of A quantum particle swarm optimization
 * 
 * Velocity of particle is modeled by a quantum bit (qubit)
 * <p>
 * References:
 * </p>
 * <ul><li>
 * Shuyuan Yang, Min Wang, Licheng Jiao., 
 * "A quantum particle swarm optimization" (2004). 
 * IEEE Congress on evolutionary computation, vol 1, pp 320--324, 2004
 * </li></ul>
 */
public class BinaryQuantumPositionProvider implements PositionProvider {

    private RandomProvider random;

    /**
     * Create an instance of {@linkplain BinaryQuantumPositionProvider}.
     */
    public BinaryQuantumPositionProvider() {
        this(new MersenneTwister());
    }

    public BinaryQuantumPositionProvider(RandomProvider random) {
        this.random = random;
    }

    /**
     * Create a copy of the provided instance.
     * @param copy The instance to copy.
     */
    public BinaryQuantumPositionProvider(BinaryQuantumPositionProvider copy) {
        this.random = copy.random;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BinaryQuantumPositionProvider getClone() {
        return new BinaryQuantumPositionProvider(this);
    }

    /*
     * Updates the particle's position by treating velocity as a 
     * set of quantum bits 
     */
    @Override
    public Vector get(Particle particle) {
        Vector velocity = (Vector) particle.getVelocity();
        Vector position = (Vector) particle.getPosition();
        Vector.Builder builder = Vector.newBuilder();

        for (int i = 0; i < particle.getDimension(); i++) {
            double rand = this.random.nextDouble();
            double q = velocity.doubleValueOf(i);

            if (rand > q) {
                builder.addWithin(1.0, position.boundsOf(i));
            } else {
                builder.addWithin(0.0, position.boundsOf(i));
            }
        }
        return builder.build();
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