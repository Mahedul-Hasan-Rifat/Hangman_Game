package hangman_game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage ;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Hangman extends JLabel
{
    private final int pref_width, pref_height;
    
    private final String img_base, img_dir, img_type ;
    
    private String path;
    
    private BufferedImage image;
    
    public Hangman(String base, String dir, String typ)
    {
        pref_width = 440;
        pref_height = 225;
        
        img_base = base;
        img_dir = dir;
        img_type = typ;
        
        path = img_dir + img_base + "_0" + img_type;
        setPreferredSize(new Dimension(pref_width, pref_height));

        image = loadImage(path);
    }
    
    private BufferedImage loadImage(String imagePath)
    {
        BufferedImage img = null;
        
        try
        {
            img = ImageIO.read(new File(imagePath));
        }
        
        catch(IOException ex)
        {
            System.out.println("Image doesn't exist");
            System.exit(1);
        }
        
        return img;
    }
    
    private void loadNewImage(String suff)
    {
        path = img_dir + img_base + "_" + suff + img_type;
        image = loadImage(path);
        repaint();
    }
    
    public void nextImage(int image_num)
    {
        loadNewImage(String.valueOf(image_num));
    }
    
    public void loseImage(){
        loadNewImage("lose");
    }
    
    public void winImage(){
        loadNewImage("win");
    }
    
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, pref_width, pref_height, null);
    } 
}
