//package hazard;

public class BranchPrediction {
    public static void main(String args[]) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j <1000; j ++) {
                for (int k = 0; k < 10000; k++) {
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Time spent is " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j <1000; j ++) {
                for (int k = 0; k < 100; k++) {
                }
            }
        }
        end = System.currentTimeMillis();
        System.out.println("Time spent is " + (end - start) + "ms");
    }
}

/*
    Time spent is 2
    Time spent is 6ms
 */


/*
test_hazard.java:3: 错误: 类 BranchPrediction 是公共的, 应在名为 BranchPrediction.java 的文件中声明
public class BranchPrediction {
       ^

解决：这种报错是由于定义的public class和文件名不一致
 */

/*
错误: 找不到或无法加载主类 BranchPrediction.class
原因: java.lang.ClassNotFoundException: BranchPrediction.class
解决：把java文件中的包删掉或者注释掉后，重新编译并运行
 */
