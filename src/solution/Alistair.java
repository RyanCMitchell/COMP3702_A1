package solution;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;


import problem.ProblemSpec;
import problem.Obstacle;

public class Alistair {
	
	
	public ProblemSpec ps;
	
//	public List<List<Point2D.Double>> createPRM(Node start, Node end, int N) {
//		double maxEdgeDistance = 0.1;
//		
//		Random generator = new Random();
//		ProblemSpec ps = new ProblemSpec();
//		graph.addVertex(start);
//		graph.addVertex(end);
//		
//		boolean valid = true;
//		//graph.addVertex(newNode);
//		for(int i=0;i<N;i++) {
//			Node newNode = new Node(generator.nextDouble(),generator.nextDouble());
//			valid = true;
//			//for(Obstacle o: ps.getObstacles()) {
//			//	if(o.getRect().contains(newNode)) {
//			//		valid = false;
//			//	}
//			//}
//			if(valid) {
//				graph.addVertex(newNode);
//				for(Node n : graph.vertexSet()) {
//					if(newNode.getDistanceTo(n) < maxEdgeDistance) {
//						graph.addEdge(newNode, n);
//						graph.setEdgeWeight(graph.getEdge(newNode, n), newNode.getDistanceTo(n));
//					}
//				}
//				
//			}
//		}
//		List<List<Point2D.Double>> list1 = new ArrayList<List<Point2D.Double>>();
//		for(DefaultEdge e: graph.edgeSet()) {
//			List<Point2D.Double> l = new ArrayList<Point2D.Double>();
//			l.add(graph.getEdgeSource(e).toPoint2D());
//			l.add(graph.getEdgeTarget(e).toPoint2D());
//			list1.add(l);
//		}
//		
//		
//		return list1;
//		
//	}
	
	public List<List<Point2D.Double>> createPRM(Node start, Node end, int N) {
		double maxEdgeDistance = 0.02;
		
		HashMap<Node,HashMap<Node,Double>> map = new HashMap<Node,HashMap<Node,Double>>();
	
		
		Random generator = new Random();
		ps = new ProblemSpec();
		
		try{
			ps.loadProblem("src/testcases/3ASV.txt");
		} catch (IOException e) {
			
		}
		
		map.put(start, new HashMap<Node,Double>());
		map.put(end, new HashMap<Node,Double>());
	
		
		boolean valid = true;
		//graph.addVertex(newNode);
		for(int i=0;i<N;i++) {
			Node newNode = new Node(generator.nextDouble(),generator.nextDouble());
			valid = true;
			for(Obstacle o: ps.getObstacles()) {
				if(o.getRect().contains(newNode)) {
					valid = false;
				}
			}
			if(valid) {
				map.put(newNode,new HashMap<Node,Double>());
				for(Node n : map.keySet()) {
					if(newNode.getDistanceTo(n) < maxEdgeDistance) {
						
						boolean add = true;
						
						for(Obstacle o: ps.getObstacles()) {
							if(new Line2D.Double(newNode.toPoint2D(),n.toPoint2D()).intersects(o.getRect())) {
								add = false;
							}
						}
						if(add) {
							map.get(newNode).put(n, newNode.getDistanceTo(n));
							map.get(n).put(newNode, newNode.getDistanceTo(n));
						}
						
						//graph.setEdgeWeight(graph.getEdge(newNode, n), newNode.getDistanceTo(n));
					}
				}
				
			}
		}
		List<List<Point2D.Double>> list1 = new ArrayList<List<Point2D.Double>>();
		for(Node n: map.keySet()) {
			for(Node n1: map.get(n).keySet()) {
				List<Point2D.Double> l = new ArrayList<Point2D.Double>();
				l.add(n.toPoint2D());
				l.add(n1.toPoint2D());
				list1.add(l);
			}
		}
		
		
		return list1;
		
	}
			
}
