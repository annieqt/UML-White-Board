package util.message;

import java.io.Serializable;

public class UserMsg implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public final String id;
	public final String name;
	public final String password;
	
	public UserMsg(String id,String name,String password) {
		this.id=id;
		this.name=name;
		this.password=password;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UserMsg) {
			UserMsg user=(UserMsg) obj;
			return user.id.equals(this.id);
		}else {
			return false;
		}
		
	}
	
	
}
