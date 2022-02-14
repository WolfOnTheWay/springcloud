package org.example;

final class DefaultSearchService{
    public Object search(String key){
//        long t1 = System.nanoTime();
        System.out.println("search by "+key);
//        long t2 = System.nanoTime();
//        System.out.println(t2-t1);
        return "result from database";
    }
}
class LogSearchService {
    private DefaultSearchService searchService;
    //依赖注入
    public LogSearchService(DefaultSearchService searchService){
        this.searchService = searchService;
    }
    public Object search(String key) {
        long t1 = System.nanoTime();
        Object result=searchService.search(key);
        long t2 = System.nanoTime();
        System.out.println(t2-t1);
        return result;
    }
}
public class TestCompose {
    public static void main(String[] args) {
        LogSearchService ts = new LogSearchService(new DefaultSearchService());
        ts.search("大西瓜");
    }
}
