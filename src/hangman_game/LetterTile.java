package hangman_game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class LetterTile extends JLabel
{
    private final int pref_width, pref_height;
    
    private final char img_letter;
    
    private final String img_dir, img_type;
    
    private String path;
    
    private BufferedImage image;
    
    private MouseListener tileListener;
    
    public LetterTile(char letter, String dir, String type)
    {
        img_letter = letter;
        img_dir = dir;
        img_type = type;
        
        pref_width = pref_height = 50;
        
        setPreferredSize(new Dimension(pref_width,pref_height));
        path = img_dir + img_letter + img_type;
        image = loadImage(path);
    }
    
    private BufferedImage loadImage(String path)
    {
        BufferedImage img = null;

        try 
        {
            img = ImageIO.read(new File(path));
        } 

        catch (IOException ex) 
        {
            System.err.println("loadImage(): Error: Image at "
                    + path + " could not be found");
            System.exit(1);
        }

        return img;
    }
    
    private void loadNewImage(String suff)
    {
        path = img_dir + img_letter + "_" + suff + img_type;
        image = loadImage(path);
        repaint();  
    }
    
    public char guess()
    {
        loadNewImage("guessed");
        removeTileListener();
        return img_letter;
    }
    
    public void addTileListener(MouseListener l) 
    { 
        tileListener = l;
        addMouseListener(tileListener);
    }
    
    public void removeTileListener(){
        removeMouseListener(tileListener);
    }
    
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.drawImage(image, 
                0, 
                0, 
                pref_width, 
                pref_height, 
                null);
    }
}

