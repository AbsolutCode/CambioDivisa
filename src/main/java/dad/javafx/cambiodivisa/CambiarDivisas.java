package dad.javafx.cambiodivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambiarDivisas extends Application {
	
	private TextField cantidadOrigen, cantidadDestino;
	private ComboBox<Divisa> comboOrigen, comboDestino;
	private Button botonCambiar;
	
	private Divisa euro = new Divisa("Euro", 1.0);
	private Divisa libra = new Divisa("Libra", 0.9);
	private Divisa dolar = new Divisa("Dolar", 1.1);
	private Divisa yen = new Divisa("Yen", 125.4);
	
	private Divisa [] divisas = {euro, libra, dolar, yen};

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		cantidadOrigen = new TextField("0");
		cantidadOrigen.setPrefColumnCount(4);
		cantidadDestino = new TextField("0");
		cantidadDestino.setPrefColumnCount(4);
		cantidadDestino.setEditable(false);
		
		comboOrigen = new ComboBox<>();
		comboOrigen.getItems().addAll(divisas);
		comboOrigen.getSelectionModel().selectFirst();
		comboDestino = new ComboBox<>();
		comboDestino.getItems().addAll(divisas);
		comboDestino.getSelectionModel().select(libra);
		
		botonCambiar = new Button("Cambiar");
		botonCambiar.setOnAction(e -> onCambiarButton(e));
		
		HBox origenBox = new HBox();
		origenBox.setAlignment(Pos.BASELINE_CENTER);
		origenBox.setSpacing(5);
		origenBox.getChildren().addAll(cantidadOrigen, comboOrigen);
		
		HBox destinoBox = new HBox ();
		destinoBox.setSpacing(5);
		destinoBox.getChildren().addAll(cantidadDestino, comboDestino);
		destinoBox.setAlignment(Pos.BASELINE_CENTER);
		
		VBox root = new VBox ();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(origenBox, destinoBox, botonCambiar);
		
		Scene escena = new Scene(root, 320, 200);
		
		primaryStage.setTitle("Cambio de Divisas");
		primaryStage.setScene(escena);
		primaryStage.show();
		
	}
	
	private void onCambiarButton(ActionEvent e) {
		
		try {
			
			Double cantidadOriginal = Double.parseDouble(cantidadOrigen.getText());
			Divisa divisaOrigen = comboOrigen.getSelectionModel().getSelectedItem();
			Divisa divisaDestino = comboDestino.getSelectionModel().getSelectedItem();
			
			Double enEuros = divisaOrigen.toEuro(cantidadOriginal);
			Double mostrarDestino = divisaDestino.fromEuro(enEuros);
			cantidadDestino.setText("" + mostrarDestino);
			
		}
		
		catch (NumberFormatException nfe) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Cambio de Divisas");
			error.setHeaderText("Error");
			error.setContentText("Por favor, introduzca un número.");
			error.showAndWait();
		}
		
	}

	public static void main(String[] args) {
		
		launch(args);

	}

}
