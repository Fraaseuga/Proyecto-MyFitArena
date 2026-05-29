package Interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Funcionalidades.Colores;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaCuenta extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfNombre;
    private JTextField tfApellidos;
    private JTextField tfCorreo;
    private JTextField tfTelefono;
    private JPasswordField pfContraseña;
    private JButton btnEliminar;
    private JButton btnConfirmar;
    
    public static void main(String[] args) {
		VentanaCuenta frame = new VentanaCuenta();
		frame.setVisible(true);
	}

    public VentanaCuenta(){
    	//AJUSTES DE LA VENTANA
    	setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaDetallesClub.class.getResource("/Interfaces/logo.png")));
        setTitle("Datos de la Cuenta");
        setBounds(100, 100, 500, 450);
        setLocationRelativeTo(null);
        //PANEL PRINCIPAL
        contentPane = new JPanel();
        contentPane.setBackground(Colores.negro);
        contentPane.setLayout(null);
        setContentPane(contentPane);
        //LABELS
        JLabel lblTitulo = new JLabel("Datos de la Cuenta");
        lblTitulo.setForeground(Colores.amarilloVivo);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lblTitulo.setBounds(30, 36, 300, 40);
        contentPane.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre: ");
        lblNombre.setForeground(Colores.amarilloTexto);
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNombre.setBounds(30, 117, 400, 25);
        contentPane.add(lblNombre);

        JLabel lblContraseña = new JLabel("Contraseña: " + ocultarContraseña("cacahuete"));
        lblContraseña.setForeground(Colores.amarilloTexto);
        lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblContraseña.setBounds(30, 187, 400, 25);
        contentPane.add(lblContraseña);

        JLabel lblCapacidad = new JLabel("Correo: ");
        lblCapacidad.setForeground(Colores.amarilloTexto);
        lblCapacidad.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCapacidad.setBounds(30, 222, 432, 25);
        contentPane.add(lblCapacidad);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setForeground(Colores.amarilloTexto);
        lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTelefono.setBounds(30, 257, 432, 25);
        
        JLabel lblClub = new JLabel("Club actual: ");
        lblClub.setForeground(new Color(255, 250, 201));
        lblClub.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblClub.setBounds(30, 292, 400, 25);
        contentPane.add(lblClub);
        
        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setForeground(new Color(255, 250, 201));
        lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblApellidos.setBounds(30, 152, 432, 25);
        contentPane.add(lblApellidos);
        //TEXTFIELD
        tfNombre = new JTextField();
        tfNombre.setBounds(133, 123, 329, 18);
        contentPane.add(tfNombre);
        tfNombre.setColumns(10);
        contentPane.add(lblTelefono);
        tfNombre.setVisible(false);
        
        tfApellidos = new JTextField();
        tfApellidos.setColumns(10);
        tfApellidos.setBounds(133, 159, 329, 18);
        contentPane.add(tfApellidos);
        tfApellidos.setVisible(false);
        
        tfCorreo = new JTextField();
        tfCorreo.setColumns(10);
        tfCorreo.setBounds(133, 228, 329, 18);
        contentPane.add(tfCorreo);
        tfCorreo.setVisible(false);
        
        tfTelefono = new JTextField();
        tfTelefono.setColumns(10);
        tfTelefono.setBounds(133, 263, 329, 18);
        contentPane.add(tfTelefono);
        tfTelefono.setVisible(false);
        //PASSWORDFILEDS
        pfContraseña = new JPasswordField();
        pfContraseña.setEchoChar('*');
        pfContraseña.setBounds(133, 193, 329, 18);
        contentPane.add(pfContraseña);
        pfContraseña.setVisible(false);
        //BOTONES
        JButton btnEditar = new JButton("Editar cuenta");
        btnEditar.setBackground(Colores.grisMedio);
        btnEditar.setForeground(Colores.amarilloVivo);
        btnEditar.setBorder(null);
        btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnEditar.setBounds(29, 327, 200, 35);
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//Aparecen los componente para modifcar
            	tfNombre.setVisible(true);
            	tfApellidos.setVisible(true);
            	pfContraseña.setVisible(true);
            	tfCorreo.setVisible(true);
            	tfTelefono.setVisible(true);
            	//Se intercambian los botnes Eliminar por Confirmar
            	btnEliminar.setVisible(false);
            	btnConfirmar.setVisible(true);
                //Mark haz la magia
            	
            	
            }
        });
        contentPane.add(btnEditar);
        
        btnEliminar = new JButton("EliminarCuenta");
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Mark es tu movimiento
        	}
        });
        btnEliminar.setForeground(new Color(255, 215, 0));
        btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnEliminar.setBorder(null);
        btnEliminar.setBackground(new Color(51, 51, 51));
        btnEliminar.setBounds(262, 327, 200, 35);
        contentPane.add(btnEliminar);
        
        btnConfirmar = new JButton("Confirmar cambios");
        btnConfirmar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(contentPane, "Se ha modificados los cambios.", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        		//Mark te toca a ti
        	}
        });
        btnConfirmar.setForeground(new Color(255, 215, 0));
        btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnConfirmar.setBorder(null);
        btnConfirmar.setBackground(new Color(51, 51, 51));
        btnConfirmar.setBounds(262, 327, 200, 35);
        contentPane.add(btnConfirmar);
        btnConfirmar.setVisible(false);
        
        //Metodo Closing
        addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			dispose();
    			VentanaPrincipal principal = new VentanaPrincipal();
    			principal.setVisible(true);
    		}
    	});
    }
    //Funcion para filtrar la contraseña al mostrarla
    public String ocultarContraseña(String contraseña) {
    	String ocultado = "";
    	for(int i = 0; i < contraseña.length(); i++) {
    		ocultado += "*";
    	}
    	return ocultado;
    }
}


