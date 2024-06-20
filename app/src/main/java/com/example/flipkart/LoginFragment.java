package com.example.flipkart;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class LoginFragment extends Fragment {
    private ImageView imageView;
    private TextView emailId;
    private Spinner spinner;
    private String verificationId;
    AppCompatButton sendCode,verifyCode;
    EditText enterCode;
    FirebaseAuth mAuth;
    TextInputEditText enterNumber;
    CountryCodePicker countryCodePicker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        findId(rootView);
        onClick();
        // Apply spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.languages_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return rootView;
    }

    private void findId(View rootView) {
        imageView = rootView.findViewById(R.id.cross);
        spinner = rootView.findViewById(R.id.language);
        emailId = rootView.findViewById(R.id.email_id);
        sendCode = rootView.findViewById(R.id.sendCode);
        verifyCode = rootView.findViewById(R.id.verifyCode);
        enterCode = rootView.findViewById(R.id.enterCode);
        enterNumber = rootView.findViewById(R.id.enterNumber);
        countryCodePicker = rootView.findViewById(R.id.country_code);
        mAuth = FirebaseAuth.getInstance();
    }

    private void onClick() {
        imageView.setOnClickListener(v -> {
            Intent i = new Intent(requireContext(), MainActivity.class);
            startActivity(i);
            requireActivity().finish();
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedLanguage = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        emailId.setOnClickListener(v -> {
            Intent i = new Intent(requireContext(), EmailActivity.class);
            startActivity(i);
        });


        countryCodePicker.setDefaultCountryUsingNameCode("IN");

        sendCode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String number =enterNumber.getText().toString();
                if(TextUtils.isEmpty(number)){
                    Toast.makeText(getContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
                }else {
                    String fullPhoneNumber = "+" + countryCodePicker.getSelectedCountryCode() + number;
                    sendVerificationCode(fullPhoneNumber);
                }
            }
        });

        verifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = enterCode.getText().toString();
                if(TextUtils.isEmpty(code)){
                    Toast.makeText(getContext(), "Enter code", Toast.LENGTH_SHORT).show();
                }else {
                    enterVerifyCode(code);
                }
            }
        });
    }

    private void sendVerificationCode(String PhoneNumber) {
        try{
            PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                    .setPhoneNumber(PhoneNumber)
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(getActivity())
                    .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            signInWithPhoneAuthCredential(phoneAuthCredential);
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            Toast.makeText(getContext(), "Verification failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            super.onCodeSent(s, forceResendingToken);
                            verificationId = s;
                            Toast.makeText(getContext(), "Code sent", Toast.LENGTH_SHORT).show();

                        }
                    }).build();
            PhoneAuthProvider.verifyPhoneNumber(options);
        }
        catch (Exception e){
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
    mAuth.signInWithCredential(phoneAuthCredential)
            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    private void enterVerifyCode(String code) {
        try{
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
            signInWithPhoneAuthCredential(credential);
        }
        catch (Exception e){
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
