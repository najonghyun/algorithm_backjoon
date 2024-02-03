import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	
	static class Node{
		int vertex;
		Node left;
		Node right;
		
		public Node(int vertex) {
			super();
			this.vertex = vertex;
			this.left = null;
			this.right = null;
		}
	}
	
	static class BinaryTree{
		Node root;
		
		public BinaryTree() {
			this.root = null;
		}
				
		public void insert(int data) {
			Node newNode = new Node(data);
			
			if(root == null) {
				root = newNode;
			}else {
				root = add(root, newNode);
			}
		}
		
		private Node add(Node node, Node newNode) {
			if(node == null) {
				return newNode;
			}else {
				if(node.vertex > newNode.vertex) {
					node.left = add(node.left, newNode);				
				}else if(node.vertex < newNode.vertex) {
					node.right = add(node.right, newNode);	
				}
				return node;
			}
		}
		
		public void prefix() {
			prefixResult(root);
		}
		
		private void prefixResult(Node root) {
			if(root == null) {
				return;
			}
			prefixResult(root.left);
			prefixResult(root.right);
			sb.append(root.vertex).append("\n");
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BinaryTree tree = new BinaryTree();
		String node = "";
		
		while((node = in.readLine()) != null) {
			tree.insert(parseInt(node));
		}
		
		tree.prefix();
		
		System.out.println(sb);
		
	}

}