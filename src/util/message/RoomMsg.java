package util.message;

import java.io.Serializable;

public class RoomMsg implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public final int id;
	public final String name;
	public final UserMsg creator;
	public final int maxMem;
	
	public RoomMsg(int id,String name,UserMsg creator,int maxMem) {
		this.id=id;
		this.name=name;
		this.creator=creator;
		this.maxMem=maxMem;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RoomMsg) {
			RoomMsg room=(RoomMsg) obj;
			return room.id==this.id;
		}else {
			return false;
		}
		
	}
}
