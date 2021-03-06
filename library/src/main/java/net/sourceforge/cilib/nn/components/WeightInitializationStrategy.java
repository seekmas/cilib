/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.nn.components;

import net.sourceforge.cilib.type.types.container.Vector;

/**
 * Interface for a strategy class that initializes a set of neuron weights.
 */
public interface WeightInitializationStrategy {

    /**
     * Initialize (set the initial values of) the given set of weights.
     * @param weights the weights to initialize.
     */
    void initialize(Vector weights);
}
