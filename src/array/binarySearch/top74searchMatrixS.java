package array.binarySearch;
//注意审题，相信直觉--导向最优解一般啊哈哈哈哈
/**
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 *
 *         1. 每行中的整数从左到右按非严格递增顺序排列。
 *         2. 每行的第一个整数大于前一行的最后一个整数。
 *         给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 */
public class top74searchMatrixS {
//利用第二条信息， 在matrix的第一列中找到不大于target的最后一个数， 然后就在那一行利用二分法寻找
    // >=target 的最右边 的数字的变种使用在这了   upperbound <target <=downbound, 这里的downbound就是要找到的数
    //
    public static boolean searchMatrix_twoBinarySearch(int[][] matrix, int target) {
        int column = matrix[0].length;
        int row = matrix.length;
        if (column == 0 || row == 0) return false;

        int upperrow =0,downrow =row-1;
        int ansrow = -1;
        while (upperrow<=downrow){
            int middle = upperrow+((downrow-upperrow)>>1);
            if (target==matrix[middle][0]){
                return true;
            }
            if (target<matrix[middle][0]){
                //最后一次一定是 l=r,这个时候还没找到的话,>=target 的最右边 的数字的变种使用在这了   upperbound <target <=downbound,
                // 这里的downbound就是要找到的数
                downrow=middle-1;
                //注意这里需要注意java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 1
                // 如果只有一row 的matrix, 那么downrow =-1,这时候就只需要在这一列查询
                ansrow= downrow<0?0:downrow;
            }else {
                upperrow=middle+1;
            }
        }
        //代码到这如果没有return true, 说明不在第一列，继续在那ansrow那一列进行二分查询
        int leftcolumn = 0,rightcolumn=column-1;
        while (leftcolumn<=rightcolumn){
            int middleofcolumn = leftcolumn+((rightcolumn-leftcolumn)>>1);


            if (target==matrix[ansrow][middleofcolumn]){
                return true;
            }
            if (target<matrix[ansrow][middleofcolumn]){
                rightcolumn=middleofcolumn-1;
            }else {
                leftcolumn=middleofcolumn+1;
            }
        }

        return false;

    }
    //利用第二条信息，可以将第二行的数组放到第一行结尾， 以此类推，再用二分法也是一样的，这样总体的复杂度就是log(m*n),这里有m*n个数字
    //代码实现时，可以二分升序数组的下标，将其映射到原矩阵的行和列上
    public static boolean searchMartix_append(int[][] matrix, int target) {
        int column = matrix[0].length;
        int row = matrix.length;
        if (column == 0 || row == 0) return false;
        /*//准备一个一维数组来存储这个二维数组的数
        int[] allnums = new int[row*column];
        int index =0;
        //这里需要二外的空间不好，如果不使用额外的空间怎么写
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                //这里要计算 convert之后的index,
                allnums[index++]=matrix[i][j];
            }

        }
*/
        int left = 0,right = row*column-1; //3*4=12,中点就是6，也就是第一行+x=6
        /**
         * `m x n` 的矩阵，其中 `m` 是行数，`n` 是列数
         * 1  2  3  4
         * 5  6  7  8
         * 9 10 11 12
         * 将其展平成一维数组后，会变成
         *
         * 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
         *
         * - **行索引 `row`**：在一维数组中，每 `n` 个元素构成一行。因此，`mid / n` 给出的是当前元素所在的行索引。例如，`mid = 5` 时，`5 / 4 = 1`，
         * 表示第 2 行（因为索引从 0 开始）
         * - **列索引 `col`**：在一维数组中，`mid % n` 给出的是当前元素在其所在行中的列索引。例如，`mid = 5` 时，`5 % 4 = 1`，表示第 2 列（因为索引从 0 开始）。
         具体来说：
         - `mid / n` 计算的是当前元素在一维数组中属于哪一行。因为每一行有 `n` 个元素，所以 `mid / n` 就是当前元素所在的行索引。
         - `mid % n` 计算的是当前元素在一维数组中属于该行的哪个位置。因为 `%` 运算符返回的是除法的余数，所以 `mid % n` 就是当前元素在其所在行中的列索引。



         */
        while (left<=right){
           int mid = left+((right-left)>>1); //第几个数
           int x = matrix[mid/column][mid%column]; //个人认为数学天赋不太好这里，不太好理解一开始怎么求row,column,后来一看解释都懂了，有时候还是要直觉，直觉，直觉，不必要的时候不需要推理，不需要一点点推理，直觉
            if (x==target) {return true;}
            if (x<target){left=mid+1;}
            if (x>target){right=mid-1;}
        }
      return false;


    }




    //每行二分法， 从第一行到最后一行,这个不是最好的解法， 复杂度得有个n*log2n,没有用上第二个条件-每行的第一个整数大于前一行的最后一个整数。
    public static boolean searchMatrix(int[][] matrix, int target) {
        int column = matrix[0].length;
        int row = matrix.length;
        if (column == 0 || row == 0) return false;
        for (int i = 0; i < row; i++) {
            int leftcolumn = 0,rightcolumn=column-1;
            while (leftcolumn<=rightcolumn){
                int middleofcolumn = leftcolumn+((rightcolumn-leftcolumn)>>1);
                if (target==matrix[i][middleofcolumn]){
                    return true;
                }
                if (target<matrix[i][middleofcolumn]){
                    rightcolumn=middleofcolumn-1;
                }else {
                    leftcolumn=middleofcolumn+1;
                }
            }
        }

        return false;

    }
    public static void main(String[] args) {
        
        //[[1,3,5,7],[10,11,16,20],[23,30,34,60]]
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        // Example: Search for a target value in the matrix
        int target = 20;
       // boolean found = searchMatrix(matrix, target);
       // boolean found = searchMatrix_twoBinarySearch(matrix,target);
        boolean found = searchMartix_append(matrix,target);
        System.out.println("Target " + target + (found ? " found" : " not found") + " in the matrix.");

    }
}
