package dap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private IArray arrayImplementation;

    public MainFrame() {
        // Configuración del JFrame
        setTitle("Selector de Implementación");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 150);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Botones para seleccionar la implementación
        JButton listaButton = createStyledButton("Usar Lista de Java");
        JButton miArrayButton = createStyledButton("Usar MiArray");

        // Configuración de acciones para los botones
        listaButton.addActionListener(e -> {
            arrayImplementation = new ListAdapter(new ArrayList<>());
            openOperationsFrame(arrayImplementation);
        });

        miArrayButton.addActionListener(e -> {
            arrayImplementation = new MiArray();
            openOperationsFrame(arrayImplementation);
        });

        // Añadir componentes al JFrame
        add(Box.createVerticalGlue()); // Espacio en la parte superior
        add(listaButton);
        add(Box.createVerticalStrut(10)); // Espacio entre botones
        add(miArrayButton);
        add(Box.createVerticalGlue()); // Espacio en la parte inferior

        // Mostrar el JFrame
       //pack(); // Ajustar el tamaño automáticamente
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto", Font.PLAIN, 18));
        button.setSize(150, 100);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void openOperationsFrame(IArray arrayImplementation) {
        // Crear la segunda ventana para operaciones
        JFrame operationsFrame = new JFrame("Operaciones con " + arrayImplementation.getClass().getSimpleName() );
        operationsFrame.setSize(400, 300);
        operationsFrame.setLayout(new BorderLayout()); // Cambiar el layout a BorderLayout
        operationsFrame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        // Crear panel para los botones (métodos)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3, 10, 10)); // GridLayout para los botones

        JButton addButton = new JButton("Añadir Elemento");
        JButton removeButton = new JButton("Eliminar Elemento");
        JButton clearButton = new JButton("Limpiar Array");
        JButton isEmptyButton = new JButton("¿Es Vacío?");
        JButton sizeButton = new JButton("Tamaño");
        JButton getFirstButton = new JButton("Primer Elemento");
        JButton getLastButton = new JButton("Último Elemento");
        JButton viewAllButton = new JButton("View");

        // Añadir botones al panel
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(isEmptyButton);
        buttonPanel.add(sizeButton);
        buttonPanel.add(getFirstButton);
        buttonPanel.add(getLastButton);
        buttonPanel.add(viewAllButton);

        // Crear panel para el resultado
        JTextArea resultTextArea = new JTextArea(4, 20); // Ajustar el número de filas y columnas
        resultTextArea.setEditable(false);
        resultTextArea.setFont(new Font("Roboto", Font.PLAIN, 12)); // Ajustar el tamaño de la fuente
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textPanel.add(resultTextArea, BorderLayout.CENTER);


        // Configuración de acciones para los botones
        addButton.addActionListener(e -> {
            if (arrayImplementation != null) {
                try {
                    int element = Integer.parseInt(JOptionPane.showInputDialog("Introduce el elemento a añadir:"));
                    arrayImplementation.anadir(element);
                    resultTextArea.setText("Elemento añadido: " + element + "\n");
                } catch (NumberFormatException ex) {
                    resultTextArea.setText("Error: Introduce un número válido\n");
                }
            }
        });

        removeButton.addActionListener(e -> {
            if (arrayImplementation != null) {
                try {
                    int position = Integer.parseInt(JOptionPane.showInputDialog("Introduce la posición a eliminar:"));
                    arrayImplementation.eliminar(position);
                    resultTextArea.setText("Elemento en la posición " + position + " eliminado\n");
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    resultTextArea.setText("Error: Introduce una posición válida\n");
                }
            }
        });

        clearButton.addActionListener(e -> {
            if (arrayImplementation != null) {
                arrayImplementation.vaciar();
                resultTextArea.setText("Array vaciado\n");
            }
        });

        isEmptyButton.addActionListener(e -> {
            if (arrayImplementation != null) {
                resultTextArea.setText("Array vacío: " + arrayImplementation.esVacio() + "\n");
            }
        });

        sizeButton.addActionListener(e -> {
            if (arrayImplementation != null) {
                resultTextArea.setText("Tamaño del array:" + arrayImplementation.tamano() + "\n");
            }
        });

        getFirstButton.addActionListener(e -> {
            if (arrayImplementation != null) {
                try {
                    resultTextArea.setText("Primer elemento: " + arrayImplementation.primero() + "\n");
                } catch (IllegalStateException ex) {
                    resultTextArea.setText("Error: " + ex.getMessage() + "\n");
                }
            }
        });

        getLastButton.addActionListener(e -> {
            if (arrayImplementation != null) {
                try {
                    resultTextArea.setText("Último elemento: " + arrayImplementation.ultimo() + "\n");
                } catch (IllegalStateException ex) {
                    resultTextArea.setText("Error: " + ex.getMessage() + "\n");
                }
            }
        });

        viewAllButton.addActionListener(e -> {
            if (arrayImplementation != null) {
                resultTextArea.setText("Contenido del array:\n");
                for (int i = 0; i < arrayImplementation.tamano(); ++i) {
                    resultTextArea.append(arrayImplementation.devolverPosicion(i) + " ");
                }
                resultTextArea.append("\n");
            }
        });

        // Añadir paneles a la segunda ventana
        buttonPanel.setPreferredSize(new Dimension(400, 100));
        operationsFrame.add(buttonPanel, BorderLayout.SOUTH); // Botones en la parte inferior
        operationsFrame.add(textPanel, BorderLayout.CENTER); // Resultado en el centro

        // Mostrar la segunda ventana
        operationsFrame.setResizable(false);
        operationsFrame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        operationsFrame.pack();
        operationsFrame.setVisible(true);
    }

}
