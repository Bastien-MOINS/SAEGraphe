package fr.sae_graphes.main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.dot.DOTExporter;


public class App {
    
    public static void main(String[] args) {
        
        try {
            String cheminFichier = "data_100.txt"; // il faut que le fichier soit dans le même répertoire que pom.xml
            ConvertisseurJSON convertisseur = new ConvertisseurJSON();
            Graph<String, DefaultEdge> graphe = convertisseur.jsonVersGraphe(cheminFichier);
          
            
            DOTExporter<String, DefaultEdge> exporter = new DOTExporter<>();
            exporter.setVertexAttributeProvider((x) -> Collections.singletonMap("label", new DefaultAttribute<>(x, AttributeType.STRING)));
            exporter.exportGraph(graphe, new FileWriter("acteurs_graph.dot"));
            System.out.println("Le fichier DOT généré avec succès yay");
            
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier: " + e.getMessage());
            e.printStackTrace();
        }


    }
}
