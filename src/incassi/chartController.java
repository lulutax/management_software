package incassi;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class chartController    {

    @FXML
    private BarChart<String, Integer> grafico;
    @FXML
    private CategoryAxis coordinateMesi;

    @FXML
    private NumberAxis coordinateIncassi;
    private ObservableList < String > mesi = FXCollections . observableArrayList ();  
    
  
    public void setIncassi(List<Incasso> incassi) {
        int[] contatoreIncassi = new int[12];
        for (Incasso i : incassi) {
        	String month = i.getData();
            char s=month.charAt(3);
            char s2=month.charAt(4);
            StringBuilder str = new StringBuilder(2);
            
            str.append(s);
            str.append(s2);
           int m=Integer.parseInt(str.toString());

            contatoreIncassi[m-1]+=i.getIncasso();
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        for (int i =0; i <contatoreIncassi.length; i++) {
            series.getData().add(new XYChart.Data<>(mesi.get(i), contatoreIncassi[i]));
        }
        
        grafico.getData().add(series);
    }



	public void initialize() {
		 String[] months = DateFormatSymbols.getInstance(Locale.ITALIAN).getMonths();
	        mesi.addAll(Arrays.asList(months));
	}

	
}
