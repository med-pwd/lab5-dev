package com.example.lab5convertisseurtempraturedistance;



// TemperatureFragment.java
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TemperatureFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_temperature, container, false);

        RadioGroup radioGroup = view.findViewById(R.id.radioGroupTemp);
        EditText editText   = view.findViewById(R.id.editTextTemp);
        Button btnOk        = view.findViewById(R.id.btnOkTemp);
        TextView result     = view.findViewById(R.id.textResultTemp);

        btnOk.setOnClickListener(v -> {
            String input = editText.getText().toString().trim();
            if (input.isEmpty()) {
                Toast.makeText(getContext(), "Entrez une valeur", Toast.LENGTH_SHORT).show();
                return;
            }

            double valeur = Double.parseDouble(input);
            double converted;
            String label;

            // C to F : (C × 9/5) + 32
            // F to C : (F − 32) × 5/9
            if (radioGroup.getCheckedRadioButtonId() == R.id.radioCtF) {
                converted = (valeur * 9.0 / 5.0) + 32;
                label = String.format("%.2f °F", converted);
            } else {
                converted = (valeur - 32) * 5.0 / 9.0;
                label = String.format("%.2f °C", converted);
            }

            result.setText(label);
        });

        return view;
    }
}