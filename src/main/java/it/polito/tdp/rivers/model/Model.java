package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	RiversDAO dao; 
	List<River> rivers; 
	List<Flow> flows; 
	Map<Integer, River> mapRiver; 
	
	
	
	public Model() {
		super();
		this.dao = new RiversDAO();
		this.rivers = new LinkedList<River>(dao.getAllRivers());
		loadFlowInRiver(); 
		setAvgFlowOfRiver();
		loadMap(); 
		
	}


	public List<River> getAllRivers(){
		return this.rivers;
	}
	
	public void loadFlowInRiver() {
		for(River r: rivers) {
			r.setFlows(new LinkedList<>(this.dao.getFlowOfRiver(r)));
		}
	}	
	
	public void setAvgFlowOfRiver() {
		for(River r: rivers) {
			r.setFlowAvg(dao.avgFlowOfRiver(r));
		}
	}
	
	public void loadMap() {
		for(River r: rivers) {
			mapRiver.put(r.getId(), r);
		}
	}
		
	public String getFirstDateFlow(River river) {
		LocalDate first =mapRiver.get(river.getId()).getFlows().get(0).getDay() ;
		return first.toString(); 
	}
	
	public String getLastDateFlow(River river) {
		LocalDate last =mapRiver.get(river.getId()).getFlows().get(mapRiver.get(river.getId()).getFlows().size()).getDay() ;
		return last.toString();
	}
	
	public int getNumMisure(River river) {
		return mapRiver.get(river.getId()).getFlows().size();
	}
	
	public Double getAvgFlow(River river) {
		return mapRiver.get(river.getId()).getFlowAvg();
	}


}
