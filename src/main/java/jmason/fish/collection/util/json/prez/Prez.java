package jmason.fish.collection.util.json.prez;

public class Prez {
    public Image[] images;
    public int images_processed;
    
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("images:images_processed="+"\r\n");
		for (Image image : images) {
			sb.append(image).append(":"+"\r\n");
		}
		sb.append(":").append(images_processed+"\r\n");
		return sb.toString();
	}
}
