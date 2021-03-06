/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.nn.architecture.builder;

import net.sourceforge.cilib.functions.activation.ActivationFunction;
import net.sourceforge.cilib.nn.architecture.Layer;
import net.sourceforge.cilib.nn.components.Neuron;
import net.sourceforge.cilib.type.types.Real;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 * Class is a concrete extension of the abstract {@link LayerBuilder}. It constructs
 * a cascade layer by cloning a prototype neuron and adding to it weights such that it is
 * fully connected to the feeding layer, as well as all the neurons already in the current layer.
 */
public class CascadeLayerBuilder extends LayerBuilder {

    private Neuron prototypeNeuron;

    /**
     * Default constructor. Default neuron is a {@link Neuron}
     */
    public CascadeLayerBuilder() {
        prototypeNeuron = new Neuron();
    }

    /**
     * Builds a cascade layer by cloning a prototype neuron and adding to it weights such that it is
     * fully connected to the feeding layer, as well as all the neurons already in the current layer.
     * @param layerConfiguration
     * @param previousLayerAbsoluteSize Total sive of all previous layers.
     * @return the built layer.
     */
	@Override
    public Layer buildLayer(LayerConfiguration layerConfiguration, int sumOfPreviousLayerAbsoluteSizes) {
        prototypeNeuron.setActivationFunction((ActivationFunction)layerConfiguration.getActivationFunction());
        int layerSize = layerConfiguration.getSize();

        Layer layer = new Layer();
        for (int i = 0; i < layerSize; i++) {
            Neuron newNeuron = (Neuron) prototypeNeuron.getClone();
            Real domainReal = null;
            try {
                domainReal = (Real)((Vector)this.getDomainRegistry().getBuiltRepresentation()).get(0);
            }
            catch(ClassCastException exception) {
                throw new UnsupportedOperationException("The domain string of the neural network weights has to be real valued");
            }

            Real weight = Real.valueOf(domainReal.doubleValue(), domainReal.getBounds());
            Vector.Builder weights = Vector.newBuilder().copyOf(newNeuron.getWeights());
            weights.add(weight);
			
            for (int j = 1; j < sumOfPreviousLayerAbsoluteSizes + i; j++) {
                Real newWeight = weight.getClone();
                weights.add(newWeight);
            }

            Vector builtWeights = weights.build();
            this.getWeightInitializationStrategy().initialize(builtWeights);
            newNeuron.setWeights(builtWeights);
            layer.add(newNeuron);
        }
        return layer;
    }

    /**
     * Get the prototype neuron.
     * @return the prototype neuron.
     */
    public Neuron getPrototypeNeuron() {
        return prototypeNeuron;
    }

    /**
     * Set the prototype neuron.
     * @param prototypeNeuron the prototype neuron.
     */
    public void setPrototypeNeuron(Neuron prototypeNeuron) {
        this.prototypeNeuron = prototypeNeuron;
    }
}
