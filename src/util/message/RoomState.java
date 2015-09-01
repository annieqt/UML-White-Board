package util.message;

import java.io.Serializable;

public class RoomState implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public final int id;
	public final String name;
	public final UserMsg creator;
	public final int maxMem;
	public final int nowMem;
	
	public RoomState(RoomMsg room,int nowMem) {
		this.id=room.id;
		this.name=room.name;
		this.creator=room.creator;
		this.maxMem=room.maxMem;
		this.nowMem=nowMem;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RoomState) {
			RoomState room=(RoomState) obj;
			return room.id==this.id;
		}else {
			return false;
		}
		
	}
}
