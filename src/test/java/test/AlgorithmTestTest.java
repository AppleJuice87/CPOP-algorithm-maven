package test;

import algorithm.controller.Algorithm;
import algorithm.model.Pair;
import algorithm.model.Point;
import algorithm.service.PointHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.*;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AlgorithmTestTest {

    PointHandler pointHandler;

    @BeforeAll
    void set() {
        pointHandler = new PointHandler();

        Set<List<Integer>> set = new HashSet<>();
        Random rd = new Random();

        while (set.size() < 100) {
            List<Integer> tempList = new ArrayList<>();
            tempList.add(rd.nextInt(1000));
            tempList.add(rd.nextInt(1000));

            set.add(tempList);
        }

        for (List<Integer> num : set) {
            pointHandler.add(new Point(num.get(0), num.get(1)));
        }
    }

    @Test
    void test() {
        //* 세팅 시간 제외 용도 메소드
        Assertions.assertEquals(1, 1);
    }

    @Test
    void test1() {
        //* 분할정복 알고리즘
        Pair closestPair = Algorithm.CPOP(pointHandler);
        log.debug("분할정복 알고리즘 : {}", closestPair.toString());
        log.debug("거리 : {}", closestPair.getDistance());
    }

    @Test
    void test2() {
        //* 모든 경우의 수를 다 탐색하는 알고리즘
        Pair closestPair = pointHandler.getClosestPair();
        log.debug("브루트포스 알고리즘 : {}", closestPair.toString());
        log.debug("거리 : {}", closestPair.getDistance());
    }
}