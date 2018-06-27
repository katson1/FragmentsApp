package editor.katson.comsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Xoi on 12/04/2017.
 */

public class MyAccFragment extends Fragment {

    public MyAccFragment(){

    }

    View myView;

    private RelativeLayout rl_nome;
    private RelativeLayout rl_cpf;
    private RelativeLayout rl_tel;
    private RelativeLayout rl_email;
    private RelativeLayout rl_end;
    private RelativeLayout rl_comp;
    private TextView textNavHeader;
    protected TextView txt_myName;
    protected TextView txt_myCPF;
    protected TextView txt_myTel;
    protected TextView txt_myEmail;
    protected TextView txt_myEnd;
    protected TextView txt_myComp;
    private String nomeMyacc;
    private String cpfMyacc;
    private String ruaMyacc;
    private String numMyacc;
    private String telMyacc;
    private String emailMyacc;
    private String compMyacc;
    private String endCompleto;
    private UserObject user = new UserObject("Nome completo","CPF valido","Seu telefone de contato",
            "Seu Email","Endereço, numero da residencia","Complemento de endereço", false, false,
            false, false, false, false);


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.my_acc_layout, container, false);

        rl_nome = (RelativeLayout) myView.findViewById(R.id.r_layout_name);
        rl_cpf = (RelativeLayout) myView.findViewById(R.id.r_layout_cpf);
        rl_tel = (RelativeLayout) myView.findViewById(R.id.r_layout_tel);
        rl_email = (RelativeLayout) myView.findViewById(R.id.r_layout_email);
        rl_end = (RelativeLayout) myView.findViewById(R.id.r_layout_end);
        rl_comp = (RelativeLayout) myView.findViewById(R.id.r_layout_comp);

        txt_myName = (TextView) myView.findViewById(R.id.txt_nome);
        txt_myCPF = (TextView) myView.findViewById(R.id.txt_cpf);
        txt_myTel = (TextView) myView.findViewById(R.id.txt_tel);
        txt_myEmail = (TextView) myView.findViewById(R.id.txt_email);
        txt_myEnd = (TextView) myView.findViewById(R.id.txt_end);
        txt_myComp = (TextView) myView.findViewById(R.id.txt_comp);


        try {
            user = lerDados();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        txt_myName.setText(user.getNome());
        txt_myCPF.setText(user.getCpf());
        txt_myTel.setText(user.getTelefone());
        txt_myEmail.setText(user.getEmail());
        txt_myEnd.setText(user.getEnd());
        txt_myComp.setText(user.getComplemento());


        rl_nome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder nameBuilder = new AlertDialog.Builder(getActivity());
                final View nameView = getActivity().getLayoutInflater().inflate(R.layout.dialog_name_layout, null);

                final EditText edtName = (EditText) nameView.findViewById(R.id.edt_name);

                if (user.isNomeBoolean()){
                    edtName.setText(user.getNome());
                }

                nameBuilder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!edtName.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Nome salvo", Toast.LENGTH_SHORT).show();
                            nomeMyacc = edtName.getText().toString();
                            txt_myName.setText(nomeMyacc);

                            user.setNome(nomeMyacc);
                            user.setNomeBoolean(true);

                            try {
                                salvarDados();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }


                        else {
                            Toast.makeText(getActivity(), "O campo não pode estar vazio",
                                    Toast.LENGTH_SHORT).show();
                        }

                        MainActivity m = (MainActivity) getRequiredActivity(myView);
                        m.changeNameInNavHeader();

                    }

                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                nameBuilder.setView(nameView);
                final AlertDialog dialog = nameBuilder.create();

                dialog.setOnShowListener( new DialogInterface.OnShowListener() {
                                              @Override
                                              public void onShow(DialogInterface arg0) {
                                                  dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                                                          .setTextColor(getResources()
                                                                  .getColor(R.color.colorAccent));

                                                  dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                                          .setTextColor(getResources()
                                                                  .getColor(R.color.colorAccent));
                                              }

                                          });

                dialog.show();

            }
        });

        rl_cpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder cpfBuilder = new AlertDialog.Builder(getActivity());
                final View cpfView = getActivity().getLayoutInflater().inflate(R.layout.dialog_cpf_layout, null);

                final EditText edtCPF = (EditText) cpfView.findViewById(R.id.edt_cpf);

                if (user.isCpfBoolean()){
                    edtCPF.setText(user.getCpf());
                }


                cpfBuilder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!edtCPF.getText().toString().isEmpty()){
                            Toast.makeText(getActivity(), "CPF salvo", Toast.LENGTH_SHORT).show();
                            cpfMyacc = edtCPF.getText().toString();
                            txt_myCPF.setText(cpfMyacc);

                            user.setCpf(cpfMyacc);
                            user.setCpfBoolean(true);
                            try {
                                salvarDados();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        else {
                            Toast.makeText(getActivity(), "O campo não pode estar vazio",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                cpfBuilder.setView(cpfView);
                final AlertDialog dialog = cpfBuilder.create();

                dialog.setOnShowListener( new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                                .setTextColor(getResources()
                                        .getColor(R.color.colorAccent));

                        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                .setTextColor(getResources()
                                        .getColor(R.color.colorAccent));
                    }

                });


                dialog.show();
            }


        });

        rl_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder telBuilder = new AlertDialog.Builder(getActivity());
                final View telView = getActivity().getLayoutInflater().inflate(R.layout.dialog_tel_layout, null);

                final EditText edtTel = (EditText) telView.findViewById(R.id.edt_tel);

                if (user.isTelefoneBoolean()){
                    edtTel.setText(user.getTelefone());
                }

                telBuilder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!edtTel.getText().toString().isEmpty()){
                            Toast.makeText(getActivity(), "Telefone salvo", Toast.LENGTH_SHORT).show();
                            telMyacc = edtTel.getText().toString();
                            txt_myTel.setText(telMyacc);

                            user.setTelefone(telMyacc);
                            user.setTelefoneBoolean(true);
                            try {
                                salvarDados();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        else {
                            Toast.makeText(getActivity(), "O campo não pode estar vazio",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                telBuilder.setView(telView);
                final AlertDialog dialog = telBuilder.create();

                dialog.setOnShowListener( new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                                .setTextColor(getResources()
                                        .getColor(R.color.colorAccent));

                        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                .setTextColor(getResources()
                                        .getColor(R.color.colorAccent));
                    }

                });

                dialog.show();
            }


        });

        rl_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder emailBuilder = new AlertDialog.Builder(getActivity());
                final View emailView = getActivity().getLayoutInflater().inflate(R.layout.dialog_email_layout, null);

                final EditText edtEmail = (EditText) emailView.findViewById(R.id.edt_email);

                if (user.isEmailBoolean()){
                    edtEmail.setText(user.getEmail());
                }

                emailBuilder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!edtEmail.getText().toString().isEmpty()){
                            Toast.makeText(getActivity(), "E-mail salvo", Toast.LENGTH_SHORT).show();
                            emailMyacc = edtEmail.getText().toString();
                            txt_myEmail.setText(emailMyacc);

                            user.setEmail(emailMyacc);
                            user.setEmailBoolean(true);

                            try {
                                salvarDados();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        else {
                            Toast.makeText(getActivity(), "O campo não pode estar vazio",
                                    Toast.LENGTH_SHORT).show();
                        }

                        MainActivity m = (MainActivity) getRequiredActivity(myView);
                        m.changeEmailInNavHeader();

                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                emailBuilder.setView(emailView);
                final AlertDialog dialog = emailBuilder.create();

                dialog.setOnShowListener( new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                                .setTextColor(getResources()
                                        .getColor(R.color.colorAccent));

                        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                .setTextColor(getResources()
                                        .getColor(R.color.colorAccent));
                    }

                });

                dialog.show();
            }
        });

        rl_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder endBuilder = new AlertDialog.Builder(getActivity());
                final View endView = getActivity().getLayoutInflater().inflate(R.layout.dialog_end_layout, null);

                final EditText edtRua = (EditText) endView.findViewById(R.id.edt_rua);
                final EditText edtNum = (EditText) endView.findViewById(R.id.edt_num);

                if (user.isEndBoolean()){
                    edtRua.setText(user.getEnd().split(", ")[0]);
                    if (user.getEnd().split(", ")[1].equals("sem número")){
                        edtNum.setText("0");
                    }else{
                        edtNum.setText(user.getEnd().split(", ")[1]);
                    }
                }

                endBuilder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!edtRua.getText().toString().isEmpty() && !edtNum.getText().toString().isEmpty()){
                            Toast.makeText(getActivity(), "Endereço salvo", Toast.LENGTH_SHORT).show();
                            ruaMyacc = edtRua.getText().toString();
                            numMyacc = edtNum.getText().toString();

                            if (numMyacc.equals("0")){
                                endCompleto = ruaMyacc+", sem número";
                            }else{
                                endCompleto = ruaMyacc+", "+numMyacc;
                            }

                            txt_myEnd.setText(endCompleto);

                            user.setEnd(endCompleto);
                            user.setEndBoolean(true);

                            try {
                                salvarDados();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        else {
                            Toast.makeText(getActivity(), "O campo não pode estar vazio",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                endBuilder.setView(endView);
                final AlertDialog dialog = endBuilder.create();

                dialog.setOnShowListener( new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                                .setTextColor(getResources()
                                        .getColor(R.color.colorAccent));

                        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                .setTextColor(getResources()
                                        .getColor(R.color.colorAccent));
                    }

                });

                dialog.show();
            }
        });

        rl_comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder compBuilder = new AlertDialog.Builder(getActivity());
                final View compView = getActivity().getLayoutInflater().inflate(R.layout.dialog_comp_layout, null);

                final EditText edtComp = (EditText) compView.findViewById(R.id.edt_comp);

                if (user.isEndBoolean()){
                    edtComp.setText(user.getComplemento());
                }

                compBuilder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!edtComp.getText().toString().isEmpty()){
                            Toast.makeText(getActivity(), "Complemento salvo", Toast.LENGTH_SHORT).show();
                            compMyacc = edtComp.getText().toString();
                            txt_myComp.setText(compMyacc);

                            user.setComplemento(compMyacc);
                            user.setComplementoBoolean(true);

                            try {
                                salvarDados();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        else {
                            Toast.makeText(getActivity(), "O campo não pode estar vazio",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                compBuilder.setView(compView);
                final AlertDialog dialog = compBuilder.create();

                dialog.setOnShowListener( new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                                .setTextColor(getResources()
                                        .getColor(R.color.colorAccent));

                        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                .setTextColor(getResources()
                                        .getColor(R.color.colorAccent));
                    }

                });

                dialog.show();
            }
        });


        return myView;

    }

    public void salvarDados() throws IOException{
        String FILENAME = "user.txt";
        File file = myView.getContext().getFileStreamPath(FILENAME);

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(user);

        oos.close();
        fos.close();
    }

    public UserObject lerDados() throws ClassNotFoundException, IOException{
        String FILENAME = "user.txt";
        File file = myView.getContext().getFileStreamPath(FILENAME);
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        UserObject user = (UserObject) ois.readObject();
        fis.close();
        ois.close();
        return user;
    }

    private Activity getRequiredActivity(View req_view) {
        Context context = req_view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }

}
