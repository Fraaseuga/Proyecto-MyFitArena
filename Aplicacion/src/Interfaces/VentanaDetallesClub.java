package Interfaces;

import javax.swing.*;

import Funcionalidades.Colores;

import java.awt.*;
import java.awt.event.*;

public class VentanaDetallesClub extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public VentanaDetallesClub(VentanaPrincipal vp, String nombre, String miembros, String capacidad, String descripcion) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaDetallesClub.class.getResource("/Interfaces/logo.png")));

        setTitle("Detalles del Club");
        setBounds(100, 100, 500, 450);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Colores.negro);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Detalles del Club");
        lblTitulo.setForeground(Colores.amarilloVivo);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lblTitulo.setBounds(30, 20, 300, 40);
        contentPane.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre: " + nombre);
        lblNombre.setForeground(Colores.amarilloTexto);
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNombre.setBounds(30, 90, 400, 25);
        contentPane.add(lblNombre);

        JLabel lblMiembros = new JLabel("Miembros: " + miembros);
        lblMiembros.setForeground(Colores.amarilloTexto);
        lblMiembros.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMiembros.setBounds(30, 130, 400, 25);
        contentPane.add(lblMiembros);

        JLabel lblCapacidad = new JLabel("Capacidad: " + capacidad);
        lblCapacidad.setForeground(Colores.amarilloTexto);
        lblCapacidad.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCapacidad.setBounds(30, 170, 400, 25);
        contentPane.add(lblCapacidad);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setForeground(Colores.amarilloTexto);
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblDescripcion.setBounds(30, 210, 200, 25);
        contentPane.add(lblDescripcion);

        JTextArea taDescripcion = new JTextArea(descripcion);
        taDescripcion.setMargin(new Insets(7, 7, 7, 7));
        taDescripcion.setEditable(false);
        taDescripcion.setBackground(Colores.grisMedio);
        taDescripcion.setForeground(Color.WHITE);
        taDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        taDescripcion.setLineWrap(true);
        taDescripcion.setWrapStyleWord(true);

        JScrollPane spDesc = new JScrollPane(taDescripcion);
        spDesc.setBounds(30, 240, 420, 120);
        spDesc.setBorder(null);
        contentPane.add(spDesc);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(Colores.grisMedio);
        btnVolver.setForeground(Colores.amarilloVivo);
        btnVolver.setBorder(null);
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnVolver.setBounds(180, 370, 120, 35);
        contentPane.add(btnVolver);

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaClub(vp).setVisible(true);
                VentanaDetallesClub.this.dispose();
            }
        });
    }
}
