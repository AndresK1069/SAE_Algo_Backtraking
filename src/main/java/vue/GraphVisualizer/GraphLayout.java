package vue.GraphVisualizer;

import java.util.*;

public class GraphLayout {
    public static Map<Integer, double[]> computeCircularLayout(Set<Integer> nodes , double centerX ,double centerY , double radius){
        Map<Integer,double[]> positions = new HashMap<>();
        List<Integer> nodeList = new ArrayList<>(nodes);
        int n = nodeList.size();

        for(int i = 0; i < n; i++){
            double angle = 2*Math.PI*i/n - Math.PI/2;
            double x = centerX + radius*Math.cos(angle);
            double y = centerY + radius*Math.sin(angle);
            positions.put(nodeList.get(i),new double[]{x,y});
        }
        return positions;
    }
}
