import java.io.*;
import java.util.*;




public class FordFulkerson {


	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> Stack = new ArrayList<Integer>();

		/* YOUR CODE GOES HERE
		//
		//
		//
		//
		//
		//
		//
		*/

		ArrayList<Integer> path = new ArrayList<Integer>();
		ArrayList<Integer> visited = new ArrayList<Integer>();

		visited.add(source);
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(source);
		
		while(!stack.empty()){ 
			
			Integer a = stack.pop();
			visited.add(a);
			
			while(path.size() != 0 && (graph.getEdge(path.get(path.size() - 1) , a) == null || graph.getEdge(path.get(path.size() - 1), a).weight == 0)){
				path.remove(path.size()-1);
			}
			
			path.add(a);
			for(Edge e : graph.getEdges()){
				
				if(e.nodes[0]==a && e.weight>0 && !visited.contains(e.nodes[1])){
					
					if(e.nodes[1]==destination){
						path.add(destination);
						stack.clear();
						break;
					}
					
					else{
						stack.push(e.nodes[1]);
					}
					
				}
				
			}
			
		}
		return path;
	}



	public static void fordfulkerson(Integer source, Integer destination, WGraph graph, String filePath){
		String answer="";
		String myMcGillID = "260672965"; //Please initialize this variable with your McGill ID
		int maxFlow = 0;

		/* YOUR CODE GOES HERE
		//
		//
		//
		//
		//
		//
		//
		*/

		WGraph temp= new WGraph(graph);
		ArrayList<Integer> shortPath= new ArrayList<Integer>();

		for(Edge e: graph.getEdges()){
			e.weight=0;
		}
		
		while((shortPath = pathDFS(source, destination, temp)).get(shortPath.size() - 1) == destination){
			Integer augPath = Integer.MAX_VALUE;

			for(int i = 1; i < shortPath.size(); i++){
				
				Edge e = temp.getEdge(shortPath.get(i - 1),shortPath.get(i));
				if(e.weight < augPath){
					augPath = e.weight;
				}
				
			}
			
			maxFlow += augPath;

			for(int j = 1; j < shortPath.size(); j++){

				Edge e = temp.getEdge(shortPath.get(j - 1), shortPath.get(j));
				e.weight -= augPath;
				Edge tempBack = temp.getEdge(e.nodes[1], e.nodes[0]);

				if(tempBack != null){
					tempBack.weight+=augPath;
				}

				else{
					temp.addEdge(new Edge(e.nodes[1], e.nodes[0], augPath));
				}

			}

			for(int k = 1; k < shortPath.size(); k++){

				Edge forward = graph.getEdge(shortPath.get(k - 1), shortPath.get(k));

				if(forward != null){
					forward.weight += augPath;
				}

				else{
					Edge back = graph.getEdge(shortPath.get(k), shortPath.get(k-1));
					back.weight-=augPath;
				}

			}

		}

		answer += maxFlow + "\n" + graph.toString();
		writeAnswer(filePath+myMcGillID+".txt",answer);
		System.out.println(answer);
	}


	public static void writeAnswer(String path, String line){
		BufferedReader br = null;
		File file = new File(path);
		// if file doesnt exists, then create it

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(line+"\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args){
		String file = args[0];
		File f = new File(file);
		WGraph g = new WGraph(file);
		fordfulkerson(g.getSource(),g.getDestination(),g,f.getAbsolutePath().replace(".txt",""));
	}
}
