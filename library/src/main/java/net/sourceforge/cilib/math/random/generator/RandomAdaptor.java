/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.math.random.generator;

/**
 *
 */
public final class RandomAdaptor extends java.util.Random {
    private static final long serialVersionUID = 9072860165404102971L;
    private final RandomProvider delegate;

    private double nextNextGaussian;
    private boolean haveNextNextGaussian = false;

    public RandomAdaptor(RandomProvider provider) {
        this.delegate = provider;
    }

    @Override
    public boolean nextBoolean() {
        return delegate.nextBoolean();
    }

    @Override
    public void nextBytes(byte[] bytes) {
        delegate.nextBytes(bytes);
    }

    @Override
    public double nextDouble() {
        return delegate.nextDouble();
    }

    @Override
    public float nextFloat() {
        return delegate.nextFloat();
    }

    @Override
    public synchronized double nextGaussian() {
        // See Knuth, ACP, Section 3.4.1 Algorithm C.
        if (haveNextNextGaussian) {
            haveNextNextGaussian = false;
            return nextNextGaussian;
        } else {
            double v1, v2, s;
            do {
                v1 = 2 * nextDouble() - 1; // between -1 and 1
                v2 = 2 * nextDouble() - 1; // between -1 and 1
                s = v1 * v1 + v2 * v2;
            } while (s >= 1 || s == 0);
            double multiplier = StrictMath.sqrt(-2 * StrictMath.log(s)/s);
            nextNextGaussian = v2 * multiplier;
            haveNextNextGaussian = true;
            return v1 * multiplier;
        }
    }

    @Override
    public int nextInt() {
        return delegate.nextInt();
    }

    @Override
    public int nextInt(int n) {
        return delegate.nextInt(n);
    }

    @Override
    public long nextLong() {
        return delegate.nextLong();
    }

    @Override
    public synchronized void setSeed(long seed) {
        // Nothing is to be done in this method. The seed value should never be redefined.
        // If you want it to be redefined, recreate the RandomProvider with the desired seed.
    }

}
