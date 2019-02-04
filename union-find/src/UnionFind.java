import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UnionFind {
	int[] arr;
	
	public UnionFind(int n)	{
		arr = new int[n];
		for (int i=0; i<n; ++i)
			arr[i] = i;
	}
	
	public String toString()
	{
		String s = "";
		for (int i : arr) {
			s = s + "," + arr[i];
		}
		
		return "[" + s + "]";
	}
	
	private int root(int i)	{
		if(arr[i] != i)
			return root(arr[i]);
		else
			return i;
	}
	
	public boolean connected(int x, int y) {
		return root(x) == root(y);
	}
	
	public void union(int x, int y)	{
		//arr[arr[x]] = y;
		int i = root(x);
		int j = root(y);
		arr[i] = j;
	}
	
	public int find(int i) {
		return 0;
	}
}
