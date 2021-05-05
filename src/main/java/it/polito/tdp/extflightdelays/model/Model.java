package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> grafo;
	// IDENTITY MAP per creare gli oggetti una sola volta
	private Map<Integer, Airport> idMap;
	private ExtFlightDelaysDAO dao;
	
	public Model() {
		idMap = new HashMap <Integer,Airport>();
		dao = new ExtFlightDelaysDAO();
	}
	
	
	public void creaGrafo(Integer distanza) {
		grafo = new SimpleWeightedGraph<> (DefaultWeightedEdge.class);
		
		dao.loadAllAirports(idMap);
		//AGGIUNGIAMO I VERTICI
		Graphs.addAllVertices(grafo, idMap.values());
		
		//Aggiungiamo gli archi
		List<Voli> listaV = dao.getMediaVoli();
		DefaultWeightedEdge e;
		for (Voli v: listaV) {
			if (!this.grafo.containsEdge(idMap.get(v.getId_partenza()), idMap.get(v.getId_arrivo()))) {
				if (v.getDistanza()>=distanza)
				Graphs.addEdge(this.grafo, idMap.get(v.getId_partenza()), idMap.get(v.getId_arrivo()), v.getDistanza());
			}
			else {
				e = grafo.getEdge(idMap.get(v.getId_partenza()), idMap.get(v.getId_arrivo()));
				grafo.setEdgeWeight(e,( grafo.getEdgeWeight(e)+v.getDistanza())/2);
				if (grafo.getEdgeWeight(e)<distanza) 
					grafo.removeEdge(e);
			}
		
		}
		
		System.out.format("Grafo creato con %d vertici e %d archi\n",
 				this.grafo.vertexSet().size(), this.grafo.edgeSet().size()); 
			
	}
	
	public int getNVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public String descriviti() {
		String s ="";
		for (DefaultWeightedEdge e : this.grafo.edgeSet()) {
			s+= e.toString()+" "+ grafo.getEdgeWeight(e)+"\n";
		}
		
		return s;
	}
 
}
