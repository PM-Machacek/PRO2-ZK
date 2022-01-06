package cz.uhk.machafi1.zk.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Record> records = new ArrayList<Record>();
    private List<ActionListener> listenersRecordsChanged = new ArrayList<>();
    private float averagePCR, averagePCRPositive, averagePositive;

    public Data() {
        averagePCR = 0;
        averagePCRPositive = 0;
        averagePositive = 0;
    }


    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }



    public void addActionListenerRecordsChanged(ActionListener toAdd) {
        listenersRecordsChanged.add(toAdd);
    }

    public void raiseEventRecordsChanged() {
        recountAverages();
        for (ActionListener al : listenersRecordsChanged) {
            al.actionPerformed(new ActionEvent(this, 1, "recordsChanged"));
        }
    }



    public void recountAverages() {
        float totalAveragePCR = 0;
        float totalAveragePCRPositive = 0;
        float totalAveragePositive = 0;


        for (Record record : getRecords()) {
            totalAveragePCR += record.getPocetPCRtestu();
            totalAveragePCRPositive += record.getPocetPCRPozitivnich();
            totalAveragePositive += record.getPocetPozitivnich();
        }

        averagePCR = totalAveragePCR / getRecords().size();
        averagePCRPositive = totalAveragePCRPositive / getRecords().size();
        averagePositive = totalAveragePositive / getRecords().size();
    }

    public float getAveragePCR() {
        return averagePCR;
    }

    public float getAveragePCRPositive() {
        return averagePCRPositive;
    }

    public float getAveragePositive() {
        return averagePositive;
    }
}
