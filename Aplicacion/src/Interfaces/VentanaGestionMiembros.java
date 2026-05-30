package Interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Funcionalidades.Colores;
import dao.ClubDAO;
import dao.UsuarioDAO;
import Funcionalidades.Club;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VentanaGestionMiembros extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableMiembros;
    private DefaultTableModel modelo;

    public VentanaGestionMiembros(VentanaPrincipal vp, Club club) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaDetallesClub.class.getResource("/Interfaces/logo.png")));

        setTitle("Gestion Miembros");
      //setBounds(100, 100, 500, 450);
        setBounds(100, 100, 500, 450);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Colores.negro);
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        
        JLabel lblTitulo = new JLabel("Gestion de Miembros");
        lblTitulo.setForeground(Colores.amarilloVivo);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lblTitulo.setBounds(30, 20, 300, 40);
        contentPane.add(lblTitulo);
        
        modelo = new DefaultTableModel();
        tableMiembros = new JTable(modelo);
        tableMiembros.setForeground(Colores.amarilloTexto);
        tableMiembros.setFillsViewportHeight(true);
        tableMiembros.setBackground(Colores.grisMedio);
        JScrollPane scrollTablaMiembros = new JScrollPane(tableMiembros);
        scrollTablaMiembros.setBackground(new Color(0, 128, 0));
        scrollTablaMiembros.setBounds(20, 100, 445, 196);
        contentPane.add(scrollTablaMiembros);
        

        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Telefono");
        modelo.addColumn("Correo");

        ArrayList<String[]> miembros =
                UsuarioDAO.getMiembrosClub(
                        club.getCodClub());

        for(String[] fila : miembros){

            modelo.addRow(fila);
        }
       
        JButton btnEliminarMiembro = new JButton("Eliminar Miembro");
        btnEliminarMiembro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		int fila = tableMiembros.getSelectedRow();

        	    if(fila == -1) {

        	        JOptionPane.showMessageDialog(
        	                contentPane,
        	                "Selecciona un miembro");

        	        return;
        	    }

        	    String dni =
        	            tableMiembros
        	            .getValueAt(fila, 0)
        	            .toString();

        	    int opcion =
        	            JOptionPane.showConfirmDialog(
        	                    contentPane,
        	                    "¿Eliminar este miembro del club?",
        	                    "Confirmación",
        	                    JOptionPane.YES_NO_OPTION);

        	    if(opcion == JOptionPane.YES_OPTION) {

        	        boolean eliminado =
        	                UsuarioDAO.eliminarMiembroClub(dni);

        	        if(eliminado) {

        	            modelo.removeRow(fila);

        	            JOptionPane.showMessageDialog(
        	                    contentPane,
        	                    "Miembro eliminado correctamente");

        	        } else {

        	            JOptionPane.showMessageDialog(
        	                    contentPane,
        	                    "No se pudo eliminar",
        	                    "Error",
        	                    JOptionPane.ERROR_MESSAGE);
        	        }
        	    }
        	}
        });
        btnEliminarMiembro.setBounds(164, 319, 151, 28);
        btnEliminarMiembro.setBackground(Colores.grisMedio);
		btnEliminarMiembro.setForeground(Colores.amarilloVivo);
		btnEliminarMiembro.setBorder(null);
        contentPane.add(btnEliminarMiembro);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                new VentanaDetallesClub(vp, club).setVisible(true);
                dispose();
            }
        });
        btnVolver.setBackground(Colores.grisMedio);
        btnVolver.setForeground(Colores.amarilloVivo);
        btnVolver.setBorder(null);
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnVolver.setBounds(180, 370, 120, 35);
        contentPane.add(btnVolver);
        
    }
}
