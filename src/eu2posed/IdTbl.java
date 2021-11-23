/*
 * IdTbl.java
 *
 * Created on December 5, 2006, 4:37 PM
 */

package eu2posed;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to read and store data from id.tbl.
 * <p>
 * File description (from inferis.org):
 * <blockquote>
 * This file is a sort of "hit-test" matrix. With the data contained in this
 * file, you can easily find out which pixel on the map belongs to which
 * province.
 * <p>
 * This file is made up of two parts: offsets and data.
 * <p>
 * The <b>offsets</b> are a list of 7296 4-byte integers, which represent an
 * offset into the file pointing to the datablocks. Since there are 7296
 * offsets, this means that each datablock contains information about each
 * <i>scanline</i> on the map (since the map is 18944x7296 pixels).
 * <br>
 * The offsets are relative into the file: offset 0 does not mean the beginning
 * of the file, but the first byte right after the offsets are done plus 4 bytes.
 * <pre>
 * # index is the index of the line you want to read (0-7295)
 * relative_offset = read_file( index )
 * real_offset = 4 * ( map_height + 1 + relative_offset )
 * </pre>
 * <p>
 * Now we know the offset of the datablock for each line.
 * Each of these lines contains a number of smaller blocks of 6 bytes each.
 * <br>
 * A small block represents a <i>span</i> in the line. It contains a start X
 * coordinate, an end X coordinate and an province Id.
 * <p>
 * The blocks are formatted like this:
 * <table border="1" cellpadding="2" cellspacing="0">
 * <tr>
 * <td>bytes 1 & 2</td>
 * <td>bytes 3 & 4</td>
 * <td>bytes 5 & 6</td>
 * </tr>
 * <tr>
 * <td>start X value</td>
 * <td>province ID</td>
 * <td>end X value</td>
 * </tr>
 * </table>
 * <p>
 * So, each line contains a number of spans. All spans combines form a whole
 * line: the first block starts with start-x value 0, the last block has and
 * end-x value of 18944 (the map size). So you know when to stop reading when
 * the end-x value is equal or greater than the map size. The block after the
 * last block (in a line) is the first block of the next line.
 * </blockquote>
 * @author Michael Myers
 */
public class IdTbl {
    
    private static final int MAP_HEIGHT = 7296;
    private static final int MAP_WIDTH = 18944;
//    private static final int OFFSET_END = MAP_HEIGHT*4; // 4 bytes per int
    
    private final ProvinceData province;
    private BufferedImage image;
    
    private final ByteBuffer file;
    
    private static final Color oceanColor = new Color(111, 168, 223);
//    private static final Color landColor = new Color(255, 243, 200);
    private static final Color ptiColor = Color.BLACK;
    
    public IdTbl(String filename, ProvinceData data) {
        this(readRawFile(filename), data);
    }
    
    public IdTbl(final byte[] file, ProvinceData data) {
        this.file = ByteBuffer.wrap(file);
        this.file.order(ByteOrder.LITTLE_ENDIAN);
        province = data;
    }
    
    private static byte[] readRawFile(String filename) {
        FileInputStream stream = null;
        try {
            File file = new File(filename);
            byte[] buf = new byte[(int) file.length()];
            stream = new FileInputStream(file);
            if (stream.read(buf) != buf.length) {
                System.err.println("???");
            }
            stream.close();
            return buf;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IdTbl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IdTbl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stream != null)
                    stream.close();
            } catch (IOException ex) {
                Logger.getLogger(IdTbl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public int getId(int x, int y) {
        int relativeOffset = file.getInt(y*4);
        int realOffset = 4 * (MAP_HEIGHT + 1 + relativeOffset);
        
        // Now read the line until we come to the right x
        
        int endOffset = 4 * (MAP_HEIGHT + 1 + file.getInt((y+1)*4));
        
        for (; realOffset < endOffset; realOffset += 8) {
            short startX = file.getShort(realOffset);
            if (startX > x)
                continue;
            short endX = file.getShort(realOffset + 4);
            if (endX > x) {
                // found what we want.
                return (int) file.getShort(realOffset + 2);
            }
        }
        
        return -1;
    }

    
    private final BufferedImage draw() {
        final BufferedImage img =
                new BufferedImage(MAP_WIDTH, MAP_HEIGHT, BufferedImage.TYPE_BYTE_INDEXED);
        
        final Graphics2D g = img.createGraphics();
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, MAP_WIDTH, MAP_HEIGHT);
        
        g.setColor(Color.BLACK);
        
//        final int end = file.getInt(0);
        
        for (int y = 1; y < MAP_HEIGHT; y++) {
            int relativeOffset = file.getInt(y*4);
            int realOffset = 4 * (MAP_HEIGHT + 1 + relativeOffset);
            
//            System.out.println("relativeOffset = " + relativeOffset);
//            System.out.println("realOffset = " + realOffset);
            
            short x = file.getShort(realOffset);
            realOffset += 2;
            
            while (true) {
                short startX = x;
                short id = file.getShort(realOffset);
                x = file.getShort(realOffset + 2);
                
                if (province.isPTI(id)) {
                    g.setColor(ptiColor);
                    g.drawLine(startX, y, x, y);
                } else if (!province.isLand(id)) {
                    g.setColor(oceanColor);
                    g.drawLine(startX, y, x, y);
                } /*else {
                    g.setColor(landColor);
                    g.drawLine(startX, y, endX, y);
                }*/
                g.setColor(Color.BLACK);
                
                // borders
                g.drawLine(startX, y, startX+1, y);
                g.drawLine(x-1, y, x, y);
                
                if (x >= MAP_WIDTH) {
                    break;
                }
                
                realOffset += 4;
            }
        }
        
        g.dispose();
        
        return img;
    }
    

    public BufferedImage getImage() {
        if (image == null)
            image = draw();
        return image;
    }
    
     
//    public static void main(String[] args) throws FileNotFoundException, IOException {
//        System.out.println("Loading map...");
//        final String filename = "C:\\Program Files\\Strategy First\\Europa Universalis 2\\Map\\id.tbl";
//        File file = new File(filename);
//        byte[] buf = new byte[(int)file.length()];
//        FileInputStream stream = new FileInputStream(file);
//        if (stream.read(buf) != buf.length)
//            System.err.println("???");
//        stream.close();
//        
//        System.out.println("Reading provinces...");
//        final IdTbl id = new IdTbl(buf);
//        
//        System.out.println("Reading map...");
//        final BufferedImage image = id.getImage();
//        
//        JFrame frame = new JFrame("id.tbl Test");
//        
//        JPanel panel = new JPanel() {
//            private final Dimension preferredSize =
//                    new Dimension(image.getWidth(), image.getHeight());
//            
//            @Override
//            protected void paintComponent(Graphics g) {
//                ((Graphics2D)g).drawImage(image, 0, 0, null);
//            }
//
//            @Override
//            public Dimension getPreferredSize() {
//                return preferredSize;
//            }
//        };
//        
//        final JLabel statusLabel = new JLabel();
//        
//        panel.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                int ID = id.getId(e.getX(), e.getY());
//                statusLabel.setText(id.province.getName(ID) + " (" + ID + ")");
//            }
//        });
//        
//        frame.setLayout(new BorderLayout());
//        frame.add(new JScrollPane(panel), BorderLayout.CENTER);
//        frame.add(statusLabel, BorderLayout.SOUTH);
////        frame.pack();
//        Rectangle bounds =
//                GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
//        frame.setSize(bounds.width, bounds.height);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//        
////        try {
////            ImageIO.write(image, "BMP", new File("out.bmp")); // probably huge
////            ImageIO.write(image, "JPG", new File("out.jpg")); // probably also huge
////        } catch (IOException ex) {
////            System.err.println("Error writing image");
////        }
//
//    }

    public ProvinceData getProvinceData() {
        return province;
    }

}
