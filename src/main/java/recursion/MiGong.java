package recursion;


import java.util.LinkedList;

/**
 * 利用递归解决迷宫问题
 */
public class MiGong {

    //二维数组,0表示可以走空间,1表示障碍,2表示可以走,3表示不可以走
    int[][] arr; //数组
    int row; //行和列数
    int col;
    LinkedList<XY> path;

    public static void main(String[] args) {
        MiGong miGong = new MiGong(10, 10);
        miGong.init();
        miGong.addBarrie(0.3);
        miGong.setWay(1, 1);
        miGong.show();
    }

    public MiGong(int row, int col) {
        this.row = row;
        this.col = col;
        this.arr = new int[row][col];
        path = new LinkedList<>();
        init();
        addBarrie(0.3);
//        setWay(1, 1);
    }

    /**
     * 初始化
     * 1.4面都是墙
     * 2.第3行有障碍物
     */
    public void init() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1) {
                    arr[i][j] = 1;
                }
                if (j == 0 || j == col - 1) {
                    arr[i][j] = 1;
                }
            }
        }
        arr[3][0] = 1;
        arr[3][1] = 1;
        arr[3][2] = 1;
    }

    /**
     * @param percent 障碍物的权重
     */

    public void addBarrie(double percent) {
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++) {
                if (i == 1 && j == 1 || i == row - 2 && j == col - 2) continue;
                int rand = (int) (Math.random() * (1.0f) / percent);
                arr[i][j] = rand > 0 ? 0 : 1;
            }
        }
    }


    /**
     * 打印迷宫
     */
    public void show() {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
//                System.out.print(arr[i][j] + " ");
                if (arr[i][j] == 0) {
                    System.out.print("░");
                } else if (arr[i][j] == 1) {
                    System.out.print("▒");
                } else if (arr[i][j] == 2) {
                    System.out.print("▓");
                }
            }
            System.out.println();
        }
    }

    /**
     * 设置路径
     *
     * @param i 当前处在的格子
     * @param j 移动策略: 左-上-右-下
     * @return 是否能走通
     */
    public boolean setWay(int i, int j) {
        if (arr[row - 2][col - 2] == 2) { //表示找到
            return true;
        } else {
            if (arr[i][j] == 0) { //当前点还没走
                arr[i][j] = 2; //假设当前点可以走
                path.add(new XY(i,j));
                if (setWay(i - 1, j)) {
                    return true;
                } else if (setWay(i, j + 1)) {
                    return true;
                } else if (setWay(i + 1, j)) {
                    return true;
                } else if (setWay(i, j - 1)) {
                    return true;
                } else { //说明该点是走不通的
                    arr[i][j] = 3;
                    return false;
                }

            } else {
                return false;
            }
        }
    }

    public static class XY {
        int x;
        int y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
