package dataSchool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class DijkstrasAlgo {
	HashMap<String, Node> graph = new HashMap<String, Node>();

	public void readGraph() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data[] = null;
		try {
			data = br.readLine().split(" ");
			Node aux = graph.get(data[0]);
			if (aux == null) {
				aux = new Node();
				aux.adjecentMap = new HashMap<String, Integer>();
				aux.dist=Integer.MAX_VALUE;
			}
			aux.current = data[0];
			aux.adjecentMap.put(data[1], Integer.parseInt(data[2]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void findFastestPathFrom(String s, String d, int lmt) {
		Node src = graph.get(s);
		src.dist = 0;
		LinkedHashMap<String, Integer> adjQ = new LinkedHashMap<String, Integer>();
		HashMap<String, Integer> adjAuxA = src.adjecentMap;
		Iterator iterator = adjAuxA.keySet().iterator();
		String aux = "";
		while (iterator.hasNext()) {
			aux = (String) iterator.next();
			adjQ.put(aux, adjAuxA.get(aux));
		}
		
		 iterator = adjQ.keySet().iterator();
		 Node auxN=null;
		 while (iterator.hasNext()) {
				aux = (String) iterator.next();
				/**
				 * TODO need to implement using stack
				 */
				auxN=graph.get(aux);
				
			}
		 
		 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Node {
	int dist = 0;
	String parent = null, current = null;
	HashMap<String, Integer> adjecentMap = new HashMap<String, Integer>();
}