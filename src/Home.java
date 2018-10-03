
public class Home {
	private int lat;
	private int lon;
	private int nb_room;
	private int value;
	
	
	public Home(int lat, int lon,int nb_room, int value) {
		this.lat = lat;
		this.lon = lon;
		this.nb_room = nb_room;
		this.value = value;
	}


	public int getLat() {
		return lat;
	}


	public void setLat(int lat) {
		this.lat = lat;
	}


	public int getLon() {
		return lon;
	}


	public void setLon(int lon) {
		this.lon = lon;
	}


	public int getNb_room() {
		return nb_room;
	}


	public void setNb_room(int nb_room) {
		this.nb_room = nb_room;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}
	
	
	
}
