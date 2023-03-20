package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;

public class ProviderSearchActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_search);

        db = new DatabaseHelper(this);
        SearchView searchView = findViewById(R.id.searchView);
        ListView listView = findViewById(R.id.listView);

        // get the list of providers using getProviders()
        List providers = db.getProviders();
        // get the name of each provider and add it to an array
        String[] providerNames = new String[providers.size()];
        for (int i = 0; i < providers.size(); i++) {
            // get the name of the provider using getProviderName() of the provider item model
            providerNames[i] = ((ProviderItemModel) providers.get(i)).getProviderName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, providerNames);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        // when a provider is clicked, go to the provider profile page
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // get the user id of the provider that was clicked using its item id
            int providerId = ((ProviderItemModel) providers.get((int) id)).getProviderId();
            // go to the provider profile page
            // pass the provider id to the provider profile page
            Intent intent = new Intent(ProviderSearchActivity.this, ProfileViewActivity.class);
            intent.putExtra("id", providerId);
            intent.putExtra("name", db.getCompanyName(providerId));
            startActivity(intent);
        });
    }
}