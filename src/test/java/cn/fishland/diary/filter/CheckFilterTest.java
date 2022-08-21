package cn.fishland.diary.filter;

import org.junit.jupiter.api.Test;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
class CheckFilterTest {

    @Test
    public void testSuShu() {
        int x = 0;
        boolean flag;
        for (int i = 101; i <= 200; i++) {
            flag = true;
            for (int i1 = 2; i1 < i; i1++) {
                if (i % i1 == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                x++;
                System.out.println("素数：" + i);
            }
        }
        System.out.println("总数：" + x);
    }

}