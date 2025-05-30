package fr.sae_graphes.main;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.util.SupplierUtil;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;



/** !! NOTE AUX PROFESSEURS !!
 * Si vous avez bien l'extension VSCode Java Test Runner, 
 * vous pouvez lancer les tests en appuyant sur le petit bouton vert qui apparait à gauche de la ligne de code.
 */


public class AppTest 
{
    /**
     * Rigorous Test :-) Test fourni avec le TP
     */
    @Test
    public void rigorousTest()
    {
        Graph<String, DefaultEdge> graph = GraphTypeBuilder
				.directed()
				.allowingMultipleEdges(true)
				.allowingSelfLoops(true)
				.vertexSupplier(SupplierUtil.createStringSupplier())
				.edgeSupplier(SupplierUtil.createDefaultEdgeSupplier())
				.buildGraph();
        
        String v0 = graph.addVertex();
        String v1 = graph.addVertex();
        String v2 = graph.addVertex();
        
        graph.addEdge(v0, v1);
        graph.addEdge(v0, v2);
        graph.addEdge(v1, v2);
        
        assertEquals(3, graph.vertexSet().size());
        assertEquals(3, graph.edgeSet().size());
        
        assertEquals(2, graph.outDegreeOf(v0));
        assertEquals(1, graph.outDegreeOf(v1));
        assertEquals(0, graph.outDegreeOf(v2));
    }

	/**
     * Test de la méthode enCommun
     */
    @Test
    public void TestCollabCommun(){
      Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
      graph.addVertex("Acteur 1");
      graph.addVertex("Acteur 2");
      graph.addVertex("Acteur 3");
      graph.addVertex("Acteur 4");
      graph.addEdge("Acteur 1", "Acteur 2");
      graph.addEdge("Acteur 1", "Acteur 3");
      graph.addEdge("Acteur 1", "Acteur 4");
      graph.addEdge("Acteur 3", "Acteur 4");
      graph.addEdge("Acteur 4", "Acteur 1");
      graph.addEdge("Acteur 2", "Acteur 4");

      try {
        Set<String> collaborateursCommuns = CollaborateursCommuns.enCommun(graph, "Acteur 1", "Acteur 2");
        assertEquals(1, collaborateursCommuns.size());
        assertTrue(collaborateursCommuns.contains("Acteur 4"));
        assertFalse(collaborateursCommuns.contains("Acteur 5"));
      } catch (PasDeTelActeurException e) {
        e.printStackTrace();
      }
      
    }

    /**
     * Test de la méthode collabProches
     */
    @Test
    public void TestCollabProches(){
      Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
      graph.addVertex("Acteur 1");
      graph.addVertex("Acteur 2");
      graph.addVertex("Acteur 3");
      graph.addVertex("Acteur 4");
      graph.addEdge("Acteur 1", "Acteur 2");
      graph.addEdge("Acteur 1", "Acteur 3");
      graph.addEdge("Acteur 1", "Acteur 4");
      graph.addEdge("Acteur 3", "Acteur 4");
      graph.addEdge("Acteur 4", "Acteur 1");
      graph.addEdge("Acteur 2", "Acteur 4");

      try {
        Set<String> collaborateursProches = CollaborateursProches.collaborateur_proches(graph, "Acteur 1", 1);
        assertEquals(4, collaborateursProches.size());
        assertTrue(collaborateursProches.contains("Acteur 4"));
        assertFalse(collaborateursProches.contains("Acteur 5"));
      } catch (PasDeTelActeurException e) {
        e.printStackTrace();
      }
    }

    /**
     * Test de la méthode QuiEstAuCentre
     */
    @Test
    public void TestQuiEstAuCentre(){
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        
        graph.addVertex("Acteur 1");
        graph.addVertex("Acteur 2");
        graph.addVertex("Acteur 3");
        graph.addVertex("Acteur 4");
        graph.addVertex("Acteur 5");
        
        graph.addEdge("Acteur 1", "Acteur 2");
        graph.addEdge("Acteur 1", "Acteur 3");
        graph.addEdge("Acteur 1", "Acteur 4");
        graph.addEdge("Acteur 1", "Acteur 5");
        
        graph.addEdge("Acteur 2", "Acteur 3");

        String acteurCentral = QuiEstAuCentre.centreDuGraphe(graph);
        assertEquals("Acteur 1", acteurCentral); 


    }
      /**
     * Test UnePetiteFamille 
     */

    @Test
    public void TestPetiteFamille(){
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        graph.addVertex("Acteur 1");
        graph.addVertex("Acteur 2");
        graph.addVertex("Acteur 3");
        graph.addVertex("Acteur 4");
        graph.addVertex("Acteur 5"); 
        
        graph.addEdge("Acteur 1", "Acteur 2");
        graph.addEdge("Acteur 1", "Acteur 3");
        graph.addEdge("Acteur 1", "Acteur 4");
        graph.addEdge("Acteur 3", "Acteur 4");
        graph.addEdge("Acteur 2", "Acteur 4");

        Integer distanceMax = UnePetiteFamille.distanceMaximum(graph);
        assertEquals(Integer.valueOf(2), distanceMax); 

        // vérifie la théorie des 6 degrés
        assertTrue(distanceMax <= 6);

        try {
            Integer distanceMoyenne1 = UnePetiteFamille.distanceMoyenne(graph, "Acteur 1");
            assertEquals(Integer.valueOf(1), distanceMoyenne1);
            Integer distanceMoyenne5 = UnePetiteFamille.distanceMoyenne(graph, "Acteur 5");
            assertEquals(Integer.valueOf(0), distanceMoyenne5);

        } catch (PasDeTelActeurException e) {
            e.printStackTrace();
        }
    }
 }

    
    
    

