package solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import problem.ASVConfig;
import problem.ProblemSpec;
import tester.Tester;

public class ConfigurationGenerator {

	private static final double BOOM_LENGTH = 0.05;
	private int numSamples;

	private List<ASVConfig> configsCSpace = new ArrayList<ASVConfig>();
	private static Random rndGen = new Random(); // Random number generator
	private ProblemSpec ps = new ProblemSpec();
	private static Tester tester = new Tester();
	private HashMap<ASVConfig, HashMap<ASVConfig, Double>> configMap = new HashMap<ASVConfig, HashMap<ASVConfig, Double>>();

	public ConfigurationGenerator(int nSample) {
		this.numSamples = nSample;
		setPS(ps);
	}

	public List<ASVConfig> getConfigs() {
		return this.configsCSpace;
	}

	public void setNumSamples(int nSamples) {
		this.numSamples = nSamples;
	}

	public void setPS(ProblemSpec ps) {
		this.ps = ps;
		configMap.clear();
		configsCSpace.clear();
		this.configsCSpace.add(ps.getInitialState());
		this.configsCSpace.add(ps.getGoalState());
		this.configMap.put(ps.getInitialState(),
				new HashMap<ASVConfig, Double>());
		this.configMap.put(ps.getGoalState(), new HashMap<ASVConfig, Double>());
	}

	public void generateConfigs(Node start, int numASV) {
		double maxDist = BOOM_LENGTH;
		// int numASV = ps.getASVCount();
		int i = 0;
		int j = 0;
		double randVal;
		double[] coords;
		ASVConfig cfg;
		// System.out.println("A2");

		while (i < this.numSamples) {
			coords = new double[numASV + 1];
			j = 0;
			// System.out.println("B2");

			while (j < numASV) {
				if (j == 0) {
					coords[0] = start.getX()
							+ ((rndGen.nextFloat() - 0.5) / 2.0) * maxDist;
					coords[1] = start.getY()
							+ ((rndGen.nextFloat() - 0.5) / 2.0) * maxDist;
					j++;
				}
				randVal = rndGen.nextFloat();
				coords[j + 1] = randVal * 2 * Math.PI;
				j++;
			}
			// System.out.println("C2");

			cfg = new ASVConfig(false, coords);

			// Test Configuration Validity
			if (tester.hasValidBoomLengths(cfg) && tester.hasEnoughArea(cfg)
					&& tester.fitsBounds(cfg) && tester.isConvex(cfg)
					&& !tester.hasCollision(cfg, this.ps.getObstacles())) {
				configsCSpace.add(cfg);
				System.out.println("A2");

				// System.out.println("ADDED CONFIG: " + (i + 1) + "/"
				// + numSamples);
				i++;
			}
		}
	}
}
