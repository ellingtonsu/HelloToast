package tw.edu.au.csie.hellotoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    final static int TOP = 0, MIDDLE = 1, BOTTOM = 2;
    final static int LEFT = 0, CENTER = 1, RIGHT = 2;

    RadioButton vRbBuildIn;
    EditText vEtMessage;
    Button vButton[][] = new Button[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vRbBuildIn = (RadioButton)findViewById(R.id.rb_buildin);
        vEtMessage = (EditText)findViewById(R.id.et_toast_message);

        vButton[TOP][LEFT]      = (Button)findViewById(R.id.bt_top_left);
        vButton[TOP][CENTER]    = (Button)findViewById(R.id.bt_top_center);
        vButton[TOP][RIGHT]     = (Button)findViewById(R.id.bt_top_right);
        vButton[MIDDLE][LEFT]   = (Button)findViewById(R.id.bt_middle_left);
        vButton[MIDDLE][CENTER] = (Button)findViewById(R.id.bt_middle_center);
        vButton[MIDDLE][RIGHT]  = (Button)findViewById(R.id.bt_middle_right);
        vButton[BOTTOM][LEFT]   = (Button)findViewById(R.id.bt_bottom_left);
        vButton[BOTTOM][CENTER] = (Button)findViewById(R.id.bt_bottom_center);
        vButton[BOTTOM][RIGHT]  = (Button)findViewById(R.id.bt_bottom_right);

        for(int i = 0 ; i < 3 ; i++)
            for(int j = 0 ; j < 3 ; j++)
                vButton[i][j].setOnClickListener(ToastOnClickListener);
    }

    View.OnClickListener ToastOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String mMessage = vEtMessage.getText().toString();
            int mPosition = 0;

            switch(view.getId()){
                case R.id.bt_top_left:
                    mPosition = Gravity.TOP|Gravity.LEFT;
                    break;

                case R.id.bt_top_center:
                    mPosition = Gravity.TOP|Gravity.CENTER;
                    break;

                case R.id.bt_top_right:
                    mPosition = Gravity.TOP|Gravity.RIGHT;
                    break;

                case R.id.bt_middle_left:
                    mPosition = Gravity.CENTER|Gravity.LEFT;
                    break;

                case R.id.bt_middle_center:
                    mPosition = Gravity.CENTER|Gravity.CENTER;
                    break;

                case R.id.bt_middle_right:
                    mPosition = Gravity.CENTER|Gravity.RIGHT;
                    break;

                case R.id.bt_bottom_left:
                    mPosition = Gravity.BOTTOM|Gravity.LEFT;
                    break;

                case R.id.bt_bottom_center:
                    mPosition = Gravity.BOTTOM|Gravity.CENTER;
                    break;

                case R.id.bt_bottom_right:
                    mPosition = Gravity.BOTTOM|Gravity.RIGHT;
                    break;
            }

            if(vRbBuildIn.isChecked()) {
                Toast toast = Toast.makeText(MainActivity.this, mMessage, Toast.LENGTH_SHORT);
                toast.setGravity(mPosition, 0, 0);
                toast.show();
            } else {
                LayoutInflater inflater = getLayoutInflater();
                View custom_toast = inflater.inflate(
                        R.layout.custom_toast_layout,
                        (ViewGroup)findViewById(R.id.custom_toast_layout)
                );
                TextView vTvMessage = (TextView)custom_toast.findViewById(R.id.tv_custom_toast_message);
                vTvMessage.setText(mMessage);
                Toast toast = new Toast(getApplicationContext());
                toast.setView(custom_toast);
                toast.setGravity(mPosition, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    };
}