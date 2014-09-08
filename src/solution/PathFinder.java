package solution;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import problem.ASVConfig;
import problem.Obstacle;
import problem.ProblemSpec;

public class PathFinder {

	private ProblemSpec ps = new ProblemSpec();

	public ArrayList<Point2D.Double> findPathCorners(ArrayList<Node> path) {
		ArrayList<Point2D.Double> corners = new ArrayList<Point2D.Double>();

		for (Obstacle o : ps.getObstacles()) {
			for (Node n : path) {
				if (n.distance(o.getRect().getMinX(), o.getRect().getMinY()) < 0.05) {
					corners.add(new Point2D.Double(o.getRect().getMinX(), o
							.getRect().getMinY()));
				}
				if (n.distance(o.getRect().getMaxX(), o.getRect().getMinY()) < 0.05) {
					corners.add(new Point2D.Double(o.getRect().getMaxX(), o
							.getRect().getMinY()));
				}
				if (n.distance(o.getRect().getMinX(), o.getRect().getMaxY()) < 0.05) {
					corners.add(new Point2D.Double(o.getRect().getMinX(), o
							.getRect().getMaxY()));
				}
				if (n.distance(o.getRect().getMaxX(), o.getRect().getMaxY()) < 0.05) {
					corners.add(new Point2D.Double(o.getRect().getMaxX(), o
							.getRect().getMaxY()));
				}
			}

		}
		return corners;
	}

	public void initASVs(double[] coords) {
		ASVConfig a = new ASVConfig(coords);

	}

	public ASVConfig moveASVsAlong(ASVConfig conf, Node direction0,
			Node direction1) {
		double theta = 0;

		Point2D.Double p0 = conf.getPosition(0);
		double angle0 = Math.atan2(direction0.getY() - p0.getY(),
				direction0.getX() - p0.getX());

		Point2D.Double p1 = conf.getASVPositions().get(
				conf.getASVPositions().size() - 1);
		double angle1 = Math.atan2(direction1.getY() - p1.getY(),
				direction1.getX() - p1.getX());

		System.out.println(angle0 + " " + angle1);

		double m0 = Math.tan(angle0 - Math.PI / 2);
		double m1 = Math.tan(angle1 - Math.PI / 2);
		if (m0 == m1) {
			List<Point2D.Double> list1 = new ArrayList<Point2D.Double>();
			for (Point2D.Double point : conf.getASVPositions()) {
				double newx = point.getX() + 0.001 * Math.cos(angle0);
				double newy = point.getY() + 0.001 * Math.sin(angle0);
				list1.add(new Point2D.Double(newx, newy));
			}
			return new ASVConfig(list1);
		}
		double centreX = (m0 * p0.getX() - m1 * p1.getX() - p0.getY() + p1
				.getY()) / (m0 - m1);
		double centreY = m1 * (centreX - p1.getX()) + p1.getY();

		if (angle1 < angle0) {
			theta = -(0.001 / (p1.distance(centreX, centreY))) * 0.9;
		} else {
			theta = (0.001 / (p1.distance(centreX, centreY))) * 0.9;
		}

		List<Point2D.Double> list1 = new ArrayList<Point2D.Double>();
		for (Point2D.Double point : conf.getASVPositions()) {
			double newx = centreX + (point.getX() - centreX) * Math.cos(theta)
					- (point.getY() - centreY) * Math.sin(theta);
			double newy = centreY + (point.getX() - centreX) * Math.sin(theta)
					+ (point.getY() - centreY) * Math.cos(theta);
			list1.add(new Point2D.Double(newx, newy));
		}

		return new ASVConfig(list1);
	}
}