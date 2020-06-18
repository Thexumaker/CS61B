import org.junit.Test;
import static org.junit.Assert.*;
public class UnionFindTest {


    @Test
    public void basicTest() {
        UnionFind uf = new UnionFind(16);
        uf.union(1, 2);


        uf.union(1, 2);


        uf.union(3, 4);


        uf.union(1, 0);


        uf.union(1, 3);


        uf.find(4);

    }
}
