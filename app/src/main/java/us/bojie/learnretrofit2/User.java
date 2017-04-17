package us.bojie.learnretrofit2;

/**
 * Created by bojiejiang on 4/16/17.
 */

public class User {

    /**
     * head_url : https://avatars2.githubusercontent.com/u/7081069?v=3&s=460
     * id : 1
     * username : Bojie
     */

    private String head_url;
    private int id;
    private String username;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public User() {
    }

    public User(String head_url, int id, String username) {
        this.head_url = head_url;
        this.id = id;
        this.username = username;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
