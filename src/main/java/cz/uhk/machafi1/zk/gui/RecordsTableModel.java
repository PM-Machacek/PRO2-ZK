package cz.uhk.machafi1.zk.gui;

import cz.uhk.machafi1.zk.model.Data;
import javax.swing.table.AbstractTableModel;

public class RecordsTableModel extends AbstractTableModel {

   private Data data;

    public RecordsTableModel(Data data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.getRecords().size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.getRecords().get(rowIndex).getDatum();
            case 1:
                return data.getRecords().get(rowIndex).getPocetPCRtestu();
            case 2:
                return data.getRecords().get(rowIndex).getPocetPCRPozitivnich();
            case 3:
                return data.getRecords().get(rowIndex).getPocetPozitivnich();
            default:
                return null;
        }

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Datum";
            case 1:
                return "Počet PCR";
            case 2:
                return "Počet PCR pozit. symptomů";
            case 3:
                return "Počet pozitivních";
            default:
                return null;
        }
    }
}