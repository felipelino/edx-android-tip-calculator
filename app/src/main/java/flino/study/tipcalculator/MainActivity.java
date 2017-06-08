package flino.study.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etBillAmount)
    EditText etBillAmount;
    @BindView(R.id.tvTipPercent)
    TextView tvTipPercent;
    @BindView(R.id.tvTipAmount)
    TextView tvTipAmount;
    @BindView(R.id.tvBillTotalAmount)
    TextView tvBillTotalAmount;

    float percentage = 0;
    float tipTotal = 0;
    float finalBillAmount = 0;
    float totalBillAmount = 0;

    float REGULAR_TIP_PERCENT = 10;
    float DEFAULT_TIP_PERCENT = 15;
    float EXCELLENT_TIP_PERCENT = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            percentage = savedInstanceState.getFloat("percentage");
        }

        setTipValues();
    }

    private void setTipValues() {
        tvTipPercent.setText(getString(R.string.main_msg_tippercent, percentage));
        tvTipAmount.setText(getString(R.string.main_msg_tiptotal, tipTotal));
        tvBillTotalAmount.setText(getString(R.string.main_msg_billtotalresult, finalBillAmount));
    }


    @OnClick({R.id.ibRegularService, R.id.ibGoodService, R.id.ibExcellentService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibRegularService:
                percentage = REGULAR_TIP_PERCENT;
                break;
            case R.id.ibGoodService:
                percentage = DEFAULT_TIP_PERCENT;
                break;
            case R.id.ibExcellentService:
                percentage = EXCELLENT_TIP_PERCENT;
                break;
        }

        calculateFinalBill();
        setTipValues();
    }

    @OnTextChanged(R.id.etBillAmount)
    public void onTextChanged() {
        calculateFinalBill();
        setTipValues();
    }

    private void calculateFinalBill() {

        if(percentage == 0) {
            percentage = DEFAULT_TIP_PERCENT;
        }

        String strEtBillAmount = etBillAmount.getText().toString();
        if(!strEtBillAmount.equals("") && !strEtBillAmount.equals(".")) {
            totalBillAmount = Float.valueOf(strEtBillAmount);
        }
        else {
            totalBillAmount = 0;
        }

        tipTotal = (totalBillAmount *percentage)/100;
        finalBillAmount = (totalBillAmount + tipTotal);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putFloat("percentage", percentage);
        super.onSaveInstanceState(outState);
    }
}
