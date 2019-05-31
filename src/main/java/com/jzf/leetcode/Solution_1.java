package com.jzf.leetcode;

/**
 * 在无限的平面上,机器人最初位于(0,0)处,面朝北方.机器人可以接受下列三条指令之一:
 * <p>
 * "G": 直走1个单位
 * "L": 左转90度
 * "R": 右转90度
 * <p>
 * 机器人按顺序执行指令instructions,并一直重复它们.
 * <p>
 * 只有在平面中存在环使得机器人永远无法离开时,返回true.否则返回false.
 *
 * @author Jia ZhiFeng <312290710@qq.com>
 * @date 2019/5/31 10:54:21
 */
public class Solution_1 {
    private int x = 0, y = 0;
    private Direction direction = Direction.NORTH;

    public boolean isRobotBounded(String instructions) {
        if (instructions.length() < 1 || instructions.length() > 100)
            throw new RuntimeException("length error:" + instructions.length());

        if (!instructions.matches("[GLR]*"))
            throw new RuntimeException("instructions error:" + instructions);

        x = 0;
        y = 0;
        direction = Direction.NORTH;

        //模拟机器人走步,模拟4次.如果4之后还没有回到原点,就判定失败.
        for (int j = 1; j <= 4; j++) {
            walk(instructions);
            if (x == 0 && y == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据指令集,模拟机器人走步.
     *
     * @param instructions 指令集
     */
    private void walk(String instructions) {
        for (int i = 0; i < instructions.length(); i++) {
            char instruction = instructions.charAt(i);
            switch (instruction) {
                case 'G':
                    switch (direction) {
                        case EAST:
                            x++;
                            break;
                        case SOUTH:
                            y--;
                            break;
                        case WEST:
                            x--;
                            break;
                        case NORTH:
                            y++;
                            break;
                    }
                    break;
                case 'L':
                    direction = direction.change('L');
                    break;
                case 'R':
                    direction = direction.change('R');
                    break;
            }
        }
    }

    /**
     * 机器人移动方向
     */
    private enum Direction {
        /**
         * 东边
         */
        EAST,
        /**
         * 南边
         */
        SOUTH,
        /**
         * 西边
         */
        WEST,
        /**
         * 北边
         */
        NORTH;

        /**
         * 'L'：左转90度
         * 'R'：右转90度
         *
         * @param turn 左转，右转
         * @return 转向后的方向
         */
        public Direction change(char turn) {
            switch (this) {
                case EAST:
                    return (turn == 'L') ? NORTH : SOUTH;
                case SOUTH:
                    return (turn == 'L') ? EAST : WEST;
                case WEST:
                    return (turn == 'L') ? SOUTH : NORTH;
                case NORTH:
                    return (turn == 'L') ? WEST : EAST;
            }
            return this;
        }
    }


    public static void main(String[] args) {
        Solution_1 solution1 = new Solution_1();
        System.out.println(solution1.isRobotBounded("GG"));
        System.out.println(solution1.isRobotBounded("GL"));

        long start = System.currentTimeMillis();
        System.out.println(solution1.isRobotBounded("GGLLGG"));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}