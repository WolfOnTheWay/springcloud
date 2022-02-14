package food.sub;

import food.pricipal.Pricipal;

public class SubNoodle extends Sub {
    Pricipal pricipal;
    public SubNoodle(Pricipal pricipal){
        this.pricipal = pricipal;
    }
    @Override
    public String getName(){
        return pricipal.getName()+"+"+"面";
    }
    @Override
    public Integer count(){
        return pricipal.count()+2;
    }
}
