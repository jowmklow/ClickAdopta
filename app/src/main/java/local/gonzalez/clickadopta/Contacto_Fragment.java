package local.gonzalez.clickadopta;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Contacto_Fragment extends Fragment {
    private Button sendMail;
    private EditText entrada;
    private EditText email;



    public Contacto_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_contacto_, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInsance) {
        super.onViewCreated(view, savedInsance);

        sendMail = (Button) getActivity().findViewById(R.id.bSend);
        email = (EditText) getActivity().findViewById(R.id.email);
        entrada = (EditText) getActivity().findViewById(R.id.enviarmail);


        sendMail.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            boolean b = entrada.getText().toString().length() < 10;
                                            boolean a = !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();
                                            if (a) {
                                                entrada.setError("Mensaje demasiado corto");
                                                //Snackbar.make(getView(), "Mensaje demasiado corto", Snackbar.LENGTH_SHORT).show();
                                            }
                                            if (b) {
                                                email.setError("Introduzca un correo valido");
                                            }
                                            if (!a && !b) {
                                                //Snackbar.make(getView(), "Introduzca un correo valido", Snackbar.LENGTH_SHORT).show();
                                                MailIntentService.startActionSendMail(getContext(), email.getText().toString(), entrada.getText().toString());
                                                Snackbar.make(getView(), "Mensaje enviado", Snackbar.LENGTH_SHORT).show();
                                                email.setText("");
                                                entrada.setText("");
                                            }

                                        }
                                    } //boton de registrar
        );

    }


}



