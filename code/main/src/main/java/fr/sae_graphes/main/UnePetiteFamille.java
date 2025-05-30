package fr.sae_graphes.main;

import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class UnePetiteFamille {
    

    private static Integer calculerDistance(Graph<String, DefaultEdge> graphe, String acteur1, String acteur2) {
        if (acteur1.equals(acteur2)) {
            return 0;
        }
        Set<String> visites = new HashSet<>();
        List<String> aTraiter = new ArrayList<>();
        Map<String, Integer> distances = new HashMap<>();
        
        aTraiter.add(acteur1);
        distances.put(acteur1, 0);
        visites.add(acteur1);
        
        while (!aTraiter.isEmpty()) {
            String actuelActeur = aTraiter.remove(0); 
            Integer distanceActuelle = distances.get(actuelActeur);
            Set<String> voisins = CollaborateursCommuns.getVoisins(graphe, actuelActeur);
            
            for (String v : voisins) {
                if (!visites.contains(v)) {
                    visites.add(v);
                    distances.put(v, distanceActuelle + 1);
                    aTraiter.add(v);
                    
                    if (v.equals(acteur2)) {
                        return distanceActuelle + 1;
                    }
                }
            }
        }
        return null; 
    }
    
    public static Integer distanceMaximum(Graph<String, DefaultEdge> graphe) {
        Integer distanceMax = 0;
        List<String> acteurs = new ArrayList<>(graphe.vertexSet());
        
        for (int i = 0; i < acteurs.size(); i++) {
            for (int j = i + 1; j < acteurs.size(); j++) {
                String acteur1 = acteurs.get(i);
                String acteur2 = acteurs.get(j);
                Integer distance = calculerDistance(graphe, acteur1, acteur2);
                
                if (distance != null && distance > distanceMax) {
                    distanceMax = distance;
                }
            }
        }
        
        return distanceMax;
    }
    

    public static Integer distanceMoyenne(Graph<String, DefaultEdge> graphe, String acteur) throws PasDeTelActeurException {
        if (!graphe.containsVertex(acteur)) {
            throw new PasDeTelActeurException();
        }
        
        Integer sommeDistances = 0;
        Integer nombreActeurs = 0;

        for (String autreActeur : graphe.vertexSet()) {
            if (!acteur.equals(autreActeur)) {
                Integer distance = calculerDistance(graphe, acteur, autreActeur);
                if (distance != null) {
                    sommeDistances = sommeDistances + distance;
                    nombreActeurs = nombreActeurs + 1;
                }
            }
        }
        if (nombreActeurs == 0) {
            return 0;
        }
        return sommeDistances / nombreActeurs; 
    }
    
}
