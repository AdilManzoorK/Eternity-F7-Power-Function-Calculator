package ca.concordia.eternity;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/** F7 Power Function GUI with precision selector and accessibility helpers. */
public class F7App extends Application {
  private int precision = 12;

  @Override
  public void start(Stage stage) {
    TextField txtX = new TextField();
    TextField txtY = new TextField();
    txtX.setPromptText("e.g., 2.5");
    txtY.setPromptText("e.g., -3 or 0.75");

    Label lblX = new Label("_Base (x):");
    lblX.setMnemonicParsing(true);
    lblX.setLabelFor(txtX);

    Label lblY = new Label("E_xponent (y):");
    lblY.setMnemonicParsing(true);
    lblY.setLabelFor(txtY);

    Label lblOut = new Label();
    lblOut.setWrapText(true);

    ComboBox<Integer> cbPrecision = new ComboBox<>();
    cbPrecision.getItems().addAll(4, 6, 8, 10, 12);
    cbPrecision.setValue(12);
    cbPrecision.setAccessibleText("Select number of digits after decimal");
    cbPrecision.setTooltip(new Tooltip("Digits after decimal in the result"));
    cbPrecision.setOnAction(e -> precision = cbPrecision.getValue());

    Button btnCalc = new Button("_Calculate");
    btnCalc.setMnemonicParsing(true);
    btnCalc.setDefaultButton(true);
    btnCalc.setOnAction(e -> {
      try {
        double x = Double.parseDouble(txtX.getText().trim());
        double y = Double.parseDouble(txtY.getText().trim());
        double result = PowCalculator.pow(x, y);
        lblOut.setText(String.format("Result: %." + precision + "f", result));
        lblOut.setAccessibleText("Result " + result);
      } catch (NumberFormatException ex) {
        lblOut.setText("ERR_NUM: invalid numeric input");
      } catch (IllegalArgumentException ex) {
        lblOut.setText(ex.getMessage());
      }
    });

    Button btnClear = new Button("_Clear");
    btnClear.setCancelButton(true);
    btnClear.setOnAction(e -> { txtX.clear(); txtY.clear(); lblOut.setText(""); });

    Button btnExit = new Button("E_xit");
    btnExit.setOnAction(e -> stage.close());

    GridPane gp = new GridPane();
    gp.setPadding(new Insets(12));
    gp.setHgap(8);
    gp.setVgap(8);

    gp.add(lblX, 0, 0); gp.add(txtX, 1, 0);
    gp.add(lblY, 0, 1); gp.add(txtY, 1, 1);
    gp.add(new Label("Precision:"), 0, 2); gp.add(cbPrecision, 1, 2);
    gp.add(btnCalc, 0, 3); gp.add(btnClear, 1, 3);
    gp.add(lblOut, 0, 4, 2, 1);
    gp.add(btnExit, 0, 5);

    Scene scene = new Scene(gp, 380, 280);
    scene.getAccelerators().put(
        new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN), stage::close);

    stage.setScene(scene);
    stage.setTitle("F7 – Power (x^y)");
    stage.show();
  }

  public static void main(String[] args) { launch(args); }
}
