/*
 * EditorDialog.java
 *
 * Created on January 14, 2008, 4:01 PM
 */

package eu2posed;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author  Michael
 */
public class EditorDialog extends javax.swing.JDialog {
    private IDMapPanel.ProvinceImage image;
    private ProvinceData.Province province;
    private boolean hasChanges = false;
    
    /** Creates new form EditorDialog */
    public EditorDialog(java.awt.Frame parent, IDMapPanel.ProvinceImage image, ProvinceData.Province data) {
        super(parent, true);
        this.image = image;
        this.province = data;
        initComponents();
        provincePanel.setImage(image);
        provincePanel.setProvince(province);
        initFields();
        setTitle("Editing " + image.getProvName());
        pack();
        setLocationRelativeTo(parent);
    }
    
    private void initFields() {
        int id = image.getProvId();
        setPositionFields(province.getCityPos(), cityXField, cityYField);
        setPositionFields(province.getArmyPos(), armyXField, armyYField);
        setPositionFields(province.getPortPos(), portXField, portYField);
        setPositionFields(province.getManuPos(), manuXField, manuYField);
        
        setPositionFields(province.getTerrain1Pos(), terrain1XField, terrain1YField);
        setPositionFields(province.getTerrain2Pos(), terrain2XField, terrain2YField);
        setPositionFields(province.getTerrain3Pos(), terrain3XField, terrain3YField);
        setPositionFields(province.getTerrain4Pos(), terrain4XField, terrain4YField);
        
        terrain1TypeField.setText(province.getTerrain1Type());
        terrain2TypeField.setText(province.getTerrain2Type());
        terrain3TypeField.setText(province.getTerrain3Type());
        terrain4TypeField.setText(province.getTerrain4Type());
    }
    
    private void setPositionFields(Point p, JTextField xField, JTextField yField) {
        if (p != null) {
            xField.setText(Integer.toString(p.x));
            yField.setText(Integer.toString(p.y));
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        provincePanel = new eu2posed.ProvincePanel();
        javax.swing.JPanel lowerPanel = new javax.swing.JPanel();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel7 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        cityXField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        cityYField = new javax.swing.JTextField();
        cityPositionButton = new javax.swing.JButton();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel8 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        armyXField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        armyYField = new javax.swing.JTextField();
        armyPositionButton = new javax.swing.JButton();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel9 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        portXField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        portYField = new javax.swing.JTextField();
        portPositionButton = new javax.swing.JButton();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel10 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        manuXField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        manuYField = new javax.swing.JTextField();
        manuPositionButton = new javax.swing.JButton();
        javax.swing.JPanel jPanel6 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel11 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        terrain1XField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        terrain1YField = new javax.swing.JTextField();
        terrain1PositionButton = new javax.swing.JButton();
        javax.swing.JPanel jPanel18 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel25 = new javax.swing.JLabel();
        terrain1TypeField = new javax.swing.JTextField();
        javax.swing.JPanel jPanel24 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel12 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel13 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        terrain2XField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        terrain2YField = new javax.swing.JTextField();
        terrain2PositionButton = new javax.swing.JButton();
        javax.swing.JPanel jPanel19 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel26 = new javax.swing.JLabel();
        terrain2TypeField = new javax.swing.JTextField();
        javax.swing.JPanel jPanel25 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel14 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel15 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        terrain3XField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel21 = new javax.swing.JLabel();
        terrain3YField = new javax.swing.JTextField();
        terrain3PositionButton = new javax.swing.JButton();
        javax.swing.JPanel jPanel20 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel27 = new javax.swing.JLabel();
        terrain3TypeField = new javax.swing.JTextField();
        javax.swing.JPanel jPanel26 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel16 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel17 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel23 = new javax.swing.JLabel();
        terrain4XField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel24 = new javax.swing.JLabel();
        terrain4YField = new javax.swing.JTextField();
        terrain4PositionButton = new javax.swing.JButton();
        javax.swing.JPanel jPanel21 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel28 = new javax.swing.JLabel();
        terrain4TypeField = new javax.swing.JTextField();
        javax.swing.JPanel jPanel27 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel22 = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        javax.swing.JPanel jPanel23 = new javax.swing.JPanel();
        gridToggleButton = new javax.swing.JToggleButton();
        updateButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(formListener);

        provincePanel.addMouseMotionListener(formListener);
        jScrollPane1.setViewportView(provincePanel);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        lowerPanel.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setText("City");
        jPanel2.add(jLabel1);

        jLabel6.setText("x");
        jPanel7.add(jLabel6);

        cityXField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel7.add(cityXField);

        jLabel7.setText("y");
        jPanel7.add(jLabel7);

        cityYField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel7.add(cityYField);

        jPanel2.add(jPanel7);

        cityPositionButton.setText("Edit position...");
        cityPositionButton.addActionListener(formListener);
        jPanel2.add(cityPositionButton);

        jPanel1.add(jPanel2);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jLabel2.setText("Army");
        jPanel3.add(jLabel2);

        jLabel8.setText("x");
        jPanel8.add(jLabel8);

        armyXField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel8.add(armyXField);

        jLabel9.setText("y");
        jPanel8.add(jLabel9);

        armyYField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel8.add(armyYField);

        jPanel3.add(jPanel8);

        armyPositionButton.setText("Edit position...");
        armyPositionButton.addActionListener(formListener);
        jPanel3.add(armyPositionButton);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jLabel3.setText("Port");
        jPanel4.add(jLabel3);

        jLabel10.setText("x");
        jPanel9.add(jLabel10);

        portXField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel9.add(portXField);

        jLabel11.setText("y");
        jPanel9.add(jLabel11);

        portYField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel9.add(portYField);

        jPanel4.add(jPanel9);

        portPositionButton.setText("Edit position...");
        portPositionButton.addActionListener(formListener);
        jPanel4.add(portPositionButton);

        jPanel1.add(jPanel4);

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jLabel4.setText("Manufactory");
        jPanel5.add(jLabel4);

        jLabel12.setText("x");
        jPanel10.add(jLabel12);

        manuXField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel10.add(manuXField);

        jLabel13.setText("y");
        jPanel10.add(jLabel13);

        manuYField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel10.add(manuYField);

        jPanel5.add(jPanel10);

        manuPositionButton.setText("Edit position...");
        manuPositionButton.addActionListener(formListener);
        jPanel5.add(manuPositionButton);

        jPanel1.add(jPanel5);

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jLabel5.setText("Terrain 1");
        jPanel6.add(jLabel5);

        jLabel14.setText("x");
        jPanel11.add(jLabel14);

        terrain1XField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel11.add(terrain1XField);

        jLabel15.setText("y");
        jPanel11.add(jLabel15);

        terrain1YField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel11.add(terrain1YField);

        jPanel6.add(jPanel11);

        terrain1PositionButton.setText("Edit position...");
        terrain1PositionButton.addActionListener(formListener);
        jPanel6.add(terrain1PositionButton);

        jPanel1.add(jPanel6);

        jPanel18.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        jLabel25.setText("Terrain 1 type");
        jPanel18.add(jLabel25);
        jPanel18.add(terrain1TypeField);
        jPanel18.add(jPanel24);

        jPanel1.add(jPanel18);

        jPanel12.setLayout(new java.awt.GridLayout(1, 0));

        jLabel16.setText("Terrain 2");
        jPanel12.add(jLabel16);

        jLabel17.setText("x");
        jPanel13.add(jLabel17);

        terrain2XField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel13.add(terrain2XField);

        jLabel18.setText("y");
        jPanel13.add(jLabel18);

        terrain2YField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel13.add(terrain2YField);

        jPanel12.add(jPanel13);

        terrain2PositionButton.setText("Edit position...");
        terrain2PositionButton.addActionListener(formListener);
        jPanel12.add(terrain2PositionButton);

        jPanel1.add(jPanel12);

        jPanel19.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        jLabel26.setText("Terrain 2 type");
        jPanel19.add(jLabel26);
        jPanel19.add(terrain2TypeField);
        jPanel19.add(jPanel25);

        jPanel1.add(jPanel19);

        jPanel14.setLayout(new java.awt.GridLayout(1, 0));

        jLabel19.setText("Terrain 3");
        jPanel14.add(jLabel19);

        jLabel20.setText("x");
        jPanel15.add(jLabel20);

        terrain3XField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel15.add(terrain3XField);

        jLabel21.setText("y");
        jPanel15.add(jLabel21);

        terrain3YField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel15.add(terrain3YField);

        jPanel14.add(jPanel15);

        terrain3PositionButton.setText("Edit position...");
        terrain3PositionButton.addActionListener(formListener);
        jPanel14.add(terrain3PositionButton);

        jPanel1.add(jPanel14);

        jPanel20.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        jLabel27.setText("Terrain 3 type");
        jPanel20.add(jLabel27);
        jPanel20.add(terrain3TypeField);
        jPanel20.add(jPanel26);

        jPanel1.add(jPanel20);

        jPanel16.setLayout(new java.awt.GridLayout(1, 0));

        jLabel22.setText("Terrain 4");
        jPanel16.add(jLabel22);

        jLabel23.setText("x");
        jPanel17.add(jLabel23);

        terrain4XField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel17.add(terrain4XField);

        jLabel24.setText("y");
        jPanel17.add(jLabel24);

        terrain4YField.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel17.add(terrain4YField);

        jPanel16.add(jPanel17);

        terrain4PositionButton.setText("Edit position...");
        terrain4PositionButton.addActionListener(formListener);
        jPanel16.add(terrain4PositionButton);

        jPanel1.add(jPanel16);

        jPanel21.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        jLabel28.setText("Terrain 4 type");
        jPanel21.add(jLabel28);
        jPanel21.add(terrain4TypeField);
        jPanel21.add(jPanel27);

        jPanel1.add(jPanel21);

        lowerPanel.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel22.setLayout(new java.awt.BorderLayout());

        statusLabel.setText(" ");
        jPanel22.add(statusLabel, java.awt.BorderLayout.SOUTH);

        gridToggleButton.setText("Toggle grid lines");
        gridToggleButton.addActionListener(formListener);
        jPanel23.add(gridToggleButton);

        updateButton.setText("Update image");
        updateButton.addActionListener(formListener);
        jPanel23.add(updateButton);

        okButton.setText("OK");
        okButton.addActionListener(formListener);
        jPanel23.add(okButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(formListener);
        jPanel23.add(cancelButton);

        jPanel22.add(jPanel23, java.awt.BorderLayout.CENTER);

        lowerPanel.add(jPanel22, java.awt.BorderLayout.SOUTH);

        getContentPane().add(lowerPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.MouseMotionListener, java.awt.event.WindowListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == updateButton) {
                EditorDialog.this.updateButtonActionPerformed(evt);
            }
            else if (evt.getSource() == okButton) {
                EditorDialog.this.okButtonActionPerformed(evt);
            }
            else if (evt.getSource() == cancelButton) {
                EditorDialog.this.cancelButtonActionPerformed(evt);
            }
            else if (evt.getSource() == cityPositionButton) {
                EditorDialog.this.cityPositionButtonActionPerformed(evt);
            }
            else if (evt.getSource() == armyPositionButton) {
                EditorDialog.this.armyPositionButtonActionPerformed(evt);
            }
            else if (evt.getSource() == portPositionButton) {
                EditorDialog.this.portPositionButtonActionPerformed(evt);
            }
            else if (evt.getSource() == manuPositionButton) {
                EditorDialog.this.manuPositionButtonActionPerformed(evt);
            }
            else if (evt.getSource() == terrain1PositionButton) {
                EditorDialog.this.terrain1PositionButtonActionPerformed(evt);
            }
            else if (evt.getSource() == terrain2PositionButton) {
                EditorDialog.this.terrain2PositionButtonActionPerformed(evt);
            }
            else if (evt.getSource() == terrain3PositionButton) {
                EditorDialog.this.terrain3PositionButtonActionPerformed(evt);
            }
            else if (evt.getSource() == terrain4PositionButton) {
                EditorDialog.this.terrain4PositionButtonActionPerformed(evt);
            }
            else if (evt.getSource() == gridToggleButton) {
                EditorDialog.this.gridToggleButtonActionPerformed(evt);
            }
        }

        public void mouseDragged(java.awt.event.MouseEvent evt) {
        }

        public void mouseMoved(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == provincePanel) {
                EditorDialog.this.provincePanelMouseMoved(evt);
            }
        }

        public void windowActivated(java.awt.event.WindowEvent evt) {
        }

        public void windowClosed(java.awt.event.WindowEvent evt) {
        }

        public void windowClosing(java.awt.event.WindowEvent evt) {
            if (evt.getSource() == EditorDialog.this) {
                EditorDialog.this.formWindowClosing(evt);
            }
        }

        public void windowDeactivated(java.awt.event.WindowEvent evt) {
        }

        public void windowDeiconified(java.awt.event.WindowEvent evt) {
        }

        public void windowIconified(java.awt.event.WindowEvent evt) {
        }

        public void windowOpened(java.awt.event.WindowEvent evt) {
        }
    }// </editor-fold>//GEN-END:initComponents

    private void provincePanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_provincePanelMouseMoved
        double x = provincePanel.reverseTranslateX(evt.getX());
        double y = provincePanel.reverseTranslateY(evt.getY());
        statusLabel.setText("x=" + x + ", y=" + y);
    }//GEN-LAST:event_provincePanelMouseMoved

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        /*setPositionIfPossible(province, ProvinceData.Province.CITY_IDX, cityXField, cityYField);
        setPositionIfPossible(province, ProvinceData.Province.ARMY_IDX, armyXField, armyYField);
        setPositionIfPossible(province, ProvinceData.Province.PORT_IDX, portXField, portYField);
        setPositionIfPossible(province, ProvinceData.Province.MANU_IDX, manuXField, manuYField);
        
        setPositionIfPossible(province, ProvinceData.Province.TERRAIN_1_IDX, terrain1XField, terrain1YField);
        setPositionIfPossible(province, ProvinceData.Province.TERRAIN_2_IDX, terrain2XField, terrain2YField);
        setPositionIfPossible(province, ProvinceData.Province.TERRAIN_3_IDX, terrain3XField, terrain3YField);
        setPositionIfPossible(province, ProvinceData.Province.TERRAIN_4_IDX, terrain4XField, terrain4YField);
        
        setIntIfPossible(province, ProvinceData.Province.TERRAIN_1_TYPE_IDX, terrain1TypeField);
        setIntIfPossible(province, ProvinceData.Province.TERRAIN_2_TYPE_IDX, terrain2TypeField);
        setIntIfPossible(province, ProvinceData.Province.TERRAIN_3_TYPE_IDX, terrain3TypeField);
        setIntIfPossible(province, ProvinceData.Province.TERRAIN_4_TYPE_IDX, terrain4TypeField);*/
        
        provincePanel.setProvince(province);
        provincePanel.repaint();
    }//GEN-LAST:event_updateButtonActionPerformed
    
    private static final void setPositionIfPossible(ProvinceData.Province prov, int index, JTextField xField, JTextField yField) {
        if (! "".equals(xField.getText()) && ! "".equals(yField.getText())) {
            try {
                int x = Integer.parseInt(xField.getText());
                int y = Integer.parseInt(yField.getText());
                //prov.setPos(index, new Point(x, y));

            } catch (NumberFormatException ex) {
                System.err.println("Invalid coordinate: " + xField.getText() + " or " + yField.getText());
            }
        }
    }
    
    private static final void setIntIfPossible(ProvinceData.Province prov, int index, JTextField field) {
        if (! "".equals(field.getText())) {
            try {
                int i = Integer.parseInt(field.getText());
                //prov.setInt(index, i);

            } catch (NumberFormatException ex) {
                System.err.println("Invalid integer: " + field.getText());
            }
        }
    }
    
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        doClose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        doClose();
    }//GEN-LAST:event_formWindowClosing

    private void doClose() {
        // TODO: Detect changes
        switch (JOptionPane.showConfirmDialog(this, "Do you want to save changes?")) {
            case JOptionPane.YES_OPTION:
                hasChanges = true;
                break;
            case JOptionPane.NO_OPTION:
                break;
            case JOptionPane.CANCEL_OPTION:
            default:
                return;
        }
        dispose();
    }
    
    private void cityPositionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityPositionButtonActionPerformed
        doSetPosition(cityXField, cityYField);
    }//GEN-LAST:event_cityPositionButtonActionPerformed

    private void armyPositionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_armyPositionButtonActionPerformed
        doSetPosition(armyXField, armyYField);
    }//GEN-LAST:event_armyPositionButtonActionPerformed

    private void portPositionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portPositionButtonActionPerformed
        doSetPosition(portXField, portYField);
    }//GEN-LAST:event_portPositionButtonActionPerformed

    private void manuPositionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuPositionButtonActionPerformed
        doSetPosition(manuXField, manuYField);
    }//GEN-LAST:event_manuPositionButtonActionPerformed

    private void terrain1PositionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terrain1PositionButtonActionPerformed
        doSetPosition(terrain1XField, terrain1YField);
    }//GEN-LAST:event_terrain1PositionButtonActionPerformed

    private void terrain2PositionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terrain2PositionButtonActionPerformed
        doSetPosition(terrain2XField, terrain2YField);
    }//GEN-LAST:event_terrain2PositionButtonActionPerformed

    private void terrain3PositionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terrain3PositionButtonActionPerformed
        doSetPosition(terrain3XField, terrain3YField);
    }//GEN-LAST:event_terrain3PositionButtonActionPerformed

    private void terrain4PositionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terrain4PositionButtonActionPerformed
        doSetPosition(terrain4XField, terrain4YField);
    }//GEN-LAST:event_terrain4PositionButtonActionPerformed

    private void gridToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gridToggleButtonActionPerformed
        provincePanel.setDrawGridLines(!gridToggleButton.isSelected());
        provincePanel.repaint();
    }//GEN-LAST:event_gridToggleButtonActionPerformed

    private void doSetPosition(final JTextField xField, final JTextField yField) {
        statusLabel.setText("Click somewhere in the province");
        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                double x = provincePanel.reverseTranslateX(e.getX());
                double y = provincePanel.reverseTranslateY(e.getY());

                xField.setText(Integer.toString((int) Math.round(x)));
                yField.setText(Integer.toString((int) Math.round(y)));

                updateButtonActionPerformed(null);
                provincePanel.removeMouseListener(this);
//                provincePanel.addMouseListener(EditorDialog.this);
            }
        };
//        provincePanel.removeMouseListener(this);
        provincePanel.addMouseListener(listener);
    }

    public boolean getHasChanges() {
        return hasChanges;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton armyPositionButton;
    javax.swing.JTextField armyXField;
    javax.swing.JTextField armyYField;
    javax.swing.JButton cancelButton;
    javax.swing.JButton cityPositionButton;
    javax.swing.JTextField cityXField;
    javax.swing.JTextField cityYField;
    javax.swing.JToggleButton gridToggleButton;
    javax.swing.JButton manuPositionButton;
    javax.swing.JTextField manuXField;
    javax.swing.JTextField manuYField;
    javax.swing.JButton okButton;
    javax.swing.JButton portPositionButton;
    javax.swing.JTextField portXField;
    javax.swing.JTextField portYField;
    eu2posed.ProvincePanel provincePanel;
    javax.swing.JLabel statusLabel;
    javax.swing.JButton terrain1PositionButton;
    javax.swing.JTextField terrain1TypeField;
    javax.swing.JTextField terrain1XField;
    javax.swing.JTextField terrain1YField;
    javax.swing.JButton terrain2PositionButton;
    javax.swing.JTextField terrain2TypeField;
    javax.swing.JTextField terrain2XField;
    javax.swing.JTextField terrain2YField;
    javax.swing.JButton terrain3PositionButton;
    javax.swing.JTextField terrain3TypeField;
    javax.swing.JTextField terrain3XField;
    javax.swing.JTextField terrain3YField;
    javax.swing.JButton terrain4PositionButton;
    javax.swing.JTextField terrain4TypeField;
    javax.swing.JTextField terrain4XField;
    javax.swing.JTextField terrain4YField;
    javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables

}
