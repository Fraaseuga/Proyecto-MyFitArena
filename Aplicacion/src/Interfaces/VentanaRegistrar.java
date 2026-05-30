package Interfaces;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Funcionalidades.Colores;
import dao.UsuarioDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaRegistrar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfApellidos;
	private JTextField tfDNI;
	private JTextField tfTelefono;
	private JTextField tfCorreo;
	private JTextField tfContrasena;
	private JTextField tfConfirmarContrasena;


	public VentanaRegistrar() {
		VentanaPrincipal vp = new VentanaPrincipal();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				vp.setVisible(true);
				VentanaRegistrar.this.dispose();
			}
		});
		setTitle("Resgistrar Nuevo Usuario");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRegistrar.class.getResource("/Interfaces/logo.png")));
		//Ajustes de la ventana
		setBounds(0, 0, 700, 550);
		//Hace que la ventana aparezca en medio de la pantalla
		setLocationRelativeTo(null);
		//PANEL PRINCIPAL
		contentPane = new JPanel();
		contentPane.setBackground(Colores.negro);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//PANEL SECUNDARIO
		JPanel panelSecundario = new JPanel();
		panelSecundario.setForeground(new Color(255, 255, 255));
		panelSecundario.setBackground(new Color(0, 0, 0));
		panelSecundario.setLocation(10, 186);
		panelSecundario.setSize(666, 317);
		contentPane.add(panelSecundario);
		panelSecundario.setLayout(null);
		//Se crea un border con 3 pixeles de ancho
		Border borde = BorderFactory.createLineBorder(Colores.amarilloVivo, 3);
        panelSecundario.setBorder(borde);
		//LABELS
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 10, 144, 38);
		panelSecundario.add(lblNombre);
		lblNombre.setForeground(Colores.amarilloVivo);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDNI = new JLabel("DNI/NIE/NIF:");
		lblDNI.setBounds(10, 165, 144, 38);
		panelSecundario.add(lblDNI);
		lblDNI.setForeground(Colores.amarilloVivo);
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel LogoMFA = new JLabel("");
		LogoMFA.setHorizontalAlignment(SwingConstants.CENTER);
		LogoMFA.setIcon(new ImageIcon(VentanaRegistrar.class.getResource("/Interfaces/logo.png")));
		LogoMFA.setBounds(10, 10, 676, 166);
		contentPane.add(LogoMFA);
		
		JLabel lblConfirmarContrasena = new JLabel("Confirmar contraseña:");
		lblConfirmarContrasena.setForeground(new Color(255, 215, 0));
		lblConfirmarContrasena.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConfirmarContrasena.setBounds(356, 165, 144, 38);
		panelSecundario.add(lblConfirmarContrasena);
		
		JLabel lblTelefono = new JLabel("Número de teléfono: ");
		lblTelefono.setForeground(new Color(255, 215, 0));
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefono.setBounds(10, 234, 144, 38);
		panelSecundario.add(lblTelefono);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setForeground(new Color(255, 215, 0));
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasena.setBounds(356, 82, 144, 38);
		panelSecundario.add(lblContrasena);
		//TEXTFIELDS
		tfNombre = new JTextField();
		tfNombre.setBounds(10, 58, 300, 25);
		panelSecundario.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfApellidos = new JTextField();
		tfApellidos.setBounds(10, 133, 300, 25);
		panelSecundario.add(tfApellidos);
		tfApellidos.setColumns(10);

		//BOTONES
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setForeground(new Color(255, 215, 0));
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(10, 82, 144, 38);
		panelSecundario.add(lblApellidos);


		JLabel lblCorreo = new JLabel("Correo eléctronico:");
		lblCorreo.setForeground(new Color(255, 215, 0));
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCorreo.setBounds(356, 10, 144, 38);
		panelSecundario.add(lblCorreo);

		tfDNI = new JTextField();
		tfDNI.setColumns(10);
		tfDNI.setBounds(10, 213, 300, 25);
		panelSecundario.add(tfDNI);
		
		tfConfirmarContrasena = new JTextField();
		tfConfirmarContrasena.setColumns(10);
		tfConfirmarContrasena.setBounds(356, 213, 300, 25);
		panelSecundario.add(tfConfirmarContrasena);
		
		tfTelefono = new JTextField();
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(10, 282, 300, 25);
		panelSecundario.add(tfTelefono);

		tfCorreo = new JTextField();
		tfCorreo.setColumns(10);
		tfCorreo.setBounds(356, 58, 300, 25);
		panelSecundario.add(tfCorreo);
		
		tfContrasena = new JTextField();
		tfContrasena.setColumns(10);
		tfContrasena.setBounds(356, 133, 300, 25);
		panelSecundario.add(tfContrasena);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBackground(Colores.amarilloVivo);
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRegistrar.setBounds(512, 282, 144, 25);
		panelSecundario.add(btnRegistrar);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Se comprueba si todos los campos están rellenados
					if(tfNombre.getText().equalsIgnoreCase("")
							|| tfApellidos.getText().equalsIgnoreCase("")
							|| tfDNI.getText().equalsIgnoreCase("")
							|| tfTelefono.getText().equalsIgnoreCase("")
							|| tfCorreo.getText().equalsIgnoreCase("")
							|| tfContrasena.getText().equalsIgnoreCase("")) {	
						JOptionPane.showMessageDialog(contentPane, 
								"Debes rellenar todos los campos",
								"ERROR",
								JOptionPane.ERROR_MESSAGE);
					}else {
						int telefono = Integer.parseInt(tfTelefono.getText());
						
						// Se comprueba si las dos contraseñas coinciden
						if(tfContrasena.getText().equals(tfConfirmarContrasena.getText())) {
							// Se comprueba si el email es válido
							if(VentanaRegistrar.esEmailValido(tfCorreo.getText())) {								
								// Se crea el usuario con el método crearUsuario de la clase UsuarioDAO
								String mensaje = UsuarioDAO.crearUsuario(
										tfDNI.getText(),
										tfNombre.getText(),
										tfApellidos.getText(),
										telefono,
										tfCorreo.getText(),
										tfContrasena.getText());
								JOptionPane.showMessageDialog(contentPane, 
										mensaje,
										"Confirmación",
										JOptionPane.INFORMATION_MESSAGE);
								
								VentanaPrincipal.propietario = tfDNI.getText();
								VentanaRegistrar.this.dispose();
								vp.dispose();
								VentanaPrincipal nueva = new VentanaPrincipal();
								nueva.setVisible(true);
							}else {
								JOptionPane.showMessageDialog(contentPane, 
										"El correo no es válido",
										"ERROR",
										JOptionPane.ERROR_MESSAGE);	
							}
							
						}else {
							JOptionPane.showMessageDialog(contentPane, 
									"Las contraseñas no coinciden",
									"ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}catch(Exception er) {
					JOptionPane.showMessageDialog(contentPane, 
							"El teléfono debe ser un número",
							"ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}
	
	public static boolean esEmailValido(String email) {
	    if (email == null) return false;

	    int length = email.length();

	    // No puede ser demasiado corto
	    if (length < 5) return false;

	    int posArroba = -1;
	    int posPunto = -1;

	    // 1. Buscar la @ y el último punto usando charAt
	    for (int i = 0; i < length; i++) {
	        char c = email.charAt(i);

	        if (c == '@') {
	            // Si ya había una @ → inválido
	            if (posArroba != -1) return false;
	            posArroba = i;
	        }

	        if (c == '.') {
	            posPunto = i;
	        }

	        // No permitir espacios
	        if (c == ' ') return false;
	    }

	    // Debe haber exactamente una @
	    if (posArroba == -1) return false;

	    // La @ no puede estar al principio ni al final
	    if (posArroba == 0 || posArroba == length - 1) return false;

	    // Debe haber un punto DESPUÉS de la @
	    if (posPunto == -1 || posPunto < posArroba) return false;

	    // El punto no puede ser el último carácter
	    if (posPunto == length - 1) return false;

	    // La extensión debe tener al menos 2 caracteres
	    if (length - posPunto - 1 < 2) return false;

	    return true;
	}

}