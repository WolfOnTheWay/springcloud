package food.sub;

import food.pricipal.Pricipal;

public class SubEgg extends Sub{
    Pricipal pricipal;
    public SubEgg(Pricipal pricipal){
        this.pricipal = pricipal;
    }
    @Override
    public String getName(){
        return pricipal.getName()+"+"+"鸡蛋";
    }
    @Override
    public Integer count(){
        return pricipal.count()+1;
    }
}
