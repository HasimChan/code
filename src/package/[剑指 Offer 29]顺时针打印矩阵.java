//输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。 
//
// 
//
// 示例 1： 
//
// 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 限制： 
//
// 
// 0 <= matrix.length <= 100 
// 0 <= matrix[i].length <= 100 
// 
//
// 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/ 
// Related Topics 数组 
// 👍 260 👎 0


import java.util.ArrayList;
//2021/6/16
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[]{};
        }
        int up = 0;
        int down = matrix.length;
        int left = 0;
        int right = matrix[0].length;
        int index = 0;

        int[] ret = new int[matrix.length * matrix[0].length];

        while (up < down && left < right) {
            for (int i = left; i < right; i++) {
                ret[index++] = matrix[up][i];
            }

            if (up + 1 >= down) {
                break;
            }

            for (int i = up + 1; i < down; i++) {
                ret[index++] = matrix[i][right - 1];
            }

            if (right - 2 < left) {
                break;
            }

            for (int i = right - 2; i >= left; i--) {
                ret[index++] = matrix[down - 1][i];
            }

            for (int i = down - 2; i > up; i--) {
                ret[index++] = matrix[i][left];
            }
            up++; down--; left++; right--;
        }
        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
