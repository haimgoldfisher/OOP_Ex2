import org.junit.Test;

public class testing {

    @Test
    public void load_Test()
    {
        MyDirectedWeightedGraphAlgorithms graphAlgorithms = new MyDirectedWeightedGraphAlgorithms();
        graphAlgorithms.load("data/G1.json");
        
    }


    @Test
    public void node_Test()
    {
        MyGeoLocation g = new MyGeoLocation(1,2,3);
        Node n = new Node(1, g);

    }

    @Test
    public void graph_Test()
    {

    }



}
