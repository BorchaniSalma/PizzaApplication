package com.gl4.pizzaApplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.gl4.pizzaApplication.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var txtNom: TextInputEditText
    lateinit var txtPrenom: TextInputEditText
    lateinit var txtAdresse: TextInputEditText
    lateinit var pizzaType: AutoCompleteTextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        txtNom = findViewById(R.id.nomlayout);
        txtPrenom = findViewById(R.id.prenomlayout);
        txtAdresse = findViewById(R.id.adresselayout);
        pizzaType = findViewById(R.id.autoCompleteTextView);
        val pizzaTypes = resources.getStringArray(R.array.pizzaTypes)
        val adapter = ArrayAdapter(
            this,
            R.layout.activity_drop_down_layout,
            pizzaTypes
        )

        with(binding.autoCompleteTextView) {
            setAdapter(adapter)
        }
    }

    fun order(view: View) {
        if (view?.id == R.id.textButton) {
            var nom = txtNom.text.toString();
            var prenom = txtPrenom.text.toString();
            var adresse = txtAdresse.text.toString();
            var pizza = pizzaType.text.toString();
            val text = "Merci de commander !";
            val order = "Merci de commander madame/monsieur $nom $prenom  , votre $pizza est en route vers $adresse";
            val duration = Toast.LENGTH_SHORT;
            val toast = Toast.makeText(applicationContext, text, duration);
            toast.show();
            val intent = Intent(view.context, ShunterThanks::class.java);
            intent.putExtra("order", order);
            startActivity(intent);

            val intentMail = Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, "sborcheni@gmail.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, order);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intentMail);
            }

        }
    }



}