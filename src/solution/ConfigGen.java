package solution;

import problem.ASVConfig;
import problem.Obstacle;
import problem.ProblemSpec;
import tester.Tester;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ConfigGen {

	private static final double BOOM_LENGTH = 0.05;
	public static final double DEFAULT_MAX_ERROR = 1e-5;

	private int numSamples = 1000;
	private List<ASVConfig> configs = new ArrayList<ASVConfig>();
	private Random rndGen = new Random(); // Random number generator
	private ProblemSpec ps = new ProblemSpec();

	public ConfigGen() {

	}

	public ConfigGen(int nSample) {
		this.numSamples = nSample;
	}

	public void generateConfig(Node node1, int numASV) {
		int i = 0;
		int j = 0;
		double randVal;
		double[] coords, coordsCFG;
		ASVConfig cfg, cfgTemp;
		Tester tester = new Tester();// tester.DEFAULT_MAX_ERROR);
		while (i < numSamples) {
			coords = new double[2 * numASV];
			coordsCFG = new double[2 * numASV];
			coords[0] = node1.getX();
			coords[1] = node1.getY();
			coordsCFG[0] = node1.getX();
			coordsCFG[1] = node1.getX();
			j = 1;
			while (j < numASV) {
				randVal = rndGen.nextFloat();
				coords[2 * j] = coords[2 * j - 2] + BOOM_LENGTH
						* Math.cos(randVal * 2 * Math.PI);
				coords[2 * j + 1] = coords[2 * j - 1] + BOOM_LENGTH
						* Math.sin(randVal * 2 * Math.PI);				
				j++;
			}

			cfg = new ASVConfig(coords);

			//Test Configuration Validity
			if (tester.hasValidBoomLengths(cfg) 
					&& tester.hasEnoughArea(cfg)
					&& tester.fitsBounds(cfg) 
					&& tester.isConvex(cfg)
					&& !tester.hasCollision(cfg, this.ps.getObstacles())) {
				configs.add(cfg);
				System.out.println("ADDED CONFIG: " + (i + 1) + "/"
						+ numSamples);
				i++;
			}
		}

	}

	public List<ASVConfig> getConfigs() {
		return configs;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ConfigGen a = new ConfigGen(5);

		// generateConfig(2);

		System.out.print(a.getConfigs().toString());

	}

	public void setPS(ProblemSpec ps) {
		this.ps = ps;
	}

}
