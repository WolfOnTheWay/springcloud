package org.D01;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.*;
import java.util.Date;

class Article implements Serializable{
    private static final long serialVersionUID = -8908670959958788585L;
    private Integer id;
    private String title;
    private String content;
    private Date createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
public class TestHessian {
    public static void serialize(Object obj)throws Exception{
        OutputStream os = new FileOutputStream("1.xml");
        Hessian2Output out = new Hessian2Output(os);
        Article article = new Article();

        out.writeObject(obj);
        out.close();
    }
    public static Object deSerialize()throws Exception{
        InputStream is = new FileInputStream("1.xml");
        Hessian2Input in = new Hessian2Input(is);
        Object obj = in.readObject();
        return obj;
    }
    public static void main(String[] args) throws Exception {
        Article article = new Article();
        article.setId(1);
        article.setTitle("1111");
        article.setContent("222");
        article.setCreatedTime(new Date(2021,10,01));
        serialize(article);
        Object obj = deSerialize();
        System.out.println(obj);
    }
}
