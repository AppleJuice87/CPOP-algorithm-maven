package test;

import algorithm.controller.Algorithm;
import algorithm.model.Pair;
import algorithm.model.Point;
import algorithm.service.PointHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class AlgorithmTest {
    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 파일 입력 추가.
        String filePath = "src/main/resources/input7.txt";
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int num = Integer.parseInt(br.readLine());
        PointHandler pointList = new PointHandler();

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            pointList.add(
                    new Point(
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken())
                    )
            );
        }

        Pair closestPair = Algorithm.CPOP(pointList);

        sb.append("평면에 있는 총 점의 수는 ").append(num).append("개입니다.\n")
                .append("가장 가까운 거리에 있는 두 점은 ")
                .append(closestPair.getP1().toString())
                .append(" 와 ")
                .append(closestPair.getP2().toString())
                .append(" 이고 거리는 ")
                .append(String.format("%.4f", closestPair.getDistance()))
                .append("입니다.");

        System.out.println(sb);
    }
}
