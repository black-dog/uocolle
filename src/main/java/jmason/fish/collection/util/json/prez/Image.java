package jmason.fish.collection.util.json.prez;

public class Image {
	public Face[] faces;
	public String image;

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("faces:image="+"\r\n");
		for (Face face : faces) {
			sb.append(face).append(":"+"\r\n");
		}
		sb.append(":").append(image+"\r\n");
		return sb.toString();
	}
}
