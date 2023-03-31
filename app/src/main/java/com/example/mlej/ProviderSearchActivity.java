package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProviderSearchActivity extends AppCompatActivity {
    DatabaseHelper db;
    ArrayAdapter<String> adapter;
    List<ProviderItemModel> providers;
    List<ProviderItemModel> filteredProviders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_search);

        setTitle("Search for Service Provider");
        
        db = new DatabaseHelper(this);
        SearchView searchView = findViewById(R.id.searchView);
        ListView listView = findViewById(R.id.listView);

        // get the list of providers using getProviders()
        providers = db.getProviders();
        filteredProviders = new ArrayList<>(providers);
        // get the name of each provider and add it to an array
        String[] providerNames = new String[providers.size()];
        for (int i = 0; i < providers.size(); i++) {
            // get the name of the provider using getProviderName() of the provider item model
            providerNames[i] = ((ProviderItemModel) providers.get(i)).getProviderName();
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, providerNames);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter the copy of the original list, not the original list itself
                filteredProviders = filterList(providers, query);
                String[] filteredProviderNames = new String[filteredProviders.size()];
                for (int i = 0; i < filteredProviders.size(); i++) {
                    filteredProviderNames[i] = filteredProviders.get(i).getProviderName();
                }
                ArrayList<String> filteredList = new ArrayList<>(Arrays.asList(filteredProviderNames));
                adapter = new ArrayAdapter<>(ProviderSearchActivity.this, android.R.layout.simple_list_item_1, filteredList);
                listView.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // filter the copy of the original list, not the original list itself
                filteredProviders = filterList(providers, newText);
                String[] filteredProviderNames = new String[filteredProviders.size()];
                for (int i = 0; i < filteredProviders.size(); i++) {
                    filteredProviderNames[i] = filteredProviders.get(i).getProviderName();
                }
                ArrayList<String> filteredList = new ArrayList<>(Arrays.asList(filteredProviderNames));
                adapter = new ArrayAdapter<>(ProviderSearchActivity.this, android.R.layout.simple_list_item_1, filteredList);
                listView.setAdapter(adapter);
                return false;
            }
        });

        // when a provider is clicked, go to the provider profile page
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // get the user id of the provider that was clicked using its item id
            int providerId = filteredProviders.get(position).getProviderId();
            // go to the provider profile page
            // pass the provider id to the provider profile page
            Intent intent = new Intent(ProviderSearchActivity.this, ProfileViewActivity.class);
            intent.putExtra("id", providerId);
            intent.putExtra("name", db.getCompanyName(providerId));
            startActivity(intent);
        });
    }
    private List<ProviderItemModel> filterList(List<ProviderItemModel> originalList, String query) {
        List<ProviderItemModel> filteredList = new ArrayList<>();
        for (ProviderItemModel provider : originalList) {
            if (provider.getProviderPostalCode().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(provider);
            }
        }
        return filteredList;
    }
}