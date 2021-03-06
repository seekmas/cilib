/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.math.random;

import static com.google.common.base.Preconditions.checkArgument;
import net.sourceforge.cilib.controlparameter.ConstantControlParameter;
import net.sourceforge.cilib.controlparameter.ControlParameter;
import net.sourceforge.cilib.math.random.generator.MersenneTwister;
import net.sourceforge.cilib.math.random.generator.RandomProvider;

/**
 * 
 */
public class LaplaceDistribution implements ProbabilityDistributionFunction {

    private RandomProvider provider;
    private ControlParameter location;
    private ControlParameter scale;

    public LaplaceDistribution() {
        provider = new MersenneTwister();
        location = ConstantControlParameter.of(0.0);
        scale = ConstantControlParameter.of(1.0);
    }

    public LaplaceDistribution(long seed) {
        provider = new MersenneTwister(seed);
        location = ConstantControlParameter.of(0.0);
        scale = ConstantControlParameter.of(1.0);
    }

    /**
     * Get a Laplace-distributed random number with location 0.0 and scale 1.0.
     * @return a Laplace-distributed random number with location 0.0 and scale 1.0.
     */
    @Override
    public double getRandomNumber() {
        return getRandomNumber(location.getParameter(), scale.getParameter());
    }

    /**
     * Get a Laplace-distributed random number. Two parameters are required.
     * The first specifies the location, the second specifies the scale.
     *
     * @param location The location of the Laplace distribution.
     * @param scale The scale of the Laplace distribution
     * @return a Laplace-distributed random number.
     */
    @Override
    public double getRandomNumber(double... parameters) {
        checkArgument(parameters.length == 2, "The Laplace distribution requires two parameters.");
        checkArgument(parameters[1] > 0, "The scale parameter must be greater than zero.");

        double r = provider.nextDouble() - 0.5; //uniform number in the range (-0.5, 0.5]:

        return parameters[0] - parameters[1] * (Math.log(1 - 2 * Math.abs(r))) * Math.signum(r);
    }

    public void setScale(ControlParameter scale) {
        this.scale = scale;
    }

    public ControlParameter getScale() {
        return scale;
    }

    @Override
    public void setRandomProvider(RandomProvider provider) {
        this.provider = provider;
    }

    @Override
    public RandomProvider getRandomProvider() {
        return provider;
    }

    public void setLocation(ControlParameter location) {
        this.location = location;
    }

    public ControlParameter getLocation() {
        return location;
    }

}
