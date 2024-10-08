package sustain;
 
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
 
public class GameBanner {
	
	
	public static void printGameBanner(String textToPrint) {
		
		int width = 1000;
        int height = 50;
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
        
        Graphics2D g2D = (Graphics2D) graphics;
        
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2D.drawString(textToPrint, 10, 20);
        
        for(int y = 0; y<height; y++) {
        	StringBuilder stringBuilder = new StringBuilder();
        	for(int x = 0; x<width; x++) {
        		stringBuilder.append(image.getRGB(x, y) == -16777216 ? " " : "*");
        	}
        	
        	if(stringBuilder.toString().trim().isEmpty()) {
        		continue;
        	}
        	System.out.println(stringBuilder);
        }
		
		
	}
 
	public static void main(String[] args) {
		
		
        
	}
 
}