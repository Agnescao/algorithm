package 递归;

import java.util.Stack;

public class reverseStackUsingRecursive {
    public static void reverseStack(java.util.Stack<Integer> stack) {
      if (stack.isEmpty()){
          return;
      }
      int number = f(stack);//这个部分就是将
        reverseStack(stack);
        stack.push(number);

    }
    //将栈低元素移除掉
    //上面的元素搞下来
    //返回移除掉的栈低元素
    private static int f(Stack<Integer> stack) {
        int res = stack.pop();
    if (stack.isEmpty()){
        return res ;
    }else {
        int last = f(stack);
        stack.push(res);
        return last;
    }
    }

    public static void main(String[] args) {

    }
}
