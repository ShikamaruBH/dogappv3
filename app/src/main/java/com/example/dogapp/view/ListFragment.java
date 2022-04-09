package com.example.dogapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dogapp.R;
import com.example.dogapp.databinding.FragmentListBinding;
import com.example.dogapp.model.DogBreed;
import com.example.dogapp.viewmodel.DogDao;
import com.example.dogapp.viewmodel.DogDatabase;
import com.example.dogapp.viewmodel.DogsAdapter;
import com.example.dogapp.viewmodel.DogsApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListFragment extends Fragment {

    private DogsApiService dogsApiService;
    private List<DogBreed> dogBreeds;
    private DogsAdapter dogAdapter;
    private DogDao dogDao;
    private RecyclerView rvDogs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("");
        if (getArguments() != null) {
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvDogs = view.findViewById(R.id.rv_dogs);
        DogDatabase dogDatabase = DogDatabase.getInstance(getActivity());
        dogDao = dogDatabase.dogDao();

//        if (dogDao.getAllDog() != null && dogDao.getAllDog().size() > 0) {
//            Log.d("DEBUG1", "Dogs already there");
//            for(DogBreed dog: dogDao.getAllDog()) {
//                dogDao.delete(dog);
//            }
//            Log.d("DEBUG1", "No dogs anymore");
//        } else {
//            Log.d("DEBUG1", "No dogs");
//        }

        dogBreeds = new ArrayList<>();

        if (dogDao.getAllDog() == null || dogDao.getAllDog().size() == 0) {
            Log.d("DEBUG1", "Load data from API");
            dogsApiService = new DogsApiService();
            dogsApiService.getDogs()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                        @Override
                        public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<DogBreed> data) {
                            dogBreeds.addAll(data);
                            for(DogBreed dog: data) {
                                dogDao.insertAll(dog);
                            }
                            dogAdapter.notifyDataSetChanged();
                        }
                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                            Log.d("DEBUG1", "Fail" + e.getMessage());
                        }
                    });
        } else {
            dogBreeds.addAll(dogDao.getAllDog());
            Log.d("DEBUG1", "Using data from database");
        }

        dogAdapter = new DogsAdapter(dogBreeds);
        rvDogs.setAdapter(dogAdapter);
        rvDogs.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_dog).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                dogBreeds.clear();
                dogBreeds.addAll(dogDao.findByName("%" + s + "%"));
                dogAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}