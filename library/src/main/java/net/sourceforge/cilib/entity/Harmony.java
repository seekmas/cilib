/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.entity;

import net.sourceforge.cilib.math.random.generator.MersenneTwister;
import net.sourceforge.cilib.problem.solution.Fitness;
import net.sourceforge.cilib.problem.solution.InferiorFitness;
import net.sourceforge.cilib.problem.Problem;
import net.sourceforge.cilib.type.types.container.StructuredType;

/**
 * Entity definition for a Harmony.
 */
public class Harmony extends AbstractEntity {
    private static final long serialVersionUID = -4941414797957384798L;

    public Harmony() {
    }

    public Harmony(Harmony copy) {
        super(copy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Harmony getClone() {
        return new Harmony(this);
    }

    /**
     * {@inheritDoc}
     *
     * @param object The object to compare.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if ((object == null) || (this.getClass() != object.getClass()))
            return false;

        Harmony other = (Harmony) object;
        return super.equals(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + super.hashCode();
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void calculateFitness() {
        Fitness fitness = getFitnessCalculator().getFitness(this);
        this.getProperties().put(EntityType.FITNESS, fitness);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Entity o) {
        return this.getFitness().compareTo(o.getFitness());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getDimension() {
        return getCandidateSolution().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialise(Problem problem) {
        StructuredType harmony = problem.getDomain().getBuiltRepresentation().getClone();
        harmony.randomize(new MersenneTwister());

        setCandidateSolution(harmony);
        this.getProperties().put(EntityType.FITNESS, InferiorFitness.instance());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reinitialise() {
//        TypeUtil.randomize(getCandidateSolution());
        throw new UnsupportedOperationException("Not implemetned yet.");
    }

}
