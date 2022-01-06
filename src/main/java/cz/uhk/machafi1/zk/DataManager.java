package cz.uhk.machafi1.zk;

import com.opencsv.bean.CsvToBeanBuilder;
import cz.uhk.machafi1.zk.model.Data;
import cz.uhk.machafi1.zk.model.Record;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DataManager {
    private static final String ENDPOINT_URL = "https://lide.uhk.cz/fim/ucitel/kozelto1/prog/cov.php";
    private Data data;

    public DataManager(Data data) {
        this.data = data;
    }

    public void loadRecords(){
        try {
            HttpGet get = new HttpGet(ENDPOINT_URL);

            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse httpResponse = httpClient.execute(get);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String resultCsv = EntityUtils.toString(httpResponse.getEntity());

                data.setRecords(parseFromCSV(resultCsv));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            data.setRecords(new ArrayList<Record>());
        }

        data.raiseEventRecordsChanged();
    }

    private List<Record> parseFromCSV(String csv){
        try{
            List<Record> records = new CsvToBeanBuilder(new StringReader(csv))
                    .withType(Record.class)
                    .build()
                    .parse();

            return records;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return new ArrayList<Record>();
    }

    public void filterRecords(String date){
        List<Record> newRecords = new ArrayList<Record>();

        try{
            Date maxDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);

            Calendar c = Calendar.getInstance();
            c.setTime(maxDate);
            c.add(Calendar.DATE, -(5*7));  // number of days to add

            for (Record record: data.getRecords()) {
                Date recordDate = record.getParsedDatum();
                if (recordDate != null){
                    if (recordDate.after(c.getTime()) && recordDate.before(maxDate)){
                        newRecords.add(record);
                    }
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        data.setRecords(newRecords);
        data.raiseEventRecordsChanged();
    }
}
