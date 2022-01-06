package cz.uhk.machafi1.zk.gui;

import cz.uhk.machafi1.zk.DataManager;
import cz.uhk.machafi1.zk.model.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame {
    Data data;
    DataManager dataManager;

    JTextField txtDateInput;

    JButton btnSubmitDate, btnLoadData;

    JTable tblRecords;
    RecordsTableModel recordsTableModel;

    JLabel lblAveragePCR, lblAveragePCRPositive, lblAveragePositive;


    public MainFrame(int width, int height, Data data) {
        super("PRO2 ZK - FILIP MACHÁČEK");
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.data = data;
        dataManager = new DataManager(data);

        initGui();
    }


    //region GUI
    public void initGui() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelTable = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.LEFT));

        initTopPanel(panelTop);
        initTablePanel(panelTable);
        initFooterPanel(panelFooter);

        mainPanel.add(panelTop, BorderLayout.NORTH);
        mainPanel.add(panelTable, BorderLayout.CENTER);
        mainPanel.add(panelFooter, BorderLayout.SOUTH);
        add(mainPanel);
    }

    public void initTopPanel(JPanel panel) {
        panel.add(new JLabel("Datum"));
        txtDateInput = new JTextField("", 30);
        panel.add(txtDateInput);

        btnSubmitDate = new JButton("Potvrdit datum");
        btnSubmitDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterData(txtDateInput.getText());
            }
        });
        panel.add(btnSubmitDate);

        btnLoadData = new JButton("Načíst data");
        btnLoadData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLoadData.setText("Načítám...");
                updateData();
                btnLoadData.setText("Načíst data");
            }
        });
        panel.add(btnLoadData);

    }

    public void initTablePanel(JPanel panel) {
        recordsTableModel = new RecordsTableModel(data);
        tblRecords = new JTable();
        tblRecords.setModel(recordsTableModel);

        data.addActionListenerRecordsChanged(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recordsTableModel.fireTableDataChanged();
            }
        });


        JScrollPane scrollPane = new JScrollPane(tblRecords);
        scrollPane.setPreferredSize(new Dimension(900, 500));
        panel.add(scrollPane);
    }

    public void initFooterPanel(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        lblAveragePCR = new JLabel("Průměrný počet PCR: 0");
        panel.add(lblAveragePCR);

        lblAveragePCRPositive = new JLabel("Průměrný počet PCR pozit. symtomů: 0");
        panel.add(lblAveragePCRPositive);

        lblAveragePositive = new JLabel("Průměrný počet pozitivních: 0");
        panel.add(lblAveragePositive);

        data.addActionListenerRecordsChanged(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblAveragePCR.setText("Průměrný počet PCR: " + data.getAveragePCR());
                lblAveragePCRPositive.setText("Průměrný počet PCR pozit. symtomů: " + data.getAveragePCRPositive());
                lblAveragePositive.setText("Průměrný počet pozitivních: " + data.getAveragePositive());

            }
        });


    }
    //endregion

    public void updateData() {
        dataManager.loadRecords();
    }

    public void filterData(String date) {
        dataManager.filterRecords(date);
    }

}
