import backend.MatrixChainDP;
import backend.MatrixResult;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.*;


public class MatrixMind extends JFrame {

    private final Color BG =
            new Color(12, 16, 30);

    private final Color PANEL =
            new Color(24, 30, 48);

    private final Color PANEL2 =
            new Color(31, 38, 60);

    private final Color PANEL3 =
            new Color(38, 46, 72);

    private final Color ACCENT =
            new Color(75, 105, 255);

    private final Color CYAN =
            new Color(45, 210, 210);

    private final Color GREEN =
            new Color(75, 220, 145);

    private final Color ORANGE =
            new Color(255, 174, 70);

    private final Color RED =
            new Color(255, 90, 110);

    private final Color TEXT =
            new Color(240, 244, 252);

    private final Color MUTED =
            new Color(160, 172, 195);


    // ============================================================
    // INPUT
    // ============================================================

    private JTextField dimensionsField;

    private JPanel matrixPreviewPanel;


    // ============================================================
    // RESULT LABELS
    // ============================================================

    private JLabel resultCost;

    private JLabel resultOrder;

    private JLabel naiveCost;

    private JLabel savingLabel;

    private JLabel matrixCountLabel;

    private JLabel statusLabel;


    // ============================================================
    // TABLES
    // ============================================================

    private JTable costTable;

    private JTable splitTable;


    // ============================================================
    // DYNAMIC PROGRAMMING DATA
    // ============================================================

    private long[][] dp;

    private int[][] split;

    private int[] dimensions;

    private final MatrixChainDP matrixChainDP =
            new MatrixChainDP();


    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public MatrixMind() {

        setTitle(
                "MatrixMind - Matrix Chain Optimization"
        );

        setSize(
                1300,
                850
        );

        setMinimumSize(
                new Dimension(
                        1100,
                        700
                )
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        createUI();
    }


    // ============================================================
    // MAIN UI
    // ============================================================

    private void createUI() {

        JPanel main =
                new JPanel(
                        new BorderLayout()
                );

        main.setBackground(BG);

        main.add(
                createHeader(),
                BorderLayout.NORTH
        );

        main.add(
                createDashboard(),
                BorderLayout.CENTER
        );

        setContentPane(main);
    }


    // ============================================================
    // HEADER
    // ============================================================

    private JPanel createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(PANEL);

        header.setBorder(
                new EmptyBorder(
                        20,
                        28,
                        20,
                        28
                )
        );


        // LEFT SIDE

        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel title =
                new JLabel(
                        "MATRIXMIND"
                );

        title.setForeground(TEXT);

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        29
                )
        );


        JLabel subtitle =
                new JLabel(
                        "Matrix Chain Multiplication  •  Dynamic Programming Visualizer"
                );

        subtitle.setForeground(MUTED);

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );


        titlePanel.add(title);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(subtitle);


        // RIGHT SIDE

        JLabel badge =
                new JLabel(
                        "  INTERVAL DP  "
                );

        badge.setForeground(
                new Color(
                        10,
                        20,
                        30
                )
        );

        badge.setBackground(CYAN);

        badge.setOpaque(true);

        badge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        badge.setBorder(
                new EmptyBorder(
                        12,
                        15,
                        12,
                        15
                )
        );


        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        header.add(
                badge,
                BorderLayout.EAST
        );


        return header;
    }


    // ============================================================
    // DASHBOARD
    // ============================================================

    private JPanel createDashboard() {

        JPanel dashboard =
                new JPanel(
                        new BorderLayout(
                                18,
                                18
                        )
                );

        dashboard.setBackground(BG);

        dashboard.setBorder(
                new EmptyBorder(
                        20,
                        22,
                        20,
                        22
                )
        );


        dashboard.add(
                createInputPanel(),
                BorderLayout.NORTH
        );


        JPanel center =
                new JPanel(
                        new BorderLayout(
                                18,
                                18
                        )
                );

        center.setOpaque(false);


        center.add(
                createResultCards(),
                BorderLayout.NORTH
        );


        center.add(
                createTabs(),
                BorderLayout.CENTER
        );


        dashboard.add(
                center,
                BorderLayout.CENTER
        );


        return dashboard;
    }


    // ============================================================
    // INPUT PANEL
    // ============================================================

    private JPanel createInputPanel() {

        RoundedPanel panel =
                new RoundedPanel(
                        PANEL,
                        18
                );

        panel.setLayout(
                new BorderLayout(
                        15,
                        12
                )
        );

        panel.setBorder(
                new EmptyBorder(
                        17,
                        20,
                        17,
                        20
                )
        );


        // --------------------------------------------------------
        // TOP SECTION
        // --------------------------------------------------------

        JPanel top =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
                );

        top.setOpaque(false);


        // INFORMATION

        JPanel information =
                new JPanel();

        information.setOpaque(false);

        information.setLayout(
                new BoxLayout(
                        information,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel heading =
                new JLabel(
                        "Enter Matrix Dimensions"
                );

        heading.setForeground(TEXT);

        heading.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );


        JLabel hint =
                new JLabel(
                        "Example: 10 30 5 60 20"
                );

        hint.setForeground(MUTED);

        hint.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );


        information.add(heading);

        information.add(
                Box.createVerticalStrut(5)
        );

        information.add(hint);


        // --------------------------------------------------------
        // INPUT FIELD
        // --------------------------------------------------------

        dimensionsField =
                new JTextField(
                        "20 30 40 50"
                );

        dimensionsField.setForeground(TEXT);

        dimensionsField.setBackground(PANEL2);

        dimensionsField.setCaretColor(TEXT);

        dimensionsField.setFont(
                new Font(
                        "Monospaced",
                        Font.BOLD,
                        16
                )
        );

        dimensionsField.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(
                                        70,
                                        85,
                                        120
                                ),
                                1
                        ),
                        new EmptyBorder(
                                10,
                                12,
                                10,
                                12
                        )
                )
        );


        // --------------------------------------------------------
        // BUTTONS
        // --------------------------------------------------------

        JButton optimize =
                createButton(
                        "OPTIMIZE",
                        ACCENT
                );


        JButton demo =
                createButton(
                        "DEMO",
                        CYAN
                );


        optimize.setPreferredSize(
                new Dimension(
                        145,
                        45
                )
        );


        demo.setPreferredSize(
                new Dimension(
                        120,
                        45
                )
        );


        optimize.addActionListener(
                e -> calculate()
        );


        demo.addActionListener(
                e -> {

                    dimensionsField.setText(
                            "10 30 5 60 20"
                    );

                    calculate();
                }
        );


        JPanel buttons =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                10,
                                0
                        )
                );

        buttons.setOpaque(false);

        buttons.add(optimize);

        buttons.add(demo);


        JPanel inputArea =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        inputArea.setOpaque(false);

        inputArea.add(
                dimensionsField,
                BorderLayout.CENTER
        );

        inputArea.add(
                buttons,
                BorderLayout.EAST
        );


        top.add(
                information,
                BorderLayout.WEST
        );

        top.add(
                inputArea,
                BorderLayout.CENTER
        );


        panel.add(
                top,
                BorderLayout.NORTH
        );


        // --------------------------------------------------------
        // MATRIX PREVIEW
        // --------------------------------------------------------

        matrixPreviewPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                5
                        )
                );

        matrixPreviewPanel.setOpaque(false);


        updateMatrixPreview();


        panel.add(
                matrixPreviewPanel,
                BorderLayout.CENTER
        );


        // --------------------------------------------------------
        // UPDATE MATRIX PREVIEW WHILE TYPING
        // --------------------------------------------------------

        dimensionsField
                .getDocument()
                .addDocumentListener(
                        new javax.swing.event.DocumentListener() {

                            @Override
                            public void insertUpdate(
                                    javax.swing.event.DocumentEvent e
                            ) {

                                updateMatrixPreview();
                            }

                            @Override
                            public void removeUpdate(
                                    javax.swing.event.DocumentEvent e
                            ) {

                                updateMatrixPreview();
                            }

                            @Override
                            public void changedUpdate(
                                    javax.swing.event.DocumentEvent e
                            ) {

                                updateMatrixPreview();
                            }
                        }
                );


        return panel;
    }


    // ============================================================
    // MATRIX PREVIEW
    // ============================================================

    private void updateMatrixPreview() {

        if (matrixPreviewPanel == null) {
            return;
        }


        matrixPreviewPanel.removeAll();


        String input =
                dimensionsField.getText().trim();


        if (input.isEmpty()) {

            matrixPreviewPanel.revalidate();

            matrixPreviewPanel.repaint();

            return;
        }


        String[] values =
                input.split("\\s+");


        if (values.length < 2) {

            matrixPreviewPanel.revalidate();

            matrixPreviewPanel.repaint();

            return;
        }


        try {

            int[] dims =
                    new int[values.length];


            for (
                    int i = 0;
                    i < values.length;
                    i++
            ) {

                dims[i] =
                        Integer.parseInt(
                                values[i]
                        );


                if (dims[i] <= 0) {
                    return;
                }
            }


            int matrixCount =
                    dims.length - 1;


            JLabel chainLabel =
                    new JLabel(
                            "MATRIX CHAIN:"
                    );

            chainLabel.setForeground(
                    MUTED
            );

            chainLabel.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            11
                    )
            );


            matrixPreviewPanel.add(
                    chainLabel
            );


            for (
                    int i = 0;
                    i < matrixCount;
                    i++
            ) {

                JPanel matrixCard =
                        createMatrixCard(
                                "A" + (i + 1),
                                dims[i],
                                dims[i + 1]
                        );


                matrixPreviewPanel.add(
                        matrixCard
                );


                if (i < matrixCount - 1) {

                    JLabel arrow =
                            new JLabel(
                                    "  →  "
                            );

                    arrow.setForeground(
                            CYAN
                    );

                    arrow.setFont(
                            new Font(
                                    "SansSerif",
                                    Font.BOLD,
                                    18
                            )
                    );


                    matrixPreviewPanel.add(
                            arrow
                    );
                }
            }


            matrixPreviewPanel.revalidate();

            matrixPreviewPanel.repaint();

        }
        catch (NumberFormatException ignored) {

            matrixPreviewPanel.revalidate();

            matrixPreviewPanel.repaint();
        }
    }


    // ============================================================
    // MATRIX CARD
    // ============================================================

    private JPanel createMatrixCard(
            String name,
            int rows,
            int columns
    ) {

        RoundedPanel card =
                new RoundedPanel(
                        PANEL2,
                        14
                );


        card.setPreferredSize(
                new Dimension(
                        125,
                        70
                )
        );


        card.setLayout(
                new BorderLayout()
        );


        card.setBorder(
                new EmptyBorder(
                        8,
                        12,
                        8,
                        12
                )
        );


        JLabel matrixName =
                new JLabel(
                        name,
                        SwingConstants.CENTER
                );

        matrixName.setForeground(
                CYAN
        );

        matrixName.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );


        JLabel dimensions =
                new JLabel(
                        rows + " × " + columns,
                        SwingConstants.CENTER
                );

        dimensions.setForeground(
                Color.WHITE
        );

        dimensions.setFont(
                new Font(
                        "Monospaced",
                        Font.BOLD,
                        14
                )
        );


        card.add(
                matrixName,
                BorderLayout.NORTH
        );


        card.add(
                dimensions,
                BorderLayout.CENTER
        );


        return card;
    }


    // ============================================================
    // RESULT CARDS
    // ============================================================

    private JPanel createResultCards() {

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                1,
                                5,
                                12,
                                0
                        )
                );

        cards.setOpaque(false);


        resultCost =
                createValueLabel("—");


        resultOrder =
                createValueLabel("—");


        naiveCost =
                createValueLabel("—");


        savingLabel =
                createValueLabel("—");


        matrixCountLabel =
                createValueLabel("—");


        cards.add(
                createCard(
                        "MINIMUM COST",
                        resultCost,
                        "scalar multiplications",
                        GREEN
                )
        );


        cards.add(
                createCard(
                        "OPTIMAL ORDER",
                        resultOrder,
                        "parenthesization",
                        CYAN
                )
        );


        cards.add(
                createCard(
                        "NAIVE COST",
                        naiveCost,
                        "left-to-right strategy",
                        ORANGE
                )
        );


        cards.add(
                createCard(
                        "COST SAVING",
                        savingLabel,
                        "using Dynamic Programming",
                        ACCENT
                )
        );


        cards.add(
                createCard(
                        "MATRICES",
                        matrixCountLabel,
                        "in the chain",
                        RED
                )
        );


        return cards;
    }


    // ============================================================
    // RESULT CARD
    // ============================================================

    private JPanel createCard(
            String title,
            JLabel value,
            String description,
            Color accent
    ) {

        RoundedPanel card =
                new RoundedPanel(
                        PANEL,
                        16
                );


        card.setLayout(
                new BorderLayout(
                        5,
                        5
                )
        );


        card.setBorder(
                new EmptyBorder(
                        13,
                        15,
                        13,
                        15
                )
        );


        JLabel t =
                new JLabel(
                        title
                );

        t.setForeground(
                MUTED
        );

        t.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );


        value.setForeground(
                accent
        );


        JLabel d =
                new JLabel(
                        description
                );

        d.setForeground(
                MUTED
        );

        d.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );


        card.add(
                t,
                BorderLayout.NORTH
        );


        card.add(
                value,
                BorderLayout.CENTER
        );


        card.add(
                d,
                BorderLayout.SOUTH
        );


        return card;
    }


    // ============================================================
    // VALUE LABEL
    // ============================================================

    private JLabel createValueLabel(
            String text
    ) {

        JLabel label =
                new JLabel(
                        text
                );


        label.setForeground(
                TEXT
        );


        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );


        return label;
    }


    // ============================================================
    // TABS
    // ============================================================

    private JTabbedPane createTabs() {

        JTabbedPane tabs =
                new JTabbedPane();


        tabs.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );


        tabs.setBackground(
                PANEL
        );


        tabs.setForeground(
                TEXT
        );


        tabs.setOpaque(true);


        tabs.setUI(
                new BasicTabbedPaneUI() {

                    @Override
                    protected void paintTabBackground(
                            Graphics g,
                            int tabPlacement,
                            int tabIndex,
                            int x,
                            int y,
                            int w,
                            int h,
                            boolean isSelected
                    ) {

                        Graphics2D g2 =
                                (Graphics2D)
                                        g.create();


                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );


                        if (isSelected) {

                            g2.setColor(
                                    CYAN
                            );

                        }
                        else {

                            g2.setColor(
                                    PANEL2
                            );
                        }


                        g2.fillRoundRect(
                                x + 2,
                                y + 2,
                                w - 4,
                                h,
                                10,
                                10
                        );


                        g2.dispose();
                    }


                    @Override
                    protected void paintText(
                            Graphics g,
                            int tabPlacement,
                            Font font,
                            FontMetrics metrics,
                            int tabIndex,
                            String title,
                            Rectangle textRect,
                            boolean isSelected
                    ) {

                        Graphics2D g2 =
                                (Graphics2D)
                                        g.create();


                        g2.setFont(
                                font
                        );


                        if (isSelected) {

                            g2.setColor(
                                    BG
                            );

                        }
                        else {

                            g2.setColor(
                                    TEXT
                            );
                        }


                        g2.drawString(
                                title,
                                textRect.x,
                                textRect.y
                                        + metrics.getAscent()
                        );


                        g2.dispose();
                    }


                    @Override
                    protected Insets
                    getContentBorderInsets(
                            int tabPlacement
                    ) {

                        return new Insets(
                                2,
                                2,
                                2,
                                2
                        );
                    }


                    @Override
                    protected int
                    calculateTabHeight(
                            int tabPlacement,
                            int tabIndex,
                            int fontHeight
                    ) {

                        return 40;
                    }
                }
        );


        tabs.addTab(
                "DP COST TABLE",
                createCostTablePanel()
        );


        tabs.addTab(
                "SPLIT TABLE",
                createSplitTablePanel()
        );


        tabs.addTab(
                "ANALYSIS",
                createAnalysisPanel()
        );


        tabs.addTab(
                "ALGORITHM",
                createAlgorithmPanel()
        );


        return tabs;
    }


    // ============================================================
    // COST TABLE
    // ============================================================

    private JPanel createCostTablePanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );


        panel.setBackground(
                PANEL
        );


        panel.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );


        JLabel heading =
                new JLabel(
                        "Minimum Scalar Multiplication Cost — DP Table"
                );


        heading.setForeground(
                TEXT
        );


        heading.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );


        costTable =
                new JTable();


        styleTable(
                costTable
        );


        JScrollPane scroll =
                new JScrollPane(
                        costTable
                );


        scroll.getViewport()
                .setBackground(
                        PANEL2
                );


        scroll.setBorder(
                new LineBorder(
                        new Color(
                                55,
                                65,
                                90
                        )
                )
        );


        panel.add(
                heading,
                BorderLayout.NORTH
        );


        panel.add(
                scroll,
                BorderLayout.CENTER
        );


        return panel;
    }


    // ============================================================
    // SPLIT TABLE
    // ============================================================

    private JPanel createSplitTablePanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );


        panel.setBackground(
                PANEL
        );


        panel.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );


        JLabel heading =
                new JLabel(
                        "Optimal Split Positions — Split Table"
                );


        heading.setForeground(
                TEXT
        );


        heading.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );


        splitTable =
                new JTable();


        styleTable(
                splitTable
        );


        JScrollPane scroll =
                new JScrollPane(
                        splitTable
                );


        scroll.getViewport()
                .setBackground(
                        PANEL2
                );


        scroll.setBorder(
                new LineBorder(
                        new Color(
                                55,
                                65,
                                90
                        )
                )
        );


        panel.add(
                heading,
                BorderLayout.NORTH
        );


        panel.add(
                scroll,
                BorderLayout.CENTER
        );


        return panel;
    }


    // ============================================================
    // ANALYSIS PANEL
    // ============================================================

    private JPanel createAnalysisPanel() {

        JPanel panel =
                new JPanel();


        panel.setBackground(
                PANEL
        );


        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );


        panel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );


        JLabel heading =
                new JLabel(
                        "Optimization Analysis"
                );


        heading.setForeground(
                TEXT
        );


        heading.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        21
                )
        );


        statusLabel =
                new JLabel(
                        "Enter matrix dimensions and click OPTIMIZE."
                );


        statusLabel.setForeground(
                MUTED
        );


        statusLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );


        panel.add(
                heading
        );


        panel.add(
                Box.createVerticalStrut(
                        12
                )
        );


        panel.add(
                statusLabel
        );


        panel.add(
                Box.createVerticalStrut(
                        25
                )
        );


        RoundedPanel explanation =
                new RoundedPanel(
                        PANEL2,
                        15
                );


        explanation.setLayout(
                new BorderLayout()
        );


        explanation.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );


        JLabel text =
                new JLabel(
                        "<html>" +

                                "<b>How MatrixMind works</b>" +

                                "<br><br>" +

                                "1. Matrix dimensions are accepted as input.<br>" +

                                "2. The system generates A1, A2, A3 and so on.<br>" +

                                "3. Dynamic Programming stores minimum multiplication costs.<br>" +

                                "4. The split table stores the best division point.<br>" +

                                "5. The optimal parenthesization is reconstructed.<br>" +

                                "6. The optimized cost is compared with a naïve strategy.<br><br>" +

                                "<font color='#4bdd91'>" +

                                "Time Complexity: O(n³) &nbsp;&nbsp; | &nbsp;&nbsp; " +

                                "Space Complexity: O(n²)" +

                                "</font>" +

                                "</html>"
                );


        text.setForeground(
                TEXT
        );


        text.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );


        explanation.add(
                text,
                BorderLayout.CENTER
        );


        panel.add(
                explanation
        );


        return panel;
    }


    // ============================================================
    // ALGORITHM PANEL
    // ============================================================

    private JPanel createAlgorithmPanel() {

        JPanel panel =
                new JPanel();


        panel.setBackground(
                PANEL
        );


        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );


        panel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );


        JLabel title =
                new JLabel(
                        "Interval Dynamic Programming"
                );


        title.setForeground(
                CYAN
        );


        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );


        JLabel formula =
                new JLabel(
                        "<html>" +

                                "<div style='font-size:14px'>" +

                                "<b>DP[i][j]</b> = minimum cost of multiplying matrices Ai ... Aj" +

                                "<br><br>" +

                                "DP[i][j] = min { DP[i][k] + DP[k+1][j] " +

                                "+ p[i-1] × p[k] × p[j] }" +

                                "<br><br>" +

                                "where i ≤ k &lt; j" +

                                "</div>" +

                                "</html>"
                );


        formula.setForeground(
                TEXT
        );


        JLabel complexity =
                new JLabel(
                        "<html>" +

                                "<b>COMPLEXITY</b>" +

                                "<br><br>" +

                                "Time Complexity: O(n³)" +

                                "<br>" +

                                "Space Complexity: O(n²)" +

                                "</html>"
                );


        complexity.setForeground(
                GREEN
        );


        complexity.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );


        panel.add(
                title
        );


        panel.add(
                Box.createVerticalStrut(
                        20
                )
        );


        panel.add(
                formula
        );


        panel.add(
                Box.createVerticalStrut(
                        30
                )
        );


        panel.add(
                complexity
        );


        return panel;
    }


    // ============================================================
    // TABLE STYLE
    // ============================================================

    private void styleTable(
            JTable table
    ) {

        table.setBackground(
                PANEL2
        );


        table.setForeground(
                TEXT
        );


        table.setGridColor(
                new Color(
                        60,
                        70,
                        95
                )
        );


        table.setRowHeight(
                36
        );


        table.setFont(
                new Font(
                        "Monospaced",
                        Font.BOLD,
                        13
                )
        );


        table.setSelectionBackground(
                ACCENT
        );


        table.setSelectionForeground(
                Color.WHITE
        );


        JTableHeader header =
                table.getTableHeader();


        header.setBackground(
                new Color(
                        40,
                        48,
                        72
                )
        );


        header.setForeground(
                TEXT
        );


        header.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );


        header.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );
    }


    // ============================================================
    // CALCULATE
    // ============================================================

    private void calculate() {

        try {
            String input = dimensionsField.getText().trim();

            if (input.isEmpty()) {
                showError("Please enter matrix dimensions.");
                return;
            }

            MatrixResult result = matrixChainDP.optimize(input);

            dimensions = result.getDimensions();
            dp = result.getDp();
            split = result.getSplit();

            int n = dimensions.length - 1;

            long minimum = result.getMinimumCost();
            long naive = result.getNaiveCost();
            long savingAmount = result.getCostSaving();
            double savingPercent = result.getSavingPercentage();
            String order = result.getOptimalOrder();

            resultCost.setText(formatNumber(minimum));

            resultOrder.setText(
                    "<html><div style='width:170px'>" +
                            order +
                            "</div></html>"
            );

            naiveCost.setText(formatNumber(naive));

            savingLabel.setText(
                    "<html>" +
                            formatNumber(savingAmount) +
                            "<br><font size='2'>" +
                            String.format("(%.2f%%)", savingPercent) +
                            "</font></html>"
            );

            matrixCountLabel.setText(
                    String.valueOf(n)
            );

            statusLabel.setText(
                    "<html>" +
                            "<font color='#4bdd91'>" +
                            "Optimization completed successfully." +
                            "</font>" +
                            "<br><br>" +
                            "Dynamic Programming minimum cost: <b>" +
                            formatNumber(minimum) +
                            "</b> scalar multiplications." +
                            "<br>" +
                            "Naïve left-to-right cost: <b>" +
                            formatNumber(naive) +
                            "</b>" +
                            "<br>" +
                            "Cost saving: <b>" +
                            formatNumber(savingAmount) +
                            "</b> (" +
                            String.format("%.2f%%", savingPercent) +
                            ")" +
                            "<br>" +
                            "Time Complexity: <b>" +
                            result.getTimeComplexity() +
                            "</b>" +
                            "<br>" +
                            "Space Complexity: <b>" +
                            result.getSpaceComplexity() +
                            "</b>" +
                            "</html>"
            );

            updateCostTable();
            updateSplitTable();
            updateMatrixPreview();

        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        }
    }

    // ============================================================
    // NAIVE STRATEGY
    // ============================================================

    private long calculateNaiveCost() {

        int n =
                dimensions.length - 1;


        if (n <= 1) {

            return 0;
        }


        /*
         * Naive strategy:
         *
         * Always multiply from left to right.
         *
         * Example:
         *
         * ((A1 × A2) × A3) × A4
         */


        long total = 0;


        int rows =
                dimensions[0];


        int currentCols =
                dimensions[1];


        for (
                int i = 2;
                i <= n;
                i++
        ) {

            total +=

                    (long)
                            rows

                            *

                            currentCols

                            *

                            dimensions[i];


            currentCols =
                    dimensions[i];
        }


        return total;
    }


    // ============================================================
    // BUILD OPTIMAL PARENTHESIZATION
    // ============================================================

    private String buildParenthesization(
            int i,
            int j
    ) {

        if (i == j) {

            return "A" + i;
        }


        int k =
                split[i][j];


        return "("

                +

                buildParenthesization(
                        i,
                        k
                )

                +

                " × "

                +

                buildParenthesization(
                        k + 1,
                        j
                )

                +

                ")";
    }


    // ============================================================
    // UPDATE COST TABLE
    // ============================================================

    private void updateCostTable() {

        int n =
                dimensions.length - 1;


        String[] columns =
                new String[n];


        for (
                int i = 0;
                i < n;
                i++
        ) {

            columns[i] =
                    "A" + (i + 1);
        }


        Object[][] data =
                new Object[n][n];


        for (
                int i = 0;
                i < n;
                i++
        ) {

            for (
                    int j = 0;
                    j < n;
                    j++
            ) {

                int row =
                        i + 1;


                int col =
                        j + 1;


                if (row > col) {

                    data[i][j] =
                            "—";
                }


                else if (row == col) {

                    data[i][j] =
                            "0";
                }


                else {

                    data[i][j] =
                            formatNumber(
                                    dp[row][col]
                            );
                }
            }
        }


        costTable.setModel(
                new DefaultTableModel(
                        data,
                        columns
                )
        );


        styleTable(
                costTable
        );


        highlightOptimalCost();
    }


    // ============================================================
    // UPDATE SPLIT TABLE
    // ============================================================

    private void updateSplitTable() {

        int n =
                dimensions.length - 1;


        String[] columns =
                new String[n];


        for (
                int i = 0;
                i < n;
                i++
        ) {

            columns[i] =
                    "A" + (i + 1);
        }


        Object[][] data =
                new Object[n][n];


        for (
                int i = 0;
                i < n;
                i++
        ) {

            for (
                    int j = 0;
                    j < n;
                    j++
            ) {

                int row =
                        i + 1;


                int col =
                        j + 1;


                if (row >= col) {

                    data[i][j] =
                            "—";
                }


                else {

                    data[i][j] =
                            "k = " +
                                    split[row][col];
                }
            }
        }


        splitTable.setModel(
                new DefaultTableModel(
                        data,
                        columns
                )
        );


        styleTable(
                splitTable
        );
    }


    // ============================================================
    // HIGHLIGHT FINAL DP CELL
    // ============================================================

    private void highlightOptimalCost() {

        if (
                costTable.getRowCount()
                        == 0
        ) {

            return;
        }


        costTable.setDefaultRenderer(
                Object.class,
                new DefaultTableCellRenderer() {

                    @Override
                    public Component
                    getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column
                    ) {

                        Component c =
                                super.getTableCellRendererComponent(
                                        table,
                                        value,
                                        isSelected,
                                        hasFocus,
                                        row,
                                        column
                                );


                        setHorizontalAlignment(
                                SwingConstants.CENTER
                        );


                        setForeground(
                                TEXT
                        );


                        setBackground(
                                PANEL2
                        );


                        int n =
                                dimensions.length - 1;


                        if (
                                row == n - 1
                                        &&
                                        column == n - 1
                        ) {

                            setBackground(
                                    new Color(
                                            45,
                                            105,
                                            80
                                    )
                            );


                            setForeground(
                                    Color.WHITE
                            );
                        }


                        if (isSelected) {

                            setBackground(
                                    ACCENT
                            );
                        }


                        return c;
                    }
                }
        );
    }


    // ============================================================
    // BUTTON
    // ============================================================

    private JButton createButton(
            String text,
            Color color
    ) {

        JButton button =
                new JButton(
                        text
                );


        button.setForeground(
                Color.WHITE
        );


        button.setBackground(
                color
        );


        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );


        button.setFocusPainted(
                false
        );


        button.setBorderPainted(
                false
        );


        button.setOpaque(
                true
        );


        button.setContentAreaFilled(
                true
        );


        button.setEnabled(
                true
        );


        button.setBorder(
                new EmptyBorder(
                        10,
                        18,
                        10,
                        18
                )
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                color.brighter()
                        );
                    }


                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                color
                        );
                    }
                }
        );


        return button;
    }


    // ============================================================
    // ERROR MESSAGE
    // ============================================================

    private void showError(
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Input Error",
                JOptionPane.ERROR_MESSAGE
        );
    }


    // ============================================================
    // NUMBER FORMAT
    // ============================================================

    private String formatNumber(
            long number
    ) {

        return String.format(
                "%,d",
                number
        );
    }


    // ============================================================
    // ROUNDED PANEL
    // ============================================================

    static class RoundedPanel
            extends JPanel {

        private final Color color;

        private final int radius;


        RoundedPanel(
                Color color,
                int radius
        ) {

            this.color =
                    color;

            this.radius =
                    radius;

            setOpaque(
                    false
            );
        }


        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();


            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );


            g2.setColor(
                    color
            );


            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    radius,
                    radius
            );


            g2.dispose();


            super.paintComponent(
                    g
            );
        }
    }


    // ============================================================
    // MAIN
    // ============================================================

    public static void main(
            String[] args
    ) {

        try {

            /*
             * Cross platform Look & Feel
             * prevents Windows from turning
             * our buttons and tabs white.
             */

            UIManager.setLookAndFeel(
                    UIManager
                            .getCrossPlatformLookAndFeelClassName()
            );

        }

        catch (
                Exception ignored
        ) {
        }


        SwingUtilities.invokeLater(
                () -> {

                    MatrixMind app =
                            new MatrixMind();


                    app.setVisible(
                            true
                    );
                }
        );
    }
}