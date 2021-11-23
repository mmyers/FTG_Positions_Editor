/*
 * ProvinceData.java
 *
 * Created on Jan 12, 2008, 12:19:12 PM
 */

package eu2posed;

import eug.parser.EUGFileIO;
import eug.shared.GenericObject;
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
        GenericObject provinces = EUGFileIO.load(filename);
        
        for (GenericObject prov : provinces.getChildren("province")) {
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
    
//    
//    public Point getCityPos(int id) {
//        return allProvs[id].getCityPos();
//    }
//    
//    public Point getArmyPos(int id) {
//        return allProvs[id].getArmyPos();
//    }
//    
//    public Point getPortPos(int id) {
//        return allProvs[id].getPortPos();
//    }
//    
//    public Point getManuPos(int id) {
//        return allProvs[id].getManuPos();
//    }
//    
//    public Point getTerrain1Pos(int id) {
//        return allProvs[id].getPos(Province.TERRAIN_1_IDX);
//    }
//    
//    public int getTerrain1Type(int id) {
//        return allProvs[id].getInt(Province.TERRAIN_1_TYPE_IDX);
//    }
//    
//    public Point getTerrain2Pos(int id) {
//        return allProvs[id].getPos(Province.TERRAIN_2_IDX);
//    }
//    
//    public int getTerrain2Type(int id) {
//        return allProvs[id].getInt(Province.TERRAIN_2_TYPE_IDX);
//    }
//    
//    public Point getTerrain3Pos(int id) {
//        return allProvs[id].getPos(Province.TERRAIN_3_IDX);
//    }
//    
//    public int getTerrain3Type(int id) {
//        return allProvs[id].getInt(Province.TERRAIN_3_TYPE_IDX);
//    }
//    
//    public Point getTerrain4Pos(int id) {
//        return allProvs[id].getPos(Province.TERRAIN_4_IDX);
//    }
//    
//    public int getTerrain4Type(int id) {
//        return allProvs[id].getInt(Province.TERRAIN_4_TYPE_IDX);
//    }
    
    public interface Province {
        
        boolean isLand();
        String getName();
        Point getCityPos();
        Point getArmyPos();
        Point getPortPos();
        Point getManuPos();
        Point getTerrain1Pos();
        Point getTerrain2Pos();
        Point getTerrain3Pos();
        Point getTerrain4Pos();
        String getTerrain1Type();
        String getTerrain2Type();
        String getTerrain3Type();
        String getTerrain4Type();
        
        void writeOut(BufferedWriter out) throws IOException;
    }
    
    private static class ProvinceCsv implements Province {
        private final String[] entry;
        
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
            final int terrain = Integer.parseInt(entry[TERRAIN_IDX]);
            return (terrain != 5 && terrain != 6);
        }
        
        @Override
        public Point getCityPos() {
            return getPos(CITY_IDX);
        }
        
        @Override
        public Point getArmyPos() {
            return getPos(ARMY_IDX);
        }
        
        @Override
        public Point getPortPos() {
            return getPos(PORT_IDX);
        }
        
        @Override
        public Point getManuPos() {
            return getPos(MANU_IDX);
        }

        @Override
        public Point getTerrain1Pos() {
            return getPos(TERRAIN_1_IDX);
        }

        @Override
        public Point getTerrain2Pos() {
            return getPos(TERRAIN_2_IDX);
        }

        @Override
        public Point getTerrain3Pos() {
            return getPos(TERRAIN_3_IDX);
        }

        @Override
        public Point getTerrain4Pos() {
            return getPos(TERRAIN_4_IDX);
        }

        @Override
        public String getTerrain1Type() {
            return getString(TERRAIN_1_TYPE_IDX);
        }

        @Override
        public String getTerrain2Type() {
            return getString(TERRAIN_2_TYPE_IDX);
        }

        @Override
        public String getTerrain3Type() {
            return getString(TERRAIN_3_TYPE_IDX);
        }

        @Override
        public String getTerrain4Type() {
            return getString(TERRAIN_1_TYPE_IDX);
        }
        
        private Point getPos(int xIndex) {
            int x = Integer.parseInt(entry[xIndex]);
            int y = Integer.parseInt(entry[xIndex+1]);
            if (x > 0 && y > 0)
                return new Point(x, y);
            return null;
        }
        
        public void setPos(int xIndex, Point pos) {
            entry[xIndex] = Integer.toString(pos.x);
            entry[xIndex+1] = Integer.toString(pos.y);
        }
        
        private void setString(int idx, String value) {
            entry[idx] = value;
        }
        
        public void setInt(int idx, int value) {
            entry[idx] = Integer.toString(value);
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
        public Point getCityPos() {
            return getPos("city");
        }

        @Override
        public Point getArmyPos() {
            return getPos("army");
        }

        @Override
        public Point getPortPos() {
            return getPos("port");
        }

        @Override
        public Point getManuPos() {
            return getPos("manufactory");
        }

        @Override
        public Point getTerrain1Pos() {
            return getPos("terrain1");
        }

        @Override
        public Point getTerrain2Pos() {
            return getPos("terrain2");
        }

        @Override
        public Point getTerrain3Pos() {
            return getPos("terrain3");
        }

        @Override
        public Point getTerrain4Pos() {
            return getPos("terrain4");
        }

        @Override
        public String getTerrain1Type() {
            return getTerrainType("terrain1");
        }

        @Override
        public String getTerrain2Type() {
            return getTerrainType("terrain2");
        }

        @Override
        public String getTerrain3Type() {
            return getTerrainType("terrain3");
        }

        @Override
        public String getTerrain4Type() {
            return getTerrainType("terrain4");
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
        public void writeOut(BufferedWriter out) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
