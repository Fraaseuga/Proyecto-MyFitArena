package Interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Funcionalidades.Club;
import Funcionalidades.Colores;
import Funcionalidades.Usuario;
import dao.ClubDAO;
import dao.UsuarioDAO;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VentanaClub extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablaClubs;
    private DefaultTableModel modelo;
    private ArrayList<Club> clubs;

    public VentanaClub(VentanaPrincipal vp) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaClub.class.getResource("/Interfaces/logo.png")));

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new VentanaPrincipal().setVisible(true);
                VentanaClub.this.setVisible(false);
            }
        });

        setTitle("Clubs");
        setBounds(100, 100, 851, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Colores.negro);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Gestión de Clubs");
        lblTitulo.setForeground(Colores.amarilloVivo);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 28));
        lblTitulo.setBounds(30, 20, 300, 40);
        contentPane.add(lblTitulo);

        // TABLA
        String[] columnas = {"Club", "Miembros", "Capacidad", "Descripción"};

        modelo = new DefaultTableModel(null, columnas);
        tablaClubs = new JTable(modelo);
        tablaClubs.setFillsViewportHeight(true);
        tablaClubs.setBackground(Colores.grisMuyOscuro);
        tablaClubs.setForeground(Colores.amarilloTexto);
        tablaClubs.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tablaClubs.setRowHeight(28);
        tablaClubs.setGridColor(Colores.grisClaro);

        JScrollPane spTabla = new JScrollPane(tablaClubs);
        spTabla.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spTabla.setBounds(30, 80, 570, 350);
        spTabla.setBorder(null);
        contentPane.add(spTabla);

        clubs = ClubDAO.getTodosLosClubs();
        
        for(Club c : clubs) {
        	modelo.addRow(new Object[] {c.getNombre(),c.getMiembros(),c.getCapacidad(),c.getDescripcion()});
        }
        
        // BOTONES
        JButton btnOrdenarNombre = new JButton("Ordenar por Club");
        btnOrdenarNombre.setBackground(Colores.grisMedio);
        btnOrdenarNombre.setForeground(Colores.amarilloVivo);
        btnOrdenarNombre.setBorder(null);
        btnOrdenarNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnOrdenarNombre.setBounds(632, 313, 180, 35);
        contentPane.add(btnOrdenarNombre);

        JButton btnOrdenarMiembros = new JButton("Ordenar por Miembros");
        btnOrdenarMiembros.setBackground(Colores.grisMedio);
        btnOrdenarMiembros.setForeground(Colores.amarilloVivo);
        btnOrdenarMiembros.setBorder(null);
        btnOrdenarMiembros.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnOrdenarMiembros.setBounds(632, 358, 180, 35);
        contentPane.add(btnOrdenarMiembros);

        JButton btnOrdenarCapacidad = new JButton("Ordenar por Capacidad");
        btnOrdenarCapacidad.setBackground(Colores.grisMedio);
        btnOrdenarCapacidad.setForeground(Colores.amarilloVivo);
        btnOrdenarCapacidad.setBorder(null);
        btnOrdenarCapacidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnOrdenarCapacidad.setBounds(632, 268, 180, 35);
        contentPane.add(btnOrdenarCapacidad);

        JButton btnCrearClub = new JButton("Crear Club");
        btnCrearClub.setBackground(Colores.grisMedio);
        btnCrearClub.setForeground(Colores.amarilloVivo);
        btnCrearClub.setBorder(null);
        btnCrearClub.setFont(new Font("Tahoma", Font.PLAIN, 16));
        if(UsuarioDAO.perteneceAClub(VentanaPrincipal.propietario)) {        	
        	btnCrearClub.setBounds(632, 80, 180, 35);
        }else {        	
        	btnCrearClub.setBounds(632, 104, 180, 35);
        }
        if(!UsuarioDAO.esDuenoDeClub(VentanaPrincipal.propietario)) {        	
        	contentPane.add(btnCrearClub);
        }

        JButton btnVerDetalles = new JButton("Ver Detalles");
        btnVerDetalles.setBackground(Colores.grisMedio);
        btnVerDetalles.setForeground(Colores.amarilloVivo);
        btnVerDetalles.setBorder(null);
        btnVerDetalles.setFont(new Font("Tahoma", Font.PLAIN, 16));
        if(UsuarioDAO.perteneceAClub(VentanaPrincipal.propietario)) {
        	btnVerDetalles.setBounds(632, 130, 180, 35);        	
        }else {        	
        btnVerDetalles.setBounds(632, 154, 180, 35);
        }
        contentPane.add(btnVerDetalles);
        
        JSeparator separator_2_5_2 = new JSeparator();
        separator_2_5_2.setBackground(Color.WHITE);
        separator_2_5_2.setBounds(681, 239, 83, 68);
        contentPane.add(separator_2_5_2);
        
        if(UsuarioDAO.perteneceAClub(VentanaPrincipal.propietario) && !UsuarioDAO.esDuenoDeClub(VentanaPrincipal.propietario)) {        	
        	JButton btnAbandonarClub = new JButton("Abandonar club");
        	btnAbandonarClub.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			String club = ClubDAO.getNombreClubPorDni(VentanaPrincipal.propietario);
        			int decision = JOptionPane.showConfirmDialog(contentPane, 
        					"¿Seguro que quieres abandonar el club "+club+"?",
    	                    "Confirmación",
    	                    JOptionPane.YES_NO_OPTION);
        			
        			if(decision == JOptionPane.YES_OPTION) {
        				UsuarioDAO.eliminarMiembroClub(VentanaPrincipal.propietario);    
        				VentanaClub.this.dispose();
        				VentanaClub vc = new VentanaClub(vp);
        				vc.setVisible(true);
        			}
        		}
        	});
        	btnAbandonarClub.setForeground(new Color(255, 128, 128));
        	btnAbandonarClub.setFont(new Font("Tahoma", Font.PLAIN, 16));
        	btnAbandonarClub.setBorder(null);
        	btnAbandonarClub.setBackground(new Color(51, 51, 51));
        	btnAbandonarClub.setBounds(632, 181, 180, 35);
        	contentPane.add(btnAbandonarClub);
        }

        // ACCIONES
        btnOrdenarNombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ordenarTabla(0);
            }
        });

        btnOrdenarMiembros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ordenarTabla(1);
            }
        });

        btnOrdenarCapacidad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ordenarTabla(2);
            }
        });

        btnCrearClub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaCrearClub vcc = new VentanaCrearClub(vp);
                vcc.setVisible(true);
                VentanaClub.this.setVisible(false);
            }
        });

        btnVerDetalles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int fila = tablaClubs.getSelectedRow();

            	if (fila == -1) {
            	    JOptionPane.showMessageDialog(contentPane, "Selecciona un club primero");
            	    return;
            	}

            	String nombreSeleccionado = modelo.getValueAt(fila, 0).toString();

            	Club clubSeleccionado = null;

            	for (Club c : clubs) {
            	    if (c.getNombre().equals(nombreSeleccionado)) {
            	        clubSeleccionado = c;
            	        break;
            	    }
            	}

            	if (clubSeleccionado == null) {
            	    JOptionPane.showMessageDialog(contentPane,
            	            "No se pudo encontrar el club seleccionado",
            	            "ERROR",
            	            JOptionPane.ERROR_MESSAGE);
            	    return;
            	}

            	VentanaDetallesClub vdc = new VentanaDetallesClub(vp, clubSeleccionado);
            	vdc.setVisible(true);
            	VentanaClub.this.setVisible(false);
            }
        });
    }

    // ORDENACIÓN CLÁSICA
    private void ordenarTabla(int columna) {

        int filas = modelo.getRowCount();
        int columnas = modelo.getColumnCount();

        for (int i = 0; i < filas - 1; i++) {
            for (int j = 0; j < filas - i - 1; j++) {

                Object o1 = modelo.getValueAt(j, columna);
                Object o2 = modelo.getValueAt(j + 1, columna);

                boolean intercambiar = false;

                if (o1 instanceof Integer && o2 instanceof Integer) {
                    if ((int) o1 > (int) o2) {
                        intercambiar = true;
                    }
                } else {
                    if (o1.toString().compareTo(o2.toString()) > 0) {
                        intercambiar = true;
                    }
                }

                if (intercambiar) {
                    for (int c = 0; c < columnas; c++) {
                        Object temp = modelo.getValueAt(j, c);
                        modelo.setValueAt(modelo.getValueAt(j + 1, c), j, c);
                        modelo.setValueAt(temp, j + 1, c);
                    }
                }
            }
        }
    }
}
