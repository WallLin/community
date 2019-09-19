package com.kyrie.community.dto;

/**
 * @author kyrie
 * @date 2019/9/19 - 20:50
 */
public class UserDTO {
    private Long id;
    private String name;
    private String bio; // 个性签名

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
