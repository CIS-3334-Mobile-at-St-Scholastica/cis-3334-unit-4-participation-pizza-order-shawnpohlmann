package css.cis3334.pizzaorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements updateViewInterface {

    RadioButton rbSmall;
    RadioButton rbMedium;
    RadioButton rbLarge;
    CheckBox chkbxCheese;
    CheckBox chkbxDelivery;
    TextView txtTotal;
    TextView txtStatus;
    PizzaOrderInterface pizzaOrderSystem;
    boolean cheese = false;
    boolean delivery = false;
    String strsize;
    Spinner spinner;
    String topping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbSmall = (RadioButton) findViewById(R.id.radioButtonSmall);
        rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        rbLarge = (RadioButton) findViewById(R.id.radioButtonLarge);

        chkbxCheese = (CheckBox) findViewById(R.id.checkBoxCheese);
        chkbxDelivery = (CheckBox) findViewById(R.id.checkBoxDeluvery);

        txtTotal = (TextView) findViewById(R.id.textViewTotal);
        txtStatus = (TextView) findViewById(R.id.textViewStatus);

        pizzaOrderSystem = new PizzaOrder(this);

        spinner = (Spinner) findViewById(R.id.spinner);

    }

    @Override
    public void updateView(String orderStatus) {
        txtStatus.setText("Order Status" + orderStatus);
    }

    public void onClickOrder(View view) {
        pizzaOrderSystem.setDelivery(chkbxDelivery.isChecked());
        if (chkbxCheese.isChecked()) {
            cheese = true;
        }

        if (rbSmall.isChecked()){
            strsize = "Small";
        }
        else if (rbMedium.isChecked()) {
            strsize = "Medium";
        }
        else if (rbLarge.isChecked()) {
            strsize = "Large";
        }
        else {
            txtStatus.setText("Please Select your size of pizza.");
        }



        topping = spinner.getSelectedItem().toString();

        String orderDescription = pizzaOrderSystem.OrderPizza(topping ,strsize, cheese);

        //display a pop up message for a long period of time
        Toast.makeText(getApplicationContext(), "You have ordered a "+orderDescription , Toast.LENGTH_LONG).show();
        txtTotal.setText("Total Due: " + pizzaOrderSystem.getTotalBill().toString());
    }
}
