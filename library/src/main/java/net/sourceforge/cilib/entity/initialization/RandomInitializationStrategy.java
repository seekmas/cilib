/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.entity.initialization;

import net.sourceforge.cilib.entity.Entity;
import net.sourceforge.cilib.math.random.generator.MersenneTwister;
import net.sourceforge.cilib.math.random.generator.RandomProvider;
import net.sourceforge.cilib.type.types.Randomizable;
import net.sourceforge.cilib.type.types.Type;

/**
 *
 * @param <E>
 */
public class RandomInitializationStrategy<E extends Entity> implements InitializationStrategy<E> {
    private static final long serialVersionUID = 5630272366805104400L;

    private RandomProvider random;

    public RandomInitializationStrategy() {
        this.random = new MersenneTwister();
    }

    public RandomInitializationStrategy(RandomInitializationStrategy copy) {
//        this.random = copy.random.getClone();
        this.random = new MersenneTwister();
    }

    @Override
    public RandomInitializationStrategy getClone() {
        return new RandomInitializationStrategy(this);
    }

    @Override
    public void initialize(Enum<?> key, E entity) {
        Type type = entity.getProperties().get(key);

        if (type instanceof Randomizable) {
            Randomizable randomizable = (Randomizable) type;
            randomizable.randomize(random);
            return;
        }

        throw new UnsupportedOperationException("Cannot initialize a non Randomizable instance.");
    }

}
