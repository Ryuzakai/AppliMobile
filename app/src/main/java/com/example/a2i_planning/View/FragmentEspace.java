package com.example.a2i_planning.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a2i_planning.R;
import com.example.a2i_planning.User.Espace;
import com.example.a2i_planning.User.User;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class FragmentEspace extends Fragment {

    TextView tvEspaceName;
    TextView tvNomRub1;
    TextView tvNomRub2;
    TextView tvNomRub3;
    EditText champRub1;
    EditText champRub2;
    EditText champRub3;

    Bundle myBdl_receive = new Bundle();
    User currentUser = new User();
    Espace currentEspace = new Espace();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_espace, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        myBdl_receive = this.getArguments();
        currentUser = myBdl_receive.getParcelable("user");
        currentEspace = myBdl_receive.getParcelable("espace");
        tvEspaceName = getActivity().findViewById(R.id.tv_espacename);
        tvNomRub1 = getActivity().findViewById(R.id.tv_nom_rub1);
        tvNomRub2 = getActivity().findViewById(R.id.tv_nom_rub2);
        tvNomRub3 = getActivity().findViewById(R.id.tv_nom_rub3);
        champRub1 = getActivity().findViewById(R.id.edt_rub_text);
        champRub2 = getActivity().findViewById(R.id.edt_rub_num);
        champRub3 = getActivity().findViewById(R.id.edt_rub_time);


        File dossier = new File(getActivity().getFilesDir() + "/espaces");
        File dossierUser = new File(dossier.getPath() + "/" + currentUser.getId());
        File newxmlfile = new File(dossierUser.getPath() + "/" + currentEspace.getIdEsp() + "-" + currentEspace.getNomEsp() + ".xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(newxmlfile);
            doc.getDocumentElement().normalize();
            String root = doc.getDocumentElement().getNodeName();
            System.out.println("Exemple root : " + root);

            NodeList items = doc.getChildNodes();

            System.out.println("Exemple items : " + items.getLength());
            for (int i = 0; i < items.getLength(); i++) {
                Node item = items.item(i);
                System.out.println("Node item name : " + item.getNodeName());
                Element eElement = (Element) item;
                tvEspaceName.setText(eElement.getElementsByTagName("nom").item(i).getTextContent());
                if (eElement.getElementsByTagName("rubrique1").item(i) != null) {
                    tvNomRub1.setText(eElement.getElementsByTagName("rubrique1").item(i).getTextContent());
                } else {
                    tvNomRub1.setVisibility(View.INVISIBLE);
                    champRub1.setVisibility(View.INVISIBLE);
                }
                if (eElement.getElementsByTagName("rubrique2").item(i) != null) {
                    tvNomRub2.setText(eElement.getElementsByTagName("rubrique2").item(i).getTextContent());
                } else {
                    tvNomRub2.setVisibility(View.INVISIBLE);
                    champRub2.setVisibility(View.INVISIBLE);
                }
                if (eElement.getElementsByTagName("rubrique2").item(i) != null) {
                    tvNomRub3.setText(eElement.getElementsByTagName("rubrique3").item(i).getTextContent());
                } else {
                    tvNomRub3.setVisibility(View.INVISIBLE);
                    champRub3.setVisibility(View.INVISIBLE);
                }


            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
