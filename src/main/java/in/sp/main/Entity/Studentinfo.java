package in.sp.main.Entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Studentinfo implements UserDetails {
	String username=null;
	String password=null;
	
	public Studentinfo(Student student) {
		username=student.getUsername();
		password=student.getPassword();
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
	
		return password;
	}

	@Override
	public String getUsername() {
	
		return username;
	}
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
