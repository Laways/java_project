package 图.utils;

import 图.DenseGraph;
import 图.Graph;
import 图.SparseGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Scanner;

public class GraphFileUtils {

    public static boolean initGraph(Graph graph, String fileName){
        if (fileName == null || "".equals(fileName)) {
            return false;
        }
        File file = new File(fileName);
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file,"UTF-8");
                String lineStr = scanner.nextLine();
                int n = Integer.parseInt(lineStr.split(" ")[0]);
                if (n != graph.getNodeCount()) {
                    System.err.println("图节点数量错误");
                    return false;
                }
                if (graph instanceof DenseGraph || graph instanceof SparseGraph) {
                    readFile(graph, scanner);
                } else {
                    readFileWeight(graph, scanner);
                }
                scanner.close();
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }else{
            System.out.println("文件不存在");

            return false;
        }
    }

    private static void readFileWeight(Graph graph, Scanner scanner) {
        String lineStr;
        int n;
        int m;
        Field field;
        try {
            Class c = graph.getClass();
            field = c.getDeclaredField("lists");
            Class<?> clazz = field.getType();
            System.out.println(clazz);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");

        }
        double weight;
        while(scanner.hasNextLine()){
            lineStr = scanner.nextLine();
            n = Integer.parseInt(lineStr.split(" ")[0]);
            m = Integer.parseInt(lineStr.split(" ")[1]);
            weight = Double.parseDouble(lineStr.split(" ")[2]);
            graph.addEdge(n,m,weight);
        }
    }

    private static void readFile(Graph graph, Scanner scanner) {
        String lineStr;
        int n;
        int m;
        while(scanner.hasNextLine()){
            lineStr = scanner.nextLine();
            n = Integer.parseInt(lineStr.split(" ")[0]);
            m = Integer.parseInt(lineStr.split(" ")[1]);
            graph.addEdge(n,m);
        }
    }
}
