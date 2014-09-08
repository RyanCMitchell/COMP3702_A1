package visualDebugger;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import problem.ASVConfig;
import problem.Obstacle;
import solution.AStar;
import solution.ConfigurationGenerator;
import solution.Node;
import solution.PRM;

public class VisualHelperTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Node start = new Node(0.1, 0.1);
		Node finish = new Node(0.9, 0.1);
		// Alistair a = new Alistair();
		PRM mapOfPRM = new PRM();
		AStar starMap = new AStar();

		HashMap<Node, HashMap<Node, Double>> pointMap;
		List<List<Point2D.Double>> mapAsList;
		ArrayList<Node> zePath;

		pointMap = mapOfPRM.createPRM(start, finish, 10000, 0.025);
		mapAsList = mapOfPRM.convertPRM(pointMap);
		zePath = starMap.performAStar(start, finish, pointMap);
		// System.out.println(zePath.size());

		// List<List<Point2D.Double>> edges2 = prmMap.createPRM(start, finish,
		// 10000, 0.025);
		// ArrayList<Node> path2 = starMap.performAStar(start, finish,
		// validPointMap);
		// List<List<Point2D.Double>> edges = a.createPRM(start, finish, 10000);
		// ArrayList<Node> path = a.AStar(start, finish);
		// ArrayList<Point2D.Double> corners = a.findPathCorners(zePath);

		VisualHelper visualHelper = new VisualHelper();

		// System.out.print(zePath);

		ArrayList<Rectangle2D> rects = new ArrayList<Rectangle2D>();
		for (Obstacle o : mapOfPRM.getPS().getObstacles()) {
			rects.add(o.getRect());
		}

		visualHelper.addRectangles(rects);

		for (List<Point2D.Double> e : mapAsList) {
			if (e.size() == 2) {
				// visualHelper.addLinkedPoints(e);
			} else {
				// visualHelper.addPoints(e);
			}

			// visualHelper.addPoints(corners);

		}
		List<Point2D.Double> l = new ArrayList<Point2D.Double>();
		if (zePath.size() != 0) {
			l.add(zePath.get(0).toPoint2D());
			l.add(zePath.get(0).toPoint2D());
			// visualHelper.addLinkedPoints(l);

			int i = 1;
			int nASV = 7;
			final int numSamples = 30;
			ConfigurationGenerator cfGen = new ConfigurationGenerator(
					numSamples);
			cfGen.setPS(mapOfPRM.getPS());

			for (Node node : zePath) {
				l.remove(0);
				l.add(node.toPoint2D());
				visualHelper.addLinkedPoints(l);

				System.out.println("A");

				cfGen.generateConfigs(node, nASV);

				System.out.println("B");

				int j = 1;
				for (ASVConfig cfg : cfGen.getConfigs()) {
					// System.out.println("Disp CFG: " + j + "/" + nASV);
					visualHelper.addPoints(cfg.getASVPositions());
					visualHelper.addLinkedPoints(cfg.getASVPositions());
					// visualHelper.repaint();
					j++;
					if (j > numSamples)
						j = 1;
					// visualHelper.addPoints(cfg.getASVPositions());
				}
				System.out.println("C");

				System.out.println("Sampling around node: " + i + "/"
						+ zePath.size());
				i++;
				visualHelper.repaint();
			}
		}

		visualHelper.repaint();

		// Wait for user key press
		visualHelper.waitKey();

		visualHelper.repaint();
	}

}
