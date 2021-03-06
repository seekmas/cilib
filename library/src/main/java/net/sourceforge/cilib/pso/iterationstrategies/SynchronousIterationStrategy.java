/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.pso.iterationstrategies;

import java.util.Iterator;

import net.sourceforge.cilib.algorithm.population.AbstractIterationStrategy;
import net.sourceforge.cilib.entity.Particle;
import net.sourceforge.cilib.entity.Topology;
import net.sourceforge.cilib.pso.PSO;

/**
 * Implementation of the synchronous iteration strategy for PSO.
 *
 */
public class SynchronousIterationStrategy extends AbstractIterationStrategy<PSO> {

    private static final long serialVersionUID = 6617737228912852220L;

    /**
     * {@inheritDoc}
     */
    @Override
    public SynchronousIterationStrategy getClone() {
        return this;
    }

    /**
     * <p>This is an ASynchronous strategy:</p>
     * <ol>
     * <li>For all particles:</li>
     * <ol><li>Update the particle velocity</li>
     *     <li>Update the particle position</li></ol>
     * <li>For all particles:</li>
     * <ol><li>Calculate the particle fitness</li>
     *     <li>For all paritcles in the current particle's neighbourhood:</li>
     *     <ol><li>Update the nieghbourhooh best</li></ol></ol>
     * </ol>
     *
     * @see net.sourceforge.cilib.PSO.IterationStrategy#performIteration(net.sourceforge.cilib.PSO.PSO)
     * @param pso The {@link PSO} to have an iteration applied.
     */
    @Override
    public void performIteration(PSO pso) {
        Topology<Particle> topology = pso.getTopology();

        for (Particle current : topology) {
            current.updateVelocity();
            current.updatePosition(); // TODO: replace with visitor (will simplify particle interface)

            boundaryConstraint.enforce(current);
        }

        for (Iterator<? extends Particle> i = topology.iterator(); i.hasNext();) {
            Particle current = i.next();
            current.calculateFitness();

            for (Iterator<? extends Particle> j = topology.neighbourhood(i); j.hasNext();) {
                Particle other = j.next();
                if (current.getSocialFitness().compareTo(other.getNeighbourhoodBest().getSocialFitness()) > 0) {
                    other.setNeighbourhoodBest(current); // TODO: neighbourhood visitor?
                }
            }
        }
    }
}
