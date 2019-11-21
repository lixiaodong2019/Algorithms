package linkedlist;

public class Hero {
    public final int no;
    private String name;
    private String nickName;

    public Hero(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Hero[" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ']';
    }
}