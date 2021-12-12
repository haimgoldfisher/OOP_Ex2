import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraphAlgorithms ga = new MyDirectedWeightedGraphAlgorithms();
        ga.load(json_file);
        return ga.getGraph();
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = new MyDirectedWeightedGraphAlgorithms();
        ans.load(json_file);
        ans.init(ans.getGraph());
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        if (json_file.endsWith(".json"))
            json_file = json_file.substring(0, json_file.length()-5);
        new GUI_Menu(json_file);
    }

    public static void main(String[] args) throws IllegalArgumentException {
        if (args.length > 0) {
            MyDirectedWeightedGraphAlgorithms graphAlgorithms = new MyDirectedWeightedGraphAlgorithms();
            runGUI(args[0]); // with JSON
        } else
            runGUI(""); // without JSON
    }
}