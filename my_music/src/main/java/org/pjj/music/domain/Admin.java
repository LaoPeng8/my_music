package org.pjj.music.domain;

/**
 * @author PengJiaJun
 * @Date 2021/11/7 16:04
 */
public class Admin {
    
    private Integer id;
    private String name;
    private String password;

    public Admin() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
