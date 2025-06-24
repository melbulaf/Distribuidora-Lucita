package Proyect.View;

import Proyect.Model.Ruta;
import Proyect.Model.RegistrarPedido;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import javax.swing.table.*;
import java.time.LocalDate;
import java.util.Locale;
import java.text.Normalizer;

public class FormRutas extends javax.swing.JPanel {
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

    private ArrayList<Ruta> rutas;
    private Ruta rutaActual;

    private int imgIndex = 0, imgCount = 2;
    private String diaAbrev = "lun";
    private JLabel imgLabel;
    private boolean mouseEstaAdentro = false;

    private final Color azulFondo = new Color(243, 246, 251);
    private final Color blanco = Color.WHITE;
    private final Color azulPrincipal = new Color(44, 62, 80);
    private final Color azulClaro = new Color(92, 160, 218);
    private final Color acentoRojo = new Color(231, 76, 60);

    private final Font fuenteTitulo = new Font("Segoe UI", Font.BOLD, 26);
    private final Font fuenteSubtitulo = new Font("Segoe UI", Font.PLAIN, 17);
    private final Font fuenteTabla = new Font("Segoe UI", Font.PLAIN, 16);
    private final Font fuenteTablaNegrita = new Font("Segoe UI", Font.BOLD, 16);
    private final Font fuenteDetalle = new Font("Segoe UI", Font.PLAIN, 16);
    private final Font fuenteBoton = new Font("Segoe UI Semibold", Font.PLAIN, 16);

    // Clientes considerados "urgentes" en los ejemplos
    private final Set<String> clientesUrgentesEjemplo = new HashSet<String>() {{
        add("Pedro Ruiz");
        add("Lucía Vargas");
        add("Sofía Herrera");
        add("Miguel Pardo");
    }};
    // Lista auxiliar para vincular filas y objetos pedido
    private ArrayList<RegistrarPedido> pedidosMostrados = new ArrayList<>();

    // Panel especial para mostrar el mensaje de que no hay entregas
    private JPanel panelSinPedidos;
    private JLabel lblSinPedidos;

    public FormRutas(ArrayList<Ruta> rutas) {
        this.rutas = rutas;
        setBackground(azulFondo);
        setOpaque(true);
        initComponents();
        cargarRutaDeHoy();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTop.setBackground(azulPrincipal);
        panelTop.setBorder(BorderFactory.createEmptyBorder(18, 30, 12, 20));
        lblRutaNombre = new JLabel("", SwingConstants.LEFT);
        lblRutaNombre.setFont(fuenteTitulo);
        lblRutaNombre.setForeground(Color.WHITE);
        panelTop.add(lblRutaNombre);

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

        cardLayout = new CardLayout();
        panelDerecho = new JPanel(cardLayout);
        panelDerecho.setBackground(azulFondo);

        modeloPedidos = new DefaultTableModel(new Object[]{"Pedido", "Lugar"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaPedidos = new JTable(modeloPedidos);
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
        tablaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPedidos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1 && tablaPedidos.getSelectedRow() >= 0 && tablaPedidos.isEnabled()) {
                    mostrarDetallePedido(tablaPedidos.getSelectedRow());
                }
            }
        });
        // SOLO el nombre del pedido en rojo si es urgente
        tablaPedidos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(fuenteTabla);

                RegistrarPedido pedido = (row >= 0 && row < pedidosMostrados.size()) ? pedidosMostrados.get(row) : null;
                String nombreCliente = null;
                if (pedido != null) {
                    String[] datosCliente = pedido.getcliente().split(" - ", 2);
                    nombreCliente = datosCliente.length > 0 ? datosCliente[0] : pedido.getcliente();
                }
                boolean esUrgente = nombreCliente != null && clientesUrgentesEjemplo.contains(nombreCliente);

                String nombrePedido = (String) modeloPedidos.getValueAt(row, 0);
                if ("No hay pedidos para esta ruta hoy".equals(nombrePedido)) {
                    c.setForeground(azulPrincipal);
                    c.setBackground(blanco);
                } else if (esUrgente && column == 0) { // SOLO columna 0 en rojo y negrita
                    c.setForeground(acentoRojo);
                    c.setFont(fuenteTablaNegrita);
                    c.setBackground(blanco);
                } else {
                    c.setForeground(azulPrincipal);
                    c.setBackground(blanco);
                }
                if (isSelected) {
                    c.setBackground(new Color(223, 234, 247));
                }
                return c;
            }
        });

        JScrollPane scrollTabla = new JScrollPane(tablaPedidos);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        scrollTabla.getViewport().setBackground(blanco);

        // Panel para mensaje de NO entregas
        panelSinPedidos = new JPanel(new GridBagLayout());
        panelSinPedidos.setBackground(blanco);
        lblSinPedidos = new JLabel("No hay entregas asignadas para hoy");
        lblSinPedidos.setFont(fuenteTitulo.deriveFont(Font.PLAIN, 22f));
        lblSinPedidos.setForeground(azulPrincipal);
        panelSinPedidos.add(lblSinPedidos);

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

        panelDerecho.add(scrollTabla, "tabla");
        panelDerecho.add(panelSinPedidos, "no_pedidos");
        panelDerecho.add(panelDetalle, "detalle");

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelImagen, panelDerecho);
        splitPane.setDividerLocation(270);
        splitPane.setResizeWeight(0.0);
        splitPane.setBorder(null);

        add(panelTop, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        setPreferredSize(new Dimension(950, 580));
    }

    private void cargarRutaDeHoy() {
        lblRutaNombre.setText(Ruta.mensajeCabecera(rutas));
        if (!Ruta.esHoyLaboral()) {
            lblRutaNombre.setForeground(acentoRojo);
            setBackground(new Color(255, 247, 247));
            panelImagen.setVisible(false);
            panelDerecho.setVisible(false);
            return;
        }

        rutaActual = Ruta.rutaDeHoy(rutas);
        setBackground(azulFondo);
        panelImagen.setVisible(true);
        panelDerecho.setVisible(true);

        if (rutaActual != null) {
            String diaActual = LocalDate.now()
                .getDayOfWeek()
                .getDisplayName(java.time.format.TextStyle.FULL, new Locale("es", "ES"))
                .toLowerCase();
            diaAbrev = abrevDia(normalizarDia(diaActual));
            imgCount = diaAbrev.equals("mie") ? 3 : 2;
            imgIndex = 0;
            mostrarImagenDelDia();

            cargarPedidos();
        } else {
            panelImagen.setVisible(false);
            panelDerecho.setVisible(false);
        }
    }

    private void cargarPedidos() {
        modeloPedidos.setRowCount(0);
        pedidosMostrados.clear();
        String fechaDeHoy = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());

        int filas = 0;
        for (RegistrarPedido pedido : RegistrarPedido.listaDePedidos) {
            String diaPedido = Ruta.normalizaDia(RegistrarPedido.obtenerDiaSemana(pedido.getFecha()));
            if (pedido.getFecha().equals(fechaDeHoy) &&
                diaPedido.equalsIgnoreCase(rutaActual != null ? Ruta.normalizaDia(rutaActual.dia) : "")) {
                String[] datosCliente = pedido.getcliente().split(" - ", 2);
                String lugar = datosCliente.length > 1 ? datosCliente[1] : (rutaActual != null ? rutaActual.nombre : "Sin ruta");
                modeloPedidos.addRow(new Object[]{
                    pedido.getProducto().nombre,
                    lugar
                });
                pedidosMostrados.add(pedido);
                filas++;
            }
        }

        if (filas == 0) {
            // No hay pedidos asignados para hoy
            cardLayout.show(panelDerecho, "no_pedidos");
        } else {
            tablaPedidos.setEnabled(true);
            cardLayout.show(panelDerecho, "tabla");
        }
    }

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

    private void cambiarImagenCiclo() {
        imgIndex = (imgIndex + 1) % imgCount;
        mostrarImagenDelDia();
    }

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

    private String normalizarDia(String dia) {
        return Normalizer.normalize(dia, Normalizer.Form.NFD)
        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
        .toLowerCase(); 
    }

    private void mostrarDetallePedido(int row) {
        if (row < 0 || row >= pedidosMostrados.size()) return;
        RegistrarPedido pedido = pedidosMostrados.get(row);
        if (pedido == null) return;

        String[] datosCliente = pedido.getcliente().split(" - ", 2);
        String nombreCliente = datosCliente.length > 0 ? datosCliente[0] : pedido.getcliente();
        String lugarPedido = datosCliente.length > 1 ? datosCliente[1] : (rutaActual != null ? rutaActual.nombre : "Sin ruta");

        boolean esUrgente = nombreCliente != null && clientesUrgentesEjemplo.contains(nombreCliente);

        lblDetalleNombre.setText("Pedido: " + pedido.getProducto().nombre);
        lblDetalleNombre.setForeground(esUrgente ? acentoRojo : azulPrincipal);
        lblDetalleCliente.setText("Cliente: " + (nombreCliente != null ? nombreCliente : ""));
        lblDetalleLugar.setText("Lugar: " + lugarPedido);
        lblDetalleHora.setText("Fecha: " + (pedido.getFecha() != null ? pedido.getFecha() : "Sin fecha"));

        if (esUrgente) {
            lblDetalleUrgente.setText("URGENTE (hora límite: 13:00)");
            lblDetalleUrgente.setForeground(acentoRojo);
        } else {
            lblDetalleUrgente.setText("");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("• ").append(pedido.getProducto().nombre).append(": ").append(pedido.getCantidadVendida());
        areaDetalleProductos.setText(sb.toString());
        cardLayout.show(panelDerecho, "detalle");
    }
}