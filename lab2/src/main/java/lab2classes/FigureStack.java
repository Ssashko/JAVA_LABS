package lab2classes;

public class FigureStack {
    private Figure[] stack;
    final int startSize = 5;
    private int realSize;
    private int head;
    FigureStack() {
        stack = new Figure[startSize];
        realSize = startSize;
        head = 0;
    }

    public Figure pop()
    {
        if(head == 0)
            throw new OutOfMemoryError("empty stack");
        return stack[--head];
    }
    public void push(Figure obj)
    {
        if(realSize <= head)
            ExtendArr();
        stack[head++] = obj;
    }
    private void ExtendArr(){
        Figure[] _oldStack = stack;
        stack = new Figure[realSize*2];
        for(int i=0;i < realSize;i++)
            stack[i] = _oldStack[i];
        realSize*=2;
    }
    private void printAllFigures () {
        for(int i=0; i < head;i++)
            System.out.println(stack[i].call());
    }
    private void printAllFiguresSquare () {
        for(int i=0; i < head;i++)
            System.out.printf("Square = %f", stack[i].getSquare());
    }
    private void printAllFiguresPerimeter () {
        for(int i=0; i < head;i++)
            System.out.printf("Perimeter = %f", stack[i].getPerimeter());
    }


}
