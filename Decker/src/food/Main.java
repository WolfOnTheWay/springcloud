package food;

import food.pricipal.Noodle;
import food.pricipal.Pricipal;
import food.sub.SubEgg;
import food.sub.SubNoodle;

public class Main {
    public static void main(String[] args) {
        Pricipal pricipal = new Noodle();
        pricipal = new SubEgg(pricipal);
        pricipal = new SubEgg(pricipal);
        pricipal = new SubNoodle(pricipal);
        System.out.println("食物"+":"+pricipal.getName());
        System.out.println("价格"+":"+pricipal.count());
    }
}
