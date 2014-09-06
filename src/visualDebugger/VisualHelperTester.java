package visualDebugger;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
<<<<<<< HEAD
=======
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.Set;
>>>>>>> 97dcfba117ab0c9e875f46d29474e97d780d9f0d
import java.util.List;

import problem.ASVConfig;
import problem.Obstacle;
import solution.Alistair;
import solution.ConfigGen;
import solution.Node;

public class VisualHelperTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Create list of points
		// ArrayList<Point2D> points = new ArrayList<Point2D>();
		// points.add(new Point2D.Double(0.3, 0.3));
		// points.add(new Point2D.Double(0.5, 0.5));
		// ArrayList<Point2D> points2 = new ArrayList<Point2D>();
		// points2.add(new Point2D.Double(0.8, 0.5));
		// points2.add(new Point2D.Double(0.9, 0.6));
		// points2.add(new Point2D.Double(0.9, 0.5));
		// points2.add(new Point2D.Double(0.8, 0.4));
		//
		// // Create list of rectangles
		// ArrayList<Rectangle2D> rects = new ArrayList<Rectangle2D>();
		// rects.add(new Rectangle2D.Double(0, 0, 0.2, 0.3));
		//

		// VisualHelper visualHelper = new VisualHelper();

		Node n1 = new Node(0.1, 0.1);
		Node n2 = new Node(0.9, 0.1);
		Alistair a = new Alistair();

		List<List<Point2D.Double>> edges = a.createPRM(n1, n2, 5000);
		ArrayList<Node> path = a.AStar(n1, n2);
<<<<<<< HEAD
		ArrayList<Point2D.Double> corners = a.findPathCorners(path);

		VisualHelper visualHelper = new VisualHelper();

=======
		//ArrayList<Point2D.Double> corners = a.findPathCorners(path);
		
		VisualHelper visualHelper = new VisualHelper();		
		
>>>>>>> 97dcfba117ab0c9e875f46d29474e97d780d9f0d
		System.out.print(path);

		ArrayList<Rectangle2D> rects = new ArrayList<Rectangle2D>();
		for (Obstacle o : a.ps.getObstacles()) {
			rects.add(o.getRect());
		}

		visualHelper.addRectangles(rects);

		for (List<Point2D.Double> e : edges) {
			if (e.size() == 2) {
				// visualHelper.addLinkedPoints(e);
			} else {
				// visualHelper.addPoints(e);
			}

			// visualHelper.addPoints(corners);

		}
		List<Point2D.Double> l = new ArrayList<Point2D.Double>();
		if (path.size() != 0) {
			l.add(path.get(0).toPoint2D());
			l.add(path.get(0).toPoint2D());
			visualHelper.addLinkedPoints(l);

			int i = 1;
			int nASV = 11;
			int nSample = 5;
			ConfigGen cfGen = new ConfigGen(nSample);
			cfGen.setPS(a.ps);

			for (Node n : path) {
				l.remove(0);
				l.add(n.toPoint2D());
				// visualHelper.addLinkedPoints(l);

				cfGen.generateConfig(n, nASV);
				int j = 1;
				for (ASVConfig cfg : cfGen.getConfigs()) {
					System.out.println("Disp CFG: " + j + "/" + nASV);
					visualHelper.addPoints(cfg.getASVPositions());
					visualHelper.addLinkedPoints(cfg.getASVPositions());
					visualHelper.repaint();
					j++;
					// visualHelper.addPoints(cfg.getASVPositions().g);
				}
				System.out.println("Node: " + i + "/" + path.size());
				i++;
			}
		}

		visualHelper.repaint();

		// Wait for user key press
		visualHelper.waitKey();

		visualHelper.repaint();
	}

}
