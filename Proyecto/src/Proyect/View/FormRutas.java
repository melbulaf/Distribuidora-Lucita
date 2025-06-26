package Proyect.View;

/**
 * Rutas del Dia
 * @author MELANIE BULA FUENTES
 */

import Proyect.Model.Ruta;
import Proyect.Model.Producto;
import Proyect.Model.Pedido;
import Proyect.Model.Cliente;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.text.Normalizer;

public class FormRutas extends javax.swing.JPanel {
    // ---- Componentes principales ----
    private JLabel lblRutaNombre;
    private JPanel panelImagen;
    private JPanel panelDerecho;
    private CardLayout cardLayout;
    private JTable tablaPedidos;
    private DefaultTableModel modeloPedidos;
    private JPanel panelDetalle;
    private JLabel lblDetalleNombre, lblDetalleCliente, lblDetalleLugar, lblDetalleHora, lblDetalleUrgente;
    private JTextArea areaDetalleProductos;
    private JButton btnAtras;

    // ---- Datos ----
    private ArrayList<Ruta> rutas;
    private Ruta rutaActual;
    private Pedido pedidoActual;
    private Cliente clienteActual;

    // ---- Imagen lateral ----
    private int imgIndex = 0, imgCount = 2;
    private String diaAbrev = "lun";
    private final int IMG_MIERCOLES = 3;
    private JLabel imgLabel;
    private boolean mouseEstaAdentro = false; // Control ciclo enter+exit

    // ---- Paleta moderna ----
    private final Color azulFondo = new Color(243, 246, 251);
    private final Color blanco = Color.WHITE;
    private final Color azulPrincipal = new Color(44, 62, 80);
    private final Color azulClaro = new Color(92, 160, 218);
    private final Color acentoRojo = new Color(231, 76, 60);
    private final Color filaUrgente = new Color(255, 241, 241);

    // ---- Fuentes modernas ----
    private final Font fuenteTitulo = new Font("Segoe UI", Font.BOLD, 26);
    private final Font fuenteSubtitulo = new Font("Segoe UI", Font.PLAIN, 17);
    private final Font fuenteTabla = new Font("Segoe UI", Font.PLAIN, 16);
    private final Font fuenteTablaNegrita = new Font("Segoe UI", Font.BOLD, 16);
    private final Font fuenteDetalle = new Font("Segoe UI", Font.PLAIN, 16);
    private final Font fuenteBoton = new Font("Segoe UI Semibold", Font.PLAIN, 16);

    public FormRutas(ArrayList<Ruta> rutas) {
        this.rutas = rutas;
        setBackground(azulFondo);
        setOpaque(true);
        initComponents();
        cargarRutaDeHoy();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // --- Cabecera: Día de la semana: Ruta ---
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTop.setBackground(azulPrincipal);
        panelTop.setBorder(BorderFactory.createEmptyBorder(18, 30, 12, 20));
        lblRutaNombre = new JLabel("", SwingConstants.LEFT);
        lblRutaNombre.setFont(fuenteTitulo);
        lblRutaNombre.setForeground(Color.WHITE);
        panelTop.add(lblRutaNombre);

        // --- Panel imagen (izquierda) ---
        panelImagen = new JPanel();
        panelImagen.setBackground(blanco);
        panelImagen.setLayout(new BoxLayout(panelImagen, BoxLayout.Y_AXIS));
        panelImagen.setPreferredSize(new Dimension(260, 400));
        panelImagen.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 3, azulClaro),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        imgLabel = new JLabel();
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imgLabel.setBorder(BorderFactory.createLineBorder(azulClaro, 2, true));
        panelImagen.add(Box.createVerticalGlue());
        panelImagen.add(imgLabel);
        panelImagen.add(Box.createVerticalGlue());

        // Cambia la imagen SOLO cuando se completa el ciclo enter+exit
        imgLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseEstaAdentro = true;
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (mouseEstaAdentro) {
                    cambiarImagenCiclo();
                    mouseEstaAdentro = false;
                }
            }
        });

        // --- Panel derecho ---
        cardLayout = new CardLayout();
        panelDerecho = new JPanel(cardLayout);
        panelDerecho.setBackground(azulFondo);

        // --- Tabla de pedidos ---
        modeloPedidos = new DefaultTableModel(new Object[]{"Pedido", "Lugar", "Cliente", "Urgente"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaPedidos = new JTable(modeloPedidos) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                boolean urgente = (boolean) getModel().getValueAt(convertRowIndexToModel(row), 3);
                if (column == 0) {
                    c.setFont(urgente ? fuenteTablaNegrita : fuenteTabla);
                    c.setForeground(urgente ? acentoRojo : azulPrincipal);
                    if (c instanceof JLabel && urgente) {
                        JLabel l = (JLabel) c;
                        l.setText("⚠️ " + getModel().getValueAt(convertRowIndexToModel(row), 0));
                    } else if (c instanceof JLabel) {
                        ((JLabel) c).setText(getModel().getValueAt(convertRowIndexToModel(row), 0).toString());
                    }
                } else {
                    c.setFont(fuenteTabla);
                    c.setForeground(azulPrincipal);
                }
                c.setBackground(urgente ? filaUrgente : blanco);
                return c;
            }
        };
        tablaPedidos.setBackground(blanco);
        tablaPedidos.setSelectionBackground(new Color(223, 234, 247));
        tablaPedidos.setSelectionForeground(azulPrincipal);
        tablaPedidos.setRowHeight(34);
        tablaPedidos.setGridColor(new Color(230, 230, 230));
        tablaPedidos.setShowGrid(true);
        tablaPedidos.setFillsViewportHeight(true);
        tablaPedidos.getTableHeader().setFont(fuenteSubtitulo);
        tablaPedidos.getTableHeader().setBackground(azulClaro);
        tablaPedidos.getTableHeader().setForeground(Color.WHITE);
        // Oculta columnas Cliente y Urgente (solo muestra Pedido y Lugar)
        tablaPedidos.removeColumn(tablaPedidos.getColumnModel().getColumn(3)); // Urgente
        tablaPedidos.removeColumn(tablaPedidos.getColumnModel().getColumn(2)); // Cliente
        tablaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPedidos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1 && tablaPedidos.getSelectedRow() >= 0) {
                    mostrarDetallePedido(tablaPedidos.getSelectedRow());
                }
            }
        });
        JScrollPane scrollTabla = new JScrollPane(tablaPedidos);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        scrollTabla.getViewport().setBackground(blanco);

        // --- Vista detalle de pedido ---
        panelDetalle = new JPanel();
        panelDetalle.setBackground(blanco);
        panelDetalle.setLayout(new BoxLayout(panelDetalle, BoxLayout.Y_AXIS));
        panelDetalle.setBorder(BorderFactory.createEmptyBorder(28, 35, 28, 35));

        lblDetalleNombre = new JLabel();
        lblDetalleNombre.setFont(fuenteTitulo);
        lblDetalleNombre.setForeground(azulPrincipal);

        lblDetalleCliente = new JLabel();
        lblDetalleCliente.setFont(fuenteDetalle);
        lblDetalleCliente.setForeground(azulPrincipal);

        lblDetalleLugar = new JLabel();
        lblDetalleLugar.setFont(fuenteDetalle);
        lblDetalleLugar.setForeground(azulPrincipal);

        lblDetalleHora = new JLabel();
        lblDetalleHora.setFont(fuenteDetalle);
        lblDetalleHora.setForeground(azulPrincipal);

        lblDetalleUrgente = new JLabel();
        lblDetalleUrgente.setFont(fuenteDetalle.deriveFont(Font.BOLD, 17f));
        lblDetalleUrgente.setForeground(acentoRojo);

        JLabel lblProductos = new JLabel("Productos solicitados:");
        lblProductos.setFont(fuenteSubtitulo);
        lblProductos.setForeground(azulPrincipal);

        areaDetalleProductos = new JTextArea(7, 26);
        areaDetalleProductos.setFont(new Font("Consolas", Font.PLAIN, 15));
        areaDetalleProductos.setForeground(azulPrincipal);
        areaDetalleProductos.setBackground(new Color(244, 248, 255));
        areaDetalleProductos.setBorder(BorderFactory.createLineBorder(azulClaro, 1));
        areaDetalleProductos.setEditable(false);

        JScrollPane scrollDetalle = new JScrollPane(areaDetalleProductos);
        scrollDetalle.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));

        btnAtras = new JButton("← Volver a la lista");
        btnAtras.setFont(fuenteBoton);
        btnAtras.setBackground(azulClaro);
        btnAtras.setForeground(Color.WHITE);
        btnAtras.setFocusPainted(false);
        btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAtras.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(azulClaro, 1, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        btnAtras.addActionListener(e -> cardLayout.show(panelDerecho, "tabla"));

        panelDetalle.add(lblDetalleNombre);
        panelDetalle.add(Box.createVerticalStrut(16));
        panelDetalle.add(lblDetalleCliente);
        panelDetalle.add(lblDetalleLugar);
        panelDetalle.add(lblDetalleHora);
        panelDetalle.add(lblDetalleUrgente);
        panelDetalle.add(Box.createVerticalStrut(14));
        panelDetalle.add(lblProductos);
        panelDetalle.add(scrollDetalle);
        panelDetalle.add(Box.createVerticalStrut(12));
        panelDetalle.add(btnAtras);

        // Añadir vistas
        panelDerecho.add(scrollTabla, "tabla");
        panelDerecho.add(panelDetalle, "detalle");

        // --- SplitPane ---
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelImagen, panelDerecho);
        splitPane.setDividerLocation(270);
        splitPane.setResizeWeight(0.0);
        splitPane.setBorder(null);

        add(panelTop, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        setPreferredSize(new Dimension(950, 580));
    }

    /**
     * Busca la ruta del día actual y la muestra.
     */
    private void cargarRutaDeHoy() {
        String diaActual = LocalDate.now()
            .getDayOfWeek()
            .getDisplayName(TextStyle.FULL, new Locale("es", "ES"))
            .toLowerCase();
        String diaActualNorm = normalizarDia(diaActual);

        // SI ES JUEVES O DOMINGO: muestra mensaje y oculta paneles
        if (diaActualNorm.equals("jueves") || diaActualNorm.equals("domingo")) {
            lblRutaNombre.setText("NO HAY ENTREGAS HOY (" + diaActual + ")");
            lblRutaNombre.setForeground(acentoRojo);
            setBackground(new Color(255, 247, 247));
            panelImagen.setVisible(false);
            panelDerecho.setVisible(false);
            return;
        }

        // Busca la ruta del día actual
        Ruta rutaHoy = null;
        for (Ruta r : rutas) {
            if (normalizarDia(r.dia).equals(diaActualNorm)) {
                rutaHoy = r;
                break;
            }
        }
        if (rutaHoy == null && !rutas.isEmpty()) {
            rutaHoy = rutas.get(0);
        }
        if (rutaHoy != null) {
            rutaActual = rutaHoy;
            setBackground(azulFondo);
            panelImagen.setVisible(true);
            panelDerecho.setVisible(true);

            // Día abreviado para imágenes
            diaAbrev = abrevDia(diaActualNorm);
            imgCount = diaAbrev.equals("mie") ? 3 : 2;
            imgIndex = 0;
            mostrarImagenDelDia();

            // Cabecera: "Día de la semana: Ruta ..."
            String mayusDia = diaActual.substring(0,1).toUpperCase() + diaActual.substring(1);
            lblRutaNombre.setText(mayusDia + ": Ruta " + rutaActual.nombreRuta);
            lblRutaNombre.setForeground(Color.WHITE);

            cargarPedidos();
            cardLayout.show(panelDerecho, "tabla");
        }
    }

    /**
     * Abreviatura de día para las imágenes.
     */
    private String abrevDia(String dia) {
        switch (dia) {
            case "lunes": return "lun";
            case "martes": return "mar";
            case "miercoles":
            case "miércoles": return "mie";
            case "jueves": return "jue";
            case "viernes": return "vie";
            case "sabado":
            case "sábado": return "sab";
            case "domingo": return "dom";
            default: return "lun";
        }
    }

    /**
     * Cambia a la siguiente imagen del día.
     */
    private void cambiarImagenCiclo() {
        imgIndex = (imgIndex + 1) % imgCount;
        mostrarImagenDelDia();
    }

    /**
     * Muestra la imagen lateral del día según imgIndex.
     */
    private void mostrarImagenDelDia() {
        String baseNombre = "/Proyect/img/img_" + diaAbrev + (imgIndex + 1);
        String[] extensiones = { ".png", ".jpg", ".jpeg" };
        java.net.URL url = null;
        for (String ext : extensiones) {
            url = getClass().getResource(baseNombre + ext);
            if (url != null) break;
        }
        if (url != null) {
            imgLabel.setIcon(new ImageIcon(url));
            imgLabel.setText("");
        } else {
            imgLabel.setIcon(null);
            imgLabel.setText("[Imagen no encontrada]");
            imgLabel.setFont(fuenteSubtitulo);
        }
        imgLabel.repaint();
    }

    /**
     * Llena la tabla con los pedidos de la ruta actual.
     */
    private void cargarPedidos() {
        modeloPedidos.setRowCount(0);
        if (rutaActual == null) return;
        for (Cliente c : rutaActual.clientes) {
            for (Pedido p : c.pedidos) {
                modeloPedidos.addRow(new Object[]{p.nombre, p.lugar, c, p.urgente});
            }
        }
    }

    /**
     * Muestra detalles de un pedido seleccionado en la tabla.
     */
    private void mostrarDetallePedido(int row) {
        int modelRow = tablaPedidos.convertRowIndexToModel(row);
        String nombrePedido = (String) modeloPedidos.getValueAt(modelRow, 0);
        String lugarPedido = (String) modeloPedidos.getValueAt(modelRow, 1);
        Cliente c = (Cliente) modeloPedidos.getValueAt(modelRow, 2);
        boolean urgente = (boolean) modeloPedidos.getValueAt(modelRow, 3);
        Pedido pedido = null;
        for (Pedido p : c.pedidos) {
            if (p.nombre.equals(nombrePedido) && p.lugar.equals(lugarPedido)) {
                pedido = p; break;
            }
        }
        if (pedido == null) return;
        pedidoActual = pedido;
        clienteActual = c;
        lblDetalleNombre.setText("Pedido: " + pedido.nombre);
        lblDetalleNombre.setForeground(azulPrincipal);
        lblDetalleCliente.setText("Cliente: " + c.nombre);
        lblDetalleLugar.setText("Lugar: " + pedido.lugar);
        lblDetalleHora.setText("Hora: " + (pedido.hora != null ? pedido.hora : "Sin hora"));
        if (urgente) {
            lblDetalleUrgente.setText("⚠️ ¡¡¡URGENTE!!!");
            lblDetalleUrgente.setForeground(acentoRojo);
        } else {
            lblDetalleUrgente.setText("");
            lblDetalleUrgente.setForeground(azulPrincipal);
        }
        StringBuilder sb = new StringBuilder();
        for (Producto prod : pedido.productos) {
            sb.append("• ").append(prod.nombre).append(": ").append(prod.cantidad).append("\n");
        }
        areaDetalleProductos.setText(sb.toString());
        cardLayout.show(panelDerecho, "detalle");
    }

    private String normalizarDia(String dia) {
        return Normalizer.normalize(dia, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
    }
}