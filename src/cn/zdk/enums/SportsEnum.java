package cn.zdk.enums;

/*
* 看timeUnit 有感练习
* 每个枚举实例都可以重写方法,拥有自己的成员变量和实例,这个例子可以加深对枚举的理解
*
*
* */
public enum SportsEnum {
    FOOTBALL("足球", "name") {
        @Override
        public void play() {
            System.out.println("play " + FOOTBALL.name);
        }
    };

    public String name;
    public String value;

    SportsEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public abstract void play();

    public static void main(String[] args) {
        FOOTBALL.play();
    }
}
