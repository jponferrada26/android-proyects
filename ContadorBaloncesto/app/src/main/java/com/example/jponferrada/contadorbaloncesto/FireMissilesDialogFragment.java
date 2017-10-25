package com.example.jponferrada.contadorbaloncesto;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class FireMissilesDialogFragment extends DialogFragment {
    boolean aceptado = false;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("¿Estás seguro que quieres empezar otro partido (se borraran los contadores)?")
                .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        aceptado = true;
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        aceptado = false;
                    }
                });
        // Create the AlertDialog object and return it
        return builder.show();
    }
}