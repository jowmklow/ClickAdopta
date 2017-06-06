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
    //instancia las variables que se necesiten


    public Contacto_Fragment() {
        // Se necesitta siempre en los fragments un constructor vacio
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Rellena el fragment con su layout correspondiente

        return inflater.inflate(R.layout.fragment_contacto_, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInsance) {
        super.onViewCreated(view, savedInsance);
        //a las variables instanciadas al principio, insertales los datos que requieren
        sendMail = (Button) getActivity().findViewById(R.id.bSend);
        email = (EditText) getActivity().findViewById(R.id.email);
        entrada = (EditText) getActivity().findViewById(R.id.enviarmail);


        sendMail.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            //crea dos boleanos antes que nada,
                                            boolean a = !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();
                                            //el boleano a le daremos la utilidad de la direccion Email, en caso de que esta no sea
                                            //correcta: emitira un error.
        /*
        *  public static final Pattern EMAIL_ADDRESS
        = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
        );*/ //los valores son los predefinidos.

                                            boolean b = entrada.getText().toString() == null || entrada.getText().toString().length() <= 10;
                                            //en caso del boleano B le introduciremos 2 constantes, 1 que si es igual a null (si no hay texto introducido)
                                            //emitira un mensaje, al igual que si el texto es menor que 10.

                                            if (a) {
                                                email.setError("Introduzca un correo valido");
                                            }
                                            if (b) {
                                                entrada.setError("Mensaje demasiado corto");
                                                //Snackbar.make(getView(), "Mensaje demasiado corto", Snackbar.LENGTH_SHORT).show();
                                            }
                                            //si los dos se cumplen envia el mesaje
                                            if (!a && !b) {
                                                //Snackbar.make(getView(), "Introduzca un correo valido", Snackbar.LENGTH_SHORT).show();
                                                MailIntentService.startActionSendMail(getContext(), email.getText().toString(), entrada.getText().toString());
                                                //recoge los datos insertados
                                                Snackbar.make(getView(), "Mensaje enviado", Snackbar.LENGTH_SHORT).show();
                                                //informa al usuario conforme se ha enviado el mensaje
                                                email.setText("");
                                                entrada.setText("");
                                                //coloca de nuevo entrada y mail en blanco
                                            }

                                        }
                                    } //boton de registrar
        );

    }


}



