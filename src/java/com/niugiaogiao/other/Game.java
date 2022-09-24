package com.niugiaogiao.other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-09-24 15:52
 */
public class Game {

    public static int transportationHub(int[][] path) {
        Set<Integer> station = new HashSet<>();
        Map<Integer, List<Integer>> route = new HashMap<>();
        for (int i = 0; i < path.length; ++i) {
            station.add(path[i][0]);
            station.add(path[i][1]);
            if (route.get(path[i][0]) == null) {
                List<Integer> node = new LinkedList<>();
                node.add(path[i][1]);
                route.put(path[i][0], node);
            } else {
                route.get(path[i][0]).add(path[i][1]);
            }
        }
        for (Integer item : station) {
            if (route.get(item) == null) {
                // 证明所有节点都不指向他
                // 判断所有节点是否都指向他
                int isReturn = 0;
                for (Integer itemStation : station) {
                    if (itemStation.equals(item)) {
                        continue;
                    }
                    List<Integer> list = route.get(itemStation);
                    if (list == null) {
                        isReturn = 2;
                        continue;
                    }
                    // 如果有一个节点指向他证明这个节点没问题
                    boolean listRes = false;
                    for (Integer itemList : list) {
                        if (itemList.equals(item)) {
                            isReturn = 1;
                            listRes = true;
                            break;
                        }
                    }

                    if (!listRes) {
                        isReturn = 2;
                        break;
                    }
                }

                if (isReturn == 1) {
                    return item;
                }
            }
        }

        return -1;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] ballGame(int num, String[] plate) {
        char[][] maps = new char[plate[0].length()][plate[0].length()];
        createMap(maps, plate);
        List<Point> res = new LinkedList<>();
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps.length; j++) {
                if ((i == 0 && j == 0 || i == 0 && j == maps.length - 1)) {
                    continue;
                } else if (i == maps.length - 1 && j == 0 || i == maps.length - 1 && j == maps.length - 1) {
                    continue;
                }
                boolean t = false;
                if (i == 0 && run(maps, num, i, j, 1)) {
                    t = true;
                } else if (i == maps.length - 1 && run(maps, num, i, j, 0)) {
                    t = true;
                } else if (j == 0 && run(maps, num, i, j, 2)) {
                    t = true;
                } else if (j == maps.length - 1 && run(maps, num, i, j, 3)) {
                    t = true;
                }
                if (t) {
                    res.add(new Point(i, j));
                }
            }
        }
        int[][] result = new int[res.size()][2];
        int line = 0;
        for (Point point : res) {
            result[line][0] = point.x;
            result[line][1] = point.y;
            line++;
        }

        return result;
    }

    public static boolean run(char[][] maps, int num, int x, int y, int up) {
        if (num < 0) return false;
        if (maps[x][y] == 'O') return true;
        if (up == 0 && y - 1 >= 0 && maps[x][y - 1] == '.') {
            // 左
            return run(maps, num - 1, x, y - 1, up);
        } else if (up == 1 && x + 1 < maps.length && maps[x + 1][y] == '.') {
            // 下
            return run(maps, num - 1, x + 1, y, up);
        } else if (up == 2 && x + 1 < maps.length && maps[x + 1][y] == '.') {
            // 右
            return run(maps, num - 1, x + 1, y, up);
        } else if (up == 3 && x - 1 >= 0 && maps[x - 1][y] == '.') {
            // 上
            return run(maps, num - 1, x - 1, y, up);
        }
        if (up == 0) {
            if (maps[x][y] == 'W') {
                return run(maps, num - 1, x - 1, y, up);
            } else if (maps[x][y] == 'E') {
                return run(maps, num - 1, x + 1, y, up);
            }
        } else if (up == 1) {
            if (maps[x][y] == 'W') {
                return run(maps, num - 1, x, y - 1, up);
            } else if (maps[x][y] == 'E') {
                return run(maps, num - 1, x + 1, y, up);
            }
        } else if (up == 2) {
            if (maps[x][y] == 'W') {
                return run(maps, num - 1, x - 1, y, up);
            } else if (maps[x][y] == 'E') {
                return run(maps, num - 1, x + 1, y, up);
            }
        } else if (up == 3) {
            if (maps[x][y] == 'W') {
                return run(maps, num - 1, x, y - 1, up);
            } else if (maps[x][y] == 'E') {
                return run(maps, num - 1, x + 1, y, up);
            }
        }

        return false;
    }

    public static void createMap(char[][] maps, String[] plate) {
        int i = 0;
        for (String item : plate) {
            char[] chars = item.toCharArray();
            for (int j = 0; j < maps.length; j++) {
                maps[i][j] = chars[j];
            }
            i++;
        }
    }

    public static void main(String[] args) {
//        String[] plate = new String[]{"..E.", ".EOW", "..W."};
        String[] plate = new String[]{".....", "..E..", ".WO..", "....."};
        ballGame(4, plate);
    }
}
