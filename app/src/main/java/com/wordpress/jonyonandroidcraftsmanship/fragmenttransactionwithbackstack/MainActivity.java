package com.wordpress.jonyonandroidcraftsmanship.fragmenttransactionwithbackstack;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private FragmentManager fragmentManager = null;
    private TextView tvBackStackMessage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);
        tvBackStackMessage = (TextView) findViewById(R.id.tvBackStackMessage);
    }

    public void addFirst(View view) {
        FirstFragment firstFragment = new FirstFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.llFragmentContainer, firstFragment, getString(R.string.first_tag));
        fragmentTransaction.addToBackStack(getString(R.string.add_first_tran));
        fragmentTransaction.commit();
    }

    public void removeFirst(View view) {
        FirstFragment firstFragment = (FirstFragment) fragmentManager.findFragmentByTag(getString(R.string.first_tag));
        if (firstFragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(firstFragment);
            fragmentTransaction.addToBackStack(getString(R.string.remove_first_tran));
            fragmentTransaction.commit();
        } else {
            Toast.makeText(this, R.string.message, Toast.LENGTH_LONG).show();
        }
    }

    public void replaceFirst(View view) {
        SecondFragment secondFragment = new SecondFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.llFragmentContainer, secondFragment, getString(R.string.second_tag));
        fragmentTransaction.addToBackStack(getString(R.string.replace_first_tran));
        fragmentTransaction.commit();
    }

    public void addSecond(View view) {
        SecondFragment secondFragment = new SecondFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.llFragmentContainer, secondFragment, getString(R.string.second_tag));
        fragmentTransaction.addToBackStack(getString(R.string.add_second_tran));
        fragmentTransaction.commit();
    }

    public void removeSecond(View view) {
        SecondFragment secondFragment = (SecondFragment) fragmentManager.findFragmentByTag(getString(R.string.second_tag));
        if (secondFragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(secondFragment);
            fragmentTransaction.addToBackStack(getString(R.string.remove_second_tran));
            fragmentTransaction.commit();
        } else {
            Toast.makeText(this, R.string.message, Toast.LENGTH_LONG).show();
        }
    }

    public void replaceSecond(View view) {
        FirstFragment firstFragment = new FirstFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.llFragmentContainer, firstFragment, getString(R.string.first_tag));
        fragmentTransaction.addToBackStack(getString(R.string.replace_second_tran));
        fragmentTransaction.commit();
    }

    public void attachFirst(View view) {
        FirstFragment firstFragment = (FirstFragment) fragmentManager.findFragmentByTag(getString(R.string.first_tag));
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (firstFragment != null) {
            fragmentTransaction.attach(firstFragment);
            fragmentTransaction.addToBackStack(getString(R.string.attach_first_tran));
            fragmentTransaction.commit();
        }
    }

    public void detachFirst(View view) {
        FirstFragment firstFragment = (FirstFragment) fragmentManager.findFragmentByTag(getString(R.string.first_tag));
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (firstFragment != null) {
            fragmentTransaction.detach(firstFragment);
            fragmentTransaction.addToBackStack(getString(R.string.detach_first_tran));
            fragmentTransaction.commit();
        }
    }

    public void back(View view) {
        fragmentManager.popBackStack();
    }

    public void popAddSecond(View view) {
        fragmentManager.popBackStack(getString(R.string.add_second_tran),FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }


    @Override
    public void onBackStackChanged() {
        tvBackStackMessage.setText(tvBackStackMessage.getText() + "\n");
        tvBackStackMessage.setText(tvBackStackMessage.getText() + "The Current Status of BackStack" + "\n");

        int count = fragmentManager.getBackStackEntryCount();
        for (int i = count - 1; i >= 0; i--) {
            FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(i);
            tvBackStackMessage.setText(tvBackStackMessage.getText() + " " + entry.getName() + "\n");
        }
        tvBackStackMessage.setText(tvBackStackMessage.getText() + "\n");
    }
}
