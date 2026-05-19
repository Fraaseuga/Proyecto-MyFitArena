package Interfaces;

import javax.swing.*;

import Funcionalidades.Colores;

import java.awt.*;
import java.awt.event.*;

public class VentanaCrearClub extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public VentanaCrearClub(VentanaPrincipal vp) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCrearClub.class.getResource("/Interfaces/logo.png")));

        setTitle("Crear Club");
        setBounds(100, 100, 500, 450);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Colores.negro);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Nuevo Club");
        lblTitulo.setForeground(Colores.amarilloVivo);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lblTitulo.setBounds(30, 20, 300, 40);
        contentPane.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(Colores.amarilloTexto);
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNombre.setBounds(30, 90, 120, 25);
        contentPane.add(lblNombre);

        JTextField tfNombre = new JTextField();
        tfNombre.setCaretColor(new Color(255, 255, 255));
        tfNombre.setBackground(Colores.grisMedio);
        tfNombre.setForeground(Color.WHITE);
        tfNombre.setBorder(null);
        tfNombre.setBounds(160, 90, 250, 25);
        contentPane.add(tfNombre);

        JLabel lblCapacidad = new JLabel("Capacidad:");
        lblCapacidad.setForeground(Colores.amarilloTexto);
        lblCapacidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCapacidad.setBounds(30, 140, 120, 25);
        contentPane.add(lblCapacidad);

        JTextField tfCapacidad = new JTextField();
        tfCapacidad.setCaretColor(new Color(255, 255, 255));
        tfCapacidad.setBackground(Colores.grisMedio);
        tfCapacidad.setForeground(Color.WHITE);
        tfCapacidad.setBorder(null);
        tfCapacidad.setBounds(160, 140, 250, 25);
        contentPane.add(tfCapacidad);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setForeground(Colores.amarilloTexto);
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDescripcion.setBounds(30, 190, 120, 25);
        contentPane.add(lblDescripcion);

        JTextArea taDescripcion = new JTextArea();
        taDescripcion.setCaretColor(new Color(255, 255, 255));
        taDescripcion.setBackground(Colores.grisMedio);
        taDescripcion.setForeground(Color.WHITE);
        taDescripcion.setBorder(null);
        taDescripcion.setLineWrap(true);
        taDescripcion.setWrapStyleWord(true);

        JScrollPane spDesc = new JScrollPane(taDescripcion);
        spDesc.setBounds(160, 190, 250, 100);
        spDesc.setBorder(null);
        contentPane.add(spDesc);

        JButton btnCrear = new JButton("Crear");
        btnCrear.setBackground(Colores.grisMedio);
        btnCrear.setForeground(Colores.amarilloVivo);
        btnCrear.setBorder(null);
        btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCrear.setBounds(160, 320, 120, 35);
        contentPane.add(btnCrear);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(Colores.grisMedio);
        btnCancelar.setForeground(Colores.amarilloVivo);
        btnCancelar.setBorder(null);
        btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCancelar.setBounds(300, 320, 120, 35);
        contentPane.add(btnCancelar);

        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new VentanaClub(vp).setVisible(true);
                VentanaCrearClub.this.dispose();
            }
        });
    }
}
