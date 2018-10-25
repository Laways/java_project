package 并查集;

import org.junit.Test;

import java.util.Random;

public class TestUnionFind {

    private double testUnionFind(UnionFind unionFind, int timers){
        int size = unionFind.getSize();
        Random random = new Random();
        long beginTime = System.nanoTime();
        for (int i = 0; i < timers; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            unionFind.unionElements(a, b);
        }

        for (int i = 0; i < timers; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            unionFind.isConnect(a, b);
        }
        long endTime = System.nanoTime();

        return (endTime - beginTime) / 1000000000.0;
    }

    @Test
    public void  test(){
        int capacity = 1000000;
        int timers = 1000000;
//        UnionFindArray unionFindArray = new UnionFindArray(capacity);
//        UnionFindTree unionFindTree = new UnionFindTree(capacity);
        UnionFindTreeSize unionFindTreeSize = new UnionFindTreeSize(capacity);
        UnionFindTreeRank unionFindTreeRank = new UnionFindTreeRank(capacity);
        UnionFindRankCompress unionFindRankCompress = new UnionFindRankCompress(capacity);
//        UnionFindRankCompress2 unionFindRankCompress2 = new UnionFindRankCompress2(capacity);
//        System.out.println("unionFindArray_time:"+testUnionFind(unionFindArray, timers));
//        System.out.println("unionFindTree_time:"+testUnionFind(unionFindTree, timers));
        System.out.println("unionFindTreeSize_time:"+testUnionFind(unionFindTreeSize, timers));
        System.out.println("unionFindTreeRank_time:"+testUnionFind(unionFindTreeRank, timers));
        System.out.println("unionFindRankCompress_time:"+testUnionFind(unionFindRankCompress, timers));
//        System.out.println("unionFindRankCompress2_time:"+testUnionFind(unionFindRankCompress2, timers));

    }

}
