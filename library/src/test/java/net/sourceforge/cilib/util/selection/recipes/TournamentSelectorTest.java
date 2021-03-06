/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.util.selection.recipes;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.sourceforge.cilib.controlparameter.ConstantControlParameter;
import net.sourceforge.cilib.controlparameter.ProportionalControlParameter;
import net.sourceforge.cilib.math.random.generator.MersenneTwister;
import net.sourceforge.cilib.math.random.generator.RandomAdaptor;
import net.sourceforge.cilib.math.random.generator.RandomProvider;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class TournamentSelectorTest {

    @Test(expected = IllegalArgumentException.class)
    public void selectEmpty() {
        List<Integer> elements = Lists.newArrayList();
        TournamentSelector<Integer> selection = new TournamentSelector<Integer>();
        selection.on(elements).select();
    }

    @Test
    public void selectSingle() {
        List<Integer> elements = Lists.newArrayList(1);
        TournamentSelector<Integer> selection = new TournamentSelector<Integer>();
        selection.setTournamentSize(new ProportionalControlParameter(1.0));
        int selected = selection.on(elements).select();
        Assert.assertThat(selected, is(1));
    }

    /**
     * This test shows that when tournament selection considers the entire
     * selection as the tournament, it will select the best entity from the
     * initial selection. This behavior is equivalent to elitist-selection.
     */
    @Test
    public void fullTournament() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        TournamentSelector<Integer> selection = new TournamentSelector<Integer>();
        selection.setTournamentSize(new ProportionalControlParameter(1.0));
        int selected = selection.on(list).select();
        Assert.assertThat(selected, is(9));
    }

    @Test
    public void partialTournament() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        TournamentSelector<Integer> selection = new TournamentSelector<Integer>();
        selection.setTournamentSize(ConstantControlParameter.of(0.5));
        selection.setRandom(new ConstantRandomNumber());
        int selected = selection.on(list).select();

        List<Integer> otherList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // First shuffle.
        Collections.shuffle(otherList, new RandomAdaptor(new ConstantRandomNumber()));

        // Select tournament list.
        int tournamentSize = Double.valueOf(selection.getTournamentSize().getParameter() * otherList.size()).intValue();
        List<Integer> tournamentList = otherList.subList(otherList.size() - tournamentSize, otherList.size());
        Assert.assertThat(tournamentList, hasItem(selected));

        // Sort tournament, and select best.
        Collections.sort(tournamentList);
        int bestIndex = tournamentList.size() - 1;
        Assert.assertThat(tournamentList.get(bestIndex), is(equalTo(selected)));
    }

    private static class ConstantRandomNumber implements RandomProvider {
        private static final long serialVersionUID = 3019387660938987850L;
        private RandomProvider randomProvider = new MersenneTwister(0);

        @Override
        public boolean nextBoolean() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int nextInt() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int nextInt(int n) {
            return this.randomProvider.nextInt(n);
        }

        @Override
        public long nextLong() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public float nextFloat() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public double nextDouble() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void nextBytes(byte[] bytes) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
