package com.coworking.texxizmat;

import static com.coworking.tehhizmat.R.layout.activity_contract;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coworking.tehhizmat.R;
import com.coworking.texxizmat.adapter.HistoryAdapter;
import java.util.ArrayList;
import java.util.List;


public class ContractActivity extends AppCompatActivity {
    private EditText num_account, name, surname, middleName, phone_num, address;
    private Button btn_reg;

    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private List<HistoryItem> historyItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(activity_contract);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the history list and adapter
        historyItemList = new ArrayList<>();
        historyAdapter = new HistoryAdapter(historyItemList);
        recyclerView.setAdapter(historyAdapter);

        byte[] byteArray = getIntent().getByteArrayExtra("image_byte_array");
        String addressText = getIntent().getStringExtra("address");
        String accountNumText = getIntent().getStringExtra("accountNum");
        String fullnameText = getIntent().getStringExtra("fullname");

        // Load history data (dummy data for example)
        historyItemList.add(new HistoryItem(addressText, accountNumText, fullnameText, byteArray));
        historyItemList.add(new HistoryItem(addressText, accountNumText, fullnameText, byteArray));
        historyItemList.add(new HistoryItem(addressText, accountNumText, fullnameText, byteArray));
        historyAdapter.notifyDataSetChanged();

    }
}