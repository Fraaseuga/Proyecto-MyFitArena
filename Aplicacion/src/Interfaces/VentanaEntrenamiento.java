package Interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Funcionalidades.Calistenia;
import Funcionalidades.Cardio;
import Funcionalidades.Colores;
import Funcionalidades.Elasticidad;
import Funcionalidades.Hipertrofia;
import Funcionalidades.Powerlifting;
import dao.EntrenamientoDAO;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.LineBorder;

public class VentanaEntrenamiento extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablaEntrenamientos;
    private DefaultTableModel modelo;
    
    private ArrayList<Calistenia> cal;
    private ArrayList<Hipertrofia> hip;
    private ArrayList<Powerlifting> pow;
    private ArrayList<Cardio> car;
    private ArrayList<Elasticidad> ela;    
    
    private boolean ordenAscendente = true;

    public VentanaEntrenamiento(VentanaPrincipal vp, int tipo) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                vp.setVisible(true);
                VentanaEntrenamiento.this.setVisible(false);
            }
        });

        setTitle("Entrenamientos");
        setBounds(100, 100, 900, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Colores.negro);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Dependiéndo del valor que se reciba de la ventana principal se ponen los elementos de un tipo de entrenamiento
        switch(tipo) {
        	case 1: // Calistenia
        		// TÍTULO
                JLabel lblTituloC = new JLabel("Calistenia");
                lblTituloC.setForeground(Colores.amarilloVivo);
                lblTituloC.setFont(new Font("Tahoma", Font.PLAIN, 28));
                lblTituloC.setBounds(30, 20, 300, 40);
                contentPane.add(lblTituloC);
                
                // TABLA
                String[] columnasC = {"Ejercicio","Dificultad","Lastre (kg)","Enfoque","Descripción"};

                modelo = new DefaultTableModel(null, columnasC);
                tablaEntrenamientos = new JTable(modelo);
                tablaEntrenamientos.setFillsViewportHeight(true);
                tablaEntrenamientos.setSelectionForeground(Colores.amarilloVivo);
                tablaEntrenamientos.setSelectionBackground(Colores.grisClaro);
                tablaEntrenamientos.setBackground(Colores.grisMuyOscuro);
                tablaEntrenamientos.setForeground(Colores.amarilloTexto);
                tablaEntrenamientos.setFont(new Font("Tahoma", Font.PLAIN, 14));
                tablaEntrenamientos.setRowHeight(28);
                tablaEntrenamientos.setGridColor(Colores.grisClaro);

                JScrollPane spTablaC = new JScrollPane(tablaEntrenamientos);
                spTablaC.setBackground(new Color(255, 255, 0));
                spTablaC.setBounds(30, 80, 820, 350);
                spTablaC.setBorder(null);
                contentPane.add(spTablaC);
                
                // Se añaden los datos a la tabla
                cal = EntrenamientoDAO.getEntrenamientoCalistenia();
                for(Calistenia c : cal) {
                	modelo.addRow(new Object[] {
                			c.getNombre(),
                			c.getDificultad(),
                			c.getLastre(),
                			c.getEnfoque(),
                			c.getDescripcion()});
                }
        		break;
        	case 2: // Hipertrofia
        		// TÍTULO
                JLabel lblTituloH = new JLabel("Hipertrofia");
                lblTituloH.setForeground(Colores.amarilloVivo);
                lblTituloH.setFont(new Font("Tahoma", Font.PLAIN, 28));
                lblTituloH.setBounds(30, 20, 300, 40);
                contentPane.add(lblTituloH);
                
                // TABLA
                String[] columnasH = {"Ejercicio","Dificultad","Músculo principal","Repeticiones","Descripción"};

                modelo = new DefaultTableModel(null, columnasH);
                tablaEntrenamientos = new JTable(modelo);
                tablaEntrenamientos.setFillsViewportHeight(true);
                tablaEntrenamientos.setSelectionForeground(Colores.amarilloVivo);
                tablaEntrenamientos.setSelectionBackground(Colores.grisClaro);
                tablaEntrenamientos.setBackground(Colores.grisMuyOscuro);
                tablaEntrenamientos.setForeground(Colores.amarilloTexto);
                tablaEntrenamientos.setFont(new Font("Tahoma", Font.PLAIN, 14));
                tablaEntrenamientos.setRowHeight(28);
                tablaEntrenamientos.setGridColor(Colores.grisClaro);

                JScrollPane spTablaH = new JScrollPane(tablaEntrenamientos);
                spTablaH.setBackground(new Color(255, 255, 0));
                spTablaH.setBounds(30, 80, 820, 350);
                spTablaH.setBorder(null);
                contentPane.add(spTablaH);
                
                // Se añaden los datos a la tabla
                hip = EntrenamientoDAO.getEntrenamientoHipertrofia();
                for(Hipertrofia h : hip) {
                	modelo.addRow(new Object[] {
                			h.getNombre(),
                			h.getDificultad(),
                			h.getMusculoPrincipal(),
                			h.getRepeticiones(),
                			h.getDescripcion()});
                }
        		break;
        	case 3: // Powerlifting
        		// TÍTULO
                JLabel lblTituloP = new JLabel("Powerlifting");
                lblTituloP.setForeground(Colores.amarilloVivo);
                lblTituloP.setFont(new Font("Tahoma", Font.PLAIN, 28));
                lblTituloP.setBounds(30, 20, 300, 40);
                contentPane.add(lblTituloP);
                
                // TABLA
                String[] columnasP = {"Ejercicio","Dificultad","Peso máximo","Repeticiones","Equipamiento","Descripción"};

                modelo = new DefaultTableModel(null, columnasP);
                tablaEntrenamientos = new JTable(modelo);
                tablaEntrenamientos.setFillsViewportHeight(true);
                tablaEntrenamientos.setSelectionForeground(Colores.amarilloVivo);
                tablaEntrenamientos.setSelectionBackground(Colores.grisClaro);
                tablaEntrenamientos.setBackground(Colores.grisMuyOscuro);
                tablaEntrenamientos.setForeground(Colores.amarilloTexto);
                tablaEntrenamientos.setFont(new Font("Tahoma", Font.PLAIN, 14));
                tablaEntrenamientos.setRowHeight(28);
                tablaEntrenamientos.setGridColor(Colores.grisClaro);

                JScrollPane spTablaP = new JScrollPane(tablaEntrenamientos);
                spTablaP.setBackground(new Color(255, 255, 0));
                spTablaP.setBounds(30, 80, 820, 350);
                spTablaP.setBorder(null);
                contentPane.add(spTablaP);
                
                // Se añaden los datos a la tabla
                pow = EntrenamientoDAO.getEntrenamientoPowerlifting();
                for(Powerlifting p : pow) {
                	modelo.addRow(new Object[] {
                			p.getNombre(),
                			p.getDificultad(),
                			p.getPesoMaximo(),
                			p.getRepeticiones(),
                			p.getEquipamiento(),
                			p.getDescripcion()});
                }
        		break;
        	case 4:	// Cardio
        		// TÍTULO
                JLabel lblTituloCar = new JLabel("Cardio");
                lblTituloCar.setForeground(Colores.amarilloVivo);
                lblTituloCar.setFont(new Font("Tahoma", Font.PLAIN, 28));
                lblTituloCar.setBounds(30, 20, 300, 40);
                contentPane.add(lblTituloCar);
                
                // TABLA
                String[] columnasCar = {"Ejercicio","Dificultad","Tipo de sesión","Calorías quemadas","Descripción"};

                modelo = new DefaultTableModel(null, columnasCar);
                tablaEntrenamientos = new JTable(modelo);
                tablaEntrenamientos.setFillsViewportHeight(true);
                tablaEntrenamientos.setSelectionForeground(Colores.amarilloVivo);
                tablaEntrenamientos.setSelectionBackground(Colores.grisClaro);
                tablaEntrenamientos.setBackground(Colores.grisMuyOscuro);
                tablaEntrenamientos.setForeground(Colores.amarilloTexto);
                tablaEntrenamientos.setFont(new Font("Tahoma", Font.PLAIN, 14));
                tablaEntrenamientos.setRowHeight(28);
                tablaEntrenamientos.setGridColor(Colores.grisClaro);

                JScrollPane spTablaCar = new JScrollPane(tablaEntrenamientos);
                spTablaCar.setBackground(new Color(255, 255, 0));
                spTablaCar.setBounds(30, 80, 820, 350);
                spTablaCar.setBorder(null);
                contentPane.add(spTablaCar);
                
                // Se añaden los datos a la tabla
                car = EntrenamientoDAO.getEntrenamientoCardio();
                for(Cardio c : car) {
                	modelo.addRow(new Object[] {
                			c.getNombre(),
                			c.getDificultad(),
                			c.getTipoSesion(),
                			c.getCaloriasQuemadas(),
                			c.getDescripcion()});
                }
        		break;
        	case 5:	// Elasticidad
        		// TÍTULO
                JLabel lblTituloE = new JLabel("Elasticidad");
                lblTituloE.setForeground(Colores.amarilloVivo);
                lblTituloE.setFont(new Font("Tahoma", Font.PLAIN, 28));
                lblTituloE.setBounds(30, 20, 300, 40);
                contentPane.add(lblTituloE);
                
                // TABLA
                String[] columnasE = {"Ejercicio","Dificultad","Zona corporal","Objetivo","Descripción"};

                modelo = new DefaultTableModel(null, columnasE);
                tablaEntrenamientos = new JTable(modelo);
                tablaEntrenamientos.setFillsViewportHeight(true);
                tablaEntrenamientos.setSelectionForeground(Colores.amarilloVivo);
                tablaEntrenamientos.setSelectionBackground(Colores.grisClaro);
                tablaEntrenamientos.setBackground(Colores.grisMuyOscuro);
                tablaEntrenamientos.setForeground(Colores.amarilloTexto);
                tablaEntrenamientos.setFont(new Font("Tahoma", Font.PLAIN, 14));
                tablaEntrenamientos.setRowHeight(28);
                tablaEntrenamientos.setGridColor(Colores.grisClaro);

                JScrollPane spTablaE = new JScrollPane(tablaEntrenamientos);
                spTablaE.setBackground(new Color(255, 255, 0));
                spTablaE.setBounds(30, 80, 820, 350);
                spTablaE.setBorder(null);
                contentPane.add(spTablaE);
                
                // Se añaden los datos a la tabla
                ela = EntrenamientoDAO.getEntrenamientoElasticidad();
                for(Elasticidad e : ela) {
                	modelo.addRow(new Object[] {
                			e.getNombre(),
                			e.getDificultad(),
                			e.getZonaCorporal(),
                			e.getObjetivo(),
                			e.getDescripcion()});
                }
        		break;
        	default:
        		break;
        }
        
        JSeparator separator_2_2_1 = new JSeparator();
        separator_2_2_1.setBackground(Color.WHITE);
        separator_2_2_1.setBounds(568, 533, 259, 40);
        contentPane.add(separator_2_2_1);

        // BOTÓN VER DETALLES
        JButton btnDescripcion = new JButton("Ver descripción");
        btnDescripcion.setBackground(Colores.grisMedio);
        btnDescripcion.setForeground(Colores.amarilloVivo);
        btnDescripcion.setBorder(null);
        btnDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnDescripcion.setBounds(342, 474, 200, 35);
        contentPane.add(btnDescripcion);

        // BOTÓN ORDENAR POR NOMBRE
        JButton btnOrdenarDificultad = new JButton("Ordenar por dificultad");
        btnOrdenarDificultad.setBackground(Colores.grisMedio);
        btnOrdenarDificultad.setForeground(Colores.amarilloVivo);
        btnOrdenarDificultad.setBorder(null);
        btnOrdenarDificultad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnOrdenarDificultad.setBounds(66, 474, 200, 35);
        contentPane.add(btnOrdenarDificultad);
        
        JTextArea taDescripcion = new JTextArea();
        taDescripcion.setBorder(null);
        taDescripcion.setMargin(new Insets(5, 5, 5, 5));
        taDescripcion.setForeground(Colores.amarilloSuave);
        taDescripcion.setBackground(Colores.grisMuyOscuro);
        taDescripcion.setBounds(569, 449, 257, 84);
        contentPane.add(taDescripcion);
        
        JSeparator separator_3 = new JSeparator();
        separator_3.setOrientation(SwingConstants.VERTICAL);
        separator_3.setBorder(null);
        separator_3.setBackground(Color.YELLOW);
        separator_3.setBounds(304, 457, 16, 68);
        contentPane.add(separator_3);

        // ACCIONES
        btnOrdenarDificultad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ordenarTablaPorDificultad();
            }
        });

        btnDescripcion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = tablaEntrenamientos.getSelectedRow();
                if (fila != -1) {
                	// Aquí se saca la descripción del la última columna que siempre será descripción sin importar la tabla
                	String descripcion = (String) modelo.getValueAt(fila, modelo.getColumnCount()-1);
                	
                	taDescripcion.setText(descripcion);
                }else {                	
                	JOptionPane.showMessageDialog(contentPane, 
                			"Selecciona un entrenamiento primero",
                			"ERROR",
                			JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Método para identificar la dificultad con un número
    private int valorDificultad(String dif) {
        if (dif.equalsIgnoreCase("Fácil")) return 1;
        if (dif.equalsIgnoreCase("Media")) return 2;
        if (dif.equalsIgnoreCase("Difícil")) return 3;
        return 0;
    }

    // Método para ordenar según la dificultad (Burbuja)
    private void ordenarTablaPorDificultad() {

        int filas = modelo.getRowCount();
        int columna = 1; // columna "Dificultad"

        for (int i = 0; i < filas - 1; i++) {
            for (int j = 0; j < filas - i - 1; j++) {

            	// Aquí se convierta la dificultad a un número
                int d1 = valorDificultad(modelo.getValueAt(j, columna).toString());
                int d2 = valorDificultad(modelo.getValueAt(j + 1, columna).toString());
                
                // Dependiendo de la variable ordenAscendente se ordenará de una forma u otra
                boolean debeIntercambiar =
                        (ordenAscendente && d1 > d2) ||	// Si se cumple se ordenará de forma ascendente
                        (!ordenAscendente && d1 < d2);	// Si no se cumple se ordenará de forma descendente

                // En caso de intercambiar las filas se camian todas las columnas de una fila por la otra
                if (debeIntercambiar) {
                    for (int c = 0; c < modelo.getColumnCount(); c++) {
                        Object temp = modelo.getValueAt(j, c);
                        modelo.setValueAt(modelo.getValueAt(j + 1, c), j, c);
                        modelo.setValueAt(temp, j + 1, c);
                    }
                }
            }
        }

        // Cambiar el estado para la próxima vez que se pulsa el botón
        ordenAscendente = !ordenAscendente;
    }
}
