/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.functions.continuous.dynamic.moo.fda2;

import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 * This function is the f1 function of the FDA2 problem defined on page 429 in the following paper:
 * M.Farina, K.Deb, P.Amato. Dynamic multiobjective optimization problems: test cases, approximations
 * and applications, IEEE Transactions on Evolutionary Computation, 8(5): 425-442, 2003
 *
 */
public class FDA2_f1 implements ContinuousFunction {

    private static final long serialVersionUID = 3509865802519318920L;

    /**
     * Default Contructor
     */
    public FDA2_f1() {
        super();
//        setDomain("R(0, 1)");
    }

    /**
     * copy constructor
     * @param copy
     */
    public FDA2_f1(FDA2_f1 copy) {
//        this.setDomain(copy.getDomain());
    }

    /**
     * Evaluates the function
     * f1(X_I) = x_1
     */
    @Override
    public Double apply(Vector input) {
        return Math.abs(input.doubleValueOf(0));
    }
}
