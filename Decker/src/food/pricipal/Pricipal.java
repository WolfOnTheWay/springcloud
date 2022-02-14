package food.pricipal;

//主食的基类
public abstract class Pricipal {
    public String name;
    public String getName(){
        return this.name;
    }
    //计算价格
    abstract public Integer count();
}
