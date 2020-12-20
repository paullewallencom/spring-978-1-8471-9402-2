package it.freshfruits.security;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.User;

public class FreshFruitUser extends User {

    public FreshFruitUser(String username, String password, boolean isEnabled, GrantedAuthority[] authorities, Object user) {
        super(username, password, isEnabled, true, true, true, authorities);
        this.setUserInfo(user);
    }

    public FreshFruitUser(String username, String password, boolean isEnabled, GrantedAuthority[] arrayAuths) {
        super(username, password, isEnabled, true, true, true, arrayAuths);
    }

    public Object getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Object userInfo) {
        this.userInfo = userInfo;
    }

    private Object userInfo;
    private static final long serialVersionUID = -343812156239227785L;
}
