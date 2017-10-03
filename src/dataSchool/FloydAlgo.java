package dataSchool;

import java.util.ArrayList;
/**
 * A,B,C,D,E,F
(A,B,1),(A,C,2),(B,E,1),(E,A,2),(E,D,1),(D,F,1),(D,C,3),(F,E,1),(F,D,1),(C,A,2),(C,D,3)
(A,F,FASTEST,1,6),(A,E,FASTEST,1,3),(A,D,SHORTEST,2,9),(A,E,FASTEST,3,5)


Y,N,Y,Y
 */
import java.util.StringTokenizer;

public class FloydAlgo {
	static java.util.HashMap<Integer, String> node2Str = new java.util.HashMap<Integer, String>();
	static int p[][] = null;

	public static String getPathFrom(int i, int j) {
		int k = p[i][j];
		if (k == -1) {
			return i + "";
		} else {
			return getPathFrom(i, k) + ":" + getPathFrom(k, j);
		}

	}

	public static String getdecisions(String input1, String input2, String input3) {
		// Write code here
		String nodes[] = input1.split(",");
		java.util.HashMap<String, Integer> nodeTable = new java.util.HashMap<String, Integer>();
		node2Str.clear();
		int len = nodes.length;
		for (int i = 0; i < len; i++) {
			nodeTable.put(nodes[i], i);
			node2Str.put(i, nodes[i]);
		}
		int s1[][] = new int[len][len];
		int s2[][] = new int[len][len];
		p = new int[len][len];
		char data[] = input2.toCharArray();
		int len2 = data.length;
		int r = 0, c = 0, d = 0;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				s1[i][j] = Integer.MAX_VALUE;
				s2[i][j] = Integer.MAX_VALUE;
				p[i][j] = -1;
			}
			s1[i][i] = 0;
			s2[i][i] = 0;
		}

		for (int i = 0; i < len2; i++) {
			i++;
			r = nodeTable.get(data[i] + "");
			i++;
			i++;
			c = nodeTable.get(data[i] + "");
			i++;
			i++;
			d = Integer.parseInt(data[i] + "");
			s1[r][c] = d;
			s2[r][c] = 1;
			i++;
			i++;
		}
		for (int k = 0; k < len; k++) {
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len; j++) {
					if ((s1[i][k] != Integer.MAX_VALUE && s1[k][j] != Integer.MAX_VALUE)
							&& s1[i][j] > s1[i][k] + s1[k][j]) {
						s1[i][j] = s1[i][k] + s1[k][j];
						p[i][j] = k;
					}
					if ((s2[i][k] != Integer.MAX_VALUE && s2[k][j] != Integer.MAX_VALUE)
							&& s2[i][j] > s2[i][k] + s2[k][j]) {
						s2[i][j] = s2[i][k] + s2[k][j];
					}
				}
			}
		}
		/*
		 * TODO remove this line
		 *
		 * System.out.println("Path from C,E=" + getPathFrom(2, 4)+":4");
		 */
		String param[] = null;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(input3, ")");
		String token = st.nextToken();
		token = token.substring(1);
		ArrayList<String> pathList = new ArrayList<String>();
		param = token.split(",");
		r = nodeTable.get(param[0]);
		c = nodeTable.get(param[1]);
		
		if ("FASTEST".equals(param[2])) {
			d = Math.abs(Integer.parseInt(param[3]) - Integer.parseInt(param[4]));
			if (s1[r][c] < d) {
				sb.append("Y,");
				pathList.add(getPathFrom(r, c)+":"+c);
			} else {
				sb.append("N,");
			}
		} else {
			d = Math.abs(Integer.parseInt(param[3]) - Integer.parseInt(param[4]));
			if (s2[r][c] < d) {
				sb.append("Y,");
				pathList.add(getPathFrom(r, c)+":"+c);
			} else {
				sb.append("N,");
			}
		}

		while (st.hasMoreTokens()) {
			token = st.nextToken();
			token = token.substring(2);

			param = token.split(",");
			r = nodeTable.get(param[0]);
			c = nodeTable.get(param[1]);
			if ("FASTEST".equals(param[2])) {
				d = Math.abs(Integer.parseInt(param[3]) - Integer.parseInt(param[4]));
				if (s1[r][c] < d) {
					sb.append("Y,");
				} else {
					sb.append("N,");
				}
			} else {
				d = Math.abs(Integer.parseInt(param[3]) - Integer.parseInt(param[4]));
				if (s2[r][c] < d) {
					sb.append("Y,");
				} else {
					sb.append("N,");
				}
			}

		}
		String result = sb.toString();
		len = result.length();
		result = result.substring(0, len - 1);
		return result;
	}

	public static String getPath(int p[][], int ng[][], String s, String d) {

		return "";
	}

	public static void main(String[] args) {
		String s1 = "A,B,C,D,E,F";
		String s2 = "(A,B,1),(A,C,2),(B,E,1),(E,A,2),(E,D,1),(D,F,1),(D,C,3),(F,E,1),(F,D,1),(C,A,2),(C,D,3)";
		String s3 = "(A,E,FASTEST,1,6),(A,E,FASTEST,1,3),(A,D,SHORTEST,2,9),(A,E,FASTEST,3,5)";
		String result = getdecisions(s1, s2, s3);
		System.out.println(result);
	}
}
