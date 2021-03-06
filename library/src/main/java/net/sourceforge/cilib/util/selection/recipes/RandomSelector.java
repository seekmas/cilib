/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.util.selection.recipes;

import net.sourceforge.cilib.math.random.generator.MersenneTwister;
import net.sourceforge.cilib.math.random.generator.RandomProvider;
import net.sourceforge.cilib.util.selection.PartialSelection;
import net.sourceforge.cilib.util.selection.Selection;
import net.sourceforge.cilib.util.selection.arrangement.RandomArrangement;

/**
 * Perform a random selection from the provided list of elements.
 * <p>
 * Random selection is performed by:
 * <ol>
 *   <li>A random element is selected from the provided list.</li>
 *   <li>Return the result.</li>
 * </ol>
 * @param <E>
 */
public class RandomSelector<E> implements Selector<E> {
    private static final long serialVersionUID = -5099663528040315048L;

    private RandomProvider random;

    /**
     * Create a new instance.
     */
    public RandomSelector() {
        this.random = new MersenneTwister();
    }

    /**
     * Create a new instance with the provided {@code Random}.
     * @param random The {@code random} to use.
     */
    public RandomSelector(RandomProvider random) {
        this.random = random;
    }

    /**
     * Create a copy of the provided instance.
     * @param copy The instance to copy.
     */
    public RandomSelector(RandomSelector copy) {
        this.random = new MersenneTwister();
    }

    @Override
    public PartialSelection<E> on(Iterable<E> iterable) {
        return (Selection<E>) Selection.copyOf(iterable).orderBy(new RandomArrangement(random));
    }
}
