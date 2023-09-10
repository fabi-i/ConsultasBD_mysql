import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Form extends JFrame{
    private JPanel panelMain;
    private JCheckBox nombreCheckBox;
    private JCheckBox ciudadCheckBox;
    private JCheckBox edadCheckBox;
    private JCheckBox oficioCheckBox;
    private JCheckBox numControlCheckBox;
    private JPanel BotonesPanel;
    private JTable empleadosTable;
    private JPanel nombresPanelOp;
    private JPanel ciudadPanelOp;
    private JPanel edadPanelOp;
    private JPanel oficioPanelOp;
    private JPanel numProyPanelOp;
    private JRadioButton edadMenorRadio;
    private JRadioButton numMenorRadio;
    private JRadioButton edadMayorRadio;
    private JRadioButton edadIgualRadio;
    private JTextField edadTextField;
    private JTextField nombreTextField;
    private JTextField oficioTextField;
    private JTextField ciudadTextField;
    private JRadioButton numMayorRadio;
    private JRadioButton numIgualRadio;
    private JTextField numTextField;
    private String nombreBusqueda, ciudadBusqueda, oficioBusqueda;
    private String edadValor, numValor;

    public Form() {
        //BD
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        llenarTabla();

        /*radio buttons*/
        //Grupo de edad
        ButtonGroup edadSimGrupo = new ButtonGroup();
        edadSimGrupo.add(edadMenorRadio);
        edadSimGrupo.add(edadMayorRadio);
        edadSimGrupo.add(edadIgualRadio);
        //Grupo de Número de proyecto (ID)
        ButtonGroup numSimGrupo = new ButtonGroup();
        numSimGrupo.add(numMenorRadio);
        numSimGrupo.add(numMayorRadio);
        numSimGrupo.add(numIgualRadio);

        //Creación de predicados Checkbox
        nombreCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //visibilidad de paneles
                llenarTabla();
                nombresPanelOp.setVisible(true);
                ciudadPanelOp.setVisible(false);
                edadPanelOp.setVisible(false);
                oficioPanelOp.setVisible(false);
                numProyPanelOp.setVisible(false);

                nombreTextField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Statement st;
                        String[] fila = new String[6];
                        String sql = "SELECT * FROM empleados WHERE Nombre LIKE '%" + nombreBusqueda + "%'";
                        nombreBusqueda = nombreTextField.getText();

                        // Limpiar tabla
                        DefaultTableModel model = (DefaultTableModel) empleadosTable.getModel();
                        model.setRowCount(0);

                        try {
                            st = conexion.createStatement();
                            ResultSet r = st.executeQuery(sql);

                            while (r.next()) {
                                fila[0] = r.getString(1);
                                fila[1] = r.getString(2);
                                fila[2] = r.getString(3);
                                fila[3] = r.getString(4);
                                fila[4] = r.getString(5);
                                fila[5] = r.getString(6);

                                model.addRow(fila);
                            }
                        } catch(SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex.toString());
                        }
                    }
                });
            }
        });
        ciudadCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //visibilidad de paneles
                llenarTabla();
                nombresPanelOp.setVisible(false);
                ciudadPanelOp.setVisible(true);
                edadPanelOp.setVisible(false);
                oficioPanelOp.setVisible(false);
                numProyPanelOp.setVisible(false);

                ciudadTextField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Statement st;
                        String[] fila = new String[6];
                        String sql = "SELECT * FROM empleados WHERE Ciudad LIKE '%" + ciudadBusqueda + "%'";
                        ciudadBusqueda = ciudadTextField.getText();

                        // Limpiar tabla
                        DefaultTableModel model = (DefaultTableModel) empleadosTable.getModel();
                        model.setRowCount(0);

                        try {
                            st = conexion.createStatement();
                            ResultSet r = st.executeQuery(sql);

                            while (r.next()) {
                                fila[0] = r.getString(1);
                                fila[1] = r.getString(2);
                                fila[2] = r.getString(3);
                                fila[3] = r.getString(4);
                                fila[4] = r.getString(5);
                                fila[5] = r.getString(6);

                                model.addRow(fila);
                            }
                        } catch(SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex.toString());
                        }
                    }
                });

            }
        });
        edadCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Visibilidad de paneles
                llenarTabla();
                edadSimGrupo.clearSelection();
                nombresPanelOp.setVisible(false);
                ciudadPanelOp.setVisible(false);
                edadPanelOp.setVisible(true);
                oficioPanelOp.setVisible(false);
                numProyPanelOp.setVisible(false);

                edadTextField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Statement st;
                        String[] fila = new String[6];
                        edadValor = edadTextField.getText();
                        String sql = "";

                        if (edadMenorRadio.isSelected()) {
                            sql = "SELECT * FROM empleados WHERE Edad < " + edadValor;
                        } else if (edadMayorRadio.isSelected()) {
                            sql = "SELECT * FROM empleados WHERE Edad > " + edadValor;
                        } else if (edadIgualRadio.isSelected()) {
                            sql = "SELECT * FROM empleados WHERE Edad = " + edadValor;
                        }

                        // Limpiar tabla
                        DefaultTableModel model = (DefaultTableModel) empleadosTable.getModel();
                        model.setRowCount(0);

                        try {
                            st = conexion.createStatement();
                            ResultSet r = st.executeQuery(sql);

                            while (r.next()) {
                                fila[0] = r.getString(1);
                                fila[1] = r.getString(2);
                                fila[2] = r.getString(3);
                                fila[3] = r.getString(4);
                                fila[4] = r.getString(5);
                                fila[5] = r.getString(6);
                                model.addRow(fila);
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex.toString());
                        }
                    }
                });
            }
        });
        oficioCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //visibilidad de paneles
                llenarTabla();
                nombresPanelOp.setVisible(false);
                ciudadPanelOp.setVisible(false);
                edadPanelOp.setVisible(false);
                oficioPanelOp.setVisible(true);
                numProyPanelOp.setVisible(false);

                oficioTextField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        Statement st;
                        String[] fila = new String[6];
                        String sql = "SELECT * FROM empleados WHERE Oficio LIKE '%" + oficioBusqueda + "%'";
                        oficioBusqueda = oficioTextField.getText();

                        // Limpiar tabla
                        DefaultTableModel model = (DefaultTableModel) empleadosTable.getModel();
                        model.setRowCount(0);

                        try {
                            st = conexion.createStatement();
                            ResultSet r = st.executeQuery(sql);

                            while (r.next()) {
                                fila[0] = r.getString(1);
                                fila[1] = r.getString(2);
                                fila[2] = r.getString(3);
                                fila[3] = r.getString(4);
                                fila[4] = r.getString(5);
                                fila[5] = r.getString(6);

                                model.addRow(fila);
                            }
                        } catch(SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex.toString());
                        }
                    }
                });

            }
        });
        numControlCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //visibilidad de paneles
                llenarTabla();
                numSimGrupo.clearSelection();
                nombresPanelOp.setVisible(false);
                ciudadPanelOp.setVisible(false);
                edadPanelOp.setVisible(false);
                oficioPanelOp.setVisible(false);
                numProyPanelOp.setVisible(true);

                numTextField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Statement st;
                        String[] fila = new String[6];
                        numValor = numTextField.getText();
                        String sql = "";

                        if (numMenorRadio.isSelected()) {
                            sql = "SELECT * FROM empleados WHERE NumControl < " + numValor;
                        } else if (numMayorRadio.isSelected()) {
                            sql = "SELECT * FROM empleados WHERE NumControl > " + numValor;
                        } else if (numIgualRadio.isSelected()) {
                            sql = "SELECT * FROM empleados WHERE NumControl = " + numValor;
                        }

                        // Limpiar tabla
                        DefaultTableModel model = (DefaultTableModel) empleadosTable.getModel();
                        model.setRowCount(0);

                        try {
                            st = conexion.createStatement();
                            ResultSet r = st.executeQuery(sql);

                            while (r.next()) {
                                System.out.println(r.getString(1));
                                fila[0] = r.getString(1);
                                fila[1] = r.getString(2);
                                fila[2] = r.getString(3);
                                fila[3] = r.getString(4);
                                fila[4] = r.getString(5);
                                fila[5] = r.getString(6);

                                model.addRow(fila);
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex.toString());
                        }
                    }
                });

            }
        });

    }

    public static void main(String[] args) {
        Form f = new Form();
        f.setContentPane(f.panelMain);
        f.setTitle("Práctica 3");
        f.setSize(800, 400);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Método para llenar la tabla de forma completa para limpieza después de búsquedas
    public void llenarTabla() {
        //BD
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        Statement st;
        String sql = "SELECT * FROM empleados";

        //tabla
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("NumControl");
        model.addColumn("Nombre");
        model.addColumn("Domicilio");
        model.addColumn("Ciudad");
        model.addColumn("Edad");
        model.addColumn("Oficio");

        //Ejecución de Query para mostrar datos
        String[] fila = new String[6];
        try {
            st = conexion.createStatement();
            ResultSet r = st.executeQuery(sql);
            while (r.next()) {
                fila[0] = r.getString(1);
                fila[1] = r.getString(2);
                fila[2] = r.getString(3);
                fila[3] = r.getString(4);
                fila[4] = r.getString(5);
                fila[5] = r.getString(6);

                model.addRow(fila);
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

        empleadosTable.setModel(model); //Dar modelo con filas y columnas a la tabla
    }
}
