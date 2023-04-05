package application;



import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


public class SampleController implements Initializable{

    @FXML
    private ComboBox<String> mcbo1;

    @FXML
    private ComboBox<String> mcbo2;

    @FXML
    private TextField vtxt1;

    

    @FXML
    private TextField mtext1;

    @FXML
    private TextField ltxt1;

    @FXML
    private TextField ltxt2;

    @FXML
    private TextField mtext2;

    @FXML
    private TextField vtxt2;

    @FXML
    private ComboBox<String> lcbo1;

    public ObservableList<String>list = FXCollections.observableArrayList("Kilogramme", "Gramme", "Milligramme");
    
    @FXML
    private ComboBox<String> lcbo2;
    
    public ObservableList<String>list2 = FXCollections.observableArrayList("Litre", "Millilitre", "Mètre Cube");

    
    
    public ObservableList<String>list3 = FXCollections.observableArrayList("Metre", "Centimetre","Kilomètre");
    @FXML
    private ComboBox<String> vcbo1;

    @FXML
    private ComboBox<String> vcbo2;

    
    private double masse []= {1.0, 1000.0,1000000.0};//déclare le variable masse a un tableau
    
    private double volume [] = {1.0, 1000.0,0.001};//déclare le variable volume a un tableau

    
    private double length [] = {1.0, 100.0,0.001};//déclare le variable length a un tableau

    
    
    
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		mcbo1.setItems(list);//place les éléments de combobox dans la liste
		mcbo2.setItems(list);
		vcbo1.setItems(list2);
		vcbo2.setItems(list2);
		lcbo1.setItems(list3);
		lcbo2.setItems(list3);
		mcbo1.getSelectionModel().selectFirst();//ceci permet que le premier élément soit toujours selectionné
		mcbo2.getSelectionModel().selectFirst();
		vcbo1.getSelectionModel().selectFirst();
		vcbo2.getSelectionModel().selectFirst();
		lcbo1.getSelectionModel().selectFirst();
		lcbo2.getSelectionModel().selectFirst();
	
		
	}
    private double setTaux(ComboBox<String> a, double tbl[])
    {
    	int item=a.getSelectionModel().getSelectedIndex();//récupère l'indice de la variable "a" et l'affecte à "item".
    	double value = tbl[item];//lie au index de masse, volume et length
    	 return value;
    }
    
    private void convertir(ComboBox<String> a, ComboBox<String> b, TextField c, TextField d, double tbl[])// cet method 'convertir' permet de convertir a variable input et un variable de tableaux. Ca prends le premier variable de la premier unité de conversion est le converti au deuxième unité choisi en utilisant le tableau
    {
    	double from =setTaux(a,tbl);
    	double depart=0;
    	
    	if(c.getText().equals(""))//verifie si le TextField c est vide
    		depart = 0;//initialize depart
    	
    	else
    		depart=Double.parseDouble(c.getText());//le string TextField c est tourner en double puis stocké dans le variable départ
    	double to=setTaux(b,tbl);
    	double dest=(to/from)*depart;
    	d.setText(String.valueOf(dest));// Text field 'd' au variable dest
    }
    
    @FXML
	void quitter() {// void quitter permet de quiter l'application, il demade de la confirmation avant de quitter
		Alert alert=new Alert(Alert.AlertType.CONFIRMATION); //cette alerte s'affiche.
		alert.setHeaderText("Confirmation");//Affiche le mot confirmation
		alert.setTitle("Sortie");//affiche le mot sortie
		alert.setContentText("Voudrais tu vraiment quitter?");//affiche le question pour confirmer de quitter
		Optional<ButtonType> result=alert.showAndWait();//attend pour que le bouton soit appuit
		if(result.get()==ButtonType.OK)//si le boutton est appuit
			System.exit(0);}//quitter le systeme
@FXML
    void convertm1 () {
    	convertir(mcbo1,mcbo2,mtext1,mtext2,masse);//Utilise le variable masse et le method convertir pour calculer l'unité de conversion
    }
    @FXML
     void convertm2 () {
    	convertir(mcbo2,mcbo1,mtext2,mtext1,masse);
    }
    @FXML
     void convertv1 () {
    	convertir(vcbo1,vcbo2,vtxt1,vtxt2,volume);
    }
    @FXML
     void convertv2 () {
    	convertir(vcbo2,vcbo1,vtxt2,vtxt1,volume);
    }
    @FXML
     void convertl1 () {
    	convertir(lcbo1,lcbo2,ltxt1,ltxt2,length);
    }
    @FXML
     void convertl2 () {
    	convertir(lcbo2,lcbo1,ltxt2,ltxt1,length);
    }
    @FXML
    private void verifNum(KeyEvent e) {
    	TextField txt=(TextField)e.getSource();//mettre ce qui est écrit dans le TextField est le mettre sur le variable txt
    	txt.textProperty().addListener((observable,oldValue,newValue)->//écout se qui est écrit dans txt field puis éxecute se qui se passe dans les lignes suivantes
    	{
    		if(!newValue.matches("^-?[0-9](\\.[0-9]+)?$"))//expression regulier, si le text field n'est pas numérique
    		{
    			txt.setText(newValue.replaceAll("[^\\d*\\.\\-]",""));//remplace les nombre numérique par rien
    		}
    	});
    }
}

