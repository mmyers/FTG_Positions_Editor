/*
 * ProvinceData.java
 *
 * Created on Jan 12, 2008, 12:19:12 PM
 */

package eu2posed;

import eug.parser.EUGFileIO;
import eug.shared.GenericObject;
import eug.shared.Style;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Michael Myers
 */
public class ProvinceData {
    
    public static int NUM_PROVINCES = 2020;
    
    private final Map<Integer, Province> allProvs = new HashMap<>();
//    private final List<Province> extras = new ArrayList<Province>();
    
    private static final Pattern SEMICOLON = Pattern.compile(";");
    
    private String headerString;
    
    private GenericObject provincesObj; // if FTG-style provinces.txt rather than CSV
    
    public ProvinceData(String filename) {
        if (filename.endsWith(".csv"))
            loadCsv(filename);
        else
            loadTxt(filename);
    }

    private void loadCsv(String filename) {
        try {
            final BufferedReader br = new BufferedReader(new FileReader(filename));
            String currLine;
            
            // City XPos;City YPos;Army XPos;ArmyYPos;PortXPos;Port YPos;Manufactory XPos; Manufactory YPos;Port/sea Adjacency;Terrain x;Terrain Y;Terrain variant;Terrain x;Terrain Y;Terrain variant;Terrain x;Terrain Y;Terrain variant;Terrain x;Terrain Y;Terrain variant;
            
            int id = -1;
            
            headerString = br.readLine(); // eat first line but save for future use
            
            while ((currLine = br.readLine()) != null) {
                if (currLine.charAt(0) == '#')
                    continue;
                
                String[] args = SEMICOLON.split(currLine, -1);
                
                try {
                    String sid = args[0];
                    
                    if (sid.length() != 0) {
                        id = Integer.parseInt(sid);
                        
                        if (id >= 0) {
                            allProvs.put(id, new ProvinceCsv(args));
                        } /*else {
                            extras.add(new Province(args));
                        }*/
                    } /*else {
                        extras.add(new Province(args));
                    }*/
                } catch (RuntimeException e) {
                    System.err.print("Error with "+id+": ");
                    e.printStackTrace();
                }
            }
            
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void loadTxt(String filename) {
        provincesObj = EUGFileIO.load(filename);
        
        for (GenericObject prov : provincesObj.getChildren("province")) {
            String idStr = prov.getString("id");
            int id = Integer.parseInt(idStr);
            allProvs.put(id, new ProvinceTxt(prov));
        }
    }
    
    public void saveCsv(String filename) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            
            writer.write(headerString);
            writer.newLine();

            boolean writtenInvalid = false;
            int maxProv = allProvs.keySet().stream().max(Comparator.naturalOrder()).get();
            for (int i = 0; i <= maxProv; i++) {
                Province p = getProvince(i);
                if (p == null) {
                    if (!writtenInvalid) {
                        writer.write("-1;Filler;inland;exotic;none;-1;-1;-1;-1;-1;0;-1;-1;9;0;0;0;0;-1;nothing;0;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;0;0;0;0;0;0;0;0;0;0;0;0;0;;;;#N/A;0;0;0;0;0;0");
                        writtenInvalid = true;
                    } else {
                        writer.write(";;;;;;;;;;;;;;0;0;0;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                    }
                } else {
                    p.writeOut(writer);
                }
                writer.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(ProvinceData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException ex) {
                Logger.getLogger(ProvinceData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void saveTxt(String filename) {
        EUGFileIO.save(provincesObj, filename, "Edited with MichaelM's FTG Positions Editor", true, Style.AGCEEP);
    }
    
    public Province getProvince(int id) {
        return allProvs.get(id);
    }
    
    public boolean isLand(int id) {
        final Province p = getProvince(id);
        if (p == null)
            return false;
        
        return p.isLand();
    }
    
    public boolean isPTI(int id) {
        final Province p = getProvince(id);
        return p == null;
    }
    
    public String getName(int id) {
        final Province p = getProvince(id);
        if (p == null)
            return "Terra Incognita";
        
        return p.getName();
    }
    
    public enum GfxType {
        CITY("city", 28),
        ARMY("army", 30),
        PORT("port", 32),
        MANU("manufactory", 34),
        TERRAIN_1("terrain1", 37),
        TERRAIN_2("terrain2", 40),
        TERRAIN_3("terrain3", 43),
        TERRAIN_4("terrain4", 46);
        
        private String name; // FTG - name inside the gfx = { } block
        private int index;   // EU2 - index into province.csv entry
        
        private GfxType(String name, int index) {
            this.name = name;
            this.index = index;
        }
    }
    
    public enum TerrainVariantType {
        TERRAIN_1_TYPE("terrain1", 39),
        TERRAIN_2_TYPE("terrain2", 42),
        TERRAIN_3_TYPE("terrain3", 45),
        TERRAIN_4_TYPE("terrain4", 48);
        
        private String name;
        private int index;   // EU2 - index into province.csv entry
        
        private TerrainVariantType(String name, int index) {
            this.name = name;
            this.index = index;
        }
    }
    
    public interface Province {
        
        boolean isLand();
        String getName();
        Point getSpritePos(GfxType type);
        String getTerrainVariant(TerrainVariantType type);
        
        void setSpritePos(GfxType type, Point p);
        void setTerrainVariant(TerrainVariantType type, int value);
        
        void writeOut(BufferedWriter out) throws IOException;
    }
    
    private static class ProvinceCsv implements Province {
        private final String[] entry;
        private final boolean isLand;
        
        public static final int NAME_IDX = 1;
        public static final int TERRAIN_IDX = 13;
        // NOTE: Entries 14, 15, and 16 were added in 1.09
        public static final int CITY_IDX = 28;
        public static final int ARMY_IDX = 30;
        public static final int PORT_IDX = 32;
        public static final int MANU_IDX = 34;
        public static final int TERRAIN_1_IDX = 37;
        public static final int TERRAIN_1_TYPE_IDX = 39;
        public static final int TERRAIN_2_IDX = 40;
        public static final int TERRAIN_2_TYPE_IDX = 42;
        public static final int TERRAIN_3_IDX = 43;
        public static final int TERRAIN_3_TYPE_IDX = 45;
        public static final int TERRAIN_4_IDX = 46;
        public static final int TERRAIN_4_TYPE_IDX = 48;
        
        private ProvinceCsv(String[] entry) {
            this.entry = entry;
            final int terrain = Integer.parseInt(entry[TERRAIN_IDX]);
            isLand = (terrain != 5 && terrain != 6);
        }
        
        public String getString(int idx) {
            return entry[idx];
        }
        
        public int getInt(int idx) {
            return Integer.parseInt(entry[idx]);
        }
        
        @Override
        public String getName() {
            return entry[NAME_IDX];
        }
        
        @Override
        public boolean isLand() {
            return isLand;
        }
        
        @Override
        public Point getSpritePos(GfxType type) {
            return getPos(type.index);
        }
        
        @Override
        public String getTerrainVariant(TerrainVariantType type) {
            return getString(type.index);
        }
        
        private Point getPos(int xIndex) {
            int x = Integer.parseInt(entry[xIndex]);
            int y = Integer.parseInt(entry[xIndex+1]);
            if (x > 0 && y > 0)
                return new Point(x, y);
            return null;
        }
        
        @Override
        public void setSpritePos(GfxType type, Point pos) {
            entry[type.index] = Integer.toString(pos.x);
            entry[type.index+1] = Integer.toString(pos.y);
        }
        
        @Override
        public void setTerrainVariant(TerrainVariantType type, int value) {
            entry[type.index] = Integer.toString(value);
        }
        
        @Override
        public void writeOut(BufferedWriter out) throws IOException {
            for (int i = 0; i < entry.length-1; i++) {
                out.write(entry[i]);
                out.write(';');
            }
            out.write(entry[entry.length-1]);
        }
    }
    
    private static final class ProvinceTxt implements Province {
        private GenericObject provObj;
        private GenericObject provGfx; // child of provObj, but we don't want to constantly call provObj.getChild("gfx")
        private final String name;
        private final boolean isLand;
        
        ProvinceTxt(GenericObject obj) {
            this.provObj = obj;
            this.provGfx = obj.getChild("gfx");
            this.name = obj.getString("name");
            String terrain = obj.getString("terrain");
            this.isLand = !(terrain.equals("sea") || terrain.equals("river"));
        }

        @Override
        public boolean isLand() {
            return isLand;
        }

        @Override
        public String getName() {
            return name;
        }
        
        @Override
        public Point getSpritePos(GfxType type) {
            return getPos(type.name);
        }
        
        @Override
        public String getTerrainVariant(TerrainVariantType type) {
            return getTerrainType(type.name);
        }
        
        private Point getPos(String name) {
            if (provGfx != null) {
                GenericObject pos = provGfx.getChild(name);
                if (pos != null) {
                    int x = pos.getInt("x");
                    int y = pos.getInt("y");
                    if (x > -1 && y > -1)
                        return new Point(x, y);
                }
            }
            return null;
        }
        
        private String getTerrainType(String terrain) {
            if (provGfx != null) {
                GenericObject pos = provGfx.getChild(terrain);
                if (pos != null) {
                    return pos.getString("variant");
                }
            }
            return "";
        }
        
        @Override
        public void setSpritePos(GfxType type, Point pos) {
            if (provGfx == null) {
                provGfx = provObj.createChild("gfx");
            }
            
            GenericObject spriteObj = provGfx.getChild(type.name);
            if (spriteObj == null) {
                spriteObj = provGfx.createChild(type.name);
            }
            spriteObj.setInt("x", pos.x);
            spriteObj.setInt("y", pos.y);
        }
        
        @Override
        public void setTerrainVariant(TerrainVariantType type, int value) {
            if (provGfx == null) {
                provGfx = provObj.createChild("gfx");
            }
            
            GenericObject spriteObj = provGfx.getChild(type.name);
            if (spriteObj != null) {
                spriteObj.setInt("variant", value);
            }
        }

        @Override
        public void writeOut(BufferedWriter out) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
