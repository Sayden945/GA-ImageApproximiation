import image.ImageLoader;
import render.Window;
import util.Args;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * Main class for testing the Args utility.
 * Overall process for flow of the program is as follows:
 * Roughly: Args parses CLI → builds ImageConfig (validating) and
 * GaConfig → Main calls ImageLoader.load(config.targetPath) →
 * constructs ImageFitness(targetImage, config.fitnessMetric) →
 * constructs the genome factory / mutation / crossover,
 * passing config where they need shape types, alpha, bounds →
 * constructs GeneticAlgorithm with GaConfig.
 * From that point on, most of the domain classes only need a slice of ImageConfig,
 * so pass the whole thing into the few places that genuinely need multiple fields
 * (like the random-genome factory) and just individual values elsewhere.
 * If you find a class taking ImageConfig only to read one field, pass the field instead —
 * it documents the real dependency.
 * <p>
 * The one place ImageConfig legitimately travels whole is the
 * random genome factory and the renderer setup,
 * since both need canvas size, shape types, alpha, bounds, and background together.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        Map<String, String> imgCfg = Args.parse(args);
        System.out.println("Loaded image configuration: " + imgCfg + '\n');

        BufferedImage img = ImageLoader.load(imgCfg.get("target"));

        Window result = new Window(img.getWidth(), img.getHeight()); // Create a canvas with the dimensions of the loaded image
        result.savePanelAsImage((JPanel) result.getContentPane(), "results/initial_canvas.png"); // Save the initial canvas as an image
    }
}