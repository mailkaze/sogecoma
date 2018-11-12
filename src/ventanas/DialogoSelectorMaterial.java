/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author Kaze
 */
public class DialogoSelectorMaterial extends javax.swing.JDialog {
    DefaultListModel Modelo = new DefaultListModel();
    private void cargarLista() throws FileNotFoundException, IOException{
        Modelo.clear();
        lstMateriales.setModel(Modelo);
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("SOGECOMA.xls"));
        HSSFWorkbook sogecoma = new HSSFWorkbook(fs);
        HSSFSheet materiales = sogecoma.getSheetAt(3);
        int NumContactos = materiales.getLastRowNum();
        //recorre las filas del archivo añadiendo nombre y apellidos al modelo de la JList.
        for (int i=1;i<=NumContactos;i++){
            Modelo.addElement(materiales.getRow(i).getCell(1).getStringCellValue());
        }
        lstMateriales.setModel(Modelo);
    }
    private void buscar() throws FileNotFoundException, IOException{
        String busqueda = txtBuscar.getText();
        busqueda.trim();
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("SOGECOMA.xls"));
        HSSFWorkbook sogecoma = new HSSFWorkbook(fs);
        HSSFSheet materiales = sogecoma.getSheetAt(3);
        int numRegistros = materiales.getLastRowNum();
        Modelo.clear();
        for (int i=1;i<=numRegistros;i++){
            //recorremos las filas.
            String celda = materiales.getRow(i).getCell(1).getStringCellValue();
            celda.trim();
            clases.IgnorarAcentos sinAcentos = new clases.IgnorarAcentos();
            celda=sinAcentos.sinAcentos(celda);
            busqueda=sinAcentos.sinAcentos(busqueda);
            if (celda.toLowerCase().contains(busqueda.toLowerCase())){
                Modelo.addElement(materiales.getRow(i).getCell(1).getStringCellValue());
            }
        }
        lstMateriales.setModel(Modelo);
    }
    private void cargarVariables(String nombre) throws FileNotFoundException, IOException{
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("SOGECOMA.xls"));
        HSSFWorkbook sogecoma = new HSSFWorkbook(fs);
        HSSFSheet materiales = sogecoma.getSheetAt(3);
        int numRegistros = materiales.getLastRowNum();
        for (int i=1;i<=numRegistros;i++){
            //recorremos las filas.
            String celda = materiales.getRow(i).getCell(1).getStringCellValue();
            celda.trim();
            if (celda.equals(nombre)){
                clases.SOGECOMA.ID_Material=(int)materiales.getRow(i).getCell(0).getNumericCellValue();
                clases.SOGECOMA.nomMaterial=materiales.getRow(i).getCell(1).getStringCellValue();
                clases.SOGECOMA.udMaterial=materiales.getRow(i).getCell(2).getStringCellValue();
                clases.SOGECOMA.stockMaterial=(double)materiales.getRow(i).getCell(3).getNumericCellValue();
                clases.SOGECOMA.minMaterial=(double)materiales.getRow(i).getCell(4).getNumericCellValue();
            }
        }
    }
    /**
     * Creates new form DialogoSelectorMaterial
     */
    public DialogoSelectorMaterial(java.awt.Frame parent, boolean modal) throws FileNotFoundException, IOException {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        cargarLista();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstMateriales = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Selector de Materiales");

        jLabel1.setText("Escriba el nombre del material que está buscando:");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        lstMateriales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstMaterialesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstMateriales);

        jLabel2.setText("Doble clic para seleccionar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(txtBuscar))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lstMaterialesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstMaterialesMouseClicked
        if (evt.getClickCount()==2){ 
            //Comprueba si es un doble clic.
            try {
                cargarVariables(lstMateriales.getSelectedValue().toString());
                this.setVisible(false);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DialogoSelectorMaterial.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DialogoSelectorMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lstMaterialesMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        try {
            buscar();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DialogoSelectorMaterial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DialogoSelectorMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_txtBuscarKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialogoSelectorMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogoSelectorMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogoSelectorMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogoSelectorMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DialogoSelectorMaterial dialog = new DialogoSelectorMaterial(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DialogoSelectorMaterial.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DialogoSelectorMaterial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstMateriales;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
