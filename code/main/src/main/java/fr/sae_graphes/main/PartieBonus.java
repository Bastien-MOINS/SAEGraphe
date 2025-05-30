package fr.sae_graphes.main;

import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;


public class PartieBonus {
    
    /**
     * trouve les acteurs les plus connectés (centraux)
     */
    public static List<String> acteursCentraux(Graph<String, DefaultEdge> graphe, Integer nombreActeurs) {
        
        // je crée d'abord une map pour compter les connections de chaque acteur
        Map<String, Integer> compteurConnections = new HashMap<>();
        
        // Pour chaque acteur, je compte ses voisins
        for (String acteur : graphe.vertexSet()) {
            Integer nbVoisins = graphe.edgesOf(acteur).size();
            compteurConnections.put(acteur, nbVoisins);
        }
        
        // je trie par nombre de connections (du plus grand au plus petit)
        // je garde seulement les premiers
        return null;
    }
    
    //puis il faut trouver les collaborateurs communs entre deux acteurs... to be continued

}