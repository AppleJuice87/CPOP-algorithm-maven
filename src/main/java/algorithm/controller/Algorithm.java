package algorithm.controller;
import algorithm.model.Pair;
import algorithm.service.PointHandler;

public class Algorithm {
    public static Pair CPOP(PointHandler points) {

        // 1. 사이즈가 3개 보다 작으면 바로 최근접 쌍 찾기
        if (points.size() <= 3) {
            return points.getClosestPair();
        }

        // 2~4 하기 전에 point 리스트 x좌표 기준으로 정렬.
        points.sort();

        // 2~4 중앙값을 기준으로 왼쪽과 오른쪽 리스트로 재귀호출
        Pair cpL = CPOP(points.getLeftPart());
        Pair cpR = CPOP(points.getRightPart());

        // 5. 두 쌍 중에 거리가 짧은거 선택.
        Pair min = cpL.getDistance() < cpR.getDistance() ? cpL : cpR;

        // 그걸 기준으로 분할면 센터영역 탐색 수행.
        PointHandler centerList = points.getCenterList(min.getDistance());

        //! 센터에 있는 점 개수가 1개 이하면 cpC 쌍은 제외하고 얼리 리턴.
        if (centerList.size() < 2) {
            return min;
        }

        Pair cpC = centerList.getClosestPair();

        // cpL, cpC, cpR 중에서 거리가 가장 짧은 쌍 반환.
        // min 과 cpC 만 고려하면 됨.
//        return (cpL.getDistance() < cpC.getDistance()) ?
//                (cpL.getDistance() < cpR.getDistance() ? cpL : cpR) :
//                (cpC.getDistance() < cpR.getDistance() ? cpC : cpR);
        return (min.getDistance() < cpC.getDistance()) ? min : cpC;
    }
}
