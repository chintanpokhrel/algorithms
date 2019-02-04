
public class Main {

	public static void main(String[] args) {
		UnionFind uf = new UnionFind(10);
		System.out.println(uf);
		uf.union(3, 4);
		uf.union(3, 8);
		uf.union(5, 6);
		System.out.println(uf);
		uf.union(9,  4);
		System.out.println(uf);
		uf.union(3, 0);
		System.out.println(uf);
	}

}
