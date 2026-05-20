package com.example.lab5convertisseurtempraturedistance;




// DistanceFragment.java

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DistanceFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_distance, container, false);

        RadioGroup radioGroup = view.findViewById(R.id.radioGroupDist);
        EditText editText   = view.findViewById(R.id.editTextDist);
        Button btnOk        = view.findViewById(R.id.btnOkDist);
        TextView result     = view.findViewById(R.id.textResultDist);

        btnOk.setOnClickListener(v -> {
            String input = editText.getText().toString().trim();
            if (input.isEmpty()) {
                Toast.makeText(getContext(), "Entrez une valeur", Toast.LENGTH_SHORT).show();
                return;
            }

            double valeur = Double.parseDouble(input);
            double converted;
            String label;

            // 1 KM = 0.621371 Miles
            // 1 Mile = 1.60934 KM
            if (radioGroup.getCheckedRadioButtonId() == R.id.radioKmToMiles) {
                converted = valeur * 0.621371;
                label = String.format("%.4f Miles", converted);
            } else {
                converted = valeur * 1.60934;
                label = String.format("%.4f KM", converted);
            }

            result.setText(label);
        });

        return view;
    }
}