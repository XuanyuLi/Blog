package model;

/**
 * Created by lixuanyu
 * on 2017/7/1.
 */
public class User {
    private Integer id;
    private String title;
    private String time;
    private String content;
    private Integer userId;

    public User(Integer id, String title, String time, String content, Integer userId) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.content = content;
        this.userId = userId;
    }

    public User() {
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
