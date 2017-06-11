package entity;

/**
 * 分页对象
 * 实现分页功能
 */
public class Page {
    private int num; //当前页号
    private int size;//一页的最大记录数
    private int rowCount;//最大记录数
    private int pageCount;//页面总数
    private int startRow;//开始行号
    private int first = 1;//第一页
    private int last;//最后一页
    private int prev;//上一页
    private int next;//下一页

    /**
     * 判读字符串是否是数字
     * 只能匹配正整数
     */
    private boolean isNumber(String str) {
        for (int i=0;i<str.length();i++) {
            char c = str.charAt(i);
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 构造方法
     */
    public Page(int size, String str_num, int rowCount) {
        //起始页号默认为1
        int num = 1;
        //如果传递的num不是空并且是正整数
        if (str_num != null && isNumber(str_num)) {
            num = Integer.parseInt(str_num);
        }
        this.num = num;
        this.size = size;
        this.rowCount = rowCount;
        //页面总数 = 总记录数/每一页显示的记录数
        this.pageCount = (int) Math.ceil((double) rowCount / size);
        //当前页数必须在第一页和最后一页之间
        this.num = Math.min(this.num, pageCount);
        this.num = Math.max(this.num, this.first);
        //当前页开始行数
        this.startRow = (this.num - 1) * size;
        this.last = pageCount;
        this.next = Math.min(this.pageCount, this.num + 1);
        this.prev = Math.max(this.first, this.num - 1);
    }

    //getter and setter

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }
}
