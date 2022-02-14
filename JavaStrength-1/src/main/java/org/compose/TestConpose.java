package org.compose;

interface MailService{
    void send(String msg);
}
final class DefaultMailService implements MailService{
    @Override
    public void send(String msg) {
        System.out.println("send "+msg);
    }
}
//功能扩展
class LogMailService{
    private MailService mailService;

    public LogMailService(MailService mailService) {
        this.mailService = mailService;
    }
    public void send(String msg){
        System.out.println("start:"+System.nanoTime());
        mailService.send(msg);
        System.out.println("end:"+System.nanoTime());
    }
}


public class TestConpose {
    public static void main(String[] args) {
        LogMailService ls =  new LogMailService(new DefaultMailService());
        ls.send("大声发");
    }
}
